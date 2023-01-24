package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Predicate;

public interface Collection<T> extends Iterable<T> {
	boolean add(T element);

	boolean remove(T pattern);

	default boolean removeIf(Predicate<T> predicate) {
		Iterator<T> it = iterator();
		int oldSize = size();
		while (it.hasNext()) {
			T obj = it.next();
			if (predicate.test(obj)) {
				it.remove();
			}
		}
		return oldSize > size();
	}

	boolean isEmpty();

	int size();

	boolean contains(T pattern);

	/**
	 * 
	 * @param array
	 * @return array containing elements of a Collection if arr refers to memory
	 *         that enough for all elements of collection then new
	 */
//	T[] toArray(T[] array);

	default T[] toArray(T[] array) {
		int size = size();
		if (array.length < size) {
			array = Arrays.copyOf(array, size);
		}
		Iterator<T> it = iterator();
		int index = 0;
		while (it.hasNext()) {
			array[index] = it.next();
			index++;
		}
		Arrays.fill(array, size, array.length, null);
		return array;
	}
}