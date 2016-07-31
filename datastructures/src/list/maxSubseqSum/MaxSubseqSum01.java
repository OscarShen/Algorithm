package list.maxSubseqSum;

/**
 * Two exhaustive attack methods. Time complexity is O(n3) and O(n2).
 * 
 * @author Oscar.Shen
 *
 */
public class MaxSubseqSum01 {
	/**
	 * exhaustive attack method 1
	 * 
	 * @param a
	 *            array
	 * @param n
	 *            longth of array a
	 */
	public int maxSum(int a[], int n) {
		int i, j, k;
		int maxSum = 0, thisSum;
		for (i = 0; i < n; i++) {
			for (j = i; j < n; j++) {
				thisSum = 0;
				for (k = i; k <= j; k++) {
					thisSum += a[k];
				}
				if (thisSum > maxSum) {
					maxSum = thisSum;
				}
			}
		}
		return maxSum > 0 ? maxSum : 0;
	}

	/**
	 * exhaustive attack method 2
	 * 
	 * @param a
	 *            array
	 * @param n
	 *            longth of array a
	 */
	public int maxSum2(int a[], int n) {
		int i, j;
		int maxSum = 0, thisSum;
		for (i = 0; i < n; i++) {
			thisSum = 0;
			for (j = i; j < n; j++) {
				thisSum += a[j];
				if (thisSum > maxSum) {
					maxSum = thisSum;
				}
			}
		}
		return maxSum > 0 ? maxSum : 0;
	}
}
