package telran.util.test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

import telran.util.*;

public class RangeIteratorTest {
	Integer numbers[] = { 1, 2, 3, 4, 5 };

	@Test
	void test() {
		Range range = new Range (1,6);
		ArrayList<Integer> list = new ArrayList<>();
		Iterator<Integer> it = range.iterator();
		while(it.hasNext()) {
			list.add(it.next());
		}
		assertArrayEquals(numbers, list.toArray(new Integer[0]));
	}
}
