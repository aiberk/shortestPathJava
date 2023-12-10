/**
 * Abraham Iberkleid
 * aiberkleid@brandeis.edu
 * December 9, 2023
 * PA3
 * Bugs: There are no known bugs. 
 */
package main;

/**
 * Represents a minimum priority queue implemented using a heap data structure.
 * It is designed to manage GraphNodes based on their priority values.
 */
public class MinPriorityQueue {
    public GraphNode[] heap;
    public int size;
    public int capacity;
    public HashMap indexMap;

    /**
     * Constructs a MinPriorityQueue with a specified capacity.
     * 
     * @param capacity The capacity of the priority queue.
     */
    public MinPriorityQueue(int capacity) {
        this.capacity = capacity;
        this.heap = new GraphNode[capacity];
        this.size = 0;
        this.indexMap = new HashMap(capacity);
    }

    /**
     * Inserts a new GraphNode into the priority queue.
     * 
     * @param node The GraphNode to be inserted.
     */
    public void insert(GraphNode node) {
        if (size == capacity) {
            resizeHeap();
        }
        heap[size] = node;
        indexMap.set(node, size);
        bubbleUp(size);
        size++;
    }

    /**
     * Pulls the highest priority element (with the lowest priority value) from the
     * queue.
     * 
     * @return The GraphNode with the highest priority.
     */
    public GraphNode pullHighestPriorityElement() {
        if (isEmpty()) {
            return null;
        }
        GraphNode min = heap[0];
        indexMap.set(heap[0], -1);
        heap[0] = heap[size - 1];
        indexMap.set(heap[0], 0);
        size--;
        heapify(0);
        return min;
    }

    /**
     * Rebalances the heap if the priority of a GraphNode changes.
     * 
     * @param node The GraphNode whose priority has changed.
     */
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

    /**
     * Checks if the priority queue is empty.
     * 
     * @return True if the queue is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Moves a node up in the heap until the heap property is restored.
     * This is typically called when a node's priority is increased.
     * 
     * @param index The index of the node to bubble up.
     */
    private void bubbleUp(int index) {
        while (index > 0 && heap[index].priority < heap[parent(index)].priority) {
            swap(index, parent(index));
            index = parent(index);
        }
    }

    /**
     * Restores the heap property by moving the node at the specified index down
     * in the heap until it is lower than its children or is a leaf.
     * 
     * @param index The index of the node to heapify.
     */
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

    /**
     * Swaps two nodes in the heap.
     * 
     * @param i The index of the first node.
     * @param j The index of the second node.
     */
    private void swap(int i, int j) {
        GraphNode temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
        indexMap.set(heap[i], i);
        indexMap.set(heap[j], j);
    }

    /**
     * Returns the index of the parent of the node at the specified position.
     * 
     * @param pos The index of the node.
     * @return The index of the parent node.
     */
    private int parent(int pos) {
        return (pos - 1) / 2;
    }

    /**
     * Returns the index of the left child of the node at the specified position.
     * 
     * @param pos The index of the node.
     * @return The index of the left child.
     */
    private int leftChild(int pos) {
        return (2 * pos) + 1;
    }

    /**
     * Returns the index of the right child of the node at the specified position.
     * 
     * @param pos The index of the node.
     * @return The index of the right child.
     */
    private int rightChild(int pos) {
        return (2 * pos) + 2;
    }

    /**
     * Doubles the size of the heap array when the current capacity is reached.
     * This is used to accommodate more elements when the heap is full.
     */
    private void resizeHeap() {
        GraphNode[] oldHeap = heap;
        capacity *= 2;
        heap = new GraphNode[capacity];
        System.arraycopy(oldHeap, 0, heap, 0, size);
    }

    /**
     * Prints the contents of the heap to the console.
     */
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

    /**
     * Gets the index of a given GraphNode in the heap.
     * 
     * @param node The GraphNode for which to find the index.
     * @return The index of the node in the heap or -1 if it is not in the heap.
     */
    public int getIndexMapValue(GraphNode node) {
        Integer index = indexMap.get(node);
        return index != null ? index : -1;
    }

    /**
     * Returns a string representation of the heap.
     * 
     * @return A string containing the IDs of the nodes in the heap.
     */
    @Override
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
