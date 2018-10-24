package app.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class WeatherDataCacheConfig {

    @Min(1)
    @Max(Long.MAX_VALUE)
    @JsonProperty
    public long expireAfterWriteDurationMillis;

    @Min(1)
    @Max(Long.MAX_VALUE)
    @JsonProperty
    public long maxCacheSize;


    @Override
    public String toString() {
        return MoreObjects.toStringHelper("")
                .add("expireAfterWriteDurationMillis", expireAfterWriteDurationMillis)
                .add("maxCacheSize", maxCacheSize)
                .toString();
    }
}
