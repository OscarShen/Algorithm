package com.oscarshen09.sort.seniorityCount;

import java.util.Scanner;

public class SeniorityCount {
	public void solve() {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();		// 输入人员总数
		int[] seniorities = new int[51];
		for (int i = 0; i < 51; i++) {
			seniorities[i] = 0;
		}
		for (int i = 0; i < n; i++) {
			int t = s.nextInt();
			seniorities[t]++;
		}

		for (int i = 0; i < 51; i++) {
			if (seniorities[i] != 0) {
				System.out.println(i + ":" + seniorities[i]);
			}
		}
		s.close();
	}
}
