package com.xjy.sort;
/**
 * 堆排序，升序排列 reference : Algorithm 4th
 * @author Mr.Xu
 *
 */
public class HeapSort {
	
	public static <T extends Comparable<? super T>> void sort(T[] a) {
		int n = a.length;
		//下沉法建立堆
		for(int k= n/2 ; k > 0; k--) {
			sink(a,k,n);
		}
		//不断将当前最大值与最后一个元素交换并下沉，元素个数减一
		while(n > 1) {
			exch(a,1,n--);
			sink(a,1,n);
		}
	}
	
	
	//下沉操作，k表示数组中的第k个数，而不是下标
	private static <T extends Comparable<? super T>> void sink(T[] a, int k, int n) {
		while(2*k <= n ) {
			int j = 2*k;
			if(j< n && less(a,j,j+1)) {j += 1;}//两个儿子的更大者
			if(!less(a,k,j)) break;//是最大堆，跳出循环
			exch(a,k,j);//调整
			k = j;
		}
	}
	//上浮操作(没用到)
	private static <T extends Comparable<? super T>> void swim(T[] a , int k) {
		while(k/2 > 0) {
			if(less(a,k/2,k)) {exch(a,k/2,k);}
			k = k/2;
		}
	}
	//辅助方法
	private static <T extends Comparable<? super T>> boolean less(T[] a , int i , int j) {
		if(a[i-1].compareTo(a[j-1])<0) {return true;}
		return false;
	}
	private static  void exch(Object[] a , int i , int j){
		Object tmp = a[i-1];
		a[i-1] = a[j-1];
		a[j-1] = tmp;
	}
	public static void show(Object[] a) {
		for(int i = 0 ; i < a.length; i++) {
			System.out.print(a[i]+ " ");
		}
	}
	//测试方法
	public static void main(String[] args) {
		Integer[] nums = new Integer[]{-1,2,5,23,0,-1,34,235,-5,2354};
		String[] ss = new String[] {"shg","124","2354","98","defgghn","dkeg","degggggs","zedg","yes","No"};
		HeapSort.sort(ss);
		HeapSort.show(ss);
	}
}
