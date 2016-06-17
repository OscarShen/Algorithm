package reverseLinkedList;

import java.util.Scanner;

import utils.TagNode;

public class ReverseLinkedList {
	private int firstAddr;
	private int n;// 节点总数
	private int k;// 需要逆序的节点数
	TagNode[] nodes;
	
	/**
	 * 对外的主程序
	 */
	public void reverseLinkedList() {
		scanner();
		TagNode[] node = getRes();
		
		print(node);
	}
	
	/**
	 * 录入需要的数据
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
	 * 返回完全逆序的数组
	 * @return
	 */
	private TagNode[] getRes() {
		TagNode[] res = new TagNode[n];
		res[0] = findNext(firstAddr);
		for (int i = 1; i < n; i++) {
			res[i] = findNext(nodes[i]);
		}
		return res;
	}

	/**
	 * 打印数组
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
	 * @param tagNode
	 * @return
	 */
	private TagNode findNext(TagNode tagNode) {
		return findNext(tagNode.getNextAddr());
	}
}
