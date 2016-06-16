package oneIndeterminatePolynomicalOperations;

import utils.Node2;

public class SolveByLinkedList {

	Node2 head;
	Node2 current;

	/**
	 * 调用的主要程序。
	 * 
	 * @param a
	 * @param b
	 */
	public void operation1(int[] a, int[] b) {
		Node2 head1 = createLinkedList(a);
		Node2 head2 = createLinkedList(b);
		add(head1, head2);
	}

	/**
	 * 创建一个链表
	 * 
	 * @param a
	 * @return
	 */
	private Node2 createLinkedList(int[] a) {
		Node2 head = new Node2();
		Node2 temp = head;
		for (int i = 1; i <= a[0]; i++) {
			temp.next = new Node2(a[2 * i - 1], a[2 * i]);
			temp = temp.next;
		}
		return head;
	}


	/**
	 * 两个链表相加
	 * 
	 * @param head1
	 * @param head2
	 */
	private void add(Node2 head1, Node2 head2) {
		
	}

	/**
	 * 判断是否为空
	 * 
	 * @param temp1
	 * @return
	 */
	private boolean isEmpty(Node2 temp1) {
		return head.next == null;
	}

	private void insert(Node2 node) {
		current.next = node;
		current = node;
	}

}
