package com.xjy.sort;
/**
 * 标准递归（自上而下）的归并排序
 * @author Mr.Xu
 *
 */
public class MergeSort {
	public static <T extends Comparable<? super T>> void sort(T[] a) {
		@SuppressWarnings("unchecked")
		T[] tmpArray = (T[])new Comparable[a.length];
		mergeSort(a,tmpArray,0,a.length-1);
	}
	
	private static <T extends Comparable<? super T>> void mergeSort(T[] a ,T[] tmpArr,int left,int right) {
		if(left < right) {
			int mid = (left + right)/2;
			mergeSort(a,tmpArr,left,mid);
			mergeSort(a,tmpArr,mid+1,right);
			merge(a,tmpArr,left,mid+1,right);
		}
	}
	
	private static <T extends Comparable<? super T>> void merge(T[] a ,T[] tmp,int leftPos,int rightPos ,int rightEnd) {
		int startIndex = leftPos;
		int leftStop = rightPos;
		int rightStop = rightEnd+1;
		int elementsLen = rightEnd - leftPos + 1;
		while(leftPos < leftStop && rightPos < rightStop) {
			if(ltOrEq(a[leftPos],a[rightPos])) {
				tmp[startIndex++] = a[leftPos++];
			}else {
				tmp[startIndex++] = a[rightPos++];
			}
		}
		while(leftPos < leftStop) {
			tmp[startIndex++] = a[leftPos++];
		}
		while(rightPos < rightStop) {
			tmp[startIndex++] = a[rightPos++];
		}
		for(int i=0 ; i < elementsLen; i++,rightEnd--) {
			a[rightEnd] = tmp[rightEnd];
		}
	}
	
	
	private static <T extends Comparable<? super T>> boolean ltOrEq(T t1 , T t2){
		if(t1.compareTo(t2)<=0) {return true;}
		return false;
	}
	public static void show(Object[] obs) {
		for(int i = 0 ; i < obs.length; i++) {
			System.out.print(obs[i]+" ");
		}
	}
	
	public static void main(String[] args) {
		Integer[] arrs = new Integer[] {12,23,53,83,21,3,2,6,5,53,-2,5,-2,73};
		MergeSort.sort(arrs);
		show(arrs);
	}
}
