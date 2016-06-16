package utils;

public class Node2 {
	private int a;
	private int b;
	public static final int INFINITY = Integer.MAX_VALUE;
	public Node2 next;

	public Node2() {
		this.a = INFINITY;
		this.b = INFINITY;
		this.next = null;
	}

	public Node2(int a, int b) {
		this.a = a;
		this.b = b;
		this.next = null;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	public static int getInfinity() {
		return INFINITY;
	}

}
