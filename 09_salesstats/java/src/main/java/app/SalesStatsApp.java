package app;

import app.core.SalesAmountReservoir;
import app.resources.SalesResource;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class SalesStatsApp extends Application<Configuration> {

    public static void main(String[] args) throws Exception {
        new SalesStatsApp().run(args);
    }

    @Override
    public String getName() {
        return "salesstats-akarstensen";
    }

    @Override
    public void run(Configuration conf, Environment environment) {
        final Logger logger = LoggerFactory.getLogger(SalesStatsApp.class);
        logger.info("set app configuration to: {}", conf);

        final SalesAmountReservoir reservoir = new SalesAmountReservoir(60, TimeUnit.SECONDS);

        environment.jersey().register(new SalesResource(reservoir));
    }
}
