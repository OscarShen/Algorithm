package com.oscarshen09.disjointSet;

public class DisjSets {
	private int[] s;

	public DisjSets(int numElements) {
		s = new int[numElements];
		for (int i = 0; i < s.length; i++) {
			s[i] = -1;
		}
	}

	/**
	 * 将两个组联合起来
	 * 
	 * @param root1
	 * @param root2
	 */
	public void union(int root1, int root2) {
		s[root2] = s[root1];
	}

	/**
	 * 找到x属于哪一个集合中
	 * 
	 * @param x
	 * @return
	 */
	public int find(int x) {
		if (s[x] < 0)
			return x;
		else
			return s[x] = find(s[x]);
	}
}
