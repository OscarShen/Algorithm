package sameBST;

public class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	boolean flag;// 在本题中用来作为标记是否相同

	public TreeNode() {
	}

	public TreeNode(int val, TreeNode left, TreeNode right, boolean flag) {
		this.val = val;
		this.left = left;
		this.right = right;
		this.flag = flag;
	}

	public TreeNode(int val) {
		this.val = val;
		this.left = null;
		this.right = null;
		this.flag = false;
	}

}
