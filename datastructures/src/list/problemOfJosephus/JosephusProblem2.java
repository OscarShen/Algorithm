package list.problemOfJosephus;

import utils.Node;

/**
 * 使用循环链表
 * 
 * @author ruiyao.shen
 *
 */
public class JosephusProblem2 {
	/**
	 * 使用循环链表解决问题
	 * 
	 * @param m 传递的次数
	 * @param n 可传递的总个数
	 */
	public void solve(int m, int n) {
		Node preHead = new Node(Integer.MAX_VALUE);
		Node cur = preHead;
		for (int i = 1; i <= n; i++) {
			cur.next = new Node(i);
			cur = cur.next;
		}
		cur.next = preHead.next;
		Node preCur = cur;
		cur = cur.next;

		int out = 0;
		while (out != n - 1) {
			int count = 0;
			while (count < m) {
				cur = cur.next;
				preCur = preCur.next;
				count++;
			}
			System.out.print(cur.element+"\t");
			preCur.next = cur.next;
			cur = cur.next;
			out++;
		}
	}
}
