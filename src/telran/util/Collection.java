package telran.util;

import java.util.Iterator;
import java.util.function.Predicate;

public interface Collection<T> extends Iterable<T> {
	boolean add (T element);
	boolean remove (T pattern);
	default boolean removeIf(Predicate<T> predicate) {
		Iterator<T> it = iterator();
		int oldSize = size();
		while(it.hasNext()) {
			T obj = it.next();
			if(predicate.test(obj)) {
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
	 * @return array containing elements of a Collection
	 * if arr refers to memory that enough for all elements of collection then new 
	 */
	T[] toArray(T[] array);
}
