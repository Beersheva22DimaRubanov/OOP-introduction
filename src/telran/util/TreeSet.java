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
		Node<T> removed;
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
			removed = current;
			current = getNextCurrent(current);
			flNext = true;
			return res;
		}

		@Override
		public void remove() {
			if (!flNext) {
				throw new IllegalStateException();
			}
			Node<T> removedNode = current == null ? getMaxNode() : removed;
			removeNode(removedNode);
			flNext = false;
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
		if(current.left != null && current.right != null){
			removeNodeJunction(current);
		} else {
			removeNodeNonJunction(current);
		}
		
//		if(current.left == null && current.right == null) {
//			if(current.parent == null) {
//				root = null;
//			}else  {
//				removeParent(current);
//			}
//			current.parent = null;
//		} else {
//			if(current.left != null) {
//				Node<T> replace = current.left;
//				current.obj = replace.obj;
//				current.left = null;
//				if(replace.right != null) {
//					replace.right.parent = current;
//					current = replace.right;
//					replace.right = null;
//				}
//				replace.parent = null;
//			} else {
//				Node<T> replace = getLeastNode(current.right);
//				if(replace.right != null) {
//					replace.right.parent = replace.parent;
//					replace.parent.left = replace.right;
//				} else {
//					replace.parent.left = null;
//				}
//				if(current.right != replace) {
//					replace.right = current.right;
//					current.right.parent = replace;
//					current.right = null;
//				} else {
//					current.obj = replace.obj;
//					current.right = null;
//					replace.parent = null;
//				}
//			}
//		}
		size--;
//		return true;
	}
	
	private boolean isJunction() {
		return 
	}

	private void removeNodeJunction(Node<T> current) {
			Node<T> parent = current.parent;
			Node<T> child = current.left == null ? current.right : current.left;
			if(parent == null) {
				root = child;
				
			}else {
				if(parent.left == current) {
					parent.left = child;
				} else {
					parent.right = child;
				}
			}
 	}

	private void removeNodeNonJunction(Node<T> current) {
		Node<T> substitution = getLeastNode(current.right);
		current.obj = substitution.obj;
		removeNodeNonJunction(current);
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
		T res = null;
		Node<T> current = getNode(element);
		if (comparator.compare(element, getLeastNode(root).obj) < 0) {
			res = null;
		} else if (comparator.compare(element, current.obj) < 0) {
			while (current.parent != null && comparator.compare(element, current.obj) < 0) {
				current = current.parent;
			}
			res = current.obj;
		} else {
			while (current.right != null && comparator.compare(current.right.obj, element) < 0) {
				current = current.right;
			}
			res = current.obj;
		}
		return res;
	}

	@Override
	public T ceiling(T element) {
		Node<T> current = getNode(element);
		while(current != null && comparator.compare(element, current.obj) > 0) {
			current = current.left;
		}
		return current == null ? null : current.obj;
	}

	@Override
	public T first() {
		return getLeastNode(root).obj;
	}

	@Override
	public T last() {
		return getMaxNode().obj;
	}
	
//	private T floorCeiling(T pattern) {
//		
//	}

	private Node<T> getMaxNode() {
		Node<T> current = root;
		while (current.right != null) {
			current = current.right;
		}
		return current;
	}

}
