package telran.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.MdArray;

public class MdArrayTests {
	MdArray<Integer> mdArray;
	MdArray<String> strings;
	@BeforeEach 
	void setUp() throws Exception {
		mdArray = new MdArray<>(new Integer[] {10,5,7}, 50);
		strings = new MdArray<>(new Integer[] {3,15,7,2,10}, "hello");
	}
	
	@Test
	void forEachTest() {
		Integer[] sum = {0};
		mdArray.forEach(num -> sum[0] += num);
		assertEquals(17500, sum[0]);
		Integer[] length = {0};
		strings.forEach(str -> length[0] += str.length());
		assertEquals(31500, length[0]);
	}
	
	@Test
	void getValueTest() {
		assertEquals(50, mdArray.getValue(new Integer[] {3,4,6}));
		assertThrowsExactly(ArrayIndexOutOfBoundsException.class, 
				() -> mdArray.getValue(new Integer[] {3,4,8}));
		assertThrowsExactly(IllegalStateException.class, 
				() -> mdArray.getValue(new Integer[] {3,4}));
		assertThrowsExactly(NoSuchElementException.class, 
				() -> mdArray.getValue(new Integer[] {3,4,6,0}));
		assertEquals("hello", strings.getValue(new Integer[] {2,13,4,1,6}));
	}
	
	@Test
	void setValueTest() {
		Integer[] dim = new Integer[]{3,4,6};
		mdArray.setValue(dim, 100);
		assertEquals(100, mdArray.getValue(dim));
		assertThrowsExactly(ArrayIndexOutOfBoundsException.class, 
				() -> mdArray.getValue(new Integer[] {3,4,8}));
		assertThrowsExactly(IllegalStateException.class, 
				() -> mdArray.getValue(new Integer[] {3,4}));
		assertThrowsExactly(NoSuchElementException.class, 
				() -> mdArray.getValue(new Integer[] {3,4,6,0}));
	}
	
	@Test
	void toArrayTest() {
		Integer[] ar = mdArray.toArray(new Integer[0]);
		assertEquals(350, ar.length);
		for(int i =0; i<ar.length; i++) {
			assertEquals(50, ar[i]);
		}
		String[] str = strings.toArray(new String[0]);
		assertEquals(6300, str.length);
		for(int i =0; i<str.length; i++) {
			assertEquals("hello", str[i]);
		}
	}
}
