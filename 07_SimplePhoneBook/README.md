# coding dojo - simple phone book

The goal of this kata is to create a very simple phone book application. 

**Note:** The following instructions assume that the kata is practised in Java. For other languages please use respective frameworks and techniques.


## Level 1 - Core functionality

In this level we focus on the core funtionality of the phone book: adding and listing entries. 

To make things a bit easier, we agree on using String as data type for all fields in this project.

### Excercise 1.1

Create a class *SimplePhoneBook* and implements the following methods:
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

Make sure the listing of all names and the ```toString()``` are sorted alphabetically.

### Excercise 1.2

Extend the *SimplePhoneBook* so that you can add multiple numbers for a single name. Retreiving the record for a given name should return a Collection of all numbers.

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

### Excercise 1.3

Extend the *SimplePhoneBook* so that each number is denoted by the type of the number. Possible types are HOME, CELL, WORK, FAX, PAGER. Keep in mind that some people might have two ore more numbers of the same type.

The output format of ```toString()``` changes to:
```
name1:
  type1.1: number1.1
  type1.2: number1.2
name2:
  type2.1: number2.1
(...)
```

## level 2 - Persistence

### Excercise 2.1 

Keeping our records in memory only is not enough: make sure the phone book can be persisted more permanently! Create a mechanism to read and write the entries from/to a file using Java Object serialization.

### Excercise 2.2

Extend the persistence layer: use a database to store the records of the phone book. Set up a running database (e.g. MySQL or [HSQLDB](http://hsqldb.org)) and define all tables required for your phone book implementation. Extend your application to be able to connect to the database and 

For this excercise it is sufficient to use plain JDBC. 

### Excercise 2.3

Time for a slightly more sophisticated persistence layer! Replace the JDBC solution and make use of the simple SQL wrapper framework [JDBI](http://jdbi.org).

### Excercise 2.4 (optional)

Go the extra mile and create an alternative persistence mechanism using the Java Persistence API (JPA).


## level 3 - Going enterprise

### Excercise 3.1

Create a REST API to manipulate the phone book and deploy your project to Tomcat. Use tools like *curl* or browser plugin like *RESTClient* for Firefox to play around with the application.

### Excercise 3.2

Create a simple Android application that uses the REST API to access the records in the phone book.
