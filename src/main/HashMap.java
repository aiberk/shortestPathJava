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
    private double loadFactorThreshold = 0.75; // Adjust this threshold as needed

    public HashMap(int initialCapacity) {
        this.capacity = initialCapacity;
        this.table = new Entry[capacity];
        this.size = 0;
    }

    public void set(GraphNode key, Integer value) {
        // Check and resize if needed
        if ((double) size / capacity > loadFactorThreshold) {
            resize();
        }

        int index = hash(key);
        while (table[index] != null && !isKeyEqual(table[index].key, key)) {
            index = (index + 1) % capacity; // Linear probing
        }

        if (table[index] == null || !table[index].isActive) {
            size++;
        }

        table[index] = new Entry(key, value);
    }

    public Integer get(GraphNode key) {
        int index = findIndex(key);
        return (index != -1) ? table[index].value : null;
    }

    public boolean hasKey(GraphNode key) {
        return findIndex(key) != -1;
    }

    private int findIndex(GraphNode key) {
        int index = hash(key);
        while (table[index] != null) {
            if (isKeyEqual(table[index].key, key) && table[index].isActive) {
                return index;
            }
            index = (index + 1) % capacity;
        }
        return -1;
    }

    private int hash(GraphNode key) {
        return Math.abs(key.getId().hashCode()) % capacity;
    }

    private boolean isKeyEqual(GraphNode a, GraphNode b) {
        return a.getId().equals(b.getId());
    }

    public int size() {
        return size;
    }

    private void resize() {
        // Double the capacity and rehash all existing entries
        int newCapacity = capacity * 2;
        Entry[] newTable = new Entry[newCapacity];

        for (Entry entry : table) {
            if (entry != null && entry.isActive) {
                int index = hash(entry.key);
                while (newTable[index] != null) {
                    index = (index + 1) % newCapacity;
                }
                newTable[index] = entry;
            }
        }

        table = newTable;
        capacity = newCapacity;
    }

    // Additional methods for handling deletion, if needed
}
