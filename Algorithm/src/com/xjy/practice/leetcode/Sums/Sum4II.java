package com.xjy.practice.leetcode.Sums;

import java.util.Arrays;

/**
 * @Author: Mr.Xu
 * @Date: Created in 11:11 2019/4/8
 * @Description:
 */
class Sum4II{
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        if(A == null || A.length == 0) return 0;
        int N = A.length;
        Arrays.sort(A); Arrays.sort(B); Arrays.sort(C);Arrays.sort(D);
        int minSum = B[0] + C[0];
        int maxSum = B[N-1] + C[N-1];
        int count = 0;
        for(int i = 0 ; i < N; i++){
            for(int j = 0; j < N; j++){
                int curSum = A[i] + D[j];
                if(0-curSum < minSum) break;
                else if(0-curSum > maxSum) continue;
                else{
                    for(int k = 0; k < N; k++){
                        int target = 0 - curSum - B[k];
                        if(binarySearch(C,target)){
                            count ++;
                        }
                    }
                }
            }
        }
        return count;
    }
    private boolean binarySearch(int[] arr,int target){
        int lo = 0, hi = arr.length -1;
        while(lo <= hi){
            int mid = lo + (hi-lo)/2;
            if(arr[mid] < target){
                lo = mid + 1;
            }else if(arr[mid] > target){
                hi = mid - 1;
            }else{
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] A = {0,1,-1};
        int[] B = {-1,1,0};
        int[] C = {0,0,1};
        int[] D = {-1,1,1};
        Sum4II s = new Sum4II();
        int a = s.fourSumCount(A,B,C,D);
        System.out.println(a);
    }
}
