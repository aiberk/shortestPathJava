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
	public void testRebalance() {
		GraphNode node1 = new GraphNode("A", false);
		GraphNode node2 = new GraphNode("B", false);

		minPriorityQueue.insert(node1);
		minPriorityQueue.insert(node2);

		// Modify the priority of node2 (change weights, etc.)
		node2.setNorthWeight(3); // Adjust this according to your node structure
		minPriorityQueue.rebalance(node2);

		assertEquals(node2, minPriorityQueue.pullHighestPriorityElement());
		assertEquals(node1, minPriorityQueue.pullHighestPriorityElement());
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
