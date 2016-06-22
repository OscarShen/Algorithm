package binarySearchTree;

/**
 * 二叉搜索树
 * 
 * @author ruiyao.shen
 *
 * @param <E>
 */
public class BinarySearchTree<E extends Comparable<? super E>> {
	private BinaryNode<E> root;// 根节点

	// constructors
	public BinarySearchTree() {
		root = null;
	}

	/**
	 * 使此树为空
	 */
	public void makeEmpty() {
		root = null;
	}

	/**
	 * 是否为空
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return root == null;
	}

	/**
	 * 是否包含元素
	 * 
	 * @param element
	 * @return
	 */
	public boolean contains(E element) {
		return contains(element, root);
	}

	private boolean contains(E element, BinaryNode<E> root) {
		if (element == null) {
			return false;
		}

		int compareResult = element.compareTo(root.element);

		if (compareResult < 0) {
			return contains(element, root.left);
		} else if (compareResult > 0) {
			return contains(element, root.right);
		} else {
			return true;
		}
	}

	/**
	 * 找到并返回最小的元素
	 * 
	 * @return
	 * @throws Exception
	 */
	public E findMin() throws Exception {
		if (isEmpty()) {
			throw new Exception();
		}
		return findMin(root).element;
	}

	private BinaryNode<E> findMin(BinaryNode<E> root) {
		if (root != null) {
			while (root.left != null) {
				root = root.left;
			}
		}
		return root;
	}

	/**
	 * 找到并返回最大的元素
	 * 
	 * @return
	 * @throws Exception
	 */
	public E findMax() throws Exception {
		if (isEmpty()) {
			throw new Exception();
		}
		return findMax(root).element;
	}

	private BinaryNode<E> findMax(BinaryNode<E> root) {
		if (root != null) {
			while (root.right != null) {
				root = root.right;
			}
		}
		return root;
	}

	/**
	 * 插入一个元素
	 * 
	 * @param element
	 */
	public void insert(E element) {
		root = insert(element, root);
	}

	private BinaryNode<E> insert(E element, BinaryNode<E> root) {
		if (root == null) {
			return new BinaryNode<E>(element, null, null);
		}

		int compareResult = element.compareTo(root.element);
		if (compareResult < 0) {
			root.left = insert(element, root.left);
		} else if (compareResult > 0) {
			root.right = insert(element, root.right);
		} else {
			;
		}

		return root;
	}

	/**
	 * 找到并移除某个元素
	 * 
	 * @param element
	 */
	public void remove(E element) {
		root = remove(element, root);
	}

	private BinaryNode<E> remove(E element, BinaryNode<E> root) {
		if (root == null) {
			return root;
		}
		int compareResult = element.compareTo(root.element);

		if (compareResult < 0) {
			root.left = remove(element, root.left);
		} else if (compareResult > 0) {
			root.right = remove(element, root.right);
		} else if (root.left != null && root.right != null) {
			root.element = findMin(root.right).element;
			root.right = remove(root.element, root.right);
		} else {
			root = (root.left != null) ? root.left : root.right;
		}
		return root;
	}

	/**
	 * 打印这棵树
	 */
	public void printTree() {
		printTree(root);
	}

	private void printTree(BinaryNode<E> root) {

	}
}
