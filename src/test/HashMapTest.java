package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.UUID;
import main.GraphNode;
import main.HashMap;

public class HashMapTest {

	private HashMap hashMap;

	@BeforeEach
	void setUp() {
		hashMap = new HashMap(16);
	}

	@Test
	void testSet() {
		GraphNode key = new GraphNode(UUID.randomUUID().toString(), false);
		int value = 42;

		hashMap.set(key, value);

		assertTrue(hashMap.hasKey(key));
		assertEquals(value, hashMap.get(key));
	}

	@Test
	void testUpdateValue() {
		GraphNode key = new GraphNode(UUID.randomUUID().toString(), false);
		int originalValue = 42;
		int updatedValue = 100;

		hashMap.set(key, originalValue);
		hashMap.set(key, updatedValue);

		assertTrue(hashMap.hasKey(key));
		assertEquals(updatedValue, hashMap.get(key));
	}

	@Test
	void testHasKey() {
		GraphNode key1 = new GraphNode(UUID.randomUUID().toString(), false);
		GraphNode key2 = new GraphNode(UUID.randomUUID().toString(), false);

		hashMap.set(key1, 42);

		assertTrue(hashMap.hasKey(key1));
		assertFalse(hashMap.hasKey(key2));
	}

}
