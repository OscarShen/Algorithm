package oneIndeterminatePolynomicalOperations;

import utils.Node2;

public class SolveByLinkedList {

	public void operation1(int[] a, int[] b) {
		Node2 head1 = createLinkedList(a);
		Node2 head2 = createLinkedList(b);
		add(head1, head2);
	}

	private void add(Node2 head1, Node2 head2) {
		Node2 temp1 = head1.next;
		Node2 temp2 = head2.next;
		int a1, b1 = -1, a2, b2 = -1;
		while (temp1 != null && temp2 != null) {
			if (!isEmpty(temp1)) {
				a1 = temp1.a;
				b1 = temp1.b;
			}
			if (!isEmpty(temp2)) {
				a2 = temp2.a;
				b2 = temp2.b;
			}
			if (b1 == b2) {
			}
		}
	}

	private boolean isEmpty(Node2 temp1) {
		return false;
	}

	private Node2 createLinkedList(int[] a) {
		Node2 head = new Node2();
		Node2 temp = head;
		for (int i = 1; i <= a[0]; i++) {
			temp.next = new Node2(a[2 * i - 1], a[2 * i], null);
			temp = temp.next;
		}
		return head;
	}

}
