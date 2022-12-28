package telran.util;

import java.util.Arrays;
import java.util.function.Predicate;

public class ArrayList<T> implements List<T> {
	static final int DEFAULT_CAPACITY = 16;
	private T[] array;
	private int size;
	java.util.ArrayList<String> arr = new java.util.ArrayList<String>();

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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		// TODO Auto-generated method stub
		return false;
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public T[] toArray(T[] array) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(int index, T element) {
		// TODO Auto-generated method stub

	}

	@Override
	public T remove(int index) {
		// TODO Auto-generated method stub
		return null;
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
		for(int i =0; i<array.length; i++) {
			if(isEquals(array[i], pattern)){
				res = i;
			}
		}
		return res;
	}

	@Override
	public T get(int index) {
		return isInSize(index)? array[index]:(T)new IndexOutOfBoundsException();
	}

	@Override
	public void set(int index, T element) {
		if(isInSize(index)) {
			array[index] = element;
		}
	}

	private boolean isInSize(int index) {
		return  index<= size && index>0;
	}

}
