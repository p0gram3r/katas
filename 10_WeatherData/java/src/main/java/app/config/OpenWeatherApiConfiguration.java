package app.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class OpenWeatherApiConfiguration {

    @NotBlank
    @JsonProperty
    public String forecastUri;

    @NotBlank
    @JsonProperty
    public String appId;

    @NotBlank
    @JsonProperty
    public String units;

    @Min(1)
    @Max(10000)
    @JsonProperty
    public long timeoutMillis;

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("")
                .add("forecastUri", forecastUri)
                .add("appId", appId)
                .add("units", units)
                .add("timeoutMillis", timeoutMillis)
                .toString();
    }
}
