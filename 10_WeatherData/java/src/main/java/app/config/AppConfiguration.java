package app.config;

import com.google.common.base.MoreObjects;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class AppConfiguration extends io.dropwizard.Configuration {

    public SwaggerBundleConfiguration swagger;

    @Valid
    public OpenWeatherApiConfiguration openWeatherApi;

    @Valid
    public WeatherDataCacheConfig weatherDataCache;

    @Min(1)
    @Max(5)
    public int numberOfDaysForForecast;

    @Min(1)
    @Max(60000)
    public long requestTimeoutMillis;

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("")
                .add("numberOfDaysForForecast", numberOfDaysForForecast)
                .add("openWeatherApi", openWeatherApi)
                .toString();
    }
}
