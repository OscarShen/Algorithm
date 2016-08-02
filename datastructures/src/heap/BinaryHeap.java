package heap;

/**
 * 实现一个大顶堆
 * @author ruiyao.shen
 *
 */
public class BinaryHeap<T extends Comparable<? super T>> {
	private static final int DEFAULT_CAPACITY = 10;
	private int currentSize;		//堆中元素的数量
	private T[] array;				//堆的数组
	
	public BinaryHeap() {
		this(DEFAULT_CAPACITY);
	}
	
    @SuppressWarnings("unchecked")
	public BinaryHeap( int capacity )
    {
        currentSize = 0;
        array = (T[]) new Comparable[ capacity + 1 ];
    }
	
	public BinaryHeap(T[] items) {
		
	}
	
	/**
	 * 在保持堆序的情况下，插入一个元素到优先队列中
	 * @param item 要插入的元素
	 */
	public void insert(T item) {
		if(currentSize == array.length - 1)
			enlargeArray(array.length * 2 + 1);
		//上滤
		int hole = ++currentSize;
		for(; hole > 1 && item.compareTo(array[hole/2]) > 0; hole /= 2) {
			array[hole] = array[hole/2];
		}
		array[hole] = item;
	}
	
	/**
	 * 扩大堆的大小
	 * @param newCapacity
	 */
	private void enlargeArray(int newCapacity) {
		@SuppressWarnings("unchecked")
		T[] newArray = (T[]) new Comparable[newCapacity];
		System.arraycopy(array, 0, newArray, 0, array.length);
		array = newArray;
	}

	/**
	 * 找到堆中最大的元素
	 * @return 如果堆中没有元素，返回空，否则返回堆中最大的元素
	 */
	public T findMax() {
		if(isEmpty()){
			return null;
		}
		return array[1];
	}
	
	/**
	 * 删除堆中最大的元素
	 * @return 如果堆中没有元素，返回空，否则删除堆中最大的元素
	 */
	public T deleteMax() {
		if(isEmpty()){
			return null;
		}
		T maxItem = findMax();
		array[1] = array[currentSize--];
		percolateDown(1);
		
		return maxItem;
	}
	
	/**
	 * 将指定位置的元素下潜到合适的位置
	 * @param hole
	 */
	private void percolateDown(int hole) {
		int child;
		T temp = array[hole];
		for(;hole * 2 <= currentSize;hole = child){
			child = hole * 2;
			if(child != currentSize && array[child+1].compareTo(array[child]) > 0){
				child++;
			}
			if(array[child].compareTo(temp) > 0 ){
				array[hole] = array[child];
			} else {
				break;
			}
		}
		array[hole] = temp;
	}

	/**
	 * 判断此堆是否为空
	 * @return 如果为空，返回true；否则，返回false
	 */
	public boolean isEmpty() {
		return currentSize == 0;
	}
	
	/**
	 * 清除堆中所有内容
	 */
	public void makeEmpty() {
		currentSize = 0;
	}
}
