package app.core;

import java.math.BigDecimal;
import java.util.Collection;

import static java.util.Objects.requireNonNull;

/**
 * The idea of this class is based on {@link com.codahale.metrics.UniformSnapshot}. The following modifications were
 * made to minimize the time and memory required to calculate the sales statistics:
 * <ul>
 * <li>Skip the creation and sorting of an array containing all values. In the original class this was required to
 * access the median and quantiles. For the solution of this coding challenge accessing these values are not
 * necessary</li>
 * <li>Provide a method to get the sum of all values in the snapshot. This is missing in the original and would
 * require another iteration of all values</li>
 * </ul>
 * <p>
 */
public class SalesAmountSnapshot {
    private final BigDecimal sum;
    private final BigDecimal mean;

    public SalesAmountSnapshot(Collection<BigDecimal> values) {
        requireNonNull(values);

        this.sum = values.parallelStream()
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.mean = values.isEmpty()
            ? BigDecimal.ZERO
            : sum.divide(new BigDecimal(values.size()), BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getSum() {
        return sum;
    }

    public BigDecimal getMean() {
        return mean;
    }
}
