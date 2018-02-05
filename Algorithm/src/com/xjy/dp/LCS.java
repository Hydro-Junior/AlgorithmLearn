package com.xjy.dp;

import java.util.Scanner;

import org.junit.Test;

/**
 * @author Mr.Xu �����������(longest-common-sequence)
 */
public class LCS {
	/**
	 * ���������������������У��ҳ����ǵ�����������У���������X{A,B,C,D,I,G,T},����Y{B,Q,D,G,M,A},���ǵ�LCS����{B,D,G}
	 */
	// ����������������Ϊ���������(����������X(m)������Y(n),m��nΪԪ�ظ���)
	// 1. ���x[m]��y[n]��ȣ�LCSΪX(m-1)��Y(n-1)�������м���x[m](��y[n])
	// 2. x[m]��y[n]�����,����LCSΪX(m-1)��Y(n)��������
	// 3. x[m]��y[n]�����,����LCSΪX(m)��Y(n-1)��������(ѡȡ2��3�и�����)

	/**
	 * �ݹ�ʽ�� c[i][j] = 0 when i = 0 or j = 0; c[i][j] = c[i-1][j-1] + 1 when i,j > 0
	 * and x[i] = y[j] c[i][j] = max{c[i-1][j],c[i][j-1]} when i,j > 0 and x[i] !=
	 * y[j]
	 */
	private static int[][] len; // �����洢ÿ�����е�LCS�ĳ���
	private static int[][] book; // ������ǣ����ڴ�ӡ������,ֻ��1,2,3��3��ֵ������3�����
	private static char[] x ;
	private static char[] y;
	/**
	 * �ҵ�����������еĳ��Ȳ����ñ��
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
		System.out.println("������г��ȣ� " + len[m][n]);
	}
	
	/**
	 * ��ӡLCS���������ַ�ʽֻ�ܴ�ӡ����һ�飬��������п��ܻ��ж���⣩
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
	 * ���ñ������book�����û��ݴ�ӡ���н�
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
