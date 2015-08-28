# coding dojo - simple phone book

The goal of this kata is to create a very simple phone book application. 

**Note:** The following instructions assume that the kata is practised in Java. For other languages please use respective frameworks and techniques.


## Level 1 - Core functionality

To make things a bit easier, we agree on using String as data type for all fields in this project.

### 1.1

Create a class *SimplePhoneBook* and implements the following methods:
``` java
void add(String name, String number);

Collection<String> getAllNames();

String getNumber(String name)
```

Also implement the ```toString()``` method so that it returns output in the following way:
```
name1: number1
name2: number2
(...)
```

Make sure the listing of all names and the ```toString()``` are sorted alphabetically.

### 1.2

Extend the *SimplePhoneBook* so that one can add multiple numbers for a single name. 

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

### 1.3

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

### 2.1 

Keeping all the data in memory is not enough: let's make sure the phone book can be persisted more permanently. Create a mechanism to read and write the entries from a file.

### 2.2

Extend the persistence layer: use a database to store the entries of the phonebook. For this excercise it is sufficient to use plain JDBC. 

### 2.3

Go the extra mile and replace the JDBC solution. Make use of an SQL wrapper framework like [JDBI](http://jdbi.org).

### 2.4 (optional)

Build an alternative persistence mechanism using the Java Persistence API (JPA).


## level 3 - Accessibility

### 3.1

Create a REST API to manipulate the entries of the phone book. You should be able to add new entries, get a complete listing and search for numbers by name and type.

### 3.2

Create a simple android application that uses the REST API to access the entries in the phone book.
