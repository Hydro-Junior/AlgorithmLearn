/**
 * 
 */
package com.xjy.classify.dp;

import java.util.Arrays;

/**
 * @Description 
 *	两种背包问题：01背包和完全背包
 *	有n种重量和价值分别为wi和vi的物品，从这些物品中挑选总重量不超过W的物品，求出挑选物品价值总和的最大值。（每种物品最多选一件（01背包）、每种物品可选任意多件）
 *	注意：
 *	当限制条件中重量很大而价值数值较小，不能再选择重量作为DP的对象，可以选择用DP针对不同的价值计算最小的重量（详见挑战程序设计竞赛P61）
 *	这种情况下，递推公式就成了：dp[i+1][j] = min(dp[i][j],dp[i][j-v[i]]+w[i]),最终答案就对应于令dp[n][j] <= W 的最大的j
 * @author Mr.Xu
 * @date 2018年10月2日 上午9:22:25
 *
 */
public class BackPack {
	private static int W = 100; //重量限额
	private static int N = 10;//物品总数
	private static int[] w = new int[]{9,23,3,5,8,12,14,25,7,50};
	private static int[] v = new int[]{25,78,36,40,36,32,22,42,20,20};
	private static int[] dp = new int[W+1];//dp[i]这里表示用小于等于重量W可以实现的最大价值
	
	//完全背包
	public static int backpack_full() {
	    Arrays.fill(dp,0);
		for(int i = 0 ; i < N; i++) {
			for(int j = w[i]; j <= W; j++) { //重量自低到高，可重复使用同一物件
				dp[j] = Math.max(dp[j], dp[j-w[i]]+v[i]);
			}
		}
		return dp[W];			
	}
	//01背包
	public static int backpack_01() {
        Arrays.fill(dp,0);
		for(int i = 0 ; i < N; i++) {
			for(int j = W; j >= w[i]; j--) { //重量自高到低，不重复使用同一物件
				dp[j] = Math.max(dp[j], dp[j-w[i]]+v[i]);
			}
		}
		return dp[W];	
	}
	//当W非常大，可改变dp目标,结果与上一个方法相同
	public static int dpForWeight(){
	    dp = new int[N * 78+1];//78为最大价值,dp[i]代表构成价值i所需的最小重量
        Arrays.fill(dp,100000000);
        dp[0] = 0;
        for(int i = 0; i < N; i++){
            for(int j = N * 78; j >= v[i]; j--){
                dp[j] = Math.min(dp[j],dp[j-v[i]]+w[i]);
            }
        }
        for(int i = N * 78; i >= 1;i--){
            if(dp[i] <= W) return i;
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(backpack_full());
        System.out.println(backpack_01());
        System.out.println(dpForWeight());
    }
}
