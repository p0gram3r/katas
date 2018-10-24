package app.core;

import app.config.WeatherDataCacheConfig;
import app.core.openweathermap.OpenWeatherMapClient;
import app.core.openweathermap.WeatherData;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.UncheckedExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.NotFoundException;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static java.util.Objects.requireNonNull;

public class WeatherDataCache {
    private static final Logger logger = LoggerFactory.getLogger(WeatherDataCache.class);

    private final LoadingCache<String, WeatherData> cache;

    public WeatherDataCache(OpenWeatherMapClient client, WeatherDataCacheConfig conf) {
        requireNonNull(client);

        final CacheLoader<String, WeatherData> loader = new CacheLoader<String, WeatherData>() {
            @Override
            public WeatherData load(String city) {
                return client.loadWeatherData(city);
            }
        };
        this.cache = CacheBuilder.newBuilder()
                .expireAfterWrite(conf.expireAfterWriteDurationMillis, TimeUnit.MILLISECONDS)
                .maximumSize(conf.maxCacheSize)
                .build(loader);
    }

    public Optional<WeatherData> getWeatherData(String city) {
        try {
            return Optional.of(cache.get(city));
        } catch (ExecutionException | UncheckedExecutionException e) {
            if (!(e.getCause() instanceof NotFoundException)) {
                logger.error("error fetching WeatherData for city " + city, e);
            }
        }

        return Optional.empty();
    }

}
