package app.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;

public class SalesStatisticsTest {
    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
    private static final String FIXTURE_FILE_NAME = "fixtures/stats.json";

    private static final SalesStatistics testStatistics = new SalesStatistics(
        "1000000.00",
        "45.04"
    );

    @Test
    public void serializesToJSON() throws Exception {
        final String expected = MAPPER.writeValueAsString(
            MAPPER.readValue(fixture(FIXTURE_FILE_NAME), SalesStatistics.class));
        final String actual = MAPPER.writeValueAsString(testStatistics);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void deserializesFromJSON() throws Exception {
        final SalesStatistics actual = MAPPER.readValue(fixture(FIXTURE_FILE_NAME), SalesStatistics.class);

        assertThat(actual).isEqualTo(testStatistics);
    }

}
