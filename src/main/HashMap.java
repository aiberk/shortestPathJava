/**
 * Abraham Iberkleid
 * aiberkleid@brandeis.edu
 * December 9, 2023
 * PA3
 * Bugs: There are no known bugs. 
 */
package main;

/**
 * Represents a simple hash map implementation using linear probing for
 * collision resolution.
 */
public class HashMap {

    /**
     * The array of entries that store the key-value pairs.
     */
    private Entry[] table;

    /**
     * The current capacity of the hash map.
     */
    private int capacity;

    /**
     * The number of key-value pairs stored in the hash map.
     */
    private int size;

    /**
     * The load factor threshold that triggers a resize.
     */
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;

    /**
     * Constructs a new HashMap with a specified initial capacity.
     *
     * @param initialCapacity The initial capacity of the hash map.
     */
    public HashMap(int initialCapacity) {
        this.capacity = initialCapacity;
        this.table = new Entry[capacity];
        this.size = 0;
    }

    /**
     * Inserts a key-value pair into the hash map. If the key already exists,
     * updates its value.
     *
     * @param key   The key to insert into the map.
     * @param value The value associated with the key.
     * @throws IllegalArgumentException if the key is null.
     */
    public void set(GraphNode key, Integer value) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        if ((double) size / capacity >= LOAD_FACTOR_THRESHOLD) {
            resize();
        }
        int index = hash(key);
        while (table[index] != null && !table[index].key.getId().equals(key.getId())) {
            index = (index + 1) % capacity;
        }

        if (table[index] == null) {
            table[index] = new Entry(key, value);
            size++;
        } else {
            table[index].value = value;
            table[index].isActive = true;
        }
    }

    /**
     * Retrieves the value associated with a given key.
     *
     * @param key The key whose value is to be retrieved.
     * @return The value associated with the key, or null if the key is not found.
     */
    public Integer get(GraphNode key) {
        if (key == null) {
            return null;
        }
        int index = findIndex(key);
        return (index != -1 && table[index].isActive) ? table[index].value : null;
    }

    /**
     * Checks whether a key exists in the hash map.
     *
     * @param key The key to check for in the map.
     * @return true if the key exists, false otherwise.
     */
    public boolean hasKey(GraphNode key) {
        return findIndex(key) != -1;
    }

    /**
     * Finds the index of a given key in the table.
     *
     * @param key The key whose index is to be found.
     * @return The index of the key if it exists, or -1 if it does not.
     */
    private int findIndex(GraphNode key) {
        int index = hash(key);
        while (table[index] != null && !table[index].key.getId().equals(key.getId())) {
            index = (index + 1) % capacity;
        }
        return (table[index] != null && table[index].isActive) ? index : -1;
    }

    /**
     * Computes the hash value for a given key.
     *
     * @param key The key for which to compute the hash.
     * @return The computed hash value.
     */
    private int hash(GraphNode key) {
        return Math.abs(key.getId().hashCode()) % capacity;
    }

    /**
     * Resizes the hash table when the load factor threshold is exceeded.
     * This method doubles the capacity of the hash table.
     */
    private void resize() {
        int newCapacity = capacity * 2;
        Entry[] oldTable = table;
        table = new Entry[newCapacity];
        capacity = newCapacity;
        size = 0;
        for (Entry entry : oldTable) {
            if (entry != null && entry.isActive) {
                set(entry.key, entry.value);
            }
        }
    }

    /**
     * Returns the current number of key-value pairs in the hash map.
     *
     * @return The size of the hash map.
     */
    public int size() {
        return size;
    }

}
