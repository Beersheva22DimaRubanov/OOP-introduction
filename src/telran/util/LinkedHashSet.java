package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedHashSet<T> extends AbstractCollection<T> implements Set<T> {
	HashMap hashMap;

	public LinkedHashSet() {
		this.hashMap = new HashMap<>();
	}

	private static class Node<T> {
		T obj;
		Node<T> prev;
		Node<T> next;
		
		Node(T obj) {
			this.obj = obj;
		}
	}

	private Node<T> head;
	private Node<T> tail;

	private class LinkedHashSetIterator implements Iterator<T> {
		Node<T> current = head;
		boolean flNext = false;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			T res = current.obj;
			current = current.next;
			flNext = true;
			return res;
		}

		@Override
		public void remove() {
			if (!flNext) {
				throw new IllegalStateException();
			}
			Node<T> removedNode = current == null ? tail : current.prev;
			LinkedHashSet.this.remove(removedNode.obj);
			flNext = false;
		}

	}

	@Override
	public boolean add(T element) {
		boolean res = false;
		Node<T> node = new Node(element);
		if (hashMap.put(element, node) == null) {
			size++;
			res = true;
			if (head == null) {
				head = tail = node;
			} else {
				tail.next = node;
				node.prev = tail;
				tail = node;
			}
		}

		return res;
	}

	@Override
	public boolean remove(T pattern) {
		boolean res = false;
		Node<T> obj = (Node<T>) hashMap.remove(pattern);
		if ( obj != null) {
			removeNode(obj);
			res = true;
		}
		return res;
	}
	
	private void removeNode(Node<T> current) {
		if(current == head) {
			removeFirst(current);
		}else if(current == tail) {
			removeLast(current);
		} else {
			removeMiddle(current);
		}
	}
	
	private void removeMiddle(Node<T> current) {
		Node<T> prev = current.prev;
		Node<T> next = current.next;
		prev.next = next;
		next.prev = prev;
		size--;
	}

	private void removeFirst(Node<T> current) {
		if(head.next == null) {
			head = tail = null;
		} else {
			Node<T> next = head.next;
		}
		head = current.next;
		size--;
	}

	private void removeLast(Node<T> current) {
		Node<T> prev = tail.prev;
		prev.next = null;
		tail = prev;
		size--;
	}

	@Override
	public boolean contains(T pattern) {
		return hashMap.containsKey(pattern);
	}

	@Override
	public Iterator<T> iterator() {
		return new LinkedHashSetIterator();
	}

	@Override
	public T get(T pattern) {
		T res = null;
		if (contains(pattern)) {
			res = pattern;
		}
		return res;
	}
}
