package telran.util;

import java.util.Arrays;
import java.util.Iterator;


public abstract class AbstractCollection<T> implements Collection<T> {
	protected int size;

	protected boolean isEquals(T element, T pattern) {
		return pattern == null ? element == pattern : element.equals(pattern);
	}
	
	@Override
	public int size() {
		return size;
	}
	
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	@Override
	public T[] toArray(T[] array) {
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
