package list.maxSubseqSum;

/**
 * divide-and-conquer
 * 
 * @author Oscar.Shen
 *
 */
public class MaxSubseqSum02 {
	/**
	 * divide-and-conquer
	 * 
	 * @param a
	 *            array
	 * @param n
	 *            longth of array a
	 */
	public int maxSum3(int a[], int n) {
		return maxSubSum3(a, 0, a.length - 1);
	}

	private int maxSubSum3(int[] a, int left, int right) {
		if (left == right) {
			return a[left];
		}
		int center = (left + right) / 2;
		int maxLeftSum = maxSubSum3(a, left, center);
		int maxRightSum = maxSubSum3(a, center + 1, right);

		// Finding the max sum of numbers in the left across border.
		int maxLeftBorderSum = 0, thisLeftBorderSum = 0;
		for (int i = center; i >= left; i--) {
			thisLeftBorderSum += a[i];
			if (thisLeftBorderSum > maxLeftBorderSum) {
				maxLeftBorderSum = thisLeftBorderSum;
			}
		}

		// Finding the max sum of numbers in the right across border.
		int maxRightBorderSum = 0, thisRightBorderSum = 0;
		for (int j = center + 1; j <= right; j++) {
			thisRightBorderSum += a[j];
			if (thisRightBorderSum > maxRightBorderSum) {
				maxRightBorderSum = thisRightBorderSum;
			}
		}

		int max = max3(maxLeftSum, maxRightSum, maxLeftBorderSum + maxRightBorderSum);

		return max;
	}

	/**
	 * 
	 * @param a
	 * @param b
	 * @param c
	 * @return max of three numbers
	 */
	private int max3(int a, int b, int c) {
		if (a < b) {
			a = b;
		}
		if (a < c) {
			a = c;
		}
		return a;
	}
}
