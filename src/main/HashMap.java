package main;

public class HashMap {
    private static class Entry {
        GraphNode key;
        Integer value;
        boolean isActive;

        Entry(GraphNode key, Integer value) {
            this.key = key;
            this.value = value;
            this.isActive = true;
        }
    }

    private Entry[] table;
    private int capacity;
    private int size;

    public HashMap(int capacity) {
        this.capacity = capacity;
        this.table = new Entry[capacity];
        this.size = 0;
    }

    public void set(GraphNode key, Integer value) {
        int index = hash(key);
        while (table[index] != null && !isKeyEqual(table[index].key, key)) {
            index = (index + 1) % capacity; // Linear probing
        }

        if (table[index] == null) {
            size++;
        }
        table[index] = new Entry(key, value);
    }

    public Integer get(GraphNode key) {
        int index = hash(key);
        while (table[index] != null && !isKeyEqual(table[index].key, key)) {
            index = (index + 1) % capacity; // Linear probing
        }
        return table[index] != null ? table[index].value : null;
    }

    public boolean hasKey(GraphNode key) {
        int index = hash(key);
        while (table[index] != null && !isKeyEqual(table[index].key, key)) {
            index = (index + 1) % capacity; // Linear probing
        }
        return table[index] != null;
    }

    private int hash(GraphNode key) {
        return key.getId().hashCode() % capacity;
    }

    private boolean isKeyEqual(GraphNode a, GraphNode b) {
        return a.getId().equals(b.getId());
    }

    public int size() {
        return size;
    }

    // Additional methods for resizing and handling deletions, if needed
}
