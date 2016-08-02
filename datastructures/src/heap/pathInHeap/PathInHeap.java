package heap.pathInHeap;

import java.util.Scanner;

public class PathInHeap {
	int[] items;
	int currentSize = 0;

	public void solve() {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();		 // 插入元素的个数
		int m = s.nextInt();		 // 需要打印的路径的条数
		items = new int[n + 1];
		items[0] = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			int x = s.nextInt();
			insert(x);
		}
		for (int i = 0; i < m; i++) {
			int index = s.nextInt();
			while (index > 1) {
				System.out.print(items[index] + " ");
				index /= 2;
			}
			System.out.println(items[1]);
		}
		s.close();
	}

	private void insert(int x) {
		items[++currentSize] = x;
		int hole = currentSize;

		for (; hole > 1 && x < items[hole / 2]; hole = hole / 2) {
			items[hole] = items[hole / 2];
		}
		items[hole] = x;
	}
}
