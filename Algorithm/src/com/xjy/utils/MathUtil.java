package com.xjy.utils;

import org.junit.Test;

import java.util.*;

public class MathUtil {
	//绝对值
	public static double abs(double b) {
		if(b < 0.0) {return -b;}
		else {return b;}
	}
	public static int abs(int b) {
		if(b < 0) {return -b;}
		else {return b;}
	}
	
	//欧几里得求最大公约数
	public static int gcd(int p , int q) {
		if(q == 0) {return p;}
		int r = p % q;
		return gcd(q , r);
	}

	//最小公倍数
	public static int lcm(int m, int n) {
		return m/gcd(m,n)*n;
	}

	//判断一个数是否为素数 O(√n)
	public static boolean isPrime(int N) {
		if(N < 2) {return false;}
		for(int i = 2 ; i * i <= N ; i++) {
			if(N % i == 0) return false;
		}
		return true;
	}
	//约数枚举
	public static List<Integer> divisor(int n){
		List<Integer> res = new ArrayList<>();
		for(int i = 1; i * i <= n; i++){
			if(n % i == 0){
				res.add(i);
			}
			if(i != n/i) res.add(n/i);
		}
		return res;
	}
	//整数分解
	public static Map<Integer,Integer> primeFactor(int n){
		Map<Integer,Integer> res = new HashMap<>();
		for(int i = 0 ; i * i <= n; i++){
			while(n % i == 0){
				res.put(i,res.getOrDefault(i,0)+1);
				n /= i;
			}
		}
		if(n != 1) res.put(n,1);
		return res;
	}


	/**
	 * 求一个整数内有多少个素数（埃氏筛法：针对多整数进行素性测试的更高效算法）
	 * 罗列出2-n的所有整数，选出最小的数，划去所有它的倍数，反复进行即可,时间复杂度O(nloglogn),大致线性
	 * @param n
	 * @return n以内的所有素数（包括n）
	 */
	public List<Integer> primeSeive(int n){
		List<Integer> res = new ArrayList<>();
		boolean[] isPrime = new boolean[n+1];
		Arrays.fill(isPrime,true);
		isPrime[0] = isPrime[1] = false;
		for(int i = 2; i <= n; i++){
			if(isPrime[i]){
				res.add(i);
				for(int j = 2 * i; j <= n ; j += i){
					isPrime[j] = false;
				}
			}
		}
		return res;
	}

	/**
	 * 埃氏筛法求区间素数个数<删除所有合数的思想>
	 * b 以内的合数的最小质因数不超过√b
	 * 做好[a,√b]的表和[a,b]的表，从[a,√b]筛得素数的同时，将其倍数从[a,b]中划去
	 * isPrime[i-a] = true 才说明i是素数
	 * @param a,b
	 * @return a,b之间的所有素数
	 */
	public List<Integer> primeSeive(int a, int b){
		List<Integer> res = new ArrayList<>();
		//两个数组
		boolean[] isPrimeSmall = new boolean[(int)sqrt(b)+2];//具体长度不纠结，只需能表示大于√b的整数即可
		boolean[] isPrime = new boolean[b-a+1];
		for(int i = 0; i * i <= b; i++){
			isPrimeSmall[i] = true; //先预设√b之前的都是素数
		}
		for(int i = 0; i <= b-a; i++) isPrime[i] = true;//预设a-b都是素数，i=0代表a
		for(int i = 2; i*i <= b; i++){
			if(isPrimeSmall[i]){//找出素数i
				for(int j = 2 * i; j * j <= b; j += i) isPrimeSmall[j] = false; //筛[a,√b]
				for(int j = Math.max(2,(a + i - 1)/i) * i; j <= b; j += i) isPrime[j-a] = false;
			}
		}
		for(int i = 0 ; i < isPrime.length; i++){
			if(isPrime[i]) res.add(i+a);
		}
		return  res;
	}
	//牛顿迭代法求开方： X(n+1) = X(n) - f(X(n))/f'(X(n)) --> X(n+1) = X(n)-(X(n)*X(n)-k)/2X(n)
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
