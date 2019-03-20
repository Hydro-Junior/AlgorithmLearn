package com.xjy.practice.leetcode.BasicCalculatorII;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Author: Mr.Xu
 * @Date: Created in 20:13 2018/11/13
 * @Description:leetcode227 自己比较初级的实现方式，仍然是利用栈先转为后缀表达式，再利用栈计算，时间复杂度较高
 *  Implement a basic calculator to evaluate a simple expression string.
    The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.
    Example :
    Input: "3+2*2"
    Output: 7
 */
class Solution {
    public int calculate(String s) {
        Queue<String> postfix = new LinkedList<>();
        Stack<Character> calSyb = new Stack<>();
        char[] arr = s.trim().toCharArray();
        //构建后缀表达式
        for(int i = 0 ; i < arr.length;){
            if(arr[i]=='+'||arr[i]=='-'||arr[i]=='*'||arr[i]=='/') {
                if(calSyb.isEmpty()) calSyb.push(arr[i]);
                else{
                    if(arr[i] == '+'|| arr[i] =='-'){
                        while(!calSyb.isEmpty()){
                            postfix.add(calSyb.pop()+"");
                        }
                        calSyb.push(arr[i]);
                    }else{
                        while(!calSyb.isEmpty() && !(calSyb.peek()=='+'|| calSyb.peek()=='-')){
                            postfix.add(calSyb.pop()+"");
                        }
                        calSyb.push(arr[i]);
                    }
                }
                //符号处理完毕
                i++;
                continue;
            }else if(arr[i]==' '){
                i++;
                continue;
            }
            StringBuilder sb = new StringBuilder();
            while(i < arr.length && arr[i]<='9' && arr[i]>='0'){
                sb.append(arr[i++]);
            }
            postfix.add(sb.toString());
        }
        while(!calSyb.isEmpty()){
            postfix.add(calSyb.pop()+"");
        }
        //计算后缀表达式
        Stack<Integer> calst = new Stack<>();
        for(String ss : postfix){
            if(!isSymbol(ss)){
                calst.push(Integer.parseInt(ss));
            }else{
                int aft = calst.pop();
                int pre = calst.pop();
                if(ss.equals("+")){
                    calst.push(pre + aft);
                }else if(ss.equals("-")){
                    calst.push(pre - aft);
                }else if(ss.equals("*")){
                    calst.push(pre * aft);
                }else{
                    calst.push(pre / aft);
                }
            }
        }
        return calst.pop();
    }
    public boolean isSymbol(String s){
        return s.equals("+") || s.equals("-")||s.equals("*")||s.equals("/");
    }
}