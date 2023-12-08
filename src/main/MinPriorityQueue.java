package main;
import java.util.Arrays;
import java.util.HashMap;

public class MinPriorityQueue {
    private GraphNode[] heap;
    private int size;
    private HashMap<GraphNode, Integer> nodePositions;

    public MinPriorityQueue(int capacity) {
        heap = new GraphNode[capacity];
        size = 0;
        nodePositions = new HashMap<>();
    }

    public void insert(GraphNode g) {
        if (size == heap.length) {
            // Heap is full, resize or throw exception
        }
        heap[size] = g;
        nodePositions.put(g, size);
        bubbleUp(size);
        size++;
    }

    public GraphNode pullHighestPriorityElement() {
        if (isEmpty()) {
            return null; // or throw exception
        }
        GraphNode min = heap[0];
        heap[0] = heap[size - 1];
        nodePositions.put(heap[0], 0);
        nodePositions.remove(min);
        size--;
        heapify(0);
        return min;
    }

    public void rebalance(GraphNode g) {
        Integer index = nodePositions.get(g);
        if (index != null) {
            bubbleUp(index);
            heapify(index);
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void bubbleUp(int index) {
        // Implement bubble-up logic
    }

    private void heapify(int index) {
        // Implement heapify logic
    }

    // Additional methods to support heap operations
}
