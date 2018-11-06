package com.xjy.practice.leetcode.Sums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: Mr.Xu
 * @Date: Created in 14:37 2018/11/3
 * @Description: LeetCode18 my solution
 * 确定收尾两个，中间的用2sum解决，时间复杂度O(n^3)
 * 值得一提的是两行有"continue"的语句，排除了很多情况，提交成绩 14% --> 90%（100ms --> 20ms）
 */
public class Sum4 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0 ; i < n - 3; i = nextL(nums,i,n-3)){
            if(nums[n-1]+nums[n-2]+nums[n-3] < target - nums[i]) continue;//优化,排除不可能的情况避免多余循环(最大的组合都太小，跳过这个i)
            for(int j = n-1; j > i+2; j = nextR(nums,i+2,j)){
                if(nums[i+1]+nums[i+2] > target - nums[i]-nums[j]) continue;//优化，同上（最小的组合都太大，跳过这个j）
                int lo = i+1, hi = j-1,sum = target-nums[i]-nums[j];
                while(lo < hi){
                    if(nums[lo] + nums[hi] == sum){
                        res.add(new ArrayList<>(Arrays.asList(nums[i],nums[lo],nums[hi],nums[j])));
                        lo = nextL(nums, lo, hi);
                        hi = nextR(nums,lo,hi);
                    }else if(nums[lo] + nums[hi] < sum){
                        lo = nextL(nums, lo, hi);
                    }else{
                        hi = nextR(nums, lo, hi);
                    }
                }
            }
        }
        return res;
    }
    public int nextL(int[] arr, int lo, int hi){
        int p = arr[lo];
        while(lo < hi && arr[lo] == p){
            lo++;
        }
        return lo;
    }
    public int nextR(int[] arr, int lo, int hi){
        int p = arr[hi];
        while(lo < hi && arr[hi] == p){
            hi--;
        }
        return hi;
    }
}
