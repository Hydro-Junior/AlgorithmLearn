package com.xjy.practice.leetcode.Sums;

import java.util.Arrays;

/**
 * @Author: Mr.Xu
 * @Date: Created in 11:32 2018/11/3
 * @Description: LeetCode16 3sum Closest
 * Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target.
 * Return the sum of the three integers.
 * You may assume that each input would have exactly one solution
 */
public class Sum3Closest {
    /**
     * 自己的解法,思路与下面更简洁的方法大致相同
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int minSub = Integer.MAX_VALUE;
        int res = target;
        for(int i= 0; i < nums.length-2; i++){
            if(i == 0 || (i > 0 && nums[i] != nums[i-1])){
                int sum = target-nums[i];
                int lo = i+1, hi = nums.length-1;
                while(lo < hi){
                    int tmpSum = nums[lo] + nums[hi];
                    if(tmpSum < sum){
                        if(sum - tmpSum < minSub) {
                            minSub = sum - tmpSum;
                            res = nums[i] + tmpSum;
                        }
                        lo++;
                    }else if(tmpSum > sum){
                        if(tmpSum-sum < minSub){
                            minSub = tmpSum - sum;
                            res = nums[i] + tmpSum;
                        }
                        hi--;
                    }else{
                        return target;
                    }
                }
            }
        }
        return res;
    }

    /**
     * more concise solution
     * @param num
     * @param target
     * @return
     */
    public int threeSumClosest2(int[] num, int target) {
        int result = num[0] + num[1] + num[num.length - 1];
        Arrays.sort(num);
        for (int i = 0; i < num.length - 2; i++) {
            int start = i + 1, end = num.length - 1;
            while (start < end) {
                int sum = num[i] + num[start] + num[end];
                if (sum > target) {
                    end--;
                } else {
                    start++;
                }
                if (Math.abs(sum - target) < Math.abs(result - target)) {
                    result = sum;
                }
            }
        }
        return result;
    }
}
