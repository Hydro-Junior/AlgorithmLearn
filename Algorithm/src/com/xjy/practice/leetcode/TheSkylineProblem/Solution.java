package com.xjy.practice.leetcode.TheSkylineProblem;

import java.util.*;

/**
 * @Author: Mr.Xu
 * @Date: Created in 14:51 2018/10/31
 * @Description:LeetCode218 高楼轮廓绘制问题,此类中用的是自己不断磨合修改得出的效率很低的解
 */
//尝试大数组存储每个x的高度不出意料地内存超限，考虑合理利用边信息得出结果，需要分类讨论
//先分解所有边，按横坐标排序
//对于左边L，遍历其左侧的左边，满足其右边横坐标大于b且高度大于等于L，则不添加该边
//对于右边R, 高度初始化为0，遍历其左侧的左边，如果某左边对应的右边的横坐标大于R的横坐标,更新高度，如果最终高度大于等于R的高度，不添加R
/*尝试大数组存储每个x的高度不出意料地内存超限，考虑合理利用边信息得出结果，需要分类讨论
先分解所有边，按横坐标排序
对于左边L，遍历其左侧的左边，满足其右边横坐标大于b且高度大于等于L，则不添加该边
对于右边R, 高度初始化为0，遍历其左侧的左边，如果某左边对应的右边的横坐标大于R的横坐标,更新高度，如果最终高度大于等于R的高度，不添加R*/
class Solution {
    public List<int[]> getSkyline(int[][] buildings) {
        List<Border> tmpRes = new LinkedList<>();
        List<int[]> res = new ArrayList<>();
        Map<Integer,Integer> mapRes = new LinkedHashMap<>();
        if(buildings.length == 0) return res;
        List<Border> borderList = new ArrayList<>();
        for(int i = 0 ; i < buildings.length; i++){
            int l = buildings[i][0];
            int r = buildings[i][1];
            int h = buildings[i][2];
            Border lf = new Border(true,l,h);
            Border rt = new Border(false,r,h);
            lf.mate = rt;
            rt.mate = lf;
            borderList.add(lf);
            borderList.add(rt);
        }
        //borders按横坐标排序
        Collections.sort(borderList);
        for(int i = 0 ; i < borderList.size(); i++){
            Border b = borderList.get(i);
            boolean shouldAdd = true;
            if(b.isLeft){
                for(int j = 0; j < i;j++){
                    Border pre = borderList.get(j);
                    if(pre.isLeft && pre.mate.x > b.x && pre.h >= b.h){
                        shouldAdd = false;
                        break;
                    }
                }
            }else{
                int oldHeight = b.h;
                b.h = 0;
                for(int j = 0; j < i; j++){
                    Border pre = borderList.get(j);
                    if(pre.isLeft && pre.mate.x > b.x){
                        b.h = Math.max(pre.h , b.h);
                    }
                }
                //如果最终高度大于等于R的高度，不添加R
                if(b.h > oldHeight){
                    b.h = oldHeight;
                    shouldAdd = false;
                }
            }
            if(shouldAdd) tmpRes.add(b);
        }
        //最后做检查 第一轮:类似【2，0】【2,3】这种需要作合并
        for(int i = 0; i < tmpRes.size()-1; i++){
            Border b = tmpRes.get(i);
            Border next = tmpRes.get(i+1);
            if(b.x == next.x && b.h == 0){
                b.removed = true;
            }
        }
        Iterator<Border> iterator = tmpRes.iterator();
        while (iterator.hasNext()){
            Border cur = iterator.next();
            if(cur.removed) iterator.remove();
        }
        //第二轮 合并中间等于两边的点
        for(int i = 1; i < tmpRes.size(); i++){
            Border b = tmpRes.get(i);
            Border pre = tmpRes.get(i-1);
            if(b.h == pre.h){
                b.removed = true;
            }
        }
        iterator = tmpRes.iterator();
        while (iterator.hasNext()){
            if(iterator.next().removed) iterator.remove();
        }
        //将最终结果加入res,先加入map去重，同一横坐标取最高高度
        iterator = tmpRes.iterator();
        while (iterator.hasNext()){
            Border b = iterator.next();
            mapRes.put(b.x,b.h);
        }
        for(Map.Entry<Integer,Integer> entry : mapRes.entrySet()){
            int x = entry.getKey();
            int h = entry.getValue();
            res.add(new int[]{x,h});
        }
        return res;
    }
}
class Border implements Comparable<Border>{
    boolean isLeft;
    int x;
    int h;
    boolean removed = false;
    Border mate;
    Border(boolean isLeft, int x, int h){
        this.isLeft = isLeft;
        this.x = x;
        this.h = h;
    }
    @Override
    public int compareTo(Border o) {
        if(this.x != o.x) return this.x - o.x;
        else return this.h - o.h;
    }
}
