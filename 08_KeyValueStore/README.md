# coding dojo - In Memory Key/Value Store

Your task is to write an IN-MEMORY key/value store that is loosely inspired by Riak.

## API of the Key Value Store
Write a set of java classes that support all of these operations. Please note that this is just a DEFINITION of the
functionality. Your code could use an entirely different approach to this.

1. `put(namespace:String, key:String, value:Serializable) : void`: stores or updates the `value` that `key` points to
in the `namespace`.
2. `get(namespace:String, key:String) : Serializable?`: retrieves the object identified by `namespace` and `key`.
3. `delete(namespace:String, key:String) : void`: deletes the object

## More characteristics:
1. It shall be able to CRUD (create, read, update, delete) simple (serializable) java beans:
   * Put: create or update an object
   * Get: return the object in it's current version
   * Delete: delete the object
2. Inside Store, objects can only be identified via their keys. Keys do not need to be part of the object.
3. The store should completely isolate its internal datastructure. Objects shall only be removable or changeable via
the store's interface (i.e. Objects are detached from persistence).
4. The store shall be thread safe.
    * CRUD operations shall be atomic.
    * No need for transactions
5. The store should support several namespaces.
    * Each namespace has it's isolated objects. the same key can exist in different namespaces and point to different
objects.
    * New namespaces are to be created lazily with the first write operations.

## General Hints
* No "real" persistence necessary, in-memory sufficient.
* No javadoc necessary. Tests and self explaining code is sufficient.
* Writing well testable (and well tested) code is appreciated.
* You may use any external dependencies you want.
* You do not need to build any API or network interface for this - it only needs to be accessed inside the code.

