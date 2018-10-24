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
    - 404 (NOT FOUND) - if parameter value is missing
    - 503 (SERVICE UNAVAILABLE) - if gathering the data takes too long

#### API documentation
Go to http://localhost:8080/swagger to get access to the API documentation.

#### Application Monitoring
The application exposes the usual Dropwizard PaaS endpoints, like `/ping`, `/metrics` and `/health`. A collection of 
all available endpoints is exposed in the Admin Operational Menu at http://localhost:8081


## Notes on implementation details & design decisions

# Why Dropwizard?
I simply love the framework. It is super-simplistic and comes bundled with some powerful tools for Site Reliability 
Engineers and developers with a DevOps mindset. 
     
#### YAGNI (stuff I decided to not fully implement) 
- getters & setters for the OpenWeatherMap API response objects. These objects are only used internally.     
- all other fields of the OpenWeatherMap API response objects. These are not needed at the moment and should only be 
added when necessary
- providing the possibility to specify the daily and nightly hours instead of hardcoding them. Not necessary to solve
the task.
  
#### Health Checks
At first glance, there is no simple "ping" endpoint in the OpenWeatherMap API, so I decided to fetch some data to check
the availability of the service. Should be revisited.

At the moment there are no other services involved, so no need for more health checks.

#### Dealing with data from OpenWeatherMap
This implementation assumes that the data received from OpenWeatherMap does not include old data (i.e. previous days).
A respective check can easily been added to `app.core.DataFactory`. Only thought about that when I was ready to hand in
the assignment... 

#### Caching
Implementing a cache is usually not a trivial task as it requires some insights on the app traffic. 
Among other things, it is crucial to identify a good time to evict entries from the cache to avoid memory issues and 
prevent clients from receiving stale data. The point of this case study was (hopefully) not to find a perfect solution 
for this problem, so I implemented only a very basic cache using the capabilities of Guava.

In a real-world scenario, however, it would be worth spending some time analysing the real app usage. Also, considering 
other caching strategies like HTTP-level caching (configured via HTTP cache headers) and external caches 
(e.g. memcached) might make sense. 

#### "full REST API convention"
As with many topics in software development, there are many discussions on how a REST API should be implemented. 
Adjusting to a certain style or specific requirements is always possible but that requires a discussion among the devs
involved plus a common guideline. I have no idea which specific parts the devs at finleap consider "conventional", so I
decided to only implement only the bare minimum that has been required in my last project.

#### Unit Testing
Another holy grail in software development. For an internal application like this, I don't see the point in testing 
getters and setters (e.g. for sake of code coverage) or components that solely depend on one of the frameworks in use
(which usually have been tested extensively already). Also, writing good (a.k.a. useful) tests require some time as it
might involve refactoring parts of the productive code.
 
With the time cap for this case study in mind, I limited myself to only test the most crucial parts of the code. And 
still I ran out of time... Embarrassing :-) After being overdue for 5 hours, I stopped and decided to admit my failure
on this part. There are some tests, but a very essential part of the logic has not been covered yet...  

#### Integration Testing
Integration tests for a single app? Hm... interesting, how is that supposed to look like? :-) Even the response format
has not been specified in detail... 

For some very basic tests I added a [Postman](https://www.getpostman.com/) collection, which runs tests against a 
running app on localhost. In a more complex integration test environment, one could also fake network issues to test for
timeouts and inspect the caching behavior. There would also be the possibility to fake the values provided by 
OpenWeatherMap, so the generated response can be matched against some expected values.
