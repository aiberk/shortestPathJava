package main;

public class Entry {
    public GraphNode key;
    public int value;
    public boolean isActive;

    public Entry(GraphNode key, int value) {
        this.key = key;
        this.value = value;
        this.isActive = true;
    }

}
