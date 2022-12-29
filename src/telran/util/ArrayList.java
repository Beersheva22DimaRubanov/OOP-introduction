package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Predicate;

public class ArrayList<T> implements List<T> {
	static final int DEFAULT_CAPACITY = 16;
	private T[] array;
	private int size;
	private class ArrayListIterator implements Iterator<T>{

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub
			return null;
		}

		
	}

	public ArrayList(int capacity) {
		array = (T[]) new Object[capacity];
	}

	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}

	@Override
	public boolean add(T element) {
		if (size == array.length) {
			reallocate();
		}
		array[size++] = element;
		return true;
	}

	private void reallocate() {
		array = Arrays.copyOf(array, array.length * 2);
	}

	@Override
	public boolean remove(T pattern) {
		boolean res = false;
		int index = indexOf(pattern);
		if (index > -1) {
			res = true;
			remove(index);
		}
		return res;
	}

	private void removeElement(int index) {
		if (size - 1 > index) {
			System.arraycopy(array, index + 1, array, index, (size - 1 - index));
		}
		size--;
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		//FIXME - write implementation of O[N]. Hint working with only indexes
		int oldSize = size;
		for (int i = 0; i < size; i++) {
			if (predicate.test(array[i])) {
				removeElement(i);
				i--;
			}
		}
		return oldSize > size;
	}

	@Override
	public boolean isEmpty() {
	
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean contains(T pattern) {
		boolean res = false;
		int index = 0;
		while (index < size && !res) {
			if (array[index].equals(pattern)) {
				res = true;
			}
			index++;
		}
		return res;
	}

	@Override
	public T[] toArray(T[] ar) {
		if (ar.length < size) {
			ar = Arrays.copyOf(ar, size);
		}
		System.arraycopy(array, 0, ar, 0, size);

		if (ar.length > size) {
			for (int i = size; i < ar.length; i++) {
				ar[i] = null;
			}
		}
		return ar;
	}

	@Override
	public void add(int index, T element) {
		if (size == array.length) {
			reallocate();
		}
		System.arraycopy(array, index, array, index + 1, size - index);
		array[index] = element;
		size++;
	}

	@Override
	public T remove(int index) {
		T old = array[index];
		removeElement(index);
		array[size] = null;
		return old;
	}

	@Override
	public int indexOf(T pattern) {
		int index = 0;
		while (index < size && !isEquals(array[index], pattern)) {
			index++;
		}
		return index < size ? index : -1;
	}

	private boolean isEquals(T element, T pattern) {
		return pattern == null ? element == pattern : element.equals(pattern);
	}

	@Override
	public int lastIndexOf(T pattern) {
		int res = -1;
		for (int i = 0; i < size; i++) {
			if (isEquals(array[i], pattern)) {
				res = i;
			}
		}
		return res;
	}

	@Override
	public T get(int index) {
		return isInSize(index) ? array[index] : (T) new IndexOutOfBoundsException();
	}

	@Override
	public void set(int index, T element) {
		if (isInSize(index)) {
			array[index] = element;
		}
	}

	private boolean isInSize(int index) {
		return index <= size && index >= 0;
	}

	@Override
	public Iterator<T> iterator() {
		return new ArrayListIterator();
	}

}
