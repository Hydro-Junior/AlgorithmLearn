/**
 * 
 */
package com.xjy.problems.dp;

/**
 * @Description 
 *  最长上升子序列问题（LIS,Longest Increasing Subsequence）<注意不是连续上升子序列>
 *  定义 dp[i]是以ai为末尾的最长上升子序列的长度 ，则有递推式：dp[i]= max{1,dp[j]+1 | j < i 且 aj < ai}
 *  另一种思路（用下标表示长度）：
 *  dp[i]:=长度为i+1的上升子序列中末尾元素的最小值（初始为INF），如果i=0或dp[i-1]<aj,dp[i]=min(dp[i],aj)
 *  优化详见挑战程序设计竞赛P65 
 * @author Mr.Xu
 * @date 2018年10月2日 上午10:36:14
 *
 */
public class LIS {
	int N = 100;
	int[] a = new int[N];
	int[] dp = new int[N];
	
	public int solveLIS() {
		int res = 0;
		for(int i=0 ;i < N ; i++) {
			dp[i] = 1;
			for(int j = 0; j < i; j++) {
				if(a[j] < a[i])dp[i] = Math.max(dp[i], dp[j]+1);
			}
			res = Math.max(res, dp[i]);
		}
		return res;
	}
}
