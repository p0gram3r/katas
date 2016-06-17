package org.p0gram3r.codingdojo;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class KeyValueStoreImpl implements KeyValueStore {

    private Map<String, Map<String, byte[]>> store = new ConcurrentHashMap<>();

    @Override
    public void put(String namespace, String key, Serializable value) {
        Map<String, byte[]> inner = null;

        synchronized (this) {
            inner = store.get(namespace);
            if (inner == null) {
                inner = new ConcurrentHashMap<>();
                store.put(namespace, inner);
            }
        }

        inner.put(key, storeInBytes(value));
    }

    @Override
    public Serializable get(String namespace, String key) {
        Map<String, byte[]> inner = store.get(namespace);
        if (inner == null) {
            return null;
        }

        return restoreFromBytes(inner.get(key));
    }

    @Override
    public void delete(String namespace, String key) {
        Map<String, byte[]> inner = store.get(namespace);
        if (inner != null) {
            inner.remove(key);
        }
    }

    int countNamespaces() {
        return store.size();
    }

    int sizeOfNamespace(String namespace) {
        Map<?, ?> inner = store.get(namespace);
        return inner == null ? 0 : inner.size();
    }

    private byte[] storeInBytes(Serializable value) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(value);

            return baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Serializable restoreFromBytes(byte[] array) {
        if (array == null) {
            return null;
        }

        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(array);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (Serializable) ois.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
