package com.oscarshen09.hash;

public class QuadraticProbingHashTable<T> {
	private static final int DEFAULT_TABLE_SIZE = 11;
	private HashEntry<T>[] array;
	private int currentSize;

	public QuadraticProbingHashTable() {
		this(DEFAULT_TABLE_SIZE);
	}

	public QuadraticProbingHashTable(int size) {
		allocateArray(size);
		makeEmpty();
	}

	/**
	 * 使散列表为空
	 */
	public void makeEmpty() {
		currentSize = 0;
		for (int i = 0; i < array.length; i++) {
			array[i] = null;
		}
	}

	/**
	 * 判断是否包含item
	 * 
	 * @param item 被判断的元素
	 * @return true为包含
	 */
	public boolean contains(T item) {
		int currentPos = findPos(item);

		return isActive(currentPos);
	}

	/**
	 * 插入元素item
	 * 
	 * @param item 需要插入的元素
	 */
	public void insert(T item) {
		int currentPos = findPos(item);
		if (isActive(currentPos)) {
			return;
		}
		array[currentPos] = new HashEntry<>(item, true);
		if (++currentSize > array.length / 2) {
			rehash();
		}
	}

	/**
	 * 移除元素item
	 * 
	 * @param item 需要移除的元素
	 */
	public void remove(T item) {
		int currentPos = findPos(item);
		if (isActive(currentPos)) {
			array[currentPos].isActive = false;
		}
	}

	/**
	 * 重新分配array空间
	 * 
	 * @param size 大小
	 */
	@SuppressWarnings("unchecked")
	private void allocateArray(int size) {
		array = new HashEntry[size];
	}

	/**
	 * 使用元素找到它在array中的位置
	 * 
	 * @param item 需要寻找的元素
	 * @return 被找到元素的位置号
	 */
	private int findPos(T item) {
		int offset = 1;
		int currentPos = myhash(item);

		while (array[currentPos] != null && !array[currentPos].item.equals(item)) {
			currentPos += offset;
			offset += 2;
			if (currentSize >= array.length)
				currentPos -= array.length;
		}
		return currentPos;
	}

	/**
	 * 返回适用于此散列表的元素的hashcode
	 * 
	 * @param item 需要被计算的元素
	 * @return 计算得到的hashcode
	 */
	private int myhash(T item) {
		return Math.abs((item.hashCode() % array.length));
	}

	/**
	 * 判断位于特定位置上的元素是否是存在的
	 * 
	 * @param currentPos 检查该位置
	 * @return true说明没有删除
	 */
	private boolean isActive(int currentPos) {
		return array[currentPos] != null && array[currentPos].isActive;
	}

	/**
	 * 扩容重新建立array
	 */
	private void rehash() {
		HashEntry<T>[] old = array;
		allocateArray(nextPrime(2 * array.length));
		currentSize = 0;
		for (int i = 0; i < old.length; i++)
			insert(array[i].item);
	}

	/**
	 * 获取大于n的下一个质数
	 * 
	 * @param n 输入的数字
	 * @return 下一个质数
	 */
	private int nextPrime(int n) {
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

	private static class HashEntry<T> {
		public T item;
		public boolean isActive;

		public HashEntry(T item, boolean b) {
			this.item = item;
			isActive = b;
		}
	}

	public static void main(String[] args) {
		QuadraticProbingHashTable<String> q = new QuadraticProbingHashTable<>();
		q.insert("Oscar");
		q.insert("mei");
		q.insert("adfsa");
		q.insert("afsdfgs");
		q.remove("Oscar");
	}
}
