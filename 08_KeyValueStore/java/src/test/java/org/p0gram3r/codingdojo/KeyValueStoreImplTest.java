package org.p0gram3r.codingdojo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.io.Serializable;

import org.junit.Before;
import org.junit.Test;

public class KeyValueStoreImplTest {

    private static final String NAMESPACE1 = "namespace1";
    private static final String NAMESPACE2 = "namespace2";

    private static final String KEY1 = "key1";
    private static final String KEY2 = "key2";
    private static final String KEY3 = "key3";

    private KeyValueStoreImpl store;

    @Before
    public void setUp() throws Exception {
        store = new KeyValueStoreImpl();
    }

    @Test
    public void putToEmptyStoreCreatesNamespace() {
        Serializable value = "value";

        assertEquals(0, store.countNamespaces());

        store.put(NAMESPACE1, KEY1, value);
        assertEquals(1, store.countNamespaces());
        assertEquals(1, store.sizeOfNamespace(NAMESPACE1));
    }

    @Test
    public void putSameKeyToDifferentNamespace() {
        Serializable value = "value";

        assertEquals(0, store.countNamespaces());

        store.put(NAMESPACE1, KEY1, value);
        store.put(NAMESPACE2, KEY1, value);
        assertEquals(2, store.countNamespaces());
        assertEquals(1, store.sizeOfNamespace(NAMESPACE1));
        assertEquals(1, store.sizeOfNamespace(NAMESPACE2));
    }

    @Test
    public void putDifferentKeysToSameNamespace() {
        Serializable value = "value";

        assertEquals(0, store.countNamespaces());

        store.put(NAMESPACE1, KEY1, value);
        store.put(NAMESPACE1, KEY2, value);
        assertEquals(1, store.countNamespaces());
        assertEquals(2, store.sizeOfNamespace(NAMESPACE1));
    }

    @Test
    public void putDifferentKeysToDifferentNamespaces() {
        Serializable value = "value";

        assertEquals(0, store.countNamespaces());

        store.put(NAMESPACE1, KEY1, value);
        store.put(NAMESPACE1, KEY2, value);
        store.put(NAMESPACE2, KEY3, value);
        assertEquals(2, store.countNamespaces());
        assertEquals(2, store.sizeOfNamespace(NAMESPACE1));
        assertEquals(1, store.sizeOfNamespace(NAMESPACE2));
    }

    @Test
    public void getExistingKey() {
        Serializable value = "value";

        store.put(NAMESPACE1, KEY1, value);

        Serializable retrievedValue = store.get(NAMESPACE1, KEY1);
        assertNotNull(retrievedValue);
        assertSame(value, retrievedValue);
    }

    @Test
    public void getNonExistingKey() {
        Serializable value = "value";

        store.put(NAMESPACE1, KEY1, value);
        assertEquals(1, store.sizeOfNamespace(NAMESPACE1));

        Serializable retrievedValue = store.get(NAMESPACE1, KEY2);
        assertNull(retrievedValue);
    }

    @Test
    public void getFromNonExistingNameSpace() {
        assertEquals(0, store.sizeOfNamespace(NAMESPACE1));

        Serializable retrievedValue = store.get(NAMESPACE1, KEY1);
        assertNull(retrievedValue);
    }

    @Test
    public void deleteExistingKey() {
        Serializable value = "value";

        store.put(NAMESPACE1, KEY1, value);
        assertEquals(1, store.sizeOfNamespace(NAMESPACE1));

        store.delete(NAMESPACE1, KEY1);
        assertEquals(0, store.sizeOfNamespace(NAMESPACE1));
    }

    @Test
    public void deleteNonExistingKey() {
        Serializable value = "value";

        store.put(NAMESPACE1, KEY1, value);
        assertEquals(1, store.sizeOfNamespace(NAMESPACE1));
        assertEquals(0, store.sizeOfNamespace(NAMESPACE2));

        store.delete(NAMESPACE1, KEY2);
        assertEquals(1, store.sizeOfNamespace(NAMESPACE1));
        assertEquals(0, store.sizeOfNamespace(NAMESPACE2));
    }

    @Test
    public void deleteFromNonExistingNamespace() {
        assertEquals(0, store.countNamespaces());
        assertEquals(0, store.sizeOfNamespace(NAMESPACE1));

        store.delete(NAMESPACE1, KEY1);
        assertEquals(0, store.countNamespaces());
        assertEquals(0, store.sizeOfNamespace(NAMESPACE1));
    }

}
