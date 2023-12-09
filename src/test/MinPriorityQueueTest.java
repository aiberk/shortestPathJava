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

	// TODO Test the MinPriorityQueue class with HashMaps implementation
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
	public void testRebalancePercolateDown() {
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

		// Modify the priority of node1 to make it higher than node2
		node1.priority = 4; // Higher priority

		// Rebalance node1
		minPriorityQueue.rebalance(node1);

		// Check the updated order (should be "B, C, A" because node1 has higher
		// priority)
		assertEquals("B, C, A", minPriorityQueue.toString());
	}

	@Test
	public void testIsEmpty() {
		assertTrue(minPriorityQueue.isEmpty());

		GraphNode node1 = new GraphNode("A", false);
		minPriorityQueue.insert(node1);

		assertFalse(minPriorityQueue.isEmpty());
	}

	// Add more test methods as needed...

}
