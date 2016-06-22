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
		return null;
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

	private BinaryNode<E> findMax(BinaryNode<E> root2) {
		return null;
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
		return null;
	}

	/**
	 * 找到并移除某个元素
	 * 
	 * @param element
	 */
	public void remove(E element) {
		root = remove(element, root);
	}

	private BinaryNode<E> remove(E element, BinaryNode<E> root2) {
		return null;
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
