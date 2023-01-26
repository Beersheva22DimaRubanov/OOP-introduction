package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.Sorted;
import telran.util.TreeSet;

public class TreeSetTest extends SortedTest {

	TreeSet<Integer> tree;

	@BeforeEach
	@Override
	void setUp() throws Exception {
		collection = new TreeSet<>();
		super.setUp();
		tree = (TreeSet<Integer>) collection;
	}

	@Test
	void displayRotatedTest() {
		System.out.println("*****************************************************");
		tree.displayTreeRotated();
	}

	@Test
	void heightTreeTest() {
		assertEquals(4, tree.height());
	}

	@Test
	void widthTreeTest() {
		assertEquals(4, tree.width());
	}

	@Test
	void inversionTest() {
//		{10, 100, -5, 134, 280, 120, 15}
		tree.inversion();
		Integer expected[] = { 280, 134, 120, 100, 15, 10, -5 };
		Integer actual[] = new Integer[expected.length];
		int index = 0;
		for (Integer num : tree) {
			actual[index++] = num;
		}
		assertArrayEquals(expected, actual);
		assertTrue(tree.contains(280));
	}

	@Override
	protected Sorted<Integer> getSortedCollection() {
		return new TreeSet<>();
	}

	@Test
	void balanceTest() {
		tree.balance();
		assertEquals(3, tree.height());
		assertEquals(4, tree.width());
		System.out.println("********************** balanced tree ***********************");
		tree.displayTreeRotated();
	}
	
//	@Test
//	void perfomanceTestSortedAddingWithBalance() {
//		TreeSet<Integer> sorted = new TreeSet<Integer>();
//		IntStream.range(0, N_ELEMENTS).forEach(i -> sorted.add(i));
//		sorted.balance();
//		runPerformanceTest(sorted);
//	}

}
