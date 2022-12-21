package telran.util;

import java.util.Comparator;

public class MyArrays {
	static public <T> void sort(T[] objects, Comparator<T> comparator) {
		int length = objects.length;
		do {
			length--;
		} while(moveMaxAtEnd(objects, length, comparator));
	}
	
	static public < T> int binarySearch(T[] array, Integer searchedNumber, Comparator <T> comp) {
		int right = array.length -1;
		int left = 0;
		int middle = array.length/2;
		while(right>=left && comp.compare((T) searchedNumber, array[left]) != 0) {
			if(comp.compare((T)searchedNumber, array[middle]) <= 0) {
				right = middle-1;
			} else {
				left = middle+1;
			}
			middle = (right + left)/2;
		}
		return  left < array.length && 
				comp.compare((T)searchedNumber, array[left])==0 ? left : -left - 1;
	}

	private static<T> boolean moveMaxAtEnd(T[] objects, int length, Comparator<T> comp) {
		boolean res = false;
		for(int i = 0; i<length; i++) {
			if(comp.compare(objects[i], objects[i+1]) > 0) {
				swap(objects, i, i+1);
				res = true;
			}
		}
		return res;
	}

	private static <T> void swap(T[] objects, int i, int j) {
		T tmp = objects[i];
		objects[i] = objects[j];
		objects[j] = tmp;
 	}
}
