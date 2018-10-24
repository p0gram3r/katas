package app;

import app.config.AppConfiguration;
import app.core.DataFactory;
import app.core.WeatherDataCache;
import app.core.openweathermap.OpenWeatherMapClient;
import app.health.OpenWeatherMapClientHealthCheck;
import app.resources.DataResource;
import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;

public class App extends Application<AppConfiguration> {

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }

    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {
        // allow reading config.yml from the application JAR
        bootstrap.setConfigurationSourceProvider(
                new ResourceConfigurationSourceProvider()
        );

        bootstrap.addBundle(new SwaggerBundle<AppConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(AppConfiguration cfg) {
                return cfg.swagger;
            }
        });
    }

    @Override
    public void run(AppConfiguration conf, Environment env) {
        final Logger logger = LoggerFactory.getLogger(App.class);
        logger.info("set app configuration to: {}", conf);

        final Client jerseyClient = new JerseyClientBuilder(env).build(getName());
        final OpenWeatherMapClient owmClient = new OpenWeatherMapClient(jerseyClient, conf.openWeatherApi);

        final WeatherDataCache cache = new WeatherDataCache(owmClient, conf.weatherDataCache);
        final DataFactory dataFactory = new DataFactory(conf.numberOfDaysForForecast);
        final DataResource dataResource = new DataResource(cache, dataFactory, conf.requestTimeoutMillis);

        env.jersey().register(dataResource);
        env.healthChecks().register("openWeatherMap", new OpenWeatherMapClientHealthCheck(owmClient));
    }

}
