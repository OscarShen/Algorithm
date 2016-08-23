package com.oscarshen09.sort.insertOrMerge;

import java.util.Arrays;
import java.util.Scanner;

public class InsertOrMerge {
	public void solve() {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();

		int[] origin = new int[n];
		int[] sorted = new int[n];

		for (int i = 0; i < n; i++) {
			origin[i] = s.nextInt();
		}
		for (int i = 0; i < n; i++) {
			sorted[i] = s.nextInt();
		}

		int firstReverse = firstReverse(sorted);

		boolean isInsert = true;

		for (int i = firstReverse; i < n; i++) {
			if (origin[i] != sorted[i]) {
				isInsert = false;
				break;
			}
		}

		if (isInsert) {
			System.out.println("Insertion Sort");
			int t = sorted[firstReverse];
			int i = firstReverse;
			for (; i > 0 && t < sorted[i - 1]; i--)
				sorted[i] = sorted[i - 1];
			sorted[i] = t;
		} else {
			System.out.println("Merge Sort");
			int step = 2;
			while (isOrdered(sorted, step))
				step *= 2;

			for (int i = 0; i < n; i += step) {
				if (i + step < n) {
					Arrays.sort(sorted, i, i + step - 1);
				} else {
					Arrays.sort(sorted, i, n - 1);
				}
			}
		}
		for (int j = 0; j < n; j++) {
			System.out.print(sorted[j] + " ");
		}
		s.close();
	}

	private boolean isOrdered(int[] sorted, int step) {
		for (int i = 0; i < sorted.length; i += step)
			for (int j = i + 1; j < sorted.length && j < i + step; j++) {
				if (sorted[i] > sorted[j])
					return false;
			}
		return true;
	}

	private int firstReverse(int[] array) {
		for (int i = 1; i < array.length; i++) {
			for (int j = 0; j < i; j++) {
				if (array[j] > array[i])
					return i;
			}
		}
		return -1;
	}
}
