package test;

import org.junit.jupiter.api.Test;

import main.CustomLinkedList;

import static org.junit.jupiter.api.Assertions.*;

class CustomLinkedListTest {

    @Test
    void testAddFirstAndSize() {
        CustomLinkedList list = new CustomLinkedList();
        list.addFirst("Test1");
        list.addFirst("Test2");

        assertEquals(2, list.size(), "Size should be 2 after adding two elements.");
    }

    @Test
    void testToArray() {
        CustomLinkedList list = new CustomLinkedList();
        list.addFirst("Test1");
        list.addFirst("Test2");
        
        String[] expectedArray = {"Test2", "Test1"};
        assertArrayEquals(expectedArray, list.toArray(), "toArray should return an array with all elements in correct order.");
    }
}
