package telran.util.test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import telran.util.MyArrays;

public class MyArraysTest {
	@Test
	void sortTest() {
		String[] strings = { "abcd", "lmn", "zz" };
		String[] expected = { "zz", "lmn", "abcd" };
		Integer[] ar = { 3, 2, 1 };

		MyArrays.sort(strings, new StringLengthComparator());
		assertArrayEquals(expected, strings);
		MyArrays.sort(ar, (a, b) -> a - b);
	}

	@Test
	void evenOddTest() {
		Integer numbers[] = { 13, 2, -8, 47, 100, 10, 7 };
		Integer expected[] = { -8, 2, 10, 100, 47, 13, 7 };
		MyArrays.sort(numbers, new EvenOddComparator());
		assertArrayEquals(expected, numbers);
	}
	
	@Test
	void binarySearchTest() {
		Integer numbers[] = {0,1,3,4,5,6,7,8,9,23};
		int index = MyArrays.binarySearch(numbers, 3, new IntBinarySearchComparator());
		assertEquals(2, index);
		int index2 = MyArrays.binarySearch(numbers, 23, new IntBinarySearchComparator());
		assertEquals(9, index2);
		int index3 = MyArrays.binarySearch(numbers, 8, new IntBinarySearchComparator());
		assertEquals(7, index3);
		int index4 = MyArrays.binarySearch(numbers, 2, new IntBinarySearchComparator());
		assertEquals(-3, index4);
	}

}
