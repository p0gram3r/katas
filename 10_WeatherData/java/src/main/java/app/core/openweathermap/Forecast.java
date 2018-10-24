package app.core.openweathermap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static java.time.ZoneOffset.UTC;
import static java.time.temporal.ChronoField.HOUR_OF_DAY;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Forecast {
    @Valid
    @NotNull
    public Long dt;

    @Valid
    @NotNull
    public Data main;

    public int getHourOfDayOfDt() {
        final LocalDateTime dateTime = LocalDateTime.ofEpochSecond(dt, 0, UTC);
        return dateTime.get(HOUR_OF_DAY);
    }


    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Data {
        @Valid
        @NotNull
        public BigDecimal temp;

        @Valid
        @NotNull
        public BigDecimal pressure;
    }
}
