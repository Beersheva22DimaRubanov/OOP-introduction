package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class ArrayList<T> extends AbstractCollection<T> implements List<T> {
	static final int DEFAULT_CAPACITY = 16;
	private T[] array;

	private class ArrayListIterator implements Iterator<T> {
		int index = 0;
		boolean flag  = false;

		@Override
		public boolean hasNext() {
			return index < size;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			flag = true;
			return array[index++];
		}
		
		@Override
		public void remove() {
			if(!flag) {
				throw new IllegalStateException();
			}
			ArrayList.this.remove(index-1);
			flag = false;
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

	private void removeElement(int index) {
		if (size - 1 > index) {
			System.arraycopy(array, index + 1, array, index, (size - 1 - index));
		}
		size--;
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		int oldSize = size;
		int e = 0;
		for (int i = 0; i < oldSize; i++) {
			if (!predicate.test(array[i])) {
				array[e++] = array[i];
			} else {
				size--;
			}
		}
		Arrays.fill(array, size, oldSize, null);
		return oldSize > size;
	}

	@Override
	public void add(int index, T element) {
		checkIndex(index, true);
		if (size == array.length) {
			reallocate();
		}
		System.arraycopy(array, index, array, index + 1, size - index);
		array[index] = element;
		size++;
	}

	@Override
	public T remove(int index) {
		checkIndex(index, false);
		T old = array[index];
		removeElement(index);
		array[size] = null;
		return old;
	}
	
	@Override
	public int indexOf(T pattern) {
		int index = 0;
		while(index < size && !isEquals(array[index], pattern)) {
			index++;
		}
		return index < size ? index : -1;
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
		checkIndex(index, false);
		return array[index];
	}

	@Override
	public void set(int index, T element) {
		checkIndex(index, false);
		array[index] = element;
	}

	@Override
	public Iterator<T> iterator() {
		return new ArrayListIterator();
	}

}
