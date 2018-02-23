package app.resources;

import app.api.SalesStatistics;
import app.core.SalesAmountReservoir;
import app.core.SalesAmountSnapshot;
import io.dropwizard.jersey.caching.CacheControl;
import org.glassfish.jersey.server.ManagedAsync;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;

import static java.util.Objects.requireNonNull;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/")
@Produces(APPLICATION_JSON)
public class SalesResource {
    private static final Logger logger = LoggerFactory.getLogger(SalesResource.class);

    private final SalesAmountReservoir reservoir;

    public SalesResource(SalesAmountReservoir reservoir) {
        this.reservoir = requireNonNull(reservoir);
    }

    @POST
    @Path("/sales")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @ManagedAsync
    public Response sales(@NotNull @QueryParam("sales_amount") String salesAmount) {
        Response.Status responseStatus = Response.Status.ACCEPTED;

        try {
            reservoir.update(new BigDecimal(salesAmount));
        } catch (NumberFormatException e) {
            logger.warn("unable to parse amount {}", salesAmount);
            responseStatus = Response.Status.BAD_REQUEST;
        }

        return Response.status(responseStatus).build();
    }

    @GET
    @Path("/statistics")
    @CacheControl(noCache = true, noStore = true, mustRevalidate = true)
    public SalesStatistics statistics() {
        final SalesAmountSnapshot snapshot = reservoir.getSnapshot();
        final String totalSalesAmount = snapshot.getSum().toString();
        final String averageAmountPerOrder = snapshot.getMean().toString();

        return new SalesStatistics(totalSalesAmount, averageAmountPerOrder);
    }

}
