/**
 * 
 */
package com.xjy.sort;

import java.util.Random;

/**
 * @Description 
 * 三向切分的快排，适用于有大量相同元素的数组，比如字符排序。
 * @author Mr.Xu
 * @date 2018年9月19日 下午7:37:06
 *
 */
public class Quick3way {
	public static void sort(Comparable[] a) {
		shuffle(a);//打乱，避免输入序列的影响
		sort(a,0,a.length-1);
	}
	
	private static void sort(Comparable[] a, int lo, int hi) {
		if(hi <= lo) return;
		//三向切分中几个元素的动态变化需要注意,lo:切分后的左边界，gt:切分后的右边界
		int lt = lo, i = lo + 1 , gt=hi;
		Comparable v = a[lo];
		while(i <= gt) {
			int cmp = a[i].compareTo(v);
			//三种比较结果的情况下，3个指标的变化需要细细品味
			if(cmp < 0) exch(a,lt++,i++);//把较小值与左边界值交换，各自往前1步
			else if(cmp > 0) exch(a,i,gt--);//把较大值移到后面，注意此时，gt往前但i是不变的，因为接下来还得将换过来的值和v比较。
			else i++;//相等的话i继续往前走
		}
		//lt到hi之间的值都为v！
		sort(a,lo,lt-1);
		sort(a,gt+1,hi);
	}
	
	
	private static void exch(Comparable[] a, int i, int j) {
		Comparable tmp  = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}
	//辅助程序，打乱顺序 O(n)
	private static void shuffle(Object[] a) {
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
}
