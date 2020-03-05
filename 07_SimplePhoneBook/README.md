# coding dojo - simple phone book

The goal of this kata is to create a very simple phone book application. 

**Note:** The following instructions assume that the kata is practiced in Java. For other languages please use
respective frameworks and techniques.


## Level 1 - Core functionality

In this level we focus on the core functionality of the phone book: adding and listing entries.

To make things a bit easier, we agree on using String as data type for all fields in this project.

### Exercise 1.1

Create a class *SimplePhoneBook* and implement the following methods:
``` java
void add(String name, String number);

Collection<String> getAllNames();

String getNumber(String name);
```

Also implement the ```toString()``` method so that it returns output in the following way:
```
name1: number1
name2: number2
(...)
```

Make sure the output of ```getAllNames()``` and  ```toString()``` are sorted alphabetically.

### Exercise 1.2

Extend the *SimplePhoneBook* so that you can add multiple numbers for a single name. Retrieving the record for a given
name should return a Collection of all numbers.

The output format of ```toString()``` changes to:
```
name1:
  number1.1
  number1.2
name2:
  number2.1
(...)
```

Make sure the numbers per person are sorted alphabetically in the ```toString()``` output.

### Exercise 1.3

Extend the *SimplePhoneBook* so that each number is denoted by the type of the number. Possible types are HOME, CELL,
WORK, FAX, PAGER. Keep in mind that some people might have two ore more numbers of the same type.

The output format of ```toString()``` changes to:
```
name1:
  type1.1: number1.1
  type1.2: number1.2
name2:
  type2.1: number2.1
(...)
```

### Exercise 1.4 (optional)

Add methods to remove specific numbers from a person or the entire person from the phone book entirely. 


## level 2 - Unit testing

### Exercise 2.1

Write unit tests for your application. As always you should try to achieve a code coverage of 100%. 

To measure code coverage in your project, simply run
```(bash)
mvn cobertura:cobertura
```

This will run the [Cobertura](http://cobertura.github.io/cobertura/) plug-in for Maven and generate some HTML pages.
Check out *target/site/cobertura/index.hml* and look at the results.

### Exercise 2.2

Use the [Hamcrest framework](http://hamcrest.org/JavaHamcrest/) to write more readable unit tests.

### Exercise 2.3 (optional)

Rewrite the entire application using a test-driven approach. Make sure to always create at least one unit test before
adding new or changing existing functionality.

To make it more challenging, start over from exercise 1.1. Before doing any refactoring for subsequent exercises, write
all necessary unit tests up-front.


## level 3 - Persistence

### Exercise 3.1

Keeping our records in memory only is not enough: make sure the phone book can be persisted more permanently! Create
a mechanism to read and write the entries from/to a file using Java Object serialization.

### Exercise 3.2

Extend the persistence layer: use a database to store the records of the phone book. Set up a running database (e.g.
MySQL or [HSQLDB](http://hsqldb.org)) and define all tables required for your phone book implementation. Extend your
application to be able to connect to the database and 

For this Exercise it is sufficient to use plain JDBC.

### Exercise 3.3

Time for a slightly more sophisticated persistence layer! Replace the JDBC solution and make use of the simple SQL
wrapper framework [JDBI](http://jdbi.org).

### Exercise 3.4 (optional)

Go the extra mile and create an alternative persistence mechanism using the Java Persistence API (JPA).


## level 4 - Going enterprise

### Exercise 4.1

Create a REST API to manipulate the phone book and deploy your project to Tomcat. Use tools like *curl* or browser
plug-ins like [RESTClient](https://addons.mozilla.org/de/firefox/addon/restclient/) for Firefox to play around with
the application.

### Exercise 4.2

Create a simple Android application that uses the REST API to access the records in the phone book.
