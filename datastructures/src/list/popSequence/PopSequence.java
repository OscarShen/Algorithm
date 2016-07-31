package list.popSequence;

import java.util.Scanner;
import java.util.Stack;

public class PopSequence {
	private int M;// the maximum capacity of the stack
	private int N;// the length of push sequence
	private int K;// the number of pop sequences to be checked

	/**
	 * 外部调用的主程序
	 */
	public void popSequence() {
		boolean isPossible;
		String[] str = scanner();
		for (int i = 0; i < K; i++) {
			isPossible = isPossible(str[i]);
			print(isPossible);
		}
	}

	/**
	 * 打印是否有可能产生某一顺序
	 * @param isPossible
	 */
	private void print(boolean isPossible) {
		if (isPossible) {
			System.out.println("YES!");
		} else {
			System.out.println("NO!");
		}

	}

	/**
	 * 判断栈的弹出顺序是否可能符合
	 * @param str
	 * @return
	 */
	private boolean isPossible(String str) {
		String[] s = str.split(" ");
		int[] nums = new int[N];
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(s[i]);
		}
		Stack<Integer> stack = new Stack<>();
		int cur = 1;
		stack.push(cur++);
		int i = 0;
		int temp = nums[i++];
		while (!stack.isEmpty() || cur <= N) {
			if (stack.isEmpty()) {
				stack.push(cur++);
			}
			while (stack.peek() != temp) {
				stack.push(cur);
				if (cur > N) {
					return false;
				}
				cur++;
			}
			if (stack.size() > M) {
				return false;
			}
			stack.pop();
			if (i < N) {
				temp = nums[i++];
			}
		}

		return true;
	}

	/**
	 * 扫描输入的数据
	 * @return
	 */
	private String[] scanner() {
		Scanner s = new Scanner(System.in);
		String str = s.nextLine();
		String[] s1 = str.split(" ");
		M = Integer.parseInt(s1[0]);
		N = Integer.parseInt(s1[1]);
		K = Integer.parseInt(s1[2]);
		System.out.println("M=" + M + "\tN=" + N + "\tK=" + K);

		String[] s2 = new String[K];
		for (int i = 0; i < K; i++) {
			s2[i] = s.nextLine();
		}
		s.close();

		return s2;
	}
}
