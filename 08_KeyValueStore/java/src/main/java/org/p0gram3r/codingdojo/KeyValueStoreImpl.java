package org.p0gram3r.codingdojo;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class KeyValueStoreImpl implements KeyValueStore {

    private Map<String, Map<String, Serializable>> store = new ConcurrentHashMap<>();

    @Override
    public void put(String namespace, String key, Serializable value) {
        Map<String, Serializable> inner = null;

        // TODO is this sufficient for thread safety? It should prevent overwriting a newly created inner map.
        synchronized (this) {
            inner = store.get(namespace);
            if (inner == null) {
                inner = new ConcurrentHashMap<>();
                store.put(namespace, inner);
            }
        }

        inner.put(key, value);
    }

    @Override
    public Serializable get(String namespace, String key) {
        Map<String, Serializable> inner = store.get(namespace);
        if (inner == null) {
            return null;
        }

        return inner.get(key);
    }

    @Override
    public void delete(String namespace, String key) {
        Map<String, Serializable> inner = store.get(namespace);
        if (inner != null) {
            inner.remove(key);
        }
    }

    public int countNamespaces() {
        return store.size();
    }

    public int sizeOfNamespace(String namespace) {
        Map<String, Serializable> inner = store.get(namespace);
        return inner == null ? 0 : inner.size();
    }

}
