package com.xjy.practice.leetcode.UglyNumber;

/**
 * @Author: Mr.Xu
 * @Date: Created in 17:00 2019/3/22
 * @Description:找第n个丑数：只能被2,3,5整除，1是丑数
 */
import java.util.*;
public class Solution {
    /**
     * 方法一：逐个判断，很慢
     * @param index
     * @return
     */
    public int GetUglyNumber_Solution1(int index) {
        Set<Integer> st = new HashSet<>();
        if(index == 1) return 1;
        st.add(1);
        int num = 1, cur = 1;
        while(cur < index){
            num ++;
            if((num % 2 == 0 && st.contains(num/2))||
                    (num % 3 == 0 && st.contains(num/3)) ||
                    (num % 5 == 0 && st.contains(num/5))
                    ){
                st.add(num);
                cur ++;
            }
        }
        return num;
    }
    public int GetUglyNumber_Solution2(int index) {
        Set<Integer> st = new TreeSet<>();
        if(index == 1) return 1;
        st.add(1);
        int cnt = 1, M = 1;
        while(cnt < index){
            int m2 = 0, m3 = 0, m5 = 0;
            for(int s : st){
                if(s * 2 > M) {
                    m2 = s * 2; break;
                }
            }
            for(int s : st){
                if(s * 3 > M) {
                    m3 = s * 3; break;
                }
            }
            for(int s : st){
                if(s * 5 > M) {
                    m5 = s * 5; break;
                }
            }
            M = Math.min(m2, Math.min(m3,m5));
            st.add(M);
            cnt ++;
        }
        return M;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.GetUglyNumber_Solution1(1500));
    }
}
