package main;

public class HashMap {

    private Entry[] table;
    private int capacity;
    private int size;
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;

    public HashMap(int initialCapacity) {
        this.capacity = initialCapacity;
        this.table = new Entry[capacity];
        this.size = 0;
    }

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

    public Integer get(GraphNode key) {
        if (key == null) {
            return null;
        }
        int index = findIndex(key);
        return (index != -1 && table[index].isActive) ? table[index].value : null;
    }

    public boolean hasKey(GraphNode key) {
        return findIndex(key) != -1;
    }

    private int findIndex(GraphNode key) {
        int index = hash(key);
        while (table[index] != null && !table[index].key.getId().equals(key.getId())) {
            index = (index + 1) % capacity;
        }
        return (table[index] != null && table[index].isActive) ? index : -1;
    }

    private int hash(GraphNode key) {
        return Math.abs(key.getId().hashCode()) % capacity;
    }

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

    public int size() {
        return size;
    }

}
