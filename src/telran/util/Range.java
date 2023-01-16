package telran.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Range implements Iterable<Integer>, Comparable<Range> {
	private static final String EXCEPTION_MESSAGE = "max value must be greater than min value";
	int min;
	int max;

	private class RangeIterator implements Iterator<Integer> {
		int current = min;

		@Override
		public boolean hasNext() {
			return current < max;
		}

		@Override
		public Integer next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return current++;
		}

	}

	@Override
	public Iterator<Integer> iterator() {
		return new RangeIterator();
	}

	public Range(int min, int max) {
		if (max <= min) {
			throw new IllegalArgumentException("max value must be greater than min value");
		}
		this.min = min;
		this.max = max;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		if (max <= min) {
			throw new IllegalArgumentException(EXCEPTION_MESSAGE);
		}
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		if (max <= min) {
			throw new IllegalArgumentException(EXCEPTION_MESSAGE);
		}
		this.max = max;
	}

	public boolean checkNumber(int number) {
		return number >= min && number < max;
	} 

	@Override
	public int compareTo(Range o) {
		int thisLength = max - min;
		int oLength = o.max - o.min;
		return Integer.compare(thisLength, oLength);
	}

}
