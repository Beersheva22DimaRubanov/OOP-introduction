package telran.util;

import java.util.function.Predicate;

public interface Collection<T> {
	boolean add (T element);
	boolean remove (T pattern);
	boolean removeIf(Predicate<T> predicate);
	boolean isEmpty();
	int size();
	boolean contains(T pattern);
	/**
	 * 
	 * @param array
	 * @return array containing elements of a Collection
	 * if arr refers to memory that enough for all elements of collection then new 
	 */
	T[] toArray(T[] array);
}
