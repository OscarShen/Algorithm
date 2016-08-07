package com.oscarshen09.list.polynomicalOperations;

import com.oscarshen09.utils.PolyNode;

/**
 * 由PolyList封装加法和乘法运算
 * 
 * @author Oscar
 *
 */
public class PolyList {
	/**
	 * 记录PolyList的头
	 */
	PolyNode head;
	/**
	 * 作为指针记录当前节点
	 */
	PolyNode current;

	public PolyList() {
		this.head = new PolyNode();
		this.current = head;
	}

	/**
	 * 创建一个链表
	 * 
	 * @param a
	 * @return
	 */
	private PolyList convert2List(int[] a) {
		PolyNode head1 = new PolyNode();
		PolyNode temp = head1;
		for (int i = 1; i <= a[0]; i++) {
			temp.next = new PolyNode(a[2 * i - 1], a[2 * i]);
			if (i != a[0]) {
				temp = temp.next;
			}
		}
		PolyList p = new PolyList();
		p.head = head1;
		p.current = temp;
		return p;
	}

	/**
	 * 对数组实现加法操作
	 * 
	 * @param a
	 * @param b
	 */
	public void add(int[] a, int[] b) {
		PolyList p1 = convert2List(a);
		PolyList p2 = convert2List(b);
		PolyList p = add(p1, p2);
		print(p);
	}

	/**
	 * 对PolyList实现加法操作
	 * 
	 * @param head1
	 * @param head2
	 */
	private PolyList add(PolyList p1, PolyList p2) {
		PolyList list = new PolyList();
		p1.current = p1.head.next;
		p2.current = p2.head.next;
		while (p1.current != null && p2.current != null) {
			if (p1.current.getB() == p2.current.getB()) {
				list.insert(new PolyNode(p1.current.getA() + p2.current.getA(), p1.current.getB()));
				p1.current = p1.current.next;
				p2.current = p2.current.next;
			} else if (p1.current.getB() > p2.current.getB()) {
				list.insert(p1.current);
				p1.current = p1.current.next;
			} else if (p1.current.getB() < p2.current.getB()) {
				list.insert(p2.current);
				p2.current = p2.current.next;
			}
		}
		while (p1.current != null) {
			list.insert(p1.current);
			p1.current = p1.current.next;
		}
		while (p2.current != null) {
			list.insert(p2.current);
			p2.current = p2.current.next;
		}
		return list;
	}

	/**
	 * 对数组实现乘法操作
	 * 
	 * @param a
	 * @param b
	 */
	public void multiply(int[] a, int[] b) {
		PolyList p1 = convert2List(a);
		PolyList p2 = convert2List(b);
		PolyList p = multiply(p1, p2);
		print(p);
	}

	/**
	 * 对PolyList实现乘法操作
	 * 
	 * @param p1
	 * @param p2
	 * @return
	 */
	private PolyList multiply(PolyList p1, PolyList p2) {
		PolyList p = new PolyList();
		PolyList temp = new PolyList();
		p1.current = p1.head.next;
		p2.current = p2.head.next;

		while (p1.current != null) {
			temp.current = temp.head;
			while (p2.current != null) {
				temp.insert(new PolyNode(p1.current.getA() * p2.current.getA(), p1.current.getB() + p2.current.getB()));
				p2.current = p2.current.next;
			}
			p = add(p, temp);
			p1.current = p1.current.next;
			p2.current = p2.head.next;
		}

		return p;
	}

	/**
	 * 打印操作
	 * 
	 * @param p
	 */
	private void print(PolyList p) {
		p.current = p.head.next;
		while (p.current != null) {
			System.out.print(p.current.getA() + " " + p.current.getB() + " ");
			p.current = p.current.next;
		}
		System.out.println();
	}

	/**
	 * 插入操作
	 * 
	 * @param node
	 */
	private void insert(PolyNode node) {
		current.next = node;
		current = node;
	}

}
