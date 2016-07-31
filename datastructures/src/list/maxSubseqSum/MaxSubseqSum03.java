package list.maxSubseqSum;

/**
 * Online processing algorithm
 * 
 * @author ruiyao.shen
 *
 */
public class MaxSubseqSum03 {
	/**
	 * Online processing algorithm
	 * 
	 * @param a
	 *            array
	 * @param n
	 *            longth of array a
	 */
	public int maxSum3(int a[], int n) {
		int thisSum = 0, maxSum = 0;
		for (int i = 0; i < n; i++) {
			thisSum += a[i];
			if (thisSum > maxSum) {
				maxSum = thisSum;
			}
			// if thisSum can't increase the value of maxSum, we discard it
			// and let it be zero.
			if (thisSum < 0) {
				thisSum = 0;
			}
		}

		return maxSum;
	}
}
