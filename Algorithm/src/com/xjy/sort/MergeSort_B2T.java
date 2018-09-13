package com.xjy.sort;
/**
 * 自底向上的归并排序，这种实现代码量更少  reference : Algorithm 4th
 * @author Mr.Xu
 *
 */
public class MergeSort_B2T {
	private static Comparable[] aux; //辅助数组

	//挺精简的merge方式，比T2B中自己写的merge要好些
	public static void merge(Comparable[] a, int lo, int mid, int hi) {
		int i = lo, j = mid + 1;
		for(int k = lo; k <= hi ; k++) {
			aux[k] = a[k];
		}
		for(int k = lo; k <= hi; k++) {
			if(i > mid) a[k] = aux[j++];
			else if(j > hi) a[k] = aux[i++];
			else if(less(aux[j],aux[i])) a[k] = aux[j++];
			else a[k] = aux[i++];
		}
	}
	
	public static boolean less(Comparable a, Comparable b) {
		return a.compareTo(b) < 0;
	}
	
	public static void sort(Comparable[] a) {
		int N = a.length;
		aux = new Comparable[N];
		//sz表示每次归并的两个小数组的size，自然是从1开始
		for(int sz = 1; sz < N ; sz = sz + sz) {
			for(int lo = 0; lo < N - sz; lo += sz + sz) {
				merge(a,lo,lo+sz-1,Math.min(lo+sz+sz-1, N-1));
			}
		}
	}
}
