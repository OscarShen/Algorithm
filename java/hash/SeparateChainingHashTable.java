package com.oscarshen09.hash;

import java.util.LinkedList;
import java.util.List;

/**
 * 使用分离链接法的散列(separate chainning)
 * 
 * @author ruiyao.shen
 *
 */
public class SeparateChainingHashTable<T> {
	private static final int DEFAULT_TABLE_SIZE = 101;
	private int currentSize;
	private List<T>[] theLists;

	public SeparateChainingHashTable() {
		this(DEFAULT_TABLE_SIZE);
	}

	@SuppressWarnings("unchecked")
	public SeparateChainingHashTable(int size) {
		theLists = new LinkedList[nextPrime(size)];
		for (int i = 0; i < theLists.length; i++) {
			theLists[i] = new LinkedList<T>();
		}
	}

	/**
	 * 将一个元素插入哈希表中，如果这个元素已经被包含了，则不做任何事
	 * 
	 * @param item 需要插入的元素
	 */
	public void insert(T item) {
		List<T> whichList = theLists[myhash(item)];
		if (!whichList.contains(item)) {
			whichList.add(item);
			if (++currentSize > theLists.length)
				rehash();
		}
	}

	/**
	 * 删除某个特定的元素
	 * 
	 * @param item 需要删除的元素
	 */
	public void remove(T item) {
		List<T> whichList = theLists[myhash(item)];
		if (whichList.contains(item)) {
			whichList.remove(item);
			currentSize--;
		}
	}

	/**
	 * 在哈希表中找到这个元素
	 * 
	 * @param item 需要寻找的元素
	 * @return 找到返回true
	 */
	public boolean contains(T item) {
		List<T> whichList = theLists[myhash(item)];
		return whichList.contains(item);
	}

	/**
	 * 使这个哈希表逻辑上为空
	 */
	public void makeEmpty() {
		for (int i = 0; i < theLists.length; i++) {
			theLists[i].clear();
		}
		currentSize = 0;
	}

	/**
	 * 将哈希表的容量扩大两倍
	 */
	@SuppressWarnings("unchecked")
	private void rehash() {
		List<T>[] oldLists = theLists;
		theLists = new List[nextPrime(2 * theLists.length)];
		for (int j = 0; j < theLists.length; j++)
			theLists[j] = new LinkedList<>();
		currentSize = 0;
		for (int i = 0; i < oldLists.length; i++)
			for (T item : oldLists[i])
				insert(item);
	}

	/**
	 * 获得一个哈希值，这个值与哈希表中链表的个数有关
	 * 
	 * @param item 需要获取哈希值的元素
	 * @return 该元素的哈希值
	 */
	private int myhash(T item) {
		int hashVal = item.hashCode();

		hashVal %= theLists.length;
		if (hashVal < 0) {
			hashVal += theLists.length;
		}
		return hashVal;
	}

	/**
	 * 得到输入值的下一个质数
	 * 
	 * @param n 输入的整数
	 * @return 下一个质数
	 */
	private static int nextPrime(int n) {
		if (n % 2 == 0)
			n++;
		while (!isPrime(n))
			n += 2;
		return n;
	}

	/**
	 * 判断一个整数是否为质数
	 * 
	 * @param n 输入的整数
	 * @return 质数返回true
	 */
	private static boolean isPrime(int n) {
		if (n == 2 || n == 3)
			return true;
		if (n == 1 || n % 2 == 0)
			return false;
		for (int i = 3; i * i <= n; i += 2)
			if (n % i == 0)
				return false;
		return true;
	}
}
