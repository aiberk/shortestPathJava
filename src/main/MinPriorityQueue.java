package main;

public class MinPriorityQueue {
    public GraphNode[] heap;
    public int size;
    public int capacity;
    public HashMap indexMap;

    // public static void main(String[] args) {

    // // Example usage of MinPriorityQueue
    // MinPriorityQueue queue = new MinPriorityQueue(10);

    // // Insert nodes into the queue (example)
    // GraphNode node1 = new GraphNode("1", false);
    // node1.priority = 1;
    // GraphNode node2 = new GraphNode("2", false);
    // node2.priority = 2;

    // queue.insert(node1);
    // queue.insert(node2);

    // // Print the heap
    // queue.printHeap();
    // System.out.println(queue.toString());

    // // Pull the highest priority element and print it
    // GraphNode minNode = queue.pullHighestPriorityElement();
    // if (minNode != null) {
    // System.out.println("Pulled node with highest priority: " + minNode.getId());
    // }
    // }

    public MinPriorityQueue(int capacity) {
        this.capacity = capacity;
        this.heap = new GraphNode[capacity];
        this.size = 0;
        this.indexMap = new HashMap(capacity);
    }

    public void insert(GraphNode node) {
        if (size == capacity) {
            resizeHeap();
        }
        heap[size] = node;
        indexMap.set(node, size);
        bubbleUp(size);
        size++;
    }

    public GraphNode pullHighestPriorityElement() {
        if (isEmpty()) {
            return null;
        }
        GraphNode min = heap[0];
        indexMap.set(heap[0], -1); // Mark as removed in indexMap
        heap[0] = heap[size - 1];
        indexMap.set(heap[0], 0); // Update the new root's index in indexMap
        size--;
        heapify(0);
        return min;
    }

    public void rebalance(GraphNode node) {
        Integer index = indexMap.get(node);
        if (index != -1) {
            if (index > 0 && node.priority < heap[parent(index)].priority) {
                bubbleUp(index);
            } else if ((index > 0 && node.priority == heap[parent(index)].priority)) {
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
        indexMap.set(heap[i], i);
        indexMap.set(heap[j], j);
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

    public int getIndexMapValue(GraphNode node) {
        Integer index = indexMap.get(node);
        return index != null ? index : -1; // Return -1 if the node is not in the indexMap
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            GraphNode node = heap[i];
            sb.append(node.getId());
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

}
