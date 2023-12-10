package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.MinPriorityQueue;
import main.GraphNode;

public class MinPriorityQueueTest {
	private MinPriorityQueue minPriorityQueue;

	@BeforeEach
	public void setUp() {
		minPriorityQueue = new MinPriorityQueue(10); // Adjust the capacity as needed
	}

	@Test
	public void testInsertAndPullHighestPriorityElement() {
		GraphNode node1 = new GraphNode("A", false);
		node1.priority = 3;
		GraphNode node2 = new GraphNode("B", false);
		node2.priority = 1;
		GraphNode node3 = new GraphNode("C", false);
		node3.priority = 2;

		minPriorityQueue.insert(node1);
		minPriorityQueue.insert(node2);
		minPriorityQueue.insert(node3);

		assertEquals(node2, minPriorityQueue.pullHighestPriorityElement()); // node with priority 1
		assertEquals(node3, minPriorityQueue.pullHighestPriorityElement()); // node with priority 2
		assertEquals(node1, minPriorityQueue.pullHighestPriorityElement()); // node with priority 3
	}

	@Test
	public void testRebalanceBubbleUp() {
		GraphNode node1 = new GraphNode("A", false);
		GraphNode node2 = new GraphNode("B", false);
		GraphNode node3 = new GraphNode("C", false);

		// Set initial priorities
		node1.priority = 2;
		node2.priority = 3;
		node3.priority = 4;

		minPriorityQueue.insert(node1);
		minPriorityQueue.insert(node2);
		minPriorityQueue.insert(node3);

		// Check the initial order (should be "A, B, C")
		assertEquals("A, B, C", minPriorityQueue.toString());

		// Modify the priority of node2
		node2.priority = 1; // Lower priority

		// Rebalance node2
		minPriorityQueue.rebalance(node2);

		// Check the updated order (should be "B, A, C" because node2 has lower
		// priority)
		assertEquals("B, A, C", minPriorityQueue.toString());
	}

	@Test
	public void testIsEmpty() {
		assertTrue(minPriorityQueue.isEmpty());

		GraphNode node1 = new GraphNode("A", false);
		minPriorityQueue.insert(node1);

		assertFalse(minPriorityQueue.isEmpty());
	}

	@Test
	public void testIndexMapOnInsertion() {
		GraphNode node1 = new GraphNode("A", false);
		GraphNode node2 = new GraphNode("B", false);
		GraphNode node3 = new GraphNode("C", false);

		minPriorityQueue.insert(node1);
		minPriorityQueue.insert(node2);
		minPriorityQueue.insert(node3);

		// Assert that the indexMap correctly tracks the indices
		assertEquals(0, minPriorityQueue.getIndexMapValue(node1));
		assertEquals(1, minPriorityQueue.getIndexMapValue(node2));
		assertEquals(2, minPriorityQueue.getIndexMapValue(node3));
	}

	@Test
	public void testIndexMapOnPriorityChange() {
		GraphNode node1 = new GraphNode("A", false);
		node1.priority = 3;
		GraphNode node2 = new GraphNode("B", false);
		node2.priority = 1;

		minPriorityQueue.insert(node1);
		minPriorityQueue.insert(node2);

		// Change priority and rebalance
		node1.priority = 0;
		minPriorityQueue.rebalance(node1);

		// Assert that the indexMap correctly updates the indices
		assertEquals(0, minPriorityQueue.getIndexMapValue(node1));
		assertEquals(1, minPriorityQueue.getIndexMapValue(node2));
	}

	@Test
	public void testIndexMapOnRemoval() {
		GraphNode node1 = new GraphNode("A", false);
		node1.priority = 2;
		GraphNode node2 = new GraphNode("B", false);
		node2.priority = 1;

		minPriorityQueue.insert(node1);
		minPriorityQueue.insert(node2);

		minPriorityQueue.pullHighestPriorityElement(); // Removes node2

		// Assert that node2 is marked as removed in indexMap
		assertEquals(-1, minPriorityQueue.getIndexMapValue(node2));
		// Assert that node1's index is still correctly tracked
		assertEquals(0, minPriorityQueue.getIndexMapValue(node1));
	}

}
