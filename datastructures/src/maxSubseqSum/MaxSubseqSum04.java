package maxSubseqSum;

/**
 * Online processing algorithm
 * 
 * @author ruiyao.shen
 *
 */
public class MaxSubseqSum04 {
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
		int start = 0, end = 0;
		for (int i = 0; i < n; i++) {
			thisSum += a[i];
			if (thisSum > maxSum) {
				maxSum = thisSum;
				end = i;
			}
			// if thisSum can't increase the value of maxSum, we discard it
			// and let it be zero.
			if (thisSum < 0) {
				thisSum = 0;
			}
		}

		int temp = 0;
		int j;
		for (j = end; temp < maxSum; j--) {
			temp += a[j];
		}
		start = j + 1;

		System.out.print(maxSum + " ");
		for(int k=start;k<=end;k++){
			System.out.print(a[k]+" ");
		}
		System.out.println();

		return maxSum;
	}
}
