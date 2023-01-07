package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class LinkedList<T> extends AbstractCollection<T> implements List<T> {
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

	private class LinkedListIterator implements Iterator<T> {
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
			if(!flNext) {
				throw new IllegalStateException();
			}
			Node<T> removedNode = current == null ? tail : current.prev;
			removeNode(removedNode);
			flNext = false;
		}
	}

	@Override
	public boolean add(T element) {
		java.util.LinkedList t = new java.util.LinkedList();
		Node<T> node = new Node<T>(element);
		if (head == null) {
			head = tail = node;
		} else {
			tail.next = node;
			node.prev = tail;
			tail = node;
		}
		size++;
		return true;
	}

	@Override
	public int indexOf(T pattern) {
		int index = 0;
		Node<T> current = head;
		while (current != null && !isEquals(current.obj, pattern)) {
			index++;
			current = current.next;
		}
		return current != null ? index : -1;
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

	

	@Override
	public T[] toArray(T[] array) {
		if (array.length < size) {
			array = Arrays.copyOf(array, size);
		}
		Node<T> current = head;
		for (int i = 0; i < size; i++) {
			array[i] = current.obj;
			current = current.next;
		}
		Arrays.fill(array, size, array.length, null);
		return array;
	}

	@Override
	public Iterator<T> iterator() {
		return new LinkedListIterator();
	}

	@Override
	public void add(int index, T element) {
		checkIndex(index, true);
		if (index == size) {
			add(element);
		} else if (index == 0) {
			addHead(element);
		} else {
			addMiddle(index, element);
		}
	}

	private void addMiddle(int index, T element) {
		Node<T> node = new Node<>(element);
		Node<T> nodeIndex = getNode(index);
		Node<T> nodePrev = nodeIndex.prev;
		nodePrev.next = node;
		node.prev = nodePrev;
		nodeIndex.prev = node;
		node.next = nodeIndex;
		size++;
	}
	
	/************************************************************************************/
	//Comments only for LinkedList task of loop existence
	public void setNext(int index1, int index2) {
		if (index1 < index2) {
			throw new IllegalArgumentException();
		}
		Node<T> current = getNode(index1);
		Node<T> prev = getNode(index2);
		current.next  = prev; 
	}
	public boolean hasLoop() {
		Node<T> current = head;
		boolean res = false;
		if(tail.next != null) {
			res = true;
		} 
		while(current != tail && !res) {
			if(indexOf(current.obj) >= indexOf(current.next.obj)) {
				res = true;
			}
			current = current.next;
		}
		return res;
	}
	/*********************************************************************************************/

	private Node<T> getNode(int index) {
		return index < size / 2 ? getNodeFromLeft(index) : getNodeFromRight(index);
	}

	private Node<T> getNodeFromRight(int index) {
		Node<T> current = tail;
		for (int i = size - 1; i > index; i--) {
			current = current.prev;
		}
		return current;
	}

	private Node<T> getNodeFromLeft(int index) {
		Node<T> current = head;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current;
	}

	private void addHead(T element) {
		Node<T> node = new Node<>(element);
		node.next = head;
		head.prev = node;
		head = node;
		size++;
	}

	@Override
	public T remove(int index) {
		checkIndex(index, false);
		Node<T> current = getNode(index);
		T old = current.obj;
		removeNode(current);
		return old;
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
	public int lastIndexOf(T pattern) {
		int index = size-1;
		Node<T> current = tail;
		while(current != null && !isEquals(current.obj, pattern)) {
			index--;
			current = current.prev;
		}
		return index;
	}

	@Override
	public T get(int index) {
		checkIndex(index, false);
		return getNode(index).obj;
	}

	@Override
	public void set(int index, T element) {
		checkIndex(index, false);
		Node<T> node = getNode(index);
		node.obj = element;

	}

}
