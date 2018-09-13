package com.xjy.adt;
/**
 * 索引优先队列（此类为最小堆）
 */
import java.util.Arrays;
import java.util.NoSuchElementException;

public class MyIndexMinPQ<Key extends Comparable<? super Key>> {
	private int n;
	private int[] pq;
	private int[] qp;
	private Key[] keys;

	public MyIndexMinPQ(int maxN) {
		n = 0;
		pq = new int[maxN + 1];
		qp = new int[maxN + 1];
		keys = (Key[]) new Comparable[maxN + 1];
		Arrays.fill(qp, -1);
	}

	public boolean isEmpty() {
		return n == 0;
	}

	public boolean contains(int i) {
		return qp[i] != -1;
	}

	public int size() {
		return n;
	}
	public void insert(int i, Key key) {
		if(contains(i)) {
			//replace
			changeKey(i,key);
		}
		n++;
		qp[i] = n;
		pq[n] = i;
		keys[i] = key;
		swim(n);
	}
	public int minIndex() {
		if (n == 0) throw new NoSuchElementException("Priority queue underflow");
		return pq[1];
	}
	public Key minKey() {
		if (n == 0) throw new NoSuchElementException("Priority queue underflow");
		return keys[pq[1]];
	}
	
	public int delMin() {
		int min = pq[1];
		exch(1,n--);
		sink(1);
		qp[min] = -1;
		keys[min] = null;
		pq[n+1] = -1;
		return min;
	}
	public void changeKey(int i, Key key) {
		if(contains(i)) {
			keys[i] = key;
			swim(qp[i]);
			sink(qp[i]);
		}
	}
	public void delete(int i) {
		int idx = qp[i];
		exch(idx,n--);
		swim(idx);
		sink(idx);
		keys[i] = null;
		qp[i] = -1;
	}
	/***************************************************************************
	 * Helper functions.
	 ***************************************************************************/
	private boolean greater(int i , int j) {
		return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
	}
	private void exch(int i , int j) {
		int temp = pq[i];
		pq[i] = pq[j];
		pq[j] = temp;
		qp[pq[i]] = i;
		qp[pq[j]] = j;
	}
	private void swim(int k) {
		while(k > 1 && greater(k/2,k)) {
			exch(k,k/2);
			k = k/2;
		}
	}
	private void sink(int k) {
		while(2*k <= n) {
			int j = 2*k;
			if(j < n && greater(j,j+1)) j++;//choose the less child
			if(!greater(k,j)) break;
			exch(k,j);
			k = j;
		}
	}
}
