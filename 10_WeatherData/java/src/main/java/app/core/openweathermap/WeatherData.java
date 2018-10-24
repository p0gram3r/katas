package app.core.openweathermap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherData {
    @Valid
    @NotNull
    public City city;

    @Valid
    @NotNull
    public List<Forecast> list;


    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class City {
        @Valid
        @NotNull
        public Long id;

        @Valid
        @NotBlank
        public String name;

        @Valid
        @NotBlank
        public String country;
    }

}
