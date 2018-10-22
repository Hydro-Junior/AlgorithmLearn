package com.xjy.classify.dp;

import java.util.Scanner;

import org.junit.Test;

/**
 * @author Mr.Xu 最长公共子序列(longest-common-sequence)
 */
public class LCS {
	/**
	 * 问题描述：给定两个序列，找出它们的最长公共子序列，比如序列X{A,B,C,D,I,G,T},序列Y{B,Q,D,G,M,A},它们的LCS就是{B,D,G}
	 */
	// 解决方案：子问题分为三种情况：(假设有序列X(m)和序列Y(n),m和n为元素个数)
	// 1. 如果x[m]和y[n]相等，LCS为X(m-1)和Y(n-1)的子序列加上x[m](或y[n])
	// 2. x[m]和y[n]不相等,考虑LCS为X(m-1)和Y(n)的子序列
	// 3. x[m]和y[n]不相等,考虑LCS为X(m)和Y(n-1)的子序列(选取2和3中更长者)

	/**
	 * 递归式： c[i][j] = 0 when i = 0 or j = 0; c[i][j] = c[i-1][j-1] + 1 when i,j > 0
	 * and x[i] = y[j] c[i][j] = max{c[i-1][j],c[i][j-1]} when i,j > 0 and x[i] !=
	 * y[j]
	 */
	private static int[][] len; // 用来存储每对序列的LCS的长度
	private static int[][] book; // 用来标记，便于打印出序列,只有1,2,3这3个值，代表3种情况
	private static char[] x ;
	private static char[] y;
	/**
	 * 找到最长公共子序列的长度并作好标记
	 * @param x
	 * @param y
	 */
	public static void findLCS() {
		int m = x.length - 1;
		int n = y.length - 1;
		len = new int[m + 1][n + 1];
		book = new int[m + 1][n + 1];
		for (int i = 0; i < m + 1; i++) {
			len[i][0] = 0;
		}
		for (int j = 0; j < n + 1; j++) {
			len[0][j] = 0;
		}
		for (int i = 1; i < m + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				if (x[i] == y[j]) {
					len[i][j] = len[i - 1][j - 1] + 1;
					book[i][j] = 1;
				} else if (len[i - 1][j] >= len[i][j - 1]) {
					len[i][j] = len[i - 1][j];
					book[i][j] = 2;
				} else {
					len[i][j] = len[i][j - 1];
					book[i][j] = 3;
				}
			}
		}
		System.out.println("最长子序列长度： " + len[m][n]);
	}
	
	/**
	 * 打印LCS（但是这种方式只能打印其中一组，而最长子序列可能会有多个解）
	 */
	public static void printLCS(int i, int j) {
		if (i == 0 || j == 0) {
			return;
		}
		if (book[i][j] == 1) {
			printLCS(i - 1, j - 1);
			System.out.print(x[i]);
		} else if (book[i][j] == 2) {
			printLCS( i - 1, j);
		} else {
			printLCS(i, j - 1);
		} 
	}
	/**
	 * 不用标记数组book，利用回溯打印所有解
	 */
	public static void traceBack( char[] x , char[] y) {
		
	}
	@Test
	public void testLCS() {
		@SuppressWarnings("unused")
		Scanner sc = new Scanner(System.in);
		int m = sc.nextInt();
		int n = sc.nextInt();
		x = new char[m+1];
		y = new char[n+1];
		for(int i = 1 ; i <= m ; i++) {
			x[i] = sc.next().charAt(0);
		}
		for(int j = 1 ; j <= n ; j ++) {
			y[j] = sc.next().charAt(0);
		}
		findLCS();
		printLCS(m,n);
	}
}
