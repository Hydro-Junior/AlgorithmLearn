package com.xjy.practice.leetcode.PerfectSquares;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: Mr.Xu
 * @Date: Created in 14:51 2018/10/11
 * @Description:LeetCode279
 */
public class Testor {
    Solution s = null;
    @Before
    public void setup(){
        s = new Solution();
    }
    @Test
    public void testSolution(){
        int result = s.numSquares(25);
        System.out.println(result);
    }
    @Test
    public void testInteger(){
        int a = Integer.MAX_VALUE + 1;
        int b = Integer.MIN_VALUE;
        System.out.println(a == b);
    }
}

class Solution {
    public int numSquares(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i = 1; i * i <= n; i++){
            for(int j = i*i; j <= n; j++){
                dp[j] = Math.min(dp[j], 1+dp[j-i*i]);
            }
        }
        return dp[n];
    }
}
