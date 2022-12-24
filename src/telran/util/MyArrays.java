package telran.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

public class MyArrays {
	static public <T> void sort(T[] objects, Comparator<T> comparator) {
		int length = objects.length;
		do {
			length--;
		} while (moveMaxAtEnd(objects, length, comparator));
	}

	static public <T> int binarySearch(T[] array, T key, Comparator<T> comp) {
		int right = array.length - 1;
		int left = 0;
		int middle = array.length / 2;
		while (right >= left && comp.compare(key, array[middle]) != 0) {
			if (comp.compare(key, array[middle]) <= 0) {
				right = middle - 1;
			} else {
				left = middle + 1;
			}
			middle = (right + left) / 2;
		}
		return left < array.length && comp.compare(key, array[middle]) == 0 ? middle : -left - 1;
	}

	private static <T> boolean moveMaxAtEnd(T[] objects, int length, Comparator<T> comp) {
		boolean res = false;
		for (int i = 0; i < length; i++) {
			if (comp.compare(objects[i], objects[i + 1]) > 0) {
				swap(objects, i, i + 1);
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

	public static <T> T[] filter(T[] array, Predicate<T> pred) {
		int countPredicate = getCountPredicate(array, pred);
		int index = 0;
		T[] res = Arrays.copyOf(array, countPredicate);
		for (T element : array) {
			if (pred.test(element)) {
				res[index++] = element;
			}
		}
		return res;
	}

	private static <T> int getCountPredicate(T[] array, Predicate<T> pred) {
		int res = 0;
		for (T element : array) {
			if (pred.test(element)) {
				res++;
			}
		}
		return res;
	}

	public static <T> T[] removeIf(T[] array, Predicate<T> predicate) {
		return filter(array, predicate.negate());
	}

	public static <T> T[] removeRepeated(T[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = i + 1; j < array.length; j++) {
				if (array[i] == array[j]) {
					array[j] = null;
				}
			}
		}
		return contains(array, null) ? removeIf(array, a -> a == null) : array;
	}

	public static <T> boolean contains(T[] array, T pattern) {
		boolean res = false;
		int index = 0;
		while (!res && index < array.length) {
			if (array[index] == pattern) {
				res = true;
			}
			index++;
		}
		return res;
	}
}