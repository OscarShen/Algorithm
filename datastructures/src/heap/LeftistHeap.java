package heap;

import utils.LeftistNode;

/**
 * 实现一个左式堆(小顶堆）
 * @author ruiyao.shen
 * @param <T>
 *
 */
public class LeftistHeap<T extends Comparable<? super T>> {
	private LeftistNode<T> root;
	
	public LeftistHeap(){}
	
	/**
	 * 与另一个左式堆合并
	 * @param anotherHeap 另一个左式堆
	 */
	public void merge(LeftistHeap<T> anotherHeap){
		if(this == anotherHeap){
			return;
		}
		root = merge(root,anotherHeap.root);
		anotherHeap.root = null;
	}
	
	/**
	 * 合并的驱动程序
	 * @param h1
	 * @param h2
	 * @return
	 */
	public LeftistNode<T> merge(LeftistNode<T> h1, LeftistNode<T> h2) {
		if(h1 == null)
			return h2;
		if(h2 == null)
			return h1;
		if(h1.element.compareTo(h2.element) < 0)
			return merge1(h1,h2);
		else
			return merge1(h2,h1);
	}

	/**
	 * 递归调用merge1
	 * @param h1
	 * @param h2
	 * @return
	 */
	private LeftistNode<T> merge1(LeftistNode<T> h1, LeftistNode<T> h2) {
		if(h1.left == null){
			h1.left = h2;
		} else {
			h1.right = merge(h1.right,h2);
			if(h1.left.npl<h1.right.npl){
				swapChildren(h1);
			}
			h1.npl = h1.right.npl + 1;
		}
		return h1;
	}
	

	/**
	 * 交换两个子节点
	 * @param node
	 */
	private void swapChildren(LeftistNode<T> node) {
		LeftistNode<T> temp = node.left;
		node.left = node.right;
		node.right = temp;
	}

	/**
	 * 插入
	 * @param item 如要插入的元素
	 */
	public void insert(T item){
		root = merge(new LeftistNode<T>(item), root);
	}
	
	/**
	 * 找出最小值
	 * @return 最小值元素
	 */
	public T findMin(){
		return root.element;
	}
	
	/**
	 * 删除最小值
	 * @return 被删除的最小值元素
	 */
	public T deleteMin(){
		if(isEmpty())
			return null;
		T minItem = root.element;
		root = merge(root.left,root.right);
		return minItem;
	}
	
	/**
	 * 判断是否为空
	 * @return 为空，返回true，否则，返回false
	 */
	public boolean isEmpty(){
		return root==null;
	}
	
	/**
	 * 使此堆为空
	 */
	public void makeEmpty(){
		root = null;
	}
	
	
}
