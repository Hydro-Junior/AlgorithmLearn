package com.xjy.practice.leetcode.Sums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: Mr.Xu
 * @Date: Created in 18:51 2018/11/2
 * @Description: LeetCode15-3sum 自己解决时想到的比较low的办法，先排序，再两头遍历结合二分查找解决问题
 */
public class Sum3 {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;
        for(int lo = 0 ; lo < n;){
            for(int hi = n-1; hi > lo;){
                int lastNum = 0 - nums[lo] - nums[hi];
                if(lastNum > nums[hi]) break;
                else if(lastNum < nums[lo]) {
                    hi = nextR(nums,hi);
                    continue;
                }else{
                    boolean found = binarySearch(nums,lo+1,hi-1,lastNum);//二分查找满足条件的数的个数
                    if(found){
                        List<Integer> subRes = Arrays.asList(new Integer[]{nums[lo],lastNum,nums[hi]});
                        res.add(subRes);
                    }
                    hi = nextR(nums,hi);
                }
            }
            lo = nextL(nums,lo);
        }
        return res;
    }
    public boolean binarySearch(int[] nums, int lo, int hi,int key){
        if(lo > hi) return false;
        int mid = lo + (hi - lo) /2;
        if(nums[mid] < key) return binarySearch(nums, mid + 1, hi, key);
        else if(nums[mid] > key) return binarySearch(nums, lo , mid-1,key);
        else return true;
    }
    //跳过相同元素，左边指针的下一个
    public int nextL(int[] arr, int lo){
        int p = arr[lo];
        while(lo < arr.length && arr[lo] == p){
            lo++;
        }
        return lo;
    }
    //跳过相同元素，右边指针的下一个
    public int nextR(int[] arr, int hi){
        int p = arr[hi];
        while(hi >= 0 && arr[hi] == p){
            hi--;
        }
        return hi;
    }
}
