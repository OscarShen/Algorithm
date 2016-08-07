package com.oscarshen09.list.reverseLinkedList;

import java.util.Scanner;

import com.oscarshen09.utils.TagNode;

public class ReverseLinkedList {
	private int firstAddr;// 读入的第一个地址
	private int n;// 节点总数
	private int k;// 需要逆序的节点数
	TagNode[] nodes;

	/**
	 * 对外的主程序
	 */
	public void reverseLinkedList() {
		scanner();
		TagNode[] node;
		if (n > k) {
			node = getRes(k);
		} else {
			node = getRes();
		}
		print(modifyAddr(node));
	}

	/**
	 * 调整数组的地址顺序
	 * 
	 * @param node
	 * @return
	 */
	private TagNode[] modifyAddr(TagNode[] node) {
		for (int i = 0; i < n - 1; i++) {
			node[i].setNextAddr(node[i + 1].getAddr());
		}
		node[n - 1].setNextAddr(-1);

		return node;
	}

	/**
	 * 录入需要的数据
	 * 
	 * @return
	 */
	private TagNode[] scanner() {
		Scanner s = new Scanner(System.in);
		String str = s.nextLine();
		String[] strs = str.split(" ");
		firstAddr = Integer.parseInt(strs[0]);
		n = Integer.parseInt(strs[1]);
		k = Integer.parseInt(strs[2]);
		nodes = new TagNode[n];
		for (int i = 0; i < n; i++) {
			str = s.nextLine();
			strs = str.split(" ");
			nodes[i] = new TagNode(Integer.parseInt(strs[0]),
					Integer.parseInt(strs[1]), Integer.parseInt(strs[2]));
		}
		s.close();
		return nodes;
	}

	/**
	 * 获得以k逆序后的数组，前后地址未进行调整。
	 * 
	 * @param k
	 * @return
	 */
	private TagNode[] getRes(int k) {
		TagNode[] res = new TagNode[n];
		TagNode temp = new TagNode(Integer.MAX_VALUE, Integer.MAX_VALUE,
				firstAddr);
		int count = 1;
		while (count * k < n) {
			for (int i = k - 1; i >= 0; i--) {
				int m = (count - 1) * k;
				res[i + m] = findNext(temp);
				temp = res[i + m];
			}
			count++;
		}

		for (int c = (count - 1) * k; c < n; c++) {
			res[c] = findNext(temp);
			temp = res[c];
		}

		return res;
	}

	/**
	 * 返回完全逆序的数组
	 * 
	 * @return
	 */
	private TagNode[] getRes() {
		TagNode[] res = new TagNode[n];
		TagNode temp = new TagNode(Integer.MAX_VALUE, Integer.MAX_VALUE,
				firstAddr);
		for (int i = n - 1; i >= 0; i--) {
			res[i] = findNext(temp);
			temp = res[i];
		}
		return res;
	}

	/**
	 * 打印数组
	 * 
	 * @param node
	 */
	private void print(TagNode[] node) {
		for (int i = 0; i < n; i++) {
			System.out.println(node[i].getAddr() + " " + node[i].getData()
					+ " " + node[i].getNextAddr());
		}
	}

	/**
	 * 使用地址找到下一个节点
	 * 
	 * @param firstAddr2
	 * @return
	 */
	private TagNode findNext(int firstAddr2) {
		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i].getAddr() == firstAddr2) {
				return nodes[i];
			}
		}
		return null;
	}

	/**
	 * 使用节点找到下一个节点
	 * 
	 * @param tagNode
	 * @return
	 */
	private TagNode findNext(TagNode tagNode) {
		try {
			return findNext(tagNode.getNextAddr());
		} catch (Exception e) {
			System.out.println("请检查输入的地址是否有误！");
			System.exit(0);
			return null;
		}
	}
}
