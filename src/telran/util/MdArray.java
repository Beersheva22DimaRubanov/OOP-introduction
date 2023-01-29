package telran.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class MdArray<T> {
	private MdArray<T>[] array;
	private T value;

	public MdArray(Integer[] integers, T value) {
		this(integers, 0, value);
	}

	public MdArray(Integer[] dimensions, int firstDim, T value) {
		if (firstDim == dimensions.length) {
			this.value = value;
			array = null;
		} else {
			this.value = null;
			this.array = new MdArray[dimensions[firstDim]];
			for (int i = 0; i < array.length; i++) {
				array[i] = new MdArray<T>(dimensions, firstDim + 1, value);
			}
		}
	}

	public T getValue(Integer[] dim) {
		MdArray<T> current = getCurrentArray(dim);
		return current.value;
	}

	private MdArray<T> getCurrentArray(Integer[] dim) {
		MdArray<T> current = null;
		if (dim != null) {
			current = array[dim[0]];
			int index = 1;
			while (index < dim.length && current.array != null) {
				if (current.array.length < dim[index] - 1) {
					throw new IndexOutOfBoundsException();
				}
				current = current.array[dim[index]];
				index++;
			}
			if (current.array != null) {
				throw new IllegalStateException();
			} else if (index < dim.length && current.array == null) {
				throw new NoSuchElementException();
			}
		}
		return current;
	}

	public void setValue(Integer[] dim, T value) {
		MdArray<T> current = getCurrentArray(dim);
		current.value = value;
	}

	public void forEach(Consumer<T> cons) {
		forEach(cons, array);
	}

	private void forEach(Consumer<T> cons, MdArray[] current) {
		for (int i = 0; i < current.length; i++) {
			if (current[i].value != null) {
				cons.accept((T) current[i].value);
			} else if (current[i].array != null) {
				forEach(cons, current[i].array);
			}
		}
	}

	public T[] toArray(T[] ar) {
		ArrayList<T> list = new ArrayList<>();
		forEach(x -> list.add(x));
		if (list.size() > ar.length) {
			ar = Arrays.copyOf(ar, list.size());
		}
		int index = 0;
		for (T obj : list) {
			ar[index++] = obj;
		}
		return ar;
	}
}
