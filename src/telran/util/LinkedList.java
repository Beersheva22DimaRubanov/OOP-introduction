package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class LinkedList<T> implements List<T> {
	private static class Node<T> {
		T obj;
		Node<T> prev;
		Node<T> next;
		Node(T obj){
			this.obj = obj;
		}
	}
	
	private Node<T> head;
	private Node<T> tail;
	private int size;
	
	private class LinkedListIterator implements Iterator{
		int index = 0;
		Node<T> current = head;
		
		@Override
		public boolean hasNext() {
			return current != null;
//			return index < size;
		}

		@Override
		public Object next() {
			T res = current == null ? null : current.obj;
			if(!hasNext()) {
				throw new NoSuchElementException();
			} 
				current = current.next;
				index++;
			
			return res;
		}
		
	}
	
	@Override
	public boolean add(T element) {
		Node<T> node = new Node<T>(element);
		if(head == null) {
			head = tail = node;
		} else {
			tail.next = node;
			node.prev = tail;
			tail = node;
		}
		size ++;
		return true;
	}

	@Override
	public boolean remove(T pattern) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public int indexOf(T pattern) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public boolean removeIf(Predicate<T> predicate) {
		// TODO Auto-generated method stub
		return false;
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public T[] toArray(T[] array) {
		if(array.length < size) {
			array = Arrays.copyOf(array, size);
		}
		Node<T> current = head;
		for(int i = 0; i< size; i++) {
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
		if(index == size) {
			add(element);
		} else if(index == 0) {
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

	private Node<T> getNode(int index) {
		return index < size/2 ? getNodeFromLeft(index): getNodeFromRight(index);
	}

	private Node<T> getNodeFromRight(int index) {
		Node<T> current = tail;
		for(int i = size-1; i > index; i--) {
			current = current.prev;
		}
		return current;
	}

	private Node<T> getNodeFromLeft(int index) {
		Node<T> current = head;
		for(int i = 0; i<index; i++) {
			current = current.next;
		}
		return current;
	}

	private void addHead(T element) {
		Node<T> node = new Node <>(element);
		node.next = head;
		head.prev = node;
		head = node;
		size++;
	}

	@Override
	public T remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int lastIndexOf(T pattern) {
		// TODO Auto-generated method stub
		return 0;
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
