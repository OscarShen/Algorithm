package utils;

public class TreeNode<E> {
	E element;
	TreeNode<E> left;
	TreeNode<E> right;

	public TreeNode() {
		this.element = null;
		this.left = null;
		this.right = null;
	}

	public TreeNode(E element) {
		this.element = element;
		left = null;
		right = null;
	}
}
