package org.p0gram3r.codingdojo;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class KeyValueStoreImpl implements KeyValueStore {

    private Map<String, Map<String, Serializable>> store = new HashMap<>();

    @Override
    public void put(String namespace, String key, Serializable value) {
        Map<String, Serializable> inner = null;

        synchronized (this) {
            inner = store.get(namespace);
            if (inner == null) {
                inner = new HashMap<>();
                store.put(namespace, inner);
            }
        }

        Serializable copy = createDeepCopy(value);
        inner.put(key, copy);
    }

    @Override
    public Serializable get(String namespace, String key) {
        Map<String, Serializable> inner = store.get(namespace);
        if (inner == null) {
            return null;
        }

        Serializable value = inner.get(key);
        return createDeepCopy(value);
    }

    @Override
    public void delete(String namespace, String key) {
        Map<String, Serializable> inner = store.get(namespace);
        if (inner != null) {
            inner.remove(key);
        }
    }

    int countNamespaces() {
        return store.size();
    }

    int sizeOfNamespace(String namespace) {
        Map<String, Serializable> inner = store.get(namespace);
        return inner == null ? 0 : inner.size();
    }

    private Serializable createDeepCopy(Serializable value) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(value);

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (Serializable) ois.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
