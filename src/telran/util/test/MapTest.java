package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.Collection;
import telran.util.Map;
import telran.util.Map.Entry;
import telran.util.Set;

public class MapTest {
	Map<Integer, String> map;

	@BeforeEach
	void setUp() throws Exception {
		map.put(1, "One");
		map.put(2, "Two");
		map.put(3, "Three");
	}
	
	@Test
	void getTest() {
		assertEquals("One", map.get(1));
		assertNull(map.get(1000));
	}
	
	@Test
	void putTest() {
		assertEquals("One", map.put(1, "Cxd"));
		assertEquals("Cxd", map.get(1));
		assertNull(map.put(4, "Four"));
		assertEquals("Four", map.get(4));
	}
	
	@Test
	void putIfAbsentTest() {
		assertEquals("One", map.putIfAbsent(1, "cxd"));
		assertEquals("One", map.get(1));
		assertNull(map.putIfAbsent(5, "Five"));
		assertEquals("Five", map.get(5));
	}
	
	@Test
	void getOrDefaultTest() {
		assertEquals("Two", map.getOrDefault(2, "One"));
		assertEquals("Five", map.getOrDefault(5, "Five"));
	}
	
	@Test
	void containsKeyTest() {
		assertTrue(map.containsKey(1));
		assertFalse(map.containsKey(6));
	}
	
	@Test
	void containsValueTest() {
		assertTrue(map.containsValue("One"));
		assertFalse(map.containsValue("Six"));
	}
	
	@Test
	void valuesTest() {
		Collection<String> list = map.values();
		String[] expected = {"One", "Two", "Three"};
		assertArrayEquals(expected, list.toArray(new String[0]));
	}
	
	@Test
	void keySetTest() {
		Set<Integer> keys = map.keySet();
		Integer expected[] = {1,2,3};
		assertArrayEquals(expected, keys.toArray(new Integer[0]));
	}
	
	@Test
	void entrySetTest() {
		Set<Entry<Integer, String>> entries = map.entrySet();
		int index = 0;
		String[] expected = {"One", "Two", "Three"};
		for(Entry<Integer, String> entry: entries) {
			assertEquals(index+1, entry.getKey());
			assertEquals(expected[index], entry.getValue());
			index ++;
		}
	}
	
	@Test
	void removeTest() {
		assertEquals("One", map.remove(1));
		assertNull(map.get(1));
		assertNull(map.remove(5));
	}
}
