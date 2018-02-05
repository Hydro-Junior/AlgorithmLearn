package com.xjy.dp;

import java.util.Scanner;

import org.junit.Test;

/**
 * ��̬�滮�Ĵ��� 1. �̻�һ�����Ž�Ľṹ���� 2. �ݹ�ض������Ž��ֵ 3. �Ե����ϵؼ������Ž��ֵ 4. ���ü��������Ϣ����һ�����Ž�
 * 
 * @author Mr.Xu
 *
 */
public class MatrixChain {
	static int[][] m; //������ų˴�
	static int[][] s; //������ŷָ��

	/**
	 * ���������� ����һ�������󣬸����Ǽ����ţ����ý����ʹ������˹����о����ĳ˷��������� ; 
	 * A1*A2ʱ�������ĳ˷�������p0 * p1 * p2������p0��A1��������p1��A1������Ҳ��A2������,p2��A2������;
	 * �ʶ���Ai��������p(i-1),Ai��������Pi 
	 * A1*A2*A3�ĳ˴���p0*p1*p2 + p0*p2*p3, A1*(A2*A3)�ĳ˴���p1*p2*p3 + p0*p1*p3,���߲�ͬ
	 * ��һ��������˵ľ���ĸ���n��֮�������һ����������������о������������n+1��
	 */
	// �ӽṹ��������ⷽ����m[i,j] = MIN{(i <= k < j)(m[i,k] + m[k+1,j] + p(i-1)*p(k)*p(j))
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
		for (int len = 2; len < count + 1; len++) { // len��ʾ�������ĳ���
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
		// ��ӡ���ٵĳ˴�
		System.out.println(m[1][count]);
		// ���������������
		printOptimalParens(s, 1, count);

	}
	//�õݹ�ķ�ʽ��ӡ�������ʱ�ļ������
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
