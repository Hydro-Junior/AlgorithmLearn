package com.xjy.sort;

import java.util.Scanner;

public class QuickSort {
	public static void quickSort(int[] a, int lo, int hi) {
		if (lo >= hi) {
			return;
		}
		int i = lo;
		int j = hi;
		int pivot = a[lo];
		while (i < j) {
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
