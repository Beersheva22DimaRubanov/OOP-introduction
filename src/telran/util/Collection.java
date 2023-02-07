package telran.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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

	default Stream<T> stream() {
		return StreamSupport.stream(this.spliterator(), false);
	}

	default Stream<T> parallelStream() {
		return StreamSupport.stream(this.spliterator(), true);
	}

	default T[] toArrayShuffling(T[] array) {
		T[] arr = toArray(array);
		T[] res = Arrays.copyOf(arr, arr.length);
		int[] index = {0};
		new Random().ints(0, res.length).distinct().limit(res.length)
			.forEach(i -> res[index[0]++] = arr[i]);
		return res;
	}
}
