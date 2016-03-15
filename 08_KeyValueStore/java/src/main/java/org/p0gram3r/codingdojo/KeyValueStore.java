package org.p0gram3r.codingdojo;

import java.io.Serializable;

public interface KeyValueStore {

    /**
     * stores or updates the `value` that `key` points to in the `namespace`.
     */
    void put(String namespace, String key, Serializable value);

    /**
     * retrieves the object identified by `namespace` and `key`
     */
    Serializable get(String namespace, String key);

    /**
     * deletes the object
     */
    void delete(String namespace, String key);

}
