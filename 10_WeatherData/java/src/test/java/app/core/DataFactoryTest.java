package app.core;

import app.core.openweathermap.Forecast;
import app.core.openweathermap.WeatherData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import io.dropwizard.jackson.Jackson;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;

public class DataFactoryTest {
    private static final WeatherData WEATHER_DATA;

    static {
        ObjectMapper MAPPER = Jackson.newObjectMapper();
        String FIXTURE_FILE_NAME = "fixtures/weatherData.json";
        try {
            WEATHER_DATA = MAPPER.readValue(fixture(FIXTURE_FILE_NAME), WeatherData.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private DataFactory factory;

    @Before
    public void before() {
        this.factory = new DataFactory(1);
    }

    @Test
    public void filterByHourOfDay_forEmtpyHourList() {
        List<Forecast> forecastList = factory.filterByHourOfDay(WEATHER_DATA.list, ImmutableList.of());
        assertThat(forecastList).isEmpty();
    }

    @Test
    public void filterByHourOfDay_forNonExistingHours() {
        List<Forecast> forecastList = factory.filterByHourOfDay(WEATHER_DATA.list, ImmutableList.of(2, 4));
        assertThat(forecastList).isEmpty();
    }

    @Test
    public void filterByHourOfDay_forExistingHours() {
        List<Forecast> forecastList = factory.filterByHourOfDay(WEATHER_DATA.list, ImmutableList.of(12));
        assertThat(forecastList).hasSize(5);
    }

    @Test
    public void calculateMean_forTestData() {
        ImmutableList<Forecast> list = ImmutableList.of(
                forecastWithPressure("10"),
                forecastWithPressure("15"),
                forecastWithPressure("20")
        );

        BigDecimal mean = factory.calculateMean(list, f -> f.main.pressure);
        assertThat(mean).isEqualTo("15");
    }

    @Test
    public void calculateMean_forFixture() {
        BigDecimal mean = factory.calculateMean(WEATHER_DATA.list, f -> f.main.pressure);
        assertThat(mean).isEqualTo("991.94");
    }

    @Test
    public void calculateMean_returnsBigDecimalZero_forEmtpyList() {
        BigDecimal mean = factory.calculateMean(ImmutableList.of(), f -> f.main.pressure);
        assertThat(mean).isEqualTo(BigDecimal.ZERO);
    }


    // OMG... quick and dirty...
    private Forecast forecastWithPressure(String pressure) {
        Forecast f = new Forecast();
        f.main = new Forecast.Data();
        f.main.pressure = new BigDecimal(pressure);
        return f;
    }
}
