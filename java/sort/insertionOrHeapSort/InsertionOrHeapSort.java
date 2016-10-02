package com.oscarshen09.sort.insertionOrHeapSort;

import java.util.Scanner;

import org.junit.Test;

public class InsertionOrHeapSort {
	@Test
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
			int t = sorted[0];
			int i = n - 1;
			for (; i > 0 && t < sorted[i]; i--)
				;
			sorted[0] = sorted[i];
			sorted[i] = t;

			int child;
			int j = 0;
			for (t = sorted[0]; 2 * j + 1 < i; j = child) {
				child = 2 * j + 1;
				if (child + 1 < i && sorted[child] < sorted[child + 1]) {
					child++;
				}
				if (sorted[child] > t)
					sorted[j] = sorted[child];
				else
					break;
			}
			sorted[j] = t;
		}
		for (int j = 0; j < n; j++) {
			System.out.print(sorted[j] + " ");
		}

		s.close();
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
