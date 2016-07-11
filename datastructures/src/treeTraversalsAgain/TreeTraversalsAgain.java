package treeTraversalsAgain;

import java.util.Scanner;
import java.util.Stack;

public class TreeTraversalsAgain {

	public String[] record() { 
		
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		String[] input = new String[2 * n];
		for (int i = 0; i < 2 * n; i++) {
			input[i] = s.nextLine();
		}
		s.close();
		return input;
	}

	public Node buildTree(String[] record) {
		String rootString = record[0];
		Node root = new Node(Integer.parseInt(rootString.charAt(5) + ""));

		Stack<Node> stack = new Stack<>();
		stack.push(root);
		for (int i = 1; i < record.length; i++) {
			String[] s = record[i].split(" ");
			if ("push".equals(s[0])) {
				Node temp = new Node(Integer.parseInt(record[i]));
				if (record[i - 1].contains("pop")) {
					stack.peek().right = temp;
					stack.push(temp);
				} else {
					stack.peek().left = temp;
					stack.push(temp);
				}
			} else {
			}
		}
		return null;
	}

	class Node {
		int data;
		Node left;
		Node right;

		public Node(int data) {
			this.data = data;
			this.left = null;
			this.right = null;
		}
	}
}
