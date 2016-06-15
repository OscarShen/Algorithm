package utils;

public class Node2 {
	public int a;
	public int b;
	public static final int INFINITY = Integer.MAX_VALUE;
	public Node2 next;

	public Node2() {
		this.a = INFINITY;
		this.b = INFINITY;
		this.next = null;
	}

	public Node2(int a, int b, Node2 next) {
		this.a = a;
		this.b = b;
		this.next = next;
	}

}
