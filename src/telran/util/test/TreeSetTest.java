package telran.util.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.HashSet;
import telran.util.LinkedList;
import telran.util.TreeSet;

public class TreeSetTest extends SetTest{
	Random random = new Random();
	private static final int N_RUNS = 10;
	private static final int N_NUMBERS = 100;

	@BeforeEach
	@Override
	void setUp() throws Exception {
		collection = new TreeSet<>();
		super.setUp();
	}
	
	
	@Override
	@Test
	void testIterator() {
		TreeSet<Integer> set = new TreeSet<>();
		Integer[] bigArray = getRandomArray();
		fillSet(set, bigArray);
		Arrays.sort(bigArray, 0, set.size());
		
		int index = 0;
		for(Integer el : set) {
			assertEquals(el, bigArray[index]);
			index++;
		}
	}
	
	private Integer[] getRandomArray() {
		Integer result[] = new Integer[N_NUMBERS];
		for(int i = 0; i<N_NUMBERS; i++) {
//			result[i] = random.nextInt();
			result[i] = i + 10;
		}
		return result;
	}

	private void fillSet(TreeSet<Integer> set, Integer[] numbers) {
		for(Integer num: numbers) {
			set.add(num);
		}
	}

}
