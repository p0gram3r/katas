package app.core;

import app.api.Data;
import app.core.openweathermap.Forecast;
import app.core.openweathermap.WeatherData;
import com.google.common.collect.ImmutableList;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.stream.Collectors.toList;

public class DataFactory {
    private static final List<Integer> DAILY_HOURS = ImmutableList.of(6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17);
    private static final List<Integer> NIGHTLY_HOURS = ImmutableList.of(18, 19, 20, 21, 22, 23, 0, 1, 2, 3, 4, 5);

    private final int amountOfDays;

    public DataFactory(int amountOfDays) {
        this.amountOfDays = amountOfDays;
    }

    public Data of(WeatherData weatherData) {
        return of(weatherData, Instant.now());
    }

    Data of(WeatherData weatherData, Instant startDate) {
        final Long cutoffDate = startDate.plus(amountOfDays, DAYS).getEpochSecond();

        final List<Forecast> relevantForecasts = weatherData.list.parallelStream()
                .filter(b -> b.dt <= cutoffDate)
                .collect(toList());
        final List<Forecast> daily = filterByHourOfDay(relevantForecasts, DAILY_HOURS);
        final List<Forecast> nightly = filterByHourOfDay(relevantForecasts, NIGHTLY_HOURS);

        final BigDecimal dailyMean = calculateMean(daily, f -> f.main.temp);
        final BigDecimal nightlyMean = calculateMean(nightly, f -> f.main.temp);
        final BigDecimal pressureMean = calculateMean(relevantForecasts, f -> f.main.pressure);

        return new Data(dailyMean, nightlyMean, pressureMean);
    }

    List<Forecast> filterByHourOfDay(List<Forecast> blocks, Collection<Integer> hours) {
        return blocks.parallelStream()
                .filter(block -> hours.contains(block.getHourOfDayOfDt()))
                .collect(toList());
    }

    BigDecimal calculateMean(List<Forecast> forecasts, Function<Forecast, BigDecimal> mapper) {
        final BigDecimal sum = forecasts.parallelStream()
                .map(mapper)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return forecasts.isEmpty()
                ? BigDecimal.ZERO
                : sum.divide(new BigDecimal(forecasts.size()), BigDecimal.ROUND_HALF_UP);
    }
}
