package telran.util;

import java.util.Arrays;
import java.util.function.Predicate;

public class ArrayList<T> implements List<T> {
	static final int DEFAULT_CAPACITY = 16;
	private T[] array;
	private int size;

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
		int index = 0;
		while (index < size && !array[index].equals(pattern)) {
			if (array[index].equals(pattern)) {
				removeElement(index);
			}
			index++;
		}
		return false;
	}

	private void removeElement(int index) {
		if (size - 1 > index) {
			System.arraycopy(array, index+1, array, index, (size - 1 - index));
		}
		size--;
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		for(int i = 0; i<size; i++) {
			if(predicate.test(array[i])) {
				removeElement(i);
				i--;
			}
		}
		return true;
	}

	@Override
	public boolean isEmpty() {
		int index = 0;
		boolean res = true;
		while (index < array.length && !res) {
			if (array[index] != null) {
				res = false;
			}
			index++;
		}
		return res;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean contains(T pattern) {
		boolean res = false;
		int index = 0;
		while(index < size && !res) {
			if(array[index].equals(pattern)) {
				res = true;
			}
			index++;
		}
		return res;
	}

	@Override
	public T[] toArray(T[] ar) {
		if(ar.length < size) {
			ar = Arrays.copyOf(ar, size);
		} 
		System.arraycopy(array, 0, ar, 0, size);

		if(ar.length > size) {
			for(int i = size; i < ar.length; i ++) {
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
		System.arraycopy(array, index, array, index+1, size - index);
		array[index] = element;
		size++;
	}

	@Override
	public T remove(int index) {
		T old = null;
		if (index < size) {
			old = array[index];
			removeElement(index);
		}
		return old;
	}

	@Override
	public int indexOf(T pattern) {
		int res = -1;
		int index = 0;
		while (index < array.length && res < 0) {
			if (isEquals(array[index], pattern)) {
				res = index;
			}
		}
		return res;
	}

	private boolean isEquals(T element, T pattern) {
		return pattern == null ? element == pattern : element.equals(pattern);
	}

	@Override
	public int lastIndexOf(T pattern) {
		int res = -1;
		for (int i = 0; i < array.length; i++) {
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

}
