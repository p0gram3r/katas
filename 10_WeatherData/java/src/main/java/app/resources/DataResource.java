package app.resources;

import app.api.Data;
import app.core.DataFactory;
import app.core.WeatherDataCache;
import app.core.openweathermap.WeatherData;
import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.glassfish.jersey.server.ManagedAsync;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Response;
import java.util.Optional;

import static java.util.Objects.requireNonNull;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Api(value = "/", description = "The one and only DATA resource :-)")
@Path("/")
@Produces(APPLICATION_JSON)
public class DataResource {
    private static final Logger logger = LoggerFactory.getLogger(DataResource.class);

    private final WeatherDataCache cache;
    private final DataFactory dataFactory;
    private final long timeoutMillis;

    public DataResource(WeatherDataCache cache, DataFactory dataFactory, long timeoutMillis) {
        this.cache = requireNonNull(cache);
        this.dataFactory = requireNonNull(dataFactory);
        this.timeoutMillis = timeoutMillis;
    }

    @GET
    @Path("/data")
    @Timed(name = "data")
    @ExceptionMetered(name = "data")
    @ManagedAsync
    @ApiOperation("fetches the weather forecast data for the next days for a given city")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Data.class),
            @ApiResponse(code = 400, message = "query param 'city' missing"),
            @ApiResponse(code = 404, message = "no data found for city"),
            @ApiResponse(code = 503, message = "call to weather api timed out")
    })
    public void getForecastData(@NotBlank @QueryParam("city") String city, @Suspended AsyncResponse asyncResponse) {
        asyncResponse.setTimeout(timeoutMillis, MILLISECONDS);
        asyncResponse.setTimeoutHandler(r -> r.resume(timeoutResponse()));

        final Optional<WeatherData> weatherData = cache.getWeatherData(city);
        Object response = weatherData.isPresent()
                ? dataFactory.of(weatherData.get())
                : notFoundResponse();

        asyncResponse.resume(response);
    }

    private Response timeoutResponse() {
        final String message = "call to weather api timed out!";
        logger.warn(message);
        return Response.status(Response.Status.SERVICE_UNAVAILABLE)
                .entity(message)
                .build();
    }

    private Response notFoundResponse() {
        return Response.status(Response.Status.NOT_FOUND)
                .entity("no data found for city!")
                .build();
    }
}
