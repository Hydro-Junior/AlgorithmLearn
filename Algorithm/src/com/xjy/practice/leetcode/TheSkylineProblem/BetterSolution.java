package com.xjy.practice.leetcode.TheSkylineProblem;

import java.util.*;

/**
 * @Author: Mr.Xu
 * @Date: Created in 15:36 2018/11/1
 * @Description:
 */
/*自己花半天用分析加粗暴方式解决了，但效率很低，查看discuss发现可以借助优先队列（最大堆）实现，挺难想到的，但又觉得确实可以联系到优先队列
如果是左边，加入优先队列，高度大于最大值的话加入结果集
如果是右边，意味着一幢building的结束，会将高度从优先队列移除，然后检查优先队列的最大高度H，如果H大于等于当前高度，不添加入结果集，否则，添加【x,H】*/
/*自己花半天用分析加粗暴方式解决了，但效率很低，查看discuss发现可以借助优先队列（最大堆）实现，挺难想到的，但又觉得确实可以联系到优先队列
如果是左边，加入优先队列，高度大于最大值的话加入结果集
如果是右边，意味着一幢building的结束，会将高度从优先队列移除，然后检查优先队列的最大高度H，如果H大于等于当前高度，不添加入结果集，否则，添加【x,H】*/
class BetterSolution {
    public List<int[]> getSkyline(int[][] buildings) {
        ArrayList<int[]> borders = new ArrayList<>();
        ArrayList<int[]> res = new ArrayList<>();
        for(int[] b:buildings) {
            borders.add(new int[]{b[0], -b[2]});
            borders.add(new int[]{b[1], b[2]});
        }
       // Collections.sort(borders,(a,b)->a[0] == b[0]? a[1]-b[1]:a[0]-b[0]);
        Collections.sort(borders,new Comparator<int[]>(){
                    @Override
                    public int compare(int[] a, int[] b) {
                        return a[0] == b[0]? a[1]-b[1]:a[0]-b[0];
                    }
                }
        );
       // PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> b-a);//最大堆
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        });
        pq.offer(0);
        for(int[] border : borders){
            int max = pq.peek();
            if(border[1] < 0){//是左边
                int h = 0 - border[1];
                pq.offer(h);
                if(max < h){
                    res.add(new int[]{border[0],h});
                }
            }else{
                int h = border[1];
                pq.remove(h);
                max = pq.peek();
                if(max < h) res.add(new int[]{border[0],max});
            }
        }
        //另一种可行的迭代,减少了peek语句
        /*int prev = 0;
        for(int[] h:borders) {
            if(h[1] < 0) {
                pq.offer(-h[1]);
            } else {
                pq.remove(h[1]);
            }
            int cur = pq.peek();
            if(prev != cur) {
                res.add(new int[]{h[0], cur});
                prev = cur;
            }
        }*/
        return res;
    }
}

