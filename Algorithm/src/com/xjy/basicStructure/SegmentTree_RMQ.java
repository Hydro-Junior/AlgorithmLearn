package com.xjy.basicStructure;

import java.util.Random;

/**
 * 实现基本的线段树(多用于统计或处理连续的数字)，并完成Range Minimum Query(RMQ)操作
 * @author Mr.Xu
 *
 */
public class SegmentTree_RMQ {
	private static int[] dat;
	private int n;
	public SegmentTree_RMQ(int n_){
		if(n < 0) {throw new IllegalArgumentException();}
		n = 1;
		while(n < n_) {
			n *= 2;
		}
		dat = new int[2*n-1];
		for(int i = 0 ; i < dat.length; i++) {
			dat[i] = Integer.MAX_VALUE;
		}
	}
	//把第k个值更新为a
	public void update(int k , int a) {
		k += n-1;//定位到叶子节点
		dat[k] = a;
		//向上更新
		while(k > 0) {
			k = (k-1)/2;
			dat[k] = Math.min(dat[k*2+1], dat[k*2+2]);
		}
	}
	//求[a,b)的最小值,后面的参数为计算方便而传入
	//k为节点的编号，lo，hi表示这个节点对应的是[lo,hi)区间
	private int query(int a, int b, int k, int lo,int hi) {
		//如果[a,b)和[lo,hi)不相交，返回最大整数值
		if(hi <= a || b <= lo) return Integer.MAX_VALUE;
		//如果[a,b)完全包含[lo,hi),返回当前节点的值
		if(a <= lo && hi <= b) return dat[k];
		else {
			int v1 = query(a, b, k*2+1,lo,(lo+hi)/2);
			int v2 = query(a, b, k*2+2,(lo+hi)/2,hi);
			return Math.min(v1, v2);
		}
	}
	public int queryMin(int a, int b) {
		return query(a,b,0,0,n);
	}
	//简单测试
	public static void main(String[] args) {
		//初始化要查询的区间
		Random rd = new Random();
		int[] test = new int[10000];
		for(int i = 0 ; i < 10000; i++) {
			test[i] = rd.nextInt(100);
		}
		//建立线段树
		SegmentTree_RMQ rmq = new SegmentTree_RMQ(10000);
		for(int i = 0 ; i < 10000; i++) {
			rmq.update(i, test[i]);
		}
		System.out.println("numbers in [1000,1020):");
		for(int i = 1000; i < 1020 ; i++) {
			System.out.print(test[i]+" ");
		}
		System.out.println("\nMinimum：" + rmq.queryMin(1000,1020));
	}
}
