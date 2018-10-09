/**
 * 
 */
package com.xjy.problems.dp;

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
	private static int[] w = new int[N];
	private static int[] v = new int[N];
	private static int[] dp = new int[W+1];
	
	//完全背包
	public static int backpack_full() { 
		for(int i = 0 ; i < N; i++) {
			for(int j = w[i]; j <= W; j++) { //重量自低到高，可重复使用同一物件
				dp[j] = Math.max(dp[j], dp[j-w[i]]+v[i]);
			}
		}
		return dp[W];			
	}
	//01背包
	public static int backpack_01() {
		for(int i = 0 ; i < N; i++) {
			for(int j = W; j >= w[i]; j--) { //重量自高到低，不重复使用同一物件
				dp[j] = Math.max(dp[j], dp[j-w[i]]+v[i]);
			}
		}
		return dp[W];	
	}
	
	
}
