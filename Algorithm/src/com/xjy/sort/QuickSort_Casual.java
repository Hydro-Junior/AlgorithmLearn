 package com.xjy.sort;

import java.util.Scanner;

//简单化的快排，只针对整数，不是很精细，相对于标准版，它应对不同输入的表现不是很稳定。
//1.扫描时遇到与pivot相等的元素并没有停下来，这会导致在有大量相同元素时的表现不好。
//2.面对已经排好序的序列，它的复杂度将达到O(n*2)
public class QuickSort_Casual {
	public static void quickSort(int[] a, int lo, int hi) {
		if (lo >= hi) {
			return;
		}
		int i = lo;
		int j = hi;
		int pivot = a[lo];
		while (i < j) {
			//这里肯定要先移动j,因为如果先移动i,i会停留在大元素，而之后j会过不去（i<j）,最后和lo交换的是j，这会导致程序出错。
			while (i < j && a[j] >= pivot) {
				j--;
			}
			while (i < j && a[i] <= pivot) {
				i++;
			}
			if (i < j) {
				int tmp = a[i];
				a[i] = a[j];
				a[j] = tmp;
			}
		}
		a[lo] = a[j];
		a[j] = pivot;
		quickSort(a, lo, j - 1);
		quickSort(a, j + 1, hi);
	}
	
	public static void main(String[] args) {
		int[] testDemo = new int[] {3,5,1,2,6,6,7,9,12,54,23,1,0};
		quickSort(testDemo,0,testDemo.length-1);
		for(int i = 0 ; i< testDemo.length;i++) {
			System.out.print(testDemo[i]+" ");
		}
	}
}
