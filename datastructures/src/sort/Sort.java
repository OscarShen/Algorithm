package sort;

//import org.junit.Test;

/**
 * 各种排序方法
 * 
 * @author ruiyao.shen
 *
 */
public class Sort {
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
				if (arr[j - 1].compareTo(arr[j]) > 0) {
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

//	@Test
//	public void test() {
//		Integer[] arr = { 3, 2, 4, 5, 1, 7, 6, 9, 8 };
//		bubbleSort(arr);
//		for (Integer i : arr) {
//			System.out.print(i + "\t");
//		}
//	}
}
