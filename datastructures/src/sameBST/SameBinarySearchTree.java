package sameBST;

import java.util.Scanner;

/**
 * 判断是否同一棵二叉搜索树（建两棵树的方法）
 * 
 * @author ruiyao.shen
 *
 */
public class SameBinarySearchTree {
	private Scanner s = new Scanner(System.in);

	public static void main(String[] args) {
		new SameBinarySearchTree().scanner();
	}

	/**
	 * 用来扫描数据
	 */
	public void scanner() {
		int n;
		int l;
		TreeNode tree;

		n = s.nextInt();
		while (n != 0) {
			l = s.nextInt();
			tree = makeTree(n);
			for (int i = 0; i < l; i++) {
				TreeNode tree1 = makeTree(n);
				boolean isSame = checkSame(tree, tree1);
				if (isSame) {
					System.out.println("Yes");
				} else {
					System.out.println("No");
				}
			}
			n = s.nextInt();
		}
	}

	/**
	 * 判断两棵树是否相同
	 * 
	 * @param tree
	 * @param tree1
	 * @return
	 */
	private boolean checkSame(TreeNode tree, TreeNode tree1) {
		if (tree == null || tree1 == null) {
			return tree == null && tree1 == null;
		}
		if (tree.val == tree1.val) {
			return checkSame(tree.left, tree1.left) && checkSame(tree.right, tree1.right);
		} else {
			return false;
		}
	}

	/**
	 * 建立一棵树
	 * 
	 * @param n
	 * @return
	 */
	private TreeNode makeTree(int n) {
		TreeNode tree;
		int val;

		val = s.nextInt();
		tree = new TreeNode(val);
		for (int i = 1; i < n; i++) {
			val = s.nextInt();
			tree = insert(tree, val);
		}
		return tree;
	}

	/**
	 * 将数值插入到BST中
	 * 
	 * @param tree
	 * @param val
	 * @return
	 */
	private TreeNode insert(TreeNode tree, int val) {
		if (tree == null) {
			tree = new TreeNode(val);
		} else {
			if (val > tree.val) {
				tree.right = insert(tree.right, val);
			} else {
				tree.left = insert(tree.left, val);
			}
		}
		return tree;
	}
}
