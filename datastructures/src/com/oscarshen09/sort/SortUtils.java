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
		bubbleSort(arr, 0, arr.length);
	}

	/**
	 * 冒泡排序
	 * 
	 * @param arr
	 * @param begin
	 * @param end
	 */
	public static <T extends Comparable<? super T>> void bubbleSort(T[] arr, int begin, int end) {
		if (begin < 0 || end > arr.length)
			throw new ArrayIndexOutOfBoundsException();
		for (int i = begin; i < end; i++) {
			boolean flag = false; // 表示一次遍历是否改变过位置
			for (int j = end - 1; j > i; j--) {
				if (arr[j].compareTo(arr[j - 1]) < 0) {
					T t = arr[j - 1];
					arr[j - 1] = arr[j];
					arr[j] = t;
					flag = true;
				}
			}
			if (!flag)
				return;
		}
	}

	/**
	 * 插入排序
	 * 
	 * @param arr
	 */
	public static <T extends Comparable<? super T>> void insertionSort(T[] arr) {
		insertionSort(arr, 0, arr.length);
	}

	/**
	 * 插入排序
	 * 
	 * @param arr
	 * @param begin
	 * @param end
	 */
	public static <T extends Comparable<? super T>> void insertionSort(T[] arr, int begin, int end) {
		int j;
		for (int i = begin; i < end; i++) {
			T tmp = arr[i];
			for (j = i; j > 0 && tmp.compareTo(arr[j - 1]) < 0; j--)
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
		shellSort(arr, 0, arr.length);
	}

	/**
	 * 希尔排序
	 * 
	 * @param arr
	 * @param begin
	 * @param end
	 */
	public static <T extends Comparable<? super T>> void shellSort(T[] arr, int begin, int end) {
		int j;
		for (int gap = (end - begin) / 2; gap > 0; gap /= 2) {
			for (int i = gap; i < arr.length; i++) {
				T tmp = arr[i];
				for (j = i; j >= gap && tmp.compareTo(arr[j - gap]) < 0; j -= gap) {
					arr[j] = arr[j - gap];
				}
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
		heapSort(arr, 0, arr.length);
	}

	/**
	 * 堆排序
	 * 
	 * @param arr
	 * @param begin
	 * @param end
	 */
	public static <T extends Comparable<? super T>> void heapSort(T[] arr, int begin, int end) {
		int n = end - begin;
		for (int i = n / 2; i >= 0; i--)
			percDown(arr, i, n);
		for (int i = n - 1; i > 0; i--) {
			swapReferences(arr, 0, i);
			percDown(arr, 0, i);
		}
	}

	/**
	 * 交换当前最大值与最后一个位置
	 * 
	 * @param arr
	 * @param deletedIndex
	 * @param position
	 */
	private static <T extends Comparable<? super T>> void swapReferences(T[] arr, int deletedIndex, int position) {
		T tmp = arr[0];
		arr[0] = arr[position];
		arr[position] = tmp;
	}

	/**
	 * 堆的下滤
	 * 
	 * @param arr
	 * @param begin
	 * @param end
	 */
	private static <T extends Comparable<? super T>> void percDown(T[] arr, int begin, int end) {
		int child;
		T tmp;
		for (tmp = arr[begin]; leftChild(begin) < end; begin = child) {
			child = leftChild(begin);
			if (child != end - 1 && arr[child].compareTo(arr[child + 1]) < 0)
				child++;
			if (tmp.compareTo(arr[child]) < 0)
				arr[begin] = arr[child];
			else
				break;
		}
		arr[begin] = tmp;
	}

	/**
	 * 左儿子序号(数组从零开始计算)
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
		mergeSort(arr, 0, arr.length);
	}

	/**
	 * 归并排序
	 * 
	 * @param arr
	 * @param begin
	 * @param end
	 */
	public static <T extends Comparable<? super T>> void mergeSort(T[] arr, int begin, int end) {
		@SuppressWarnings("unchecked")
		T[] tmp = (T[]) new Comparable[arr.length];
		mergeSort(arr, tmp, 0, end - 1);
	}

	/**
	 * 归并排序驱动程序，创建一个空的数组
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
	 * 合并已经排序好的数组
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
		int count = rightEnd - leftPos + 1;

		while (leftPos <= leftEnd && rightPos <= rightEnd) {
			if (arr[leftPos].compareTo(arr[rightPos]) <= 0)
				tmp[cur++] = arr[leftPos++];
			else
				tmp[cur++] = arr[rightPos++];
		}

		while (leftPos <= leftEnd)
			tmp[cur++] = arr[leftPos++];
		while (rightPos <= rightEnd)
			tmp[cur++] = arr[rightPos++];

		for (int i = 0; i < count; i++, rightEnd--)
			arr[rightEnd] = tmp[rightEnd];
	}

	@Test
	public void test() {
		Integer[] arr = { 3, 2, 4, 5, 1, 1, 4, 5, 54, 2, 34, 1, 3, 6 };
		heapSort(arr);
		for (Integer i : arr) {
			System.out.print(i + "\t");
		}
	}
}
