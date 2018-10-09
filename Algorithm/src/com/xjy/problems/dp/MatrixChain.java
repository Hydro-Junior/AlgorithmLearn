package com.xjy.problems.dp;

import java.util.Scanner;

import org.junit.Test;

/**
 * 动态规划四大步骤 1. 刻画一个最优解的结构特征 2. 递归地定义最优解的值 3. 自底向上地计算最优解的值 4. 利用计算出的信息构造一个最优解
 * @author Mr.Xu
 *
 */
public class MatrixChain {
	static int[][] m; //用来存放乘次
	static int[][] s; //用来存放分割点

	/**
	 * 问题描述： 给定一连串矩阵，给它们加括号，利用结合律使它们相乘过程中经历的乘法步骤最少 ; 
	 * A1*A2时，经历的乘法步骤是p0 * p1 * p2，其中p0是A1的行数，p1是A1的列数也是A2的行数,p2是A2的列数;
	 * 故定义Ai的行数是p(i-1),Ai的列数是Pi 
	 * A1*A2*A3的乘次是p0*p1*p2 + p0*p2*p3, A1*(A2*A3)的乘次是p1*p2*p3 + p0*p1*p3,两者不同
	 * 第一行输入相乘的矩阵的个数n，之后输入第一个矩阵的行数和所有矩阵的列数，共n+1个
	 */
	// 子结构和最优求解方案：m[i,j] = MIN{(i <= k < j)(m[i,k] + m[k+1,j] + p(i-1)*p(k)*p(j))
	public static void matrixChainOrder() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int count = sc.nextInt();
		int[] p = new int[count + 1];
		for (int i = 0; i <= count; i++) {
			p[i] = sc.nextInt();
		}
		m = new int[count + 1][count + 1];
		s = new int[count + 1][count + 1];
		/*
		 * for(int i = 0 ; i < count + 1 ; i ++) { m[i][i] = 0; }
		 */
		int q = Integer.MAX_VALUE;
		int j;
		for (int len = 2; len < count + 1; len++) { // len表示矩阵链的长度
			for (int i = 1; i <= count - len + 1; i++) {
				j = i + len - 1;
				m[i][j] = Integer.MAX_VALUE;
				for (int k = i; k < j; k++) {
					q = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
					if (q < m[i][j]) {
						m[i][j] = q;
						s[i][j] = k;
					}
				}

			}
		}
		// 打印最少的乘次
		System.out.println(m[1][count]);
		// 给出具体运算过程
		printOptimalParens(s, 1, count);

	}
	//用递归的方式打印结果最优时的计算过程
	public static void printOptimalParens(int[][] s , int i ,int j) {
		if(i == j) {
			System.out.print("A"+i); 
		}else {
			System.out.print("(" );
			printOptimalParens(s, i, s[i][j]);
			printOptimalParens(s, s[i][j]+1, j);
			System.out.print(")");
		}
	}

	@Test
	public void test() {
		matrixChainOrder();
	}
}
