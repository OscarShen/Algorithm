package com.oscarshen09.heap.huffmanCodes;

import java.util.Scanner;

/**
 * 比较输入的编码是否属于哈夫曼树
 * 
 * @author ruiyao.shen
 *
 */
public class HuffmanCodes {
	public void solve() {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		int[] chars = new int[256];
		for (int i = 0; i < n; i++) {
			char ch = s.next().charAt(0);
			int fre = s.nextInt();
			chars[ch] = fre;
		}
		int wpl = getWPL(chars);

		int k = s.nextInt();
		for (int i = 0; i < k; i++) {
			int thisWpl = 0;
			String[] numbers = new String[n];
			for (int j = 0; j < n; j++) {
				char ch = s.next().charAt(0);
				String number = s.next();
				numbers[j] = number;
				thisWpl += number.length() * chars[ch];
			}
			if (thisWpl == wpl && !hasPrefix(numbers))
				System.out.println("Yes");
			else
				System.out.println("No");
		}
		s.close();
	}

	/**
	 * 判断一个字符串数组是否拥有前缀
	 * 
	 * @param numbers
	 * @return
	 */
	private boolean hasPrefix(String[] numbers) {
		int len = numbers.length;
		for (int i = 0; i < len; i++) {
			for (int j = i + 1; j < len; j++) {
				if (isPrefix(numbers[i], numbers[j])) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 判断两个字符串其中一个是否是另一个的前缀
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	private boolean isPrefix(String s1, String s2) {
		int len = s1.length() > s2.length() ? s2.length() : s1.length();
		int i = 0;
		for (; i < len; i++) {
			if (s1.charAt(i) != s2.charAt(i)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 获取chars的wpl（带权路径长度之和）
	 * 
	 * @param chars
	 * @return
	 */
	private int getWPL(int[] chars) {
		int[] heap = new int[64];
		int size = 0;
		for (int i = 0; i < 256; i++) {
			if (chars[i] != 0) {
				heap[++size] = chars[i];
			}
		}
		heap[0] = size;
		buildMinHeap(heap);
		int wpl = 0;
		for (int i = 1; i < size; i++) {
			int w1 = deleteMin(heap);
			int w2 = deleteMin(heap);
			wpl += w1 + w2;
			insertHeap(heap, w1 + w2);
		}
		return wpl;
	}

	/**
	 * 将i插入堆中
	 * 
	 * @param heap
	 * @param i
	 */
	private void insertHeap(int[] heap, int i) {
		heap[++heap[0]] = i;
		int j = heap[0];
		for (; j > 1 && heap[j] < heap[j / 2]; j /= 2) {
			heap[j] = heap[j / 2];
		}
		heap[j] = i;
	}

	/**
	 * 删除小顶堆的一个元素
	 * 
	 * @param heap
	 * @return
	 */
	private int deleteMin(int[] heap) {
		int temp = heap[1];
		heap[1] = heap[heap[0]--];
		percolateDown(heap, 1);
		return temp;
	}

	/**
	 * 建立一个小顶堆
	 * 
	 * @param heap
	 */
	private void buildMinHeap(int[] heap) {
		for (int i = heap[0] / 2; i > 0; i--) {
			percolateDown(heap, i);
		}
	}

	/**
	 * 下滤
	 * 
	 * @param heap
	 * @param i
	 */
	private void percolateDown(int[] heap, int i) {
		int child;
		int temp = heap[i];
		for (; i * 2 <= heap[0]; i = child) {
			child = i * 2;
			if (child != heap[0] && heap[child] > heap[child + 1]) {
				child++;
			}
			if (temp > heap[child]) {
				heap[i] = heap[child];
			} else {
				break;
			}
		}
		heap[i] = temp;
	}
}
