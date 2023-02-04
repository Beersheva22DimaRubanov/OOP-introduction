package telran.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class TreeSet<T> extends AbstractCollection<T> implements Sorted<T> {
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
		boolean flNext = false;

		TreeSetIterator() {
			if (current != null) {
				current = getLeastNode(current);
			}
		}

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
			prev = current;
			current = getNextCurrent(current);
			flNext = true;
			return res;
		}

		@Override
		public void remove() {
			if (!flNext) {
				throw new IllegalStateException();
			}
			if (isJunction(prev)) {
				current = prev;
			}
			removeNode(prev);
			flNext = false;
		}
	}

	private static final String SYMBOL = " ";

	private static final int NUMBER_SYMBOLS_PER_LEVEL = 4;

	private Node<T> root;
	private Comparator<T> comparator;

	public TreeSet(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	public TreeSet() {
		this((Comparator<T>) Comparator.naturalOrder());
	}

	private Node<T> getLeastNode(Node<T> current) {
		while (current.left != null) {
			current = current.left;
		}
		return current;
	}

	private Node<T> getNextCurrent(Node<T> current) {
		return current.right == null ? getGreaterParent(current) : getLeastNode(current.right);
	}

	private Node<T> getGreaterParent(Node<T> current) {
		while (current.parent != null && current.parent.left != current) {
			current = current.parent;
		}
		return current.parent;
	}

	@Override
	public boolean add(T element) {
		boolean res = false;
		Node<T> parent = getNode(element);
		int compRes = 0;
		if (parent == null || (compRes = comparator.compare(element, parent.obj)) != 0) {
			res = true;
			size++;
			Node<T> node = new Node<T>(element);
			node.parent = parent;
			if (parent == null) {
				root = node;
			} else {
				if (compRes < 0) {
					parent.left = node;
				} else {
					parent.right = node;
				}
			}
		}
		return res;
	}

	private Node<T> getNode(T element) {
		Node<T> current = root;
		Node<T> parent = null;
		int compRes;
		while (current != null && (compRes = comparator.compare(element, current.obj)) != 0) {
			parent = current;
			current = compRes < 0 ? current.left : current.right;
		}
		return current == null ? parent : current;
	}

	@Override
	public boolean remove(T pattern) {
		boolean res = false;
		Node<T> current = getNode(pattern);
		if (current != null && comparator.compare(pattern, current.obj) == 0) {
			res = true;

			removeNode(current);
		}
		return res;
	}

	private void removeNode(Node<T> current) {
		if (isJunction(current)) {
			removeNodeJunction(current);
		} else {
			removeNodeNonJunction(current);
		}
		size--;
	}

	private boolean isJunction(Node<T> current) {
		return current.left != null && current.right != null;
	}

	private void removeNodeNonJunction(Node<T> current) {
		Node<T> parent = current.parent;
		Node<T> child = current.left == null ? current.right : current.left;
		if (parent == null) {
			root = child;
		} else {
			if (parent.left == current) {
				parent.left = child;
			} else {
				parent.right = child;
			}
		}
		if (child != null) {
			child.parent = parent;
		}
	}

	private void removeNodeJunction(Node<T> current) {
		Node<T> substitution = getLeastNode(current.right);
		current.obj = substitution.obj;
		removeNodeNonJunction(substitution);
	}

	private void removeParent(Node<T> child) {
		if (child.parent.right == child) {
			child.parent.right = null;
		} else {
			child.parent.left = null;
		}
	}

	@Override
	public boolean contains(T pattern) {
		boolean res = false;
		Node<T> node = getNode(pattern);
		return node != null && comparator.compare(pattern, node.obj) == 0;
	}

	@Override
	public Iterator<T> iterator() {
		return new TreeSetIterator();
	}

	@Override
	public T floor(T element) {
		return floorCeiling(element, true);
	}

	@Override
	public T ceiling(T element) {
		return floorCeiling(element, false);
	}

	@Override
	public T first() {
		T res = null;
		if (root != null) {
			res = getLeastNode(root).obj;
		}
		return res;
	}

	@Override
	public T last() {
		T res = null;
		if (root != null) {
			res = getMaxNode(root).obj;
		}
		return res;
	}

	private T floorCeiling(T pattern, boolean isFloor) {
		T res = null;
		int compRes = 0;
		Node<T> current = root;
		while (current != null && (compRes = comparator.compare(pattern, current.obj)) != 0) {
			if ((compRes < 0 && !isFloor) || (compRes > 0 && isFloor)) {
				res = current.obj;
			}
			current = compRes < 0 ? current.left : current.right;
		}
		return current == null ? res : current.obj;
	}

	private Node<T> getMaxNode(Node<T> current) {
		while (current.right != null) {
			current = current.right;
		}
		return current;
	}

	public void displayTreeRotated() {
		displayTreeRotated(root, 0);
	}

	private void displayTreeRotated(Node<T> root, int level) {
		if (root != null) {
			displayTreeRotated(root.right, level + 1);
			displayRoot(root, level);
			displayTreeRotated(root.left, level + 1);
		}
	}

	private void displayRoot(Node<T> root, int level) {
		System.out.printf("%s%s\n", SYMBOL.repeat(NUMBER_SYMBOLS_PER_LEVEL * level), root.obj);
	}

	public int height() {
		return height(root);
	}

	private int height(Node<T> root) {
		int res = 0;
		if (root != null) {
			int heightLeft = height(root.left);
			int heightRight = height(root.right);
			res = Math.max(heightLeft, heightRight) + 1;
		}
		return res;
	}

	public int width() {
		return width(root);
	}

	private int width(Node<T> root) {
		int res = 0;
		if (root != null) {
			if (root.left == null && root.right == null) {
				res = 1;
			} else {
				res = width(root.left) + width(root.right);
			}
		}
		return res;
	}

	public void inversion() {
		comparator = comparator.reversed();
		inversion(root);
	}

	private void inversion(Node<T> root) {
		if (root != null) {
			inversion(root.right);
			inversion(root.left);
			swap(root);
		}
	}

	private void swap(Node<T> root) {
		Node<T> tmp = root.left;
		root.left = root.right;
		root.right = tmp;
	}

	public void balance() {
		Node<T>[] array = getNodesArray();
		root = balance(array, 0, array.length - 1, null);
	}

	private Node<T> balance(Node<T>[] array, int left, int right, Node<T> parent) {
		Node<T> root = null;
		if(left <= right) {
			final int rootIndex = (left + right) / 2;
			root = array[rootIndex];
			root.parent = parent;
			root.left = balance(array, left, rootIndex - 1, root);
			root.right = balance(array, rootIndex + 1, right, root);
		}
		
		return root;
	}

	private Node<T>[] getNodesArray() {
		Node<T> res[] = new Node[size];
		int index = 0;
		if(root != null) {
			Node<T> current = getLeastNode(root);
			while (current != null) {
				res[index++] = current;
				current = getNextCurrent(current);
			}
		}
		
		return res;
	}
	
	@Override
	public T get(T pattern) {
		T res = ceiling(pattern);
		return res!= null && res.equals(pattern)? res: null;
	}

}
