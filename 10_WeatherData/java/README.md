# finleap-case-study
Super-simple and straight-forward implementation based on [Dropwizard](http://www.dropwizard.io/). For the sake of 
simplicity, the application uses an internal configuration and logs directly to STDOUT. 


## Getting started

#### Prerequisites
- Java 8
- Maven 3

#### Building
Run the following command to compile the classes, execute all tests and create an executable JAR file:
```
mvn clean package
```

#### Running
From console:
```
java -jar target/finleap-case-study.jar server /config.yml
```

From IDE:
- use Main class: `app.App`
- add program argument: `server /config.yml`


## Usage
The application will start a Jetty server and accept requests on port 8080 and 8081. 

#### Fetching average forecast 
```
curl http://localhost:8080/data?city=Berlin
```
**Notes**:
- subsequent occurrences of the `city` query param will be ignored
- Possible response codes:
    - 200 (OK) - everything ok
    - 400 (BAD REQUEST) - if parameter value is missing
    - 404 (NOT FOUND) - if no data was found for the given city
    - 503 (SERVICE UNAVAILABLE) - if gathering the data takes too long

#### API documentation
Go to http://localhost:8080/swagger to get access to the API documentation.

#### Application Monitoring
The application exposes the usual Dropwizard PaaS endpoints, like `/ping`, `/metrics` and `/health`. A collection of 
all available endpoints is exposed in the Admin Operational Menu at http://localhost:8081
