package com.xjy.utils;

import org.junit.Test;

public class MathUtil {
	//����ֵ
	public static double abs(double b) {
		if(b < 0.0) {return -b;}
		else {return b;}
	}
	public static int abs(int b) {
		if(b < 0) {return -b;}
		else {return b;}
	}
	
	//ŷ����������Լ��
	public static int gcd(int p , int q) {
		if(q == 0) {return p;}
		int r = p % q;
		return gcd(q , r);
	}
	//�ж�һ�����Ƿ�Ϊ����
	public static boolean isPrime(int N) {
		if(N < 2) {return false;}
		for(int i = 2 ; i * i <= N ; i++) {
			if(N % i == 0) return false;
		}
		return true;
	}
	
	//ţ�ٵ������󿪷��� X(n+1) = X(n) - f(X(n))/f'(X(n)) --> X(n+1) = X(n)-(X(n)*X(n)-k)/2X(n)
	//--> X(n+1) = (X(n)*X(n)+k)/2Xn
	public static double sqrt(double k) {
		if(k < 0) {return Double.NaN;}
		double err = 1e-15;
		double t = k;
		while(abs(t - k/t) > err * t) {
			t = (k/t + t)/2.0;
		}
		return t;
	}
	
	@Test
	public void test() {
		System.out.println(sqrt(6.25));
		
	}
	
	
}
