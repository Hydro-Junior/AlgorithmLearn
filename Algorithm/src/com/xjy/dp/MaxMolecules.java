package com.xjy.dp;

import org.junit.Test;

/**
 * 
 * @author Mr.Xu
 *输入4个数表示分子的质量，然后输入化合物的质量
 *该化合物最多可由多少分子组成，返回分子数
 */
public class MaxMolecules {
	//1.自顶向下递归解决（找到子问题是关键），但是这样循环次数很多
	public static int max(int a , int b) {
		return a >= b ? a:b; 
	}
	public static int findMaxMolecules(int massA , int massB , int massC, int massD , int massX) {
		if(massX == 0) {
			return 0;
		}
		if(massX < 0) {
			return -99999;
		}
		int[] a = new int[]{massA , massB , massC , massD};
		int q = 0;
		for(int i = 0 ; i < a.length ; i++) {
			q = max(q,1 + findMaxMolecules(massA , massB , massC, massD , massX - a[i]));
		}
		return q;		
	}
	//2.动态规划：带备忘的自顶向下
	private static int[] r = new int[10000];
	static{
		for(int i = 0 ; i < r.length ; i++) {
		r[i] = -99999;
	}
	}
	public static int findMaxMolecules2(int massA , int massB , int massC, int massD , int massX) {
		int q = 0;
		if(r[massX] >= 0) {
			return r[massX];
		}
		if(massX == 0) {
			q = 0;
		}
		/*if(massX < 0) {
			q = -9999;
		}*/
		int[] a = new int[]{massA , massB , massC , massD};
		for(int i = 0 ; i < a.length ; i++) {
			q = max(q,1 + findMaxMolecules(massA , massB , massC, massD , massX - a[i]));
		}
		r[massX] = q;
		return q;		
	}
	//3.动态规划：自底向上
	public static int findMaxMolecules3(int massA , int massB , int massC, int massD , int massX) {
		r[0] = 0;
		int q ;
		int[] a = new int[]{massA , massB , massC , massD};
		for(int j = 1 ; j <= massX ; j ++) {
			q = -99999;
			for(int i = 0 ; i < a.length && a[i] <= j ; i ++) {
				q = max(q , 1 + r[j - a[i]]);
			}
			r[j] = q;
		}
		for(int j = 1 ; j <= massX ; j ++) {
			System.out.println(r[j]); //输出所有结果，负数表示该化合物的质量不可能由提供的几种分子组成b
		}
		return r[massX];
	}
	@Test
	public void test() {
		int res = findMaxMolecules3(5, 8, 10, 6, 23);
		System.out.println(res);
	}                
}
