package main;

/**
 * Represents an entry in a hash map, holding a key-value pair.
 */
public class Entry {
    /**
     * The key of the entry, represented by a GraphNode.
     */
    public GraphNode key;

    /**
     * The value associated with the key in the hash map.
     */
    public int value;

    /**
     * Flag to check if the entry is active (not deleted).
     */
    public boolean isActive;

    /**
     * Constructs an Entry with a specified key and value.
     * 
     * @param key   The GraphNode key of the entry.
     * @param value The value to be associated with the key.
     */
    public Entry(GraphNode key, int value) {
        this.key = key;
        this.value = value;
        this.isActive = true;
    }

}
