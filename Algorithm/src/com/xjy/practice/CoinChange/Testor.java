package com.xjy.practice.CoinChange;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: Mr.Xu
 * @Date: Created in 14:51 2018/10/11
 * @Description:LeetCode322
 */
public class Testor {
    Solution s = null;
    @Before
    public void setup(){
        s = new Solution();
    }
    @Test
    public void testSolution(){
        int result = s.coinChange(new int[]{2},3);
        System.out.println(result);
    }
    @Test
    public void testInteger(){
        int a = Integer.MAX_VALUE + 1;
        int b = Integer.MIN_VALUE;
        System.out.println(a == b);
    }
}

//动态规划，类似问题还有多种硬币组成这个值有多少种方案
class Solution {
    public int coinChange(int[] coins, int amount) {
        int L = amount + 1;
        int[] dp = new int[L];
        //下面不能轻易使用Integer.MAX_VALUE初始化数组,它+1会成为Integer.MIN_VALUE,由于硬币最小单位是1，组成amount的硬币个数不可能超过amount+1
        Arrays.fill(dp,L);
        dp[0] = 0;
        for(int i = 0; i < coins.length; i++){
            for(int j = coins[i]; j <= amount; j++){
                dp[j] = Math.min(dp[j],1 + dp[j-coins[i]]);
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
