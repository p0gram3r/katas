package app.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;

import java.math.BigDecimal;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;

public class DataTest {
    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
    private static final String FIXTURE_FILE_NAME = "fixtures/data.json";

    private static final Data testData = new Data(
            new BigDecimal("11.11"),
            new BigDecimal("-22.22"),
            new BigDecimal("333.3")
    );

    @Test
    public void serializesToJSON() throws Exception {
        final String expected = MAPPER.writeValueAsString(MAPPER.readValue(fixture(FIXTURE_FILE_NAME), Data.class));
        final String actual = MAPPER.writeValueAsString(testData);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void deserializesFromJSON() throws Exception {
        final Data actual = MAPPER.readValue(fixture(FIXTURE_FILE_NAME), Data.class);

        assertThat(actual).isEqualTo(testData);
    }

}