package app.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

import java.math.BigDecimal;

import static com.google.common.base.MoreObjects.toStringHelper;

public class Data {
    private final BigDecimal dailyTempAverage;
    private final BigDecimal nightlyTempAverage;
    private final BigDecimal pressureAverage;

    @JsonCreator
    public Data(
            @JsonProperty("dailyTempAverage") BigDecimal dailyTempAverage,
            @JsonProperty("nightlyTempAverage") BigDecimal nightlyTempAverage,
            @JsonProperty("pressureAverage") BigDecimal pressureAverage) {
        this.dailyTempAverage = dailyTempAverage;
        this.nightlyTempAverage = nightlyTempAverage;
        this.pressureAverage = pressureAverage;
    }

    @JsonProperty
    public BigDecimal getDailyTempAverage() {
        return dailyTempAverage;
    }

    @JsonProperty
    public BigDecimal getNightlyTempAverage() {
        return nightlyTempAverage;
    }

    @JsonProperty
    public BigDecimal getPressureAverage() {
        return pressureAverage;
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("dailyTempAverage", dailyTempAverage)
                .add("nightlyTempAverage", nightlyTempAverage)
                .add("pressureAverage", pressureAverage)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Data data = (Data) o;
        return Objects.equal(dailyTempAverage, data.dailyTempAverage) &&
                Objects.equal(nightlyTempAverage, data.nightlyTempAverage) &&
                Objects.equal(pressureAverage, data.pressureAverage);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dailyTempAverage, nightlyTempAverage, pressureAverage);
    }
}
