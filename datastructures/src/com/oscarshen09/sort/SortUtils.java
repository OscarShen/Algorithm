package com.oscarshen09.sort;

import org.junit.Test;

/**
 * 各种排序方法
 * 
 * @author ruiyao.shen
 *
 */
public class SortUtils {
	/**
	 * 冒泡排序
	 * 
	 * @param arr
	 */
	public static <T extends Comparable<? super T>> void bubbleSort(T[] arr) {
		for (int i = 0; i < arr.length; i++) {
			boolean flag = false;// false表示未执行交换
			for (int j = arr.length - 1; j > i; j--) {
				if (arr[j].compareTo(arr[j - 1]) < 0) {
					T tmp = arr[j];
					arr[j] = arr[j - 1];
					arr[j - 1] = tmp;
					flag = true;
				}
			}
			if (!flag)
				break;
		}
	}

	/**
	 * 插入排序
	 * 
	 * @param arr
	 */
	public static <T extends Comparable<? super T>> void insertionSort(T[] arr) {
		insertionSort(arr, 0, arr.length - 1);
	}

	/**
	 * 插入排序
	 * 
	 * @param arr
	 * @param left
	 * @param right
	 */
	public static <T extends Comparable<? super T>> void insertionSort(T[] arr, int left, int right) {
		for (int i = left + 1; i <= right; i++) {
			T tmp = arr[i];
			int j;
			for (j = i; j > left && tmp.compareTo(arr[j - 1]) < 0; j--)
				arr[j] = arr[j - 1];
			arr[j] = tmp;
		}
	}

	/**
	 * 希尔排序
	 * 
	 * @param arr
	 */
	public static <T extends Comparable<? super T>> void shellSort(T[] arr) {
		for (int gap = arr.length / 2; gap > 0; gap /= 2) {
			for (int i = gap; i < arr.length; i++) {
				T tmp = arr[i];
				int j;
				for (j = i; j >= gap && tmp.compareTo(arr[j - gap]) < 0; j -= gap)
					arr[j] = arr[j - gap];
				arr[j] = tmp;
			}
		}
	}

	/**
	 * 堆排序
	 * 
	 * @param arr
	 */
	public static <T extends Comparable<? super T>> void heapSort(T[] arr) {
		for (int i = arr.length / 2; i >= 0; i--)
			percDown(arr, i, arr.length);
		for (int i = arr.length - 1; i > 0; i--) {
			swapReferences(arr, 0, i);
			percDown(arr, 0, i);
		}
	}

	/**
	 * 交换数组中i与j位置的元素
	 * 
	 * @param arr
	 * @param i
	 * @param j
	 */
	private static <T extends Comparable<? super T>> void swapReferences(T[] arr, int i, int j) {
		T tmp = arr[j];
		arr[j] = arr[i];
		arr[i] = tmp;
	}

	/**
	 * 将顶端元素下滤
	 * 
	 * @param arr
	 * @param i
	 *            需要下滤元素的序号
	 * @param length
	 *            当前可移动的元素总数
	 */
	private static <T extends Comparable<? super T>> void percDown(T[] arr, int i, int length) {
		T tmp;
		int child;
		for (tmp = arr[i]; leftChild(i) < length; i = child) {
			child = leftChild(i);
			if (child != length - 1 && arr[child].compareTo(arr[child + 1]) < 0)
				child++;
			if (tmp.compareTo(arr[child]) < 0)
				arr[i] = arr[child];
			else
				break;
		}
		arr[i] = tmp;
	}

	/**
	 * 求出初始为0二叉树的左儿子序号
	 * 
	 * @param i
	 * @return
	 */
	private static int leftChild(int i) {
		return 2 * i + 1;
	}

	/**
	 * 归并排序
	 * 
	 * @param arr
	 */
	public static <T extends Comparable<? super T>> void mergeSort(T[] arr) {
		@SuppressWarnings("unchecked")
		T[] tmp = (T[]) new Comparable[arr.length];
		mergeSort(arr, tmp, 0, arr.length - 1);
	}

	/**
	 * 归并排序递归程序
	 * 
	 * @param arr
	 * @param tmp
	 * @param left
	 * @param right
	 */
	private static <T extends Comparable<? super T>> void mergeSort(T[] arr, T[] tmp, int left, int right) {
		if (left < right) {
			int center = (left + right) / 2;
			mergeSort(arr, tmp, left, center);
			mergeSort(arr, tmp, center + 1, right);
			merge(arr, tmp, left, center + 1, right);
		}
	}

	/**
	 * 合并
	 * 
	 * @param arr
	 * @param tmp
	 * @param leftPos
	 * @param rightPos
	 * @param rightEnd
	 */
	private static <T extends Comparable<? super T>> void merge(T[] arr, T[] tmp, int leftPos, int rightPos,
			int rightEnd) {
		int leftEnd = rightPos - 1;
		int cur = leftPos;
		int n = rightEnd - leftPos + 1;
		while (leftPos <= leftEnd && rightPos <= rightEnd) {
			if (arr[leftPos].compareTo(arr[rightPos]) < 0)
				tmp[cur++] = arr[leftPos++];
			else
				tmp[cur++] = arr[rightPos++];
		}

		while (leftPos <= leftEnd)
			tmp[cur++] = arr[leftPos++];
		while (rightPos <= rightEnd)
			tmp[cur++] = arr[rightPos++];

		for (int i = 0; i < n; i++, rightEnd--)
			arr[rightEnd] = tmp[rightEnd];
	}

	/**
	 * 快排中使用插入排序的阈值
	 */
	private static final int CUTOFF = 10;

	/**
	 * 快速排序
	 * 
	 * @param arr
	 */
	public static <T extends Comparable<? super T>> void quickSort(T[] arr) {
		quickSort(arr, 0, arr.length - 1);
	}

	/**
	 * 快速排序递归程序
	 * 
	 * @param arr
	 * @param left
	 * @param right
	 */
	private static <T extends Comparable<? super T>> void quickSort(T[] arr, int left, int right) {
		if (CUTOFF + left <= right) {
			T pivot = median3(arr, left, right);

			int i = left, j = right - 1;
			for (;;) {
				while (arr[++i].compareTo(pivot) < 0) {
				}
				while (arr[--j].compareTo(pivot) > 0) {
				}
				if (i < j)
					swapReferences(arr, i, j);
				else
					break;
			}

			swapReferences(arr, i, right - 1);
			quickSort(arr, left, i - 1);
			quickSort(arr, i + 1, right);
		} else
			insertionSort(arr, left, right);

	}

	/**
	 * 三数取中值
	 * 
	 * @param arr
	 * @param left
	 * @param right
	 * @return
	 */
	private static <T extends Comparable<? super T>> T median3(T[] arr, int left, int right) {
		int center = (left + right) / 2;
		if (arr[center].compareTo(arr[left]) < 0)
			swapReferences(arr, left, center);
		if (arr[right].compareTo(arr[left]) < 0)
			swapReferences(arr, left, right);
		if (arr[right].compareTo(arr[center]) < 0)
			swapReferences(arr, center, right);

		// 将枢纽元与arr[right-1]交换
		swapReferences(arr, center, right - 1);
		return arr[right - 1];
	}

	@Test
	public void test() {
		Integer[] arr = { 3, 2, 4, 5, 1, 1, 4, 5, 54, 2, 34, 1, 3, 6 };
		quickSort(arr);
		for (Integer i : arr) {
			System.out.print(i + "\t");
		}
	}
}
