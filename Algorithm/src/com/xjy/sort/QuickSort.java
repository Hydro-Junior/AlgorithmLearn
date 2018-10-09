package com.xjy.sort;

import java.util.Random;
/**
 * 
 * @Description 
 * 快排标准版
 * @author Mr.Xu
 * @date 2018年9月19日 下午7:05:04
 *
 */
public class QuickSort {
	
	public static void sort(Comparable[] a) {
		shuffle(a);//打乱，避免输入序列的影响
		sort(a,0,a.length-1);
	}
	
	private static void sort(Comparable[] a, int lo, int hi) {
		if(hi <= lo) return;
		int j = partition(a,lo,hi);
		sort(a,lo,j-1);
		sort(a, j+1,hi);
	}
	private static int partition(Comparable[] a,int lo,int hi) {
		int i = lo, j = hi+1;
		Comparable pivot = a[lo];
		while(true) {
			//扫描过程中建议在遇到与pivot等值的元素时停下，尽管这样会有不必要的交换，否则在遇到数组有很多相同元素时，算法复杂度可能达到平方级别（设想所有元素都相同）。
			//此处还需注意的是，能否从i = lo + 1开始，循环条件中a[++i]改为a[i++],答案肯定是不行的，
			//使用++i，循环体中的i和条件中的i是一个值，而i++,循环体中的i为条件中的i+1！j的道理也一样。
			while(less(a[++i],pivot)) {
				if(i == hi)break;
			}
			while(less(pivot,a[--j])) {
				if(j == lo)break;//此行可以注释掉
			}
			if(i >= j) break;
			exch(a,i,j);
		}
		exch(a,lo,j);
		return j;
	}
	//辅助程序，打乱顺序 O(n)
	public static void shuffle(Object[] a) {
		if(a == null) return;
        Random rd = new Random();
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int r = i + rd.nextInt(n-i);     // between i and n-1
            Object temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }
	private static boolean less(Comparable a, Comparable b) {
		if(a.compareTo(b) < 0) return true;
		return false;
	}
	private static void exch(Comparable[] a, int i, int j) {
		Comparable tmp  = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}
	/**
	 * test
	 * @param args
	 */
	public static void main(String[] args) {
		Integer[] a = new Integer[] {1,2,3,7,33,2,13,13,34,25,32,25,53,-3,354,33,25,23,16,8,2,6};
		Integer[] b = new Integer[] {5,4,3,2,1};
		QuickSort.sort(a);
		QuickSort.sort(b);
		for(int i : a) {
			System.out.print(i+" ");
		}
		System.out.println();
		for(int i : b) {
			System.out.print(i+" ");
		}
	}
	
}
