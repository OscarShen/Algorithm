package com.oscarshen09.utils;

public class LeftistNode<T> {
	public T element;
	public LeftistNode<T> left;
	public LeftistNode<T> right;
	public int npl;				//零路径长度

	public LeftistNode() {}

	public LeftistNode(T element) {
		this.element = element;
	}
	
	public LeftistNode(T element, LeftistNode<T> left, LeftistNode<T> right){
		this.element = element;
		this.left = left;
		this.right = right;
	}
}
