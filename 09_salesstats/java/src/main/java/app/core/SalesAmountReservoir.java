package app.core;

import com.codahale.metrics.Clock;

import java.math.BigDecimal;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * This is basically a clone of {@link com.codahale.metrics.SlidingTimeWindowReservoir}
 * which unfortunately only works with long values.
 */
public class SalesAmountReservoir {

    // allow for this many duplicate ticks before overwriting measurements
    private static final int COLLISION_BUFFER = 256;
    // only trim on updating once every N
    private static final int TRIM_THRESHOLD = 256;
    // offsets the front of the time window for the purposes of clearing the buffer in trim
    private static final long CLEAR_BUFFER = TimeUnit.HOURS.toNanos(1) * COLLISION_BUFFER;

    private final Clock clock;
    private final ConcurrentSkipListMap<Long, BigDecimal> measurements;
    private final long window;
    private final AtomicLong lastTick;
    private final AtomicLong count;

    /**
     * Creates a new SalesAmountReservoir with the given window of time.
     *
     * @param window     the window of time
     * @param windowUnit the unit of {@code window}
     */
    public SalesAmountReservoir(long window, TimeUnit windowUnit) {
        this(window, windowUnit, Clock.defaultClock());
    }

    /**
     * Creates a new SalesAmountReservoir with the given clock and window of
     * time.
     *
     * @param window     the window of time
     * @param windowUnit the unit of {@code window}
     * @param clock      the {@link Clock} to use
     */
    public SalesAmountReservoir(long window, TimeUnit windowUnit, Clock clock) {
        this.clock = clock;
        this.measurements = new ConcurrentSkipListMap<>();
        this.window = windowUnit.toNanos(window) * COLLISION_BUFFER;
        this.lastTick = new AtomicLong(clock.getTick() * COLLISION_BUFFER);
        this.count = new AtomicLong();
    }

    public void update(BigDecimal value) {
        if (count.incrementAndGet() % TRIM_THRESHOLD == 0) {
            trim();
        }
        measurements.put(getTick(), value);
    }

    public SalesAmountSnapshot getSnapshot() {
        trim();
        return new SalesAmountSnapshot(measurements.values());
    }

    private long getTick() {
        for (; ; ) {
            final long oldTick = lastTick.get();
            final long tick = clock.getTick() * COLLISION_BUFFER;
            // ensure the tick is strictly incrementing even if there are duplicate ticks
            final long newTick = tick - oldTick > 0 ? tick : oldTick + 1;
            if (lastTick.compareAndSet(oldTick, newTick)) {
                return newTick;
            }
        }
    }

    private void trim() {
        final long now = getTick();
        final long windowStart = now - window;
        final long windowEnd = now + CLEAR_BUFFER;
        if (windowStart < windowEnd) {
            measurements.headMap(windowStart).clear();
            measurements.tailMap(windowEnd).clear();
        }
        else {
            measurements.subMap(windowEnd, windowStart).clear();
        }
    }
}
