package telran.util.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.LinkedList;

public class LinkedListTest extends ListTest {
	@BeforeEach
	@Override
	void setUp() throws Exception {
		collection = new LinkedList<>();
		super.setUp();
	}
	
	@Test
	void isLoopTest() {
		LinkedList<Integer> list = (LinkedList<Integer>) collection;
		assertFalse(list.hasLoop());
		list.setNext(5, 0);
		assertTrue(list.hasLoop());
		assertThrowsExactly(IllegalArgumentException.class, () -> list.setNext(1, 4));
	}
	
}
