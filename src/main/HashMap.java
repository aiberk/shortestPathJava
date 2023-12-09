package main;

// Represents a HashMap that uses closed hashing (open addressing with linear probing)
public class HashMap {

    private Entry[] table;
    private int capacity;
    private int size;
    private static final double LOAD_FACTOR_THRESHOLD = 0.75; // Load factor threshold for resizing

    // Constructor with initial capacity
    public HashMap(int initialCapacity) {
        this.capacity = initialCapacity;
        this.table = new Entry[capacity];
        this.size = 0;
    }

    // Sets a key-value pair in the HashMap
    public void set(GraphNode key, Integer value) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        // Resize if load factor exceeds threshold
        if ((double) size / capacity >= LOAD_FACTOR_THRESHOLD) {
            resize();
        }
        int index = hash(key);
        while (table[index] != null && !table[index].key.getId().equals(key.getId())) {
            index = (index + 1) % capacity; // Linear probing for collision resolution
        }
        // Insert new entry or update existing one
        if (table[index] == null) {
            table[index] = new Entry(key, value);
            size++;
        } else {
            table[index].value = value;
            table[index].isActive = true; // Mark as active in case it was previously deleted
        }
    }

    // Gets the value associated with a key
    public Integer get(GraphNode key) {
        if (key == null) {
            return null;
        }
        int index = findIndex(key);
        return (index != -1 && table[index].isActive) ? table[index].value : null;
    }

    // Checks if the key exists in the HashMap
    public boolean hasKey(GraphNode key) {
        return findIndex(key) != -1;
    }

    // Helper method to find the index of a key
    private int findIndex(GraphNode key) {
        int index = hash(key);
        while (table[index] != null && !table[index].key.getId().equals(key.getId())) {
            index = (index + 1) % capacity;
        }
        return (table[index] != null && table[index].isActive) ? index : -1;
    }

    // Hash function based on the key's unique ID
    private int hash(GraphNode key) {
        return Math.abs(key.getId().hashCode()) % capacity;
    }

    // Resizes the table when the load factor threshold is exceeded
    private void resize() {
        int newCapacity = capacity * 2;
        Entry[] oldTable = table;
        table = new Entry[newCapacity];
        capacity = newCapacity;
        size = 0;
        // Rehash all active entries
        for (Entry entry : oldTable) {
            if (entry != null && entry.isActive) {
                set(entry.key, entry.value);
            }
        }
    }

    // Returns the number of key-value pairs in the HashMap
    public int size() {
        return size;
    }

    // Additional methods (like delete) can be added if needed
}
