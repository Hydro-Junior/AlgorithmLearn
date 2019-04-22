package com.xjy.practice.leetcode.ShortestConsequenceLeastK;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author: Mr.Xu
 * @Date: Created in 14:23 2019/3/26
 * @Description:求最小长度子数组，使和大于等于给定K
 * 思路：前缀和+双端队列（按前缀和递增顺序放置B数组中下标）思想
 *      队列中实际上放置的是起始下标
 */
class Solution {
    public int shortestSubarray(int[] A, int K) {
        int N = A.length, res = N + 1;
        int[] B = new int[N + 1];
        for (int i = 0; i < N; i++) B[i + 1] = B[i] + A[i];//计算前缀和 B[j] - B[i] represents the sum of subarray A[i] ~ A[j-1]
        Deque<Integer> d = new ArrayDeque<>();
        for (int i = 0; i < N + 1; i++) {
            while (d.size() > 0 && B[i] - B[d.getFirst()] >=  K)
                res = Math.min(res, i - d.pollFirst()); //不断移出头，缩小范围
            /*
                So because the Deque is increasing (B[d[0]] <= B[d[1]]), we have B[i] - B[d[0]] >= B[i] - B[d[1]], which means the sum of the subarray starting from d[0] is greater than the sum of the sub array starting from d[1].
             */
            while (d.size() > 0 && B[i] <= B[d.getLast()]) d.pollLast();//使到起始下标的前缀和尽可能小
            d.addLast(i);
        }
        return res <= N ? res : -1;
    }
    public int shortestSubarrayOfmine(int[] A, int K) {
        int lo = 0, hi = 0,sum = 0,res = Integer.MAX_VALUE;
        while(lo < A.length){
            hi = lo;sum = 0;
            while(sum >= 0 && hi < A.length){
                sum += A[hi];
                if(sum >= K) {
                    res = Math.min(res,hi - lo + 1);
                    break;
                }
                hi ++;
            }
            //这里直接lo++能通过所有用例，但是会超时，直接加上面的if语句更也无济于事
            /*if(sum < 0 && hi < A.length) lo = hi;
            else{*/
                lo++;
            /*}*/
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
    public static void main(String[] args) {
        Solution s = new Solution();
        int A[] = new int[]{56,-21,56,35,-9};
        int res = s.shortestSubarrayOfmine(A,61);
        System.out.println(res);
    }
}
