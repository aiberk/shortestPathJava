/**
 * Abraham Iberkleid
 * aiberkleid@brandeis.edu
 * December 9, 2023
 * PA3
 * Bugs: There are no known bugs. 
 */
package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import main.GraphNode;
import main.Entry;

class EntryTest {

	@Test
	void testEntryConstructor() {
		GraphNode key = new GraphNode("TestNode", false);
		int value = 42;

		Entry entry = new Entry(key, value);

		assertEquals(key, entry.key);
		assertEquals(value, entry.value);
		assertTrue(entry.isActive);
	}

	@Test
	void testEntryDeactivation() {
		GraphNode key = new GraphNode("TestNode", false);
		int value = 42;

		Entry entry = new Entry(key, value);

		entry.isActive = false;

		assertFalse(entry.isActive);
	}

	@Test
	void testEntryEquality() {
		GraphNode key1 = new GraphNode("TestNode1", false);
		int value1 = 42;
		Entry entry1 = new Entry(key1, value1);

		GraphNode key2 = new GraphNode("TestNode2", false);
		int value2 = 42;
		Entry entry2 = new Entry(key2, value2);

		assertEquals(entry1, entry1);
		assertNotEquals(entry1, entry2);
	}
}
