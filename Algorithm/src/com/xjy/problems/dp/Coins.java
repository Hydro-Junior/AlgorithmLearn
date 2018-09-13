package com.xjy.problems.dp;

import java.util.Scanner;

/**
 * 输入甲乙两种货币，每种货币输入一系列币值，按升序排列，其中甲货币可以重复，乙货币最多只能选择一次，凑成总数m，共有几组解。
 */
public class Coins{
	static int n1,n2,m;//n1:甲货币总数；n2:乙货币总数；m
	static long sum=0L;//记录总解数
	static int[] mCoin,sCoin,res;//mCoin表示可重复货币，sCoin表示不可重复货币，res储存每种m的解
	static boolean[] sMark;//标记n2的币种是否被使用
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	n1 = sc.nextInt();
    	n2 = sc.nextInt();
    	m = sc.nextInt();
    	mCoin = new int[n1];
    	sCoin = new int[n2];
    	res = new int[m+1];
    	
    	for(int i = 0 ; i < n1; i++) {
    		mCoin[i] = sc.nextInt();
    	}
    	for(int i = 0; i< n2 ; i++) {
    		sCoin[i] = sc.nextInt();
    	}
    	sMark = new boolean[n2];
    	findSolutions(m);//计算m以内左右结果
    	sCoinTrackBack(0,0);//前一个代表回溯的步数，第二个表示目前积累的货币
    	System.out.println(sum);
    }
    
    //采用递归回溯的方法寻找
    public static void sCoinTrackBack(int i,int m2) {
    	if(i==n2) {//币种达到n2
    		if(m2 <= m) {
    			sum += res[m-m2];
    		}
    		return;
    	}
		sCoinTrackBack(i+1,m2+ 0);//分支一，不选择这个二类货币
		sCoinTrackBack(i+1,m2 + sCoin[i]);//分支二，选择这个二类货币
	}
 	
    

    
    public static void findSolutions(int m) {
        //有问题，这是组合不是排列，除非硬币有顺序，可按如下方法DP
    	/*for(int j = 1 ; j <= m; j++) {
    		for(int p = 0 ; p < n1 && j > mCoin[p]; p++) {
    			res[j] += res[j- mCoin[p]];
    		}
    	}*/
    	/*子问题划分：
    	仅仅使用前q种硬币组成金额m的方法，可以划为两个分离的部分：仅仅使用前q-1种硬币组成金额m的方法，以及，至少使用了一个第q种硬币来组成金额m的方法。
    	其中至少使用一个第q种硬币来组成金额m的方法 = 仅仅使用前q种硬币来组成 m-v[q](v[]表示币值)
    	即 res(m,q) = res(m,q-1)+res(m-v[q],q)
    	*/
    	res[0] = 1;
    	for(int i = 0 ; i < n1; i++) {
    		for(int j = mCoin[i]; j <= m;j++) {
    			//仅适用前i种硬币得到的结果
    			res[j] += res[j-mCoin[i]];
    		}
    	}
    }
    
}
