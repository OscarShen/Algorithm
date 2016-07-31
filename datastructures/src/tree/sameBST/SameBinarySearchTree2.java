package tree.sameBST;

import java.util.Scanner;

/**
 * 判断是否同一棵二叉搜索树（建一棵树的方法）
 * 
 * @author ruiyao.shen
 *
 */
public class SameBinarySearchTree2 {
	private Scanner s = new Scanner(System.in);

	public static void main(String[] args) {
		new SameBinarySearchTree2().scanner();
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
				if (judge(tree, n))
					System.out.println("Yes");
				else
					System.out.println("No");
				resetTree(tree);
			}
			n = s.nextInt();
		}
	}

	/**
	 * 清除各节点的flag标记
	 * 
	 * @param tree
	 */
	private void resetTree(TreeNode tree) {
		if (tree.left != null) {
			resetTree(tree.left);
		}
		if (tree.right != null) {
			resetTree(tree.right);
		}
		tree.flag = false;
	}

	/**
	 * 判别两棵树是否相同
	 * 
	 * 发现序列中某个数与T不一致时，必须把后面的数字全部读完！！
	 * 
	 * @param tree
	 * @param n
	 * @return
	 */
	private boolean judge(TreeNode tree, int n) {
		int val;
		boolean flag = true;// 用来表示是否一致，true目前为一致
		val = s.nextInt();
		if (val != tree.val)
			flag = false;
		else
			tree.flag = true;

		for (int i = 1; i < n; i++) {
			val = s.nextInt();
			if (flag && !check(tree, val))
				flag = false;
		}
		return flag;
	}

	/**
	 * 检查是否经过相同的路径
	 * 
	 * @param tree
	 * @param val
	 * @return
	 */
	private boolean check(TreeNode tree, int val) {
		if (tree.flag) {
			if (val < tree.val)
				return check(tree.left, val);
			else if (val > tree.val)
				return check(tree.right, val);
			else
				return false;
		} else {
			if (val == tree.val) {
				tree.flag = true;
				return true;
			} else
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
