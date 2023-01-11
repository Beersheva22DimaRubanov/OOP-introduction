package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashSet<T> extends AbstractCollection<T> implements Set<T> {
	private static final int DEFAULT_TABLE_SIZE = 16;
	private static final float DEFAULT_FACTOR = 0.75f;
	private List<T>[] hashTable;
	private float factor;

	private class HashSetIterator implements Iterator<T> {
		int index = 0;
		Iterator it = null;
		boolean flag = false;
		private Iterator it2;

		@Override
		public boolean hasNext() {
			return  index< hashTable.length;
		}
		
		private Iterator getIterator(){
			while(hashTable[index] == null && index < hashTable.length) {
				index++;
			}
			return hashTable[index].iterator();
		}
		

		@Override
		public T next() {
			boolean isCreated = false;
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			flag = true;
			if(hashTable[index] == null){
				it = getIterator();
			} else if(it == null) {
				it = hashTable[index].iterator();
			}
			T obj = (T)it.next();
			it2 = it;
			if(!it.hasNext()) {
				index++;
				it = null;
			}
			return obj;
		}

		@Override
		public void remove() {
			if (!flag) {
				throw new IllegalStateException();
			}
			it2.remove();
			if(hashTable[index-1] != null && hashTable[index-1].isEmpty()) {
				hashTable[index-1] = null;
			}
			size--;
			flag = false;
		}
	}

	@SuppressWarnings("unchecked")
	public HashSet(int tableSize, float factor) {
		if (tableSize < 1) {
			throw new IllegalArgumentException("Wrong initial size of Hash Table");
		}
		if (factor < 0.3 || factor > 1) {
			throw new IllegalArgumentException("Wrong factor value");
		}
		hashTable = new List[tableSize];
		this.factor = factor;
	}

	public HashSet() {
		this(DEFAULT_TABLE_SIZE, DEFAULT_FACTOR);
	}

	@Override
	public boolean add(T element) {
		if (size >= hashTable.length * factor) {
			tableRecreation();
		}
		int index = getHashIndex(element);
		List<T> list = hashTable[index];
		boolean res = false;
		if (list == null) {
			list = new LinkedList<>();
			hashTable[index] = list;
		}
		if (!list.contains(element)) {
			res = true;
			list.add(element);
			size++;
		}

		return res;
	}

	private void tableRecreation() {
		HashSet<T> hashSet = new HashSet<>(hashTable.length * 2, factor);
		for (List<T> list : hashTable) {
			if (list != null) {
				for (T obj : list) {
					hashSet.add(obj);
				}
			}
		}
		hashTable = hashSet.hashTable;

	}

	private int getHashIndex(T element) {

		return Math.abs(element.hashCode()) % hashTable.length;
	}

	@Override
	public boolean remove(T pattern) {
		int index = getHashIndex(pattern);
		boolean res = false;
		if (hashTable[index] != null) {
			res = hashTable[index].remove(pattern);
			if (res) {
				size--;
				if (hashTable[index].isEmpty()) {
					hashTable[index] = null;
				}
			}
		}

		return res;
	}

	@Override
	public boolean contains(T pattern) {
		boolean res = false;
		int index = getHashIndex(pattern);
		if (hashTable[index] != null) {
			res = hashTable[index].contains(pattern);
		}
		return res;
	}

	@Override
	public Iterator<T> iterator() {
		return new HashSetIterator();
	}

}