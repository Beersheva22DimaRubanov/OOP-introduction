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
	
	
}
