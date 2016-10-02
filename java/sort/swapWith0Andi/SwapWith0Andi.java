package com.oscarshen09.sort.swapWith0Andi;

import java.util.Scanner;

public class SwapWith0Andi {
	public static void main(String[] args) {
		new SwapWith0Andi().solve();
	}
	public void solve() {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		int[] nums = new int[n];
		for (int i = 0; i < n; i++) {
			nums[i] = s.nextInt();
		}

		int count = 0;
		boolean flag = false;

		while (true) {
			if (nums[0] != 0) {
				swap(0, nums[0], nums);
				count++;
			}
			if (nums[0] == 0) {
				for (int j = 1; j < n; j++) {
					if (nums[j] != j) {
						swap(0, j, nums);
						count++;
						break;
					}
					if (j == n - 1)
						flag = true;
				}
			}
			if (flag)
				break;
		}
		System.out.println(count);
		s.close();
	}

	private void swap(int i, int j, int[] nums) {
		int t = nums[i];
		nums[i] = nums[j];
		nums[j] = t;
	}
}
