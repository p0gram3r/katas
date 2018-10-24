# Java Test Case Study

## Level 1
You need to create an API service using Java which will retrieve the average forecast weather metrics of a specific city. The API should expose a “/data” endpoint to retrieve the averages.

The /data endpoint should return a JSON object with the average of the following metrics:
- Average of daily (6h-18h) temperature, in Celsius, for the following 3 days
- Average of nightly (18h-6h) temperature, in Celsius, for the following 3 days
- Average of pressure for the following 3 days

The /data endpoint needs a CITY parameter containing the city name as the input for the correct response.

Use Open Weather Map to get the data ( https://openweathermap.org/ Free account for forecast data )

Make sure you use a full REST API convention and that you return the correct error codes when necessary.

At the root of the project there must be a README file describing the process to run and test the service and any challenges/decisions made during the process of developing this case study.

**Bonus points:**
- Validate input - watch out for any injection tries that an API user might insert
- API usage docs (Swagger, or whatever is preferred)
- Unit tests
- Integration tests
- Caching


## Level 2
Change the code so that the periods used for calculating the temperature averages are not hardcoded. Instead they should be part of the app configuration.
