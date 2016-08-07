package heap;

import utils.BinomialNode;

/**
 * 二项队列
 * @author ruiyao.shen
 *
 * @param <T>
 */
public class BinomialQueue<T extends Comparable<? super T>> {
	private static final int DEFULT_TREES = 1;
	private int currentSize;
	private BinomialNode<T>[] theTrees;
	
	@SuppressWarnings("unchecked")
	public BinomialQueue(){
		theTrees = new BinomialNode[DEFULT_TREES];
		makeEmpty();
	}
	
	@SuppressWarnings("unchecked")
	public BinomialQueue(T item){
		theTrees = new BinomialNode[DEFULT_TREES];
		theTrees[0] = new BinomialNode<T>(item);
	}
	
	/**
	 * 将rhs合并到优先队列中
	 * rhs会变成空的，而且rhs必须与当前二项队列不同
	 * @param rhs 需要合并的另一个二项队列
	 */
	public void merge(BinomialQueue<T> rhs){
		if(this == rhs)
			return;
		
		currentSize += rhs.currentSize;
		
		if(currentSize > capacity()){
			int maxLength = Math.max(theTrees.length, rhs.theTrees.length);
			expandTheTrees(maxLength + 1);
		}
		
		BinomialNode<T> carry = null;
		for(int i = 0,j = 1;j <= currentSize; i++, j*=2){
			BinomialNode<T> t1 = theTrees[i];
			BinomialNode<T> t2 = i < rhs.theTrees.length?rhs.theTrees[i]:null;
			
			int whichCase = t1 == null ? 0 : 1;
			whichCase += t2 == null ? 0 : 2;
			whichCase += carry == null ? 0 : 4;
			
			switch(whichCase){
			case 0:
			case 1:
				break;
			case 2:
				theTrees[i] = t2;
				rhs.theTrees[i] = null;
				break;
			case 4:
				theTrees[i] = carry;
				carry = null;
				break;
			case 3:
				carry = combineTrees(t1,t2);
				theTrees[i] = rhs.theTrees[i] = null;
				break;
			case 5:
				carry = combineTrees(t1,carry);
				theTrees[i] = null;
				break;
			case 6:
				carry = combineTrees(t2,carry);
				break;
			case 7:
				theTrees[i] = carry;
				carry = combineTrees(t1,t2);
				rhs.theTrees[i] = null;
				break;
			}
		}
		
		for(int k =0;k < rhs.theTrees.length; k++){
			rhs.theTrees[k] = null;
		}
		rhs.currentSize = 0;
	}
	
	/**
	 * 插入一个元素，即与一个元素的二项队列合并
	 * @param item 需要插入的元素
	 */
	public void insert(T item){
		merge(new BinomialQueue<T>(item));
	}
	
	public T findMin(){
		if(isEmpty())
			return null;
		return theTrees[findMinIndex()].element;
	}
	
	/**
	 * 删除值最小的元素
	 * @return 被删除的最小元素，若队列中没有元素，则返回空
	 */
	public T deleteMin(){
		if(isEmpty()){
			return null;
		}
		
		int minIndex = findMinIndex();
		T minItem = theTrees[minIndex].element;
		
		BinomialNode<T> deletedTree = theTrees[minIndex].leftChild;
		
		BinomialQueue<T> deletedQueue = new BinomialQueue<>();
		deletedQueue.expandTheTrees(minIndex + 1);
		
		deletedQueue.currentSize = (1<<minIndex) - 1;
		for(int j = minIndex - 1; j>=0; j--){
			deletedQueue.theTrees[j] = deletedTree;
			deletedTree = deletedTree.nextSibling;
			deletedQueue.theTrees[j].nextSibling = null;
		}
		
		theTrees[minIndex] = null;
		currentSize -= deletedQueue.currentSize + 1;
		merge(deletedQueue);
		return minItem;
	}
	
	/**
	 * 判断是否为空 
	 * @return
	 */
	public boolean isEmpty(){
		return currentSize == 0;
	}
	
	/**
	 * 删除所有元素，使队列为空
	 */
	public void makeEmpty(){
		currentSize = 0;
		for(int i=0;i<theTrees.length;i++)
			theTrees[i] = null;
	}
	
	/**
	 * 扩树
	 * @param newNumTrees
	 */
	@SuppressWarnings("unchecked")
	private void expandTheTrees(int newNumTrees){
		if(newNumTrees<=theTrees.length)
			return;
		BinomialNode<T>[] newTrees = new BinomialNode[newNumTrees];
		for(int i=0;i<theTrees.length;i++)
			newTrees[i] = theTrees[i];
		theTrees = newTrees;
	}
	
	/**
	 * 合并同样大小的两颗二项树
	 * @param t1   第一棵树
	 * @param t2   第二棵树
	 * @return 合并后的树
	 */
	private BinomialNode<T> combineTrees(BinomialNode<T> t1, BinomialNode<T> t2){
		if(t1.element.compareTo(t2.element) > 0)
			return combineTrees(t2, t1);
		t2.nextSibling = t1.leftChild;
		t1.leftChild = t2;
		return t1;
	}
	
	private int capacity(){
		return (1<<theTrees.length) - 1;
	}
	
	/**
	 * 返回最小值的序号
	 * @return 返回最小值的序号，如果不存在，返回-1
	 */
	private int findMinIndex(){
		if(theTrees.length==0){
			return -1;
		}
		int i;
		for(i = 0;theTrees[i]==null;i++)
			;
		int temp = i;
		for(int min=i+1;min<theTrees.length;min++){
			if(theTrees[i]!=null && theTrees[temp].element.compareTo(theTrees[min].element)>0)
				temp = i;
		}
		return temp;
	}
}
