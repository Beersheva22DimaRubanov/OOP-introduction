package telran.util;

import java.util.Comparator;
import java.util.Iterator;

public class TreeSet<T> extends AbstractCollection<T> implements Set<T> {
	static private class Node<T> {
		T obj;
		Node<T> parent;
		Node<T> left;
		Node<T> right;

		Node(T obj) {
			this.obj = obj;
		}
	}

	private class TreeSetIterator implements Iterator<T> {
		Node<T> current = root;
		Node<T> prev;
		boolean isFirstElem = true;

		private Node<T> getFirstElem(Node<T> current) {
			while (current.left != null) {
				current = current.left;
			}
			return current;
		}
		
		private Node<T> getMaxElem(){
			Node<T> max = root;
			while (max.right != null) {
				max = max.right;
			}
			return max;
		}

		@Override
		public boolean hasNext() {
			return !current.obj.equals(getMaxElem().obj);
		}

		@Override
		public T next() {
			if(current.left == null && current.equals(root) && isFirstElem) {
				current = root;
				isFirstElem = false;
			}else if (current.left != null && current.left != prev) {
				current = getFirstElem(current);
				isFirstElem = false;
			} else if (current.right != null && current.left == null 
					|| current.right != null && current.left == prev) {
				current = getFirstElem(current.right);
				isFirstElem = false;
			}else if (current.right == null && current.left == null) {
				if (current.parent.right == current) {
					T obj = current.obj;
					while (comparator.compare(obj, current.parent.obj) > 0) {
						current = current.parent;
					}
				} else if (current.parent.left == current) {
					current = current.parent;
				}
				prev = current.left;
			}
			return current.obj;
		}

	}

	private Node<T> root;
	private Comparator<T> comparator;

	public TreeSet(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	public TreeSet() {
		this((Comparator<T>) Comparator.naturalOrder());
	}

	@Override
	public boolean add(T element) {
		boolean res = false;
		Node<T> node = new Node<T>(element);
		Node<T> current;
		if (root == null) {
			root = node;
			res = true;
			size++;
		} else {
			current = goToParentNode(element);
			res = addElementToParent(element, current, node);
		}
		return res;
	}

	private Node<T> goToParentNode(T element) {
		Node<T> current = root;
		if (comparator.compare(element, current.obj) < 0) {
			while (comparator.compare(element, current.obj) < 0 && current.left != null) {
				current = current.left;
			}
		} else if (comparator.compare(element, root.obj) > 0) {
			while (comparator.compare(element, current.obj) > 0 && current.right != null) {
				current = current.right;
			}
		}
		return current;
	}

	private boolean addElementToParent(T element, Node<T> current, Node<T> node) {
		boolean res = false;
		if (comparator.compare(element, current.obj) < 0) {
			current.left = node;
			res = true;
			node.parent = current;
			size++;
		} else if (comparator.compare(element, current.obj) == 0) {
			res = false;
		} else {
			current.right = node;
			res = true;
			node.parent = current;
			size++;
		}
		return res;
	}

	@Override
	public boolean remove(T pattern) {
		// NOT IMPLEMENTED
		return false;
	}

	@Override
	public boolean contains(T pattern) {
		boolean res = false;
		Node<T> current = goToParentNode(pattern);
		if (current.obj.equals(pattern)) {
			res = true;
		} else if (current.left != null && current.left.equals(pattern)) {
			res = true;
		} else if (current.right != null && current.right.equals(pattern)) {
			res = true;
		}
		return res;
	}

	@Override
	public Iterator<T> iterator() {
		return new TreeSetIterator();
	}

}
