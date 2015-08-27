# coding dojo - simple phone book

The goal of this kata is to create a very simple phone book application. 

**Note:** The following instructions assume that the kata is practised in Java. For other languages please use respective frameworks and techniques.


## Level 1 - Basic functionality

To make things a bit easier, we agree on using String as data type for pieces of information in this project.

### 1.1

- Namen und Nummer als Strings
- Namen alphatetisch sortiert


- methoden zum Abfragen von Einträge

- Format zeilenweise "name: nummer\n"

- methods to implement:
	- add(name, number)
	- toString()


### 1.2

- mehrere Nummern pro Name
- Nummern alphabetisch sortiert
- Format "name:\n  nummer\n"



### 1.3

- Nummern mit Typ versehen (z.B. HOME, CELL, WORK, FAX, PAGER)
- pro Person mehrere Nummern pro Typ möglich
- Format "name:\n  typ: nummer\n"


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
