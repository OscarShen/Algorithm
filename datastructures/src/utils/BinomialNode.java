package utils;

public class BinomialNode<T> {
	public T element;						//The data in the node
	public BinomialNode<T> leftChild;		//Left child
	public BinomialNode<T> nextSibling;		//Next child
	public BinomialNode(T theElement) {
		this(theElement, null, null);
	}

	public BinomialNode(T element, BinomialNode<T> leftChild, BinomialNode<T> nextSibling) {
		this.element = element;
		this.leftChild = leftChild;
		this.nextSibling = nextSibling;
	}
}
