package com.xjy.geometry;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Mr.Xu
 * @Date: Created in 15:11 2019/3/11
 * @Description: 最多有多少个点共线问题
 * 此题用除以最大公约数的方式唯一确定（x,y）对，对应一个斜率，另外，水平竖直情况返回（0,1）或（1,0）即可
 */
public class MaxPointsOnALine {
    public int maxPoints(Point[] ps) {
        Map<Integer,Map<Integer,Integer>> mp = new HashMap<>();//两个键确定一个值，可以考虑内嵌map来表示
        int res = 0;
        for(int i = 0 ; i < ps.length; i++){
            mp.clear();//注意清空map
            int overlap=0; int max = 0;
            for(int j = i + 1; j < ps.length; j++){
                if(ps[i].x == ps[j].x && ps[i].y == ps[j].y){
                    overlap++; continue;
                }
                int x = ps[i].x - ps[j].x;
                int y = ps[i].y - ps[j].y;
                int[] pair = getPair(x,y);//关键步骤
                x = pair[0]; y = pair[1];
                if(mp.containsKey(x)){
                    mp.get(x).put(y,mp.get(x).getOrDefault(y,0)+1);
                }else{
                    Map<Integer,Integer> sub = new HashMap<>();
                    sub.put(y,1);
                    mp.put(x,sub);
                }
                max = Math.max(mp.get(x).get(y),max);
            }
            res = Math.max(max+overlap+1, res);
        }
        return res;
    }
    private int[] getPair(int x, int y){
        if(x == 0) return new int[]{0,1};
        else if(y == 0) return new int[]{1,0};
        else{
            int gcd = getGcd(x, y);
            if(gcd != 0) return new int[]{x/gcd,y/gcd};
            else return new int[]{x,y};
        }
    }
    private int getGcd(int a, int b){
        if(b == 0) return a;
        else return getGcd(b,a%b);
    }
    public static void main(String[] args){
        MaxPointsOnALine moa = new MaxPointsOnALine();
        Point[] ps = new Point[4];
        ps[0] = new Point(1,1);
        ps[1] = new Point(2,2);
        ps[2] = new Point(3,3);
        ps[3] = new Point(4,5);
        int res = moa.maxPoints(ps);
        System.out.println("有"+res+"个点共线。");
    }
}

  class Point {
     int x;
     int y;
      Point() { x = 0; y = 0; }
      Point(int a, int b) { x = a; y = b; }
}
