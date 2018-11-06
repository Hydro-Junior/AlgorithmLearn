package com.xjy.practice.leetcode.Sums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: Mr.Xu
 * @Date: Created in 10:58 2018/11/3
 * @Description:在先排好序的情况下(为了顺利跳过重复元素和便于查找)
 * 第一钟思路，先确定两头，再二分找中间，最差O(n^2log(n))
 * 第二种思路，先定最小值，在用2sum确定剩余的两个，O(n^2)
 */
public class Sum3Better {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for(int i=0; i < nums.length-2;i++){
            //越过相同的元素
            if(i==0 || (i > 0 && nums[i]!=nums[i-1])){
                int lo = i+1, hi = nums.length-1,sum = 0-nums[i];
                while(lo < hi){
                    if(nums[lo] + nums[hi] == sum){
                        res.add(new ArrayList<>(Arrays.asList(nums[i],nums[lo],nums[hi])));
                        lo = nextL(nums,lo,hi);
                        hi = nextR(nums,lo,hi);
                    }else if(nums[lo] + nums[hi] < sum){
                        lo = nextL(nums,lo,hi);
                    }else{
                        hi = nextR(nums,lo,hi);
                    }
                }
            }
        }
        return res;
    }
    public int nextL(int[] arr, int lo, int hi){
        int p = arr[lo];
        while(lo < hi && arr[lo]==p){
            lo++;
        }
        return lo;
    }
    public int nextR(int[] arr,int lo, int hi){
        int p = arr[hi];
        while(lo < hi && arr[hi]==p){
            hi--;
        }
        return hi;
    }
}
