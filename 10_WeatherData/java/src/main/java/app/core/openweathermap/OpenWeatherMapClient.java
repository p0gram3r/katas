package app.core.openweathermap;

import app.config.OpenWeatherApiConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

import static java.util.Objects.requireNonNull;

public class OpenWeatherMapClient {
    private static final Logger logger = LoggerFactory.getLogger(OpenWeatherMapClient.class);

    private final WebTarget webTarget;
    private final long timeoutMillis;

    public OpenWeatherMapClient(Client client, OpenWeatherApiConfiguration conf) {
        requireNonNull(conf);

        this.timeoutMillis = conf.timeoutMillis;
        this.webTarget = client.target(conf.forecastUri)
                .queryParam("appid", conf.appId)
                .queryParam("units", conf.units);
    }

    public WeatherData loadWeatherData(String city) {
        logger.info("fetching data of city: {}", city);
        return webTarget
                .queryParam("q", city)
                .request()
                .get(WeatherData.class);
    }

    public long getTimeoutMillis() {
        return timeoutMillis;
    }
}
