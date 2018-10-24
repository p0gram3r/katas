package app.health;

import app.core.openweathermap.OpenWeatherMapClient;
import com.codahale.metrics.health.HealthCheck;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import static com.codahale.metrics.health.HealthCheck.Result.healthy;
import static com.codahale.metrics.health.HealthCheck.Result.unhealthy;
import static java.util.Objects.requireNonNull;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class OpenWeatherMapClientHealthCheck extends HealthCheck {
    private final OpenWeatherMapClient client;

    public OpenWeatherMapClientHealthCheck(OpenWeatherMapClient client) {
        this.client = requireNonNull(client);
    }

    @Override
    protected Result check() throws Exception {
        try {
            final Supplier<Result> healthCheck = () -> {
                client.loadWeatherData("Berlin");
                return healthy();
            };

            return CompletableFuture.supplyAsync(healthCheck)
                    .exceptionally(Result::unhealthy)
                    .get(client.getTimeoutMillis(), MILLISECONDS);
        } catch (InterruptedException e) {
            return unhealthy("health check timed out!");
        }
    }
}
