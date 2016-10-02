package com.oscarshen09.sort.patJudge;

import java.util.Scanner;

import com.oscarshen09.sort.SortUtils;

/**
 * 面向对象
 * 
 * @author ruiyao.shen
 *
 */
public class PATJudge {
	public static void main(String[] args) {
		new PATJudge().solve();
	}

	public void solve() {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();// uers
		int k = s.nextInt();// problems
		int m = s.nextInt();// submittions
		User[] users = new User[n];
		for (int i = 0; i < n; i++)
			users[i] = new User(k);
		int[] perfects = new int[k + 1];
		for (int i = 1; i <= k; i++)
			perfects[i] = s.nextInt();

		for (int i = 0; i < m; i++) {
			String id = s.next();
			User user = users[Integer.parseInt(id) - 1];
			if (user.id == null) {
				user.id = id;
			}
			int problem = s.nextInt();
			int score = s.nextInt();
			if (score < 0)
				score = 0;
			if (perfects[problem] == score)
				user.perfect++;
			if (score > 0)
				user.isValuable = true;
			user.totalScore += score;
		}
		SortUtils.insertionSort(users);
		for (int i = 1; i < n; i++) {
			int rank = 1;
			if (users[i].isValuable) {
				System.out.print(rank + "\t");
				rank++;
				System.out.print(users[i].totalScore + "\t");
				int scores[] = users[i].scores;
				for (int j = 1; j <= scores.length; j++) {
					System.out.print(scores[i] + "\t");
				}
				System.out.println();
			}
		}
		s.close();
	}

	class User implements Comparable<User> {
		String id;
		int[] scores;
		int totalScore;
		int perfect;
		boolean isValuable;

		public User(int problems) {
			scores = new int[problems + 1];
		}

		@Override
		public int compareTo(User o) {
			if (totalScore > o.totalScore)
				return -1;
			else if (totalScore < o.totalScore)
				return 1;
			else if (perfect > o.perfect)
				return -1;
			else if (perfect < o.perfect)
				return 1;
			else if (Integer.parseInt(id) < Integer.parseInt(o.id))
				return -1;
			else
				return 1;
		}

	}
}
