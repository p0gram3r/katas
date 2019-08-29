# salesstats
My solution for the eBay coding challenge "Sales Statistics".

Super-simple and straight-forward implementation using the [Dropwizard](http://www.dropwizard.io/) and
[Dropwizard Metrics](https://metrics.dropwizard.io/) frameworks. For the sake of simplicity, the application
does not require a configuration, logs directly to STDOUT and does not provide any liveness or readiness probes.


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
java -jar target/salesstats.jar server
```

From IDE:
- use Main class: `app.SalesStatsApp`
- add program argument: `server`


## Usage
The application will start a Jetty server and accept requests on port 8080.

#### Register a new sale
```
curl -X POST localhost:8080/sales?sales_amount=12.34
```
possible response codes:
- 202 (ACCEPTED) - for valid parameters
- 400 (BAD REQUEST) - if parameter value is missing or not a number

#### Request statistics
```
curl localhost:8080/statistics
```


## Notes on implementation details

Technically this challenge could have been solved by converting all sales amounts to cents and subsequently dealing with
`long` values only within the application. In that case, it would have been sufficient to use the Dropwizard metrics
classes [SlidingTimeWindowArrayReservoir](http://metrics.dropwizard.io/4.0.0/apidocs/com/codahale/metrics/SlidingTimeWindowArrayReservoir.html)
and [UniformSnapshot](http://metrics.dropwizard.io/4.0.0/apidocs/com/codahale/metrics/UniformSnapshot.html) for
collecting the amounts and creating the statistics.

However, this approach would have two small drawbacks:

1. The conversion of sales amounts to `long` values is somewhat error prone. It requires creating a floating point
value (e.g. double) and a multiplication before casting the value to `long`. However, the specification did not
explicitly state that the amounts are limited to two fraction digits. In theory it could be necessary to deal with
values that have more than two fraction digits. In that case, doing something like
`long amount = (long)(salesAmount * 100)` would lead to a precision loss. To avoid this problem, my implementation uses
`BigDecimal` for all calculations. This allows more accurate calculations and avoids an unnecessary math operations when
receiving a value.
1. The task description requires the calculation of the statistics to be fast. Getting the mean value of a
`UniformSnapshot` is a somewhat expensive operation as it requires two iterations plus one sorting of all values within
the snapshot. Some of these operations are actually not necessary for the use case of this application and could be
avoided to save computation time. Plus, the default snapshot does not offer a method to return the sum of all values,
simply because this is not a common use case within the Dropwizard metrics framework. So calculating the sum requires
another iteration of all values of the snapshot.

Since the Metrics framework is not designed for changing implementation details of specific classes, I decided to copy
and modify some of the original classes. To keep this PoC small and clear, I also decided to omit some unit tests,
especially for the copied classes. It was not the goal to deliver a perfect solution with 100% code coverage and I
certainly did not want to present someone else's code as mine. If this was a real project, we would probably come up
with a solution for that.
