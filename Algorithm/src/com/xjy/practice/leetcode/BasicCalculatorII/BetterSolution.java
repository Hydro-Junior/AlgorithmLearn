package com.xjy.practice.leetcode.BasicCalculatorII;

import java.util.ArrayDeque;
import java.util.Stack;

/**
 * @Author: Mr.Xu
 * @Date: Created in 20:27 2018/11/13
 * @Description:
 */
class BetterSolution {
    public int calculate(String s) {
        Stack<Integer> st = new Stack<>();
        char[] arr = s.trim().toCharArray();
        int num = 0;
        char sign = '+';
        for(int i = 0 ; i < arr.length ;i ++){
            if(arr[i] == ' ')continue;
            if(arr[i] <= '9' && arr[i]>='0') num = 10 * num + arr[i]-'0';
            if(arr[i] > '9' || arr[i] < '0' || i == arr.length-1){
                if(sign == '+'){
                    st.push(num);
                }else if(sign == '-'){
                    st.push(0-num);
                }else if(sign == '*'){
                    st.push(st.pop() * num);
                }else{
                    st.push(st.pop() / num);
                }
                num = 0;
                sign = arr[i];
            }
        }
        int res = 0;
        for(int i : st){
            res += i;
        }
        return res;
    }
}