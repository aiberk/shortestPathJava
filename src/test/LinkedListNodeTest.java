package test;

import org.junit.jupiter.api.Test;

import main.LinkedListNode;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListNodeTest {

    @Test
    void testNodeCreation() {
        String testData = "Test";
        LinkedListNode node = new LinkedListNode(testData);

        assertEquals(testData, node.data, "Data should match the constructor argument.");
        assertNull(node.next, "Next should be null in a new node.");
    }
}
