package tree.isomorphismOfTrees;

import java.util.Scanner;

/**
 * 判断树是否同构
 * 
 * @author ruiyao.shen
 *
 */
public class Isomorphism {
	Scanner in = new Scanner(System.in);
	TreeNode[] T1;
	TreeNode[] T2;
	boolean f = false;// 记录T1是否已经成功建立树

	/**
	 * 建立树，返回根节点
	 * 
	 * @return
	 */
	public int buildTree() {
		String ta = in.nextLine();
		int n = Integer.parseInt(ta);
		if (n == 0) {
			return -1;
		}
		String left, right;
		TreeNode[] T = new TreeNode[n];
		boolean[] checked = new boolean[n];
		for (int i = 0; i < n; i++) {
			T[i] = new TreeNode();
			String str = in.nextLine();
			String[] s = str.split(" ");
			T[i].element = s[0];
			left = s[1];
			if (left.charAt(0) == '-') {
				T[i].left = -1;
			} else {
				int t = Integer.parseInt(left);
				T[i].left = t;
				checked[t] = true;
			}
			right = s[2];
			if (right.charAt(0) == '-') {
				T[i].right = -1;
			} else {
				int t = Integer.parseInt(right);
				T[i].right = t;
				checked[t] = true;
			}
		}
		if (f) {// 判断是否建立过树
			T2 = T;
		} else {
			T1 = T;
		}

		f = true;

		for (int i = 0; i < n; i++) {
			if (!checked[i]) {
				return i;
			}
		}

		return -1;
	}

	public boolean isIsomor(int node1, int node2) {
		if (node1 == -1 || node2 == -1) {// 如果两个节点都为空，返回true，如果只有一个节点为空，返回false
			return node1 == -1 && node2 == -1;
		}
		if (!T1[node1].element.equals(T2[node2].element)) {// 如果两个节点值不同，返回false
			return false;
		}
		if (T1[node1].left == -1 && T2[node2].left == -1) {// 如果两个节点的左子树的值都为空，则判断右子树
			return isIsomor(T1[node1].right, T2[node2].right);
		}
		if (T1[node1].left != -1
				&& T2[node2].left != -1
				&& T1[T1[node1].left].element
						.equals(T2[T2[node2].left].element)) {// 如果左子树和右子树都不为空，而且左子树的值和另一个左子树的值相等，则判断右子树是否也同构
			return isIsomor(T1[node1].left, T2[node2].left)
					&& isIsomor(T1[node1].right, T2[node2].right);
		} else {// 如果左子树有一个为空，或者两个左子树的值不相等，则交换左右子树再判断
			return isIsomor(T1[node1].left, T2[node2].right);
		}
	}
}
