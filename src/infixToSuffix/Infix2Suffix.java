package infixToSuffix;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Infix2Suffix {

	/**
	 * 转成后缀
	 * 
	 * @param str
	 * @return
	 */
	public String[] toSuffix(String str) {
		String[] s = str.split(" ");
		Stack<String> stack = new Stack<>();
		List<String> list = new ArrayList<>();
		for (int i = 0; i < s.length; i++) {
			if ("(".equals(s[i])) {
				stack.push(s[i]);
			} else if ("+".equals(s[i]) || "-".equals(s[i]) || "*".equals(s[i])) {
				if (!stack.isEmpty()) {
					String s1 = stack.pop();
					if (comparePrior(s[i], s1)) {
						stack.push(s1);
					} else {
						list.add(s1);
					}
				}
				stack.push(s[i]);
			} else if (")".equals(s[i])) {
				while (!stack.isEmpty()) {
					String s1 = stack.pop();
					if (!"(".equals(s1)) {
						list.add(s1);
					} else {
						break;
					}
				}
			} else {
				list.add(s[i]);
			}
		}
		while (!stack.isEmpty()) {
			String s1 = stack.pop();
			list.add(s1);
		}
		return list.toArray(new String[0]);
	}

	/**
	 * 优先级比较
	 * 
	 * @param operator1
	 *            待比较量
	 * @param operator2
	 *            被比较量，栈顶的运算符
	 * @return 如果operator1优先级大于operator2，则返回true，否则返回false
	 */
	private boolean comparePrior(String operator1, String operator2) {
		if ("(".equals(operator2)) {
			return true;
		}
		if ("*".equals(operator1)) {
			if ("+".equals(operator2) || "-".equals(operator2)) {
				return true;
			}
		}
		return false;
	}
}
