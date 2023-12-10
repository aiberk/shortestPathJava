package main;

public class CustomLinkedList {
    private LinkedListNode head;

    public CustomLinkedList() {
        this.head = null;
    }

    /**
     * Adds an element to the start of the list.
     *
     * @param data The data to add to the list.
     */
    public void addFirst(String data) {
        LinkedListNode newNode = new LinkedListNode(data);
        newNode.next = head;
        head = newNode;
    }

    /**
     * Returns an array representation of the linked list.
     *
     * @return Array of list's elements.
     */
    public String[] toArray() {
        int size = size();
        String[] array = new String[size];
        int i = 0;
        LinkedListNode current = head;
        while (current != null) {
            array[i++] = current.data;
            current = current.next;
        }
        return array;
    }

    /**
     * Calculates the size of the linked list.
     *
     * @return The size of the list.
     */
    public int size() {
        int count = 0;
        LinkedListNode current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
}
