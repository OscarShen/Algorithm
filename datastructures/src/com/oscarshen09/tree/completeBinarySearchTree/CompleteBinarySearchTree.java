package com.oscarshen09.tree.completeBinarySearchTree;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 建立完全二叉搜索树
 * 
 * @author ruiyao.shen
 *
 */
public class CompleteBinarySearchTree {
	int nodes[]; //用于存储扫描的节点
	int res[]; //用于存储结果
	int n; //记录节点总个数

	/**
	 * 扫描并排序
	 */
	public void scan() {
		Scanner s = new Scanner(System.in);
		n = s.nextInt();
		nodes = new int[n + 1];
		res = new int[n + 1];
		for (int i = 1; i < n; i++) {
			nodes[i] = s.nextInt();
		}
		Arrays.sort(nodes);
		s.close();
	}

	/**
	 * 根据节点个数获取层数
	 * 
	 * @param n
	 * @return
	 */
	public int getLevel(int n) {
		int level = 0;
		while (n > 0) {
			level++;
			n >>= 1;
		}
		return level;
	}

	/**
	 * 获取根节点的位置编号
	 * 
	 * @param level
	 * @param left
	 * @param n
	 * @return
	 */
	public int getRoot(int level, int left, int n) {
		int lessThanRoot = lessThanRoot(level, left, n);
		return lessThanRoot + left;
	}

	/**
	 * 获取比根节点小的节点个数，用于获取根节点位置
	 * 
	 * @param level
	 * @param left
	 * @param right
	 * @return
	 */
	public int lessThanRoot(int level, int left, int right) {
		int n = right - left + 1;
		int fullNodes = (1 << (level - 1)) - 1;
		int lastLevelNodes = n - fullNodes;
		int lessThanRoot = 0;
		if (lastLevelNodes > (fullNodes + 1) / 2) {
			lessThanRoot = (fullNodes + 1) / 2;
		} else {
			lessThanRoot = lastLevelNodes;
		}
		lessThanRoot += (fullNodes / 2);
		return lessThanRoot;
	}

	/**
	 * 递归程序
	 * @param left
	 * @param right
	 * @param i
	 */
	public void solve(int left, int right, int i) {
		if (i > n)
			return;
		int n = right - left + 1;
		int level = getLevel(n);
		int root = getRoot(level, left, right);
		res[i] = nodes[root];
		solve(left, root - 1, i * 2);
		solve(root + 1, right, i * 2 + 1);
	}
}
