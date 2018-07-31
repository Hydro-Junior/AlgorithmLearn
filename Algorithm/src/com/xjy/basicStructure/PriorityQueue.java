package com.xjy.basicStructure;
/**
 * @Discripton
 * 优先队列
 * 二叉堆是一组能够用堆有序的完全二叉树排序的元素，并在数组中按照层级存储（不使用数组的第一个位置）
 * 不使用数组下标为0的位置可带来更多方便
 * 这里以最大堆为例
 * @author Mr.Xu
 * 
 */
public class PriorityQueue<T extends Comparable<? super T>> {
	private T[] pq;
	private int N = 0;
	public PriorityQueue() {
		pq = (T[])new Comparable[10+1];
	}
	public PriorityQueue(int maxN) {
		pq = (T[])new Comparable[maxN+1];
	}
	public boolean isEmpty() {
		return N==0;
	}
	public int size() {
		return N;
	}

	public void insert(T v) {
		//动态增大数组
		if(N == pq.length - 1 ) {
			int newLen = N * 2;
			T[] newPQ = (T[])new Comparable[1+newLen];
			for(int i = 1 ; i <= N; i++) {
				newPQ[i] = pq[i];
			}
			pq = newPQ;
		}
		
		pq[++N] = v;
		swim(N);
	}
	public T deleteMax() {
		//动态减小数组
		if(N < (pq.length-1)/2) {
			int newLen = pq.length/2;
			T[] newPQ = (T[])new Comparable[1+newLen];
			for(int i = 1 ; i <= N; i++) {
				newPQ[i] = pq[i];
			}
			pq = newPQ;
			
		}
		if(isEmpty()) {return null;}
		T max = pq[1];
		pq[1] = pq[N--];
		pq[N+1] = null;//防止对象游离；
		sink(1);
		return max;
	}
	/**
	 * 一些辅助方法
	 */
	//小于
	private boolean less(int i ,int j) {
		return pq[i].compareTo(pq[j]) < 0;
	}
	//大于
	private void exch(int i,int j) {
		T tmp = pq[i];
		pq[i] = pq[j];
		pq[j] = tmp;
	}
	//大值上浮
	private void swim(int k) {
		while(k > 1 && less(k, k/2)) {
			exch(k,k/2);
			k = k/2;
		}
	}
	//小值下沉
	public void sink(int k) {
		while(2*k <= N) {
			int j = k * 2;
			if(j+1 <= N && less(j,j+1)) {
				j++;
			}
			if(!less(k,j)) break;
			exch(k,j);
			k=j;
			
		}
	}
}
