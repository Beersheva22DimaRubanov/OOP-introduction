package telran.util.test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.util.MyArrays;

public class MyArraysTest {
	Integer numbers[] = { 13, 2, -8, 47, 100, 10, 7, -7 };
	String strings[] = { "ab", "abm", "abmb", "abmbc"}; 
	Comparator<Integer> evenOddComparator = this::evenOddCompare;

	@Test
	@Disabled
	void sortTest() {
		String[] strings = { "abcd", "lmn", "zz" };
		String[] expected = { "zz", "lmn", "abcd" };
		Integer[] ar = { 3, 2, 1 };

		MyArrays.sort(strings, new StringLengthComparator());
		assertArrayEquals(expected, strings);
		MyArrays.sort(ar, (a, b) -> a - b);
	}

	@Test 
	@Disabled
	void evenOddTest() {
		Integer expected[] = { -8, 2, 10, 100, 47, 13, 7, -7 };

		MyArrays.sort(numbers, evenOddComparator );
		assertArrayEquals(expected, numbers);
	}

	@Test
//	@Disabled
	void binarySearchTest() {
		Integer sorted[] = { 0, 1, 3, 4, 5, 6, 7, 8, 9, 23 };
		int index = MyArrays.binarySearch(sorted, 3, new IntBinarySearchComparator());
		assertEquals(2, index);
		int index2 = MyArrays.binarySearch(sorted, 23, new IntBinarySearchComparator());
		assertEquals(9, index2);
		int index3 = MyArrays.binarySearch(sorted, 8, new IntBinarySearchComparator());
		assertEquals(7, index3);
		int index4 = MyArrays.binarySearch(sorted, 2, new IntBinarySearchComparator());
		assertEquals(-3, index4);
	}

	@Test
	void filterTest() {
		int dividor = 2;
		String subStr = "m";
		Predicate<Integer> predEven = t -> t % dividor == 0;
		Predicate<String> predSubstr = s -> s.contains(subStr);
		String expectedStr[] = { "abm", "abmb", "abmbc" };
		Integer expected[] = { 2, -8, 100, 10 };
		assertArrayEquals(expectedStr, MyArrays.filter(strings, predSubstr));
		assertArrayEquals(expected, MyArrays.filter(numbers, predEven));
	}
	
	@Test
	void removeIfTest() {
		String stringsArray[] = { "ab", "abm", "abmb", "abmbc", null }; 
		String subStr = "m";
		Predicate<String> predSubstr = s -> s.contains(subStr);
		String expected[] = {"ab"};
		assertArrayEquals(expected, MyArrays.removeIf(strings, predSubstr));
		assertFalse(MyArrays.contains(stringsArray, "cx"));
	}
	
	@Test
	void removePepeatedTest() {
		Integer SortNumbers[] = { 13, 2, -8, 7, 13, 13, 7, 47, 100, 10, 7, -7, 10 };
		Integer[] expected = { 13, 2, -8, 7, 47, 100, 10, -7 };
 		assertArrayEquals(expected, MyArrays.removeRepeated(SortNumbers));
	}
	
	int evenOddCompare(Integer o1, Integer o2) {
		int res = Math.abs(o1) % 2 - Math.abs(o2) % 2;
		if (res == 0) {
			res = o1 % 2 != 0 ? Integer.compare(o2, o1) : o1 - o2;
		}
		return res; 
	}
}
