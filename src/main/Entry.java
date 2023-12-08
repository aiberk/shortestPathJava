package main;

public class Entry {
    private GraphNode key;
    private int value;

    public Entry(GraphNode key, int value) {
        this.key = key;
        this.value = value;
    }

    public GraphNode getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

