package main;

public class MinPriorityQueue {
    private GraphNode[] heap;
    private int size;
    private int capacity;

    public static void main(String[] args) {
        // Your application logic goes here.

        // Example: Print out the command-line arguments
        System.out.println("Command-line arguments:");
        for (String arg : args) {
            System.out.println(arg);
        }

        // Example usage of MinPriorityQueue
        MinPriorityQueue queue = new MinPriorityQueue(10);

        // Insert nodes into the queue (example)
        GraphNode node1 = new GraphNode("1", false);
        node1.priority = 1;
        GraphNode node2 = new GraphNode("2", false);
        node2.priority = 2;

        queue.insert(node1);
        queue.insert(node2);

        // Print the heap
        queue.printHeap();

        // Pull the highest priority element and print it
        GraphNode minNode = queue.pullHighestPriorityElement();
        if (minNode != null) {
            System.out.println("Pulled node with highest priority: " + minNode.getId());
        }
    }

    public MinPriorityQueue(int capacity) {
        this.capacity = capacity;
        this.heap = new GraphNode[capacity];
        this.size = 0;
    }

    public void insert(GraphNode node) {
        if (size == capacity) {
            resizeHeap();
        }
        heap[size] = node;
        bubbleUp(size);
        size++;
    }

    public GraphNode pullHighestPriorityElement() {
        if (isEmpty()) {
            return null;
        }
        GraphNode min = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapify(0);
        return min;
    }

    public void rebalance(GraphNode node) {
        int index = findIndex(node);
        if (index != -1) {
            if (index > 0 && node.priority < heap[parent(index)].priority) {
                bubbleUp(index);
            } else {
                heapify(index);
            }
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void bubbleUp(int index) {
        while (index > 0 && heap[index].priority < heap[parent(index)].priority) {
            swap(index, parent(index));
            index = parent(index);
        }
    }

    private void heapify(int index) {
        int left = leftChild(index);
        int right = rightChild(index);
        int smallest = index;

        if (left < size && heap[left].priority < heap[smallest].priority) {
            smallest = left;
        }
        if (right < size && heap[right].priority < heap[smallest].priority) {
            smallest = right;
        }
        if (smallest != index) {
            swap(index, smallest);
            heapify(smallest);
        }
    }

    private void swap(int i, int j) {
        GraphNode temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    private int parent(int pos) {
        return (pos - 1) / 2;
    }

    private int leftChild(int pos) {
        return (2 * pos) + 1;
    }

    private int rightChild(int pos) {
        return (2 * pos) + 2;
    }

    private void resizeHeap() {
        GraphNode[] oldHeap = heap;
        capacity *= 2;
        heap = new GraphNode[capacity];
        System.arraycopy(oldHeap, 0, heap, 0, size);
    }

    private int findIndex(GraphNode node) {
        for (int i = 0; i < size; i++) {
            if (heap[i] == node) {
                return i;
            }
        }
        return -1;
    }

    public void printHeap() {
        if (isEmpty()) {
            System.out.println("The heap is empty.");
        } else {
            System.out.println("Heap contents:");
            for (int i = 0; i < size; i++) {
                GraphNode node = heap[i];
                String details = String.format("Node ID: %s, Priority: %d", node.getId(), node.priority);
                System.out.println(details);
            }
        }
    }

}
