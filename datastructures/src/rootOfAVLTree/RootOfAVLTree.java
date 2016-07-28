package rootOfAVLTree;

import java.util.Scanner;

import utils.AVLTree;

public class RootOfAVLTree {
	public static void main(String[] args) {
		int num;
		int value;
		AVLTree avl = null;
		Scanner s = new Scanner(System.in);
		num = s.nextInt();
		for (int i = 0; i < num; i++) {
			value = s.nextInt();
			avl = insert(avl, value);
		}
		s.close();
		System.out.println(avl.data);
	}

	private static AVLTree insert(AVLTree avl, int value) {
		if (avl == null) {
			avl = new AVLTree();
			avl.left = avl.right = null;
			avl.data = value;
			avl.height = 0;
		} else if (value < avl.data) {
			avl.left = insert(avl.left, value);
			if ((getHeight(avl.left) - getHeight(avl.right)) == 2) {
				if (value < avl.left.data) {
					avl = singleLeftRotation(avl);
				} else {
					avl = doubleLeftRotation(avl);
				}
			}
		} else if (value > avl.data) {
			avl.right = insert(avl.right, value);
			if ((getHeight(avl.right) - getHeight(avl.left)) == 2) {
				if (value > avl.right.data) {
					avl = singleRightRotation(avl);
				} else {
					avl = doubleRightRotation(avl);
				}
			}
		}
		avl.height = Math.max(getHeight(avl.left), getHeight(avl.right)) + 1;
		return avl;
	}

	private static int getHeight(AVLTree avl) {
		if (avl == null)
			return 0;
		else
			return avl.height;
	}

	private static AVLTree singleLeftRotation(AVLTree avl) {
		AVLTree temp = avl.left;
		avl.left = temp.right;
		temp.right = avl;
		avl.height = Math.max(getHeight(avl.left), getHeight(avl.right)) + 1;
		temp.height = Math.max(getHeight(temp.left), getHeight(temp.right)) + 1;
		return temp;
	}

	private static AVLTree doubleLeftRotation(AVLTree avl) {
		avl.left = singleRightRotation(avl.left);
		return singleLeftRotation(avl);
	}

	private static AVLTree singleRightRotation(AVLTree avl) {
		AVLTree temp = avl.right;
		avl.right = temp.left;
		temp.left = avl;
		avl.height = Math.max(getHeight(avl.left), getHeight(avl.right)) + 1;
		temp.height = Math.max(getHeight(temp.left), getHeight(temp.right)) + 1;
		return temp;
	}

	private static AVLTree doubleRightRotation(AVLTree avl) {
		avl.right = singleLeftRotation(avl.right);
		return singleRightRotation(avl);
	}
}
