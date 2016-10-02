package com.oscarshen09.tree.binarySearchTree;

public class BinaryNode<E> {
	E element;
	BinaryNode<E> left;
	BinaryNode<E> right;

	public BinaryNode(E e) {
		this(e, null, null);
	}

	public BinaryNode(E e, BinaryNode<E> left, BinaryNode<E> right) {
		this.element = e;
		this.left = left;
		this.right = right;
	}
}
