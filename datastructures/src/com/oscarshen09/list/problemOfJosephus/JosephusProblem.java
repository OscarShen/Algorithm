package com.oscarshen09.list.problemOfJosephus;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 采用队列的方式实现
 * 
 * @author ruiyao.shen
 *
 */
public class JosephusProblem {
	/**
	 * 使用队列方式
	 * 
	 * @param m 传递的次数
	 * @param n 可传递的总个数
	 */
	public void solve(int m, int n) {
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 1; i <= n; i++) {
			queue.offer(i);
		}
		int out = 0;
		while (out != n - 1) {
			for (int i = 0; i < m; i++) {
				queue.offer(queue.poll());
			}
			System.out.print(queue.remove() + "\t");
			out++;
		}
	}
}
