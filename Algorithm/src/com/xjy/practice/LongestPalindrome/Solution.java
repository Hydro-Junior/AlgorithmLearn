package com.xjy.practice.LongestPalindrome;

/**
 * @Author: Mr.Xu
 * @Date: Created in 16:32 2018/10/30
 * @Description:LeetCode5
 */
public class Solution {
    /**
     * 普通方法从中间向两侧扩展求解字符串的最长回文子串
     * 返回符合要求的一个解即可，可能会有多个解
     * 我自己的写法，更好的解法见ConciseSolution，更高效率的还有 Manacher 的算法
     * @param s 入参
     * @return
     */
    public static String plainMethod(String s){
        if(s == null) return null;
        if(s.length() == 0) return "";
        char[] arr = s.toCharArray();
        int len = 0; //回文中心左侧的字符总数
        int idx = 0; //中心字符下标
        boolean isOdd = true;
        for(int i = 0; i < arr.length; i++){
            int len1 = expand(arr, i, i);
            int len2 = -1; //初始设为-1，确保未更新时了len2不会变成2
            if(i+1 < arr.length && arr[i] == arr[i+1]) len2 = expand(arr, i , i+1);
            //转化为实际长度用于比较
            len1 = 1 + len1 * 2;
            len2 = 2 + len2 * 2;
            if(len1 > len2) {
                if(len1 > len) {
                    idx = i;
                    len = len1;
                    isOdd = true;
                }
            }else {
                if(len2 > len){
                    idx = i;
                    len = len2;
                    isOdd = false;
                }
            }
        }
        len = (len-1)/2; //转化为单侧的字符总数
        //构建回文字符串
        if(isOdd){
            return s.substring(idx-len,idx + 1 + len);
        }else{
            return s.substring(idx-len,idx + 2 + len);
        }
    }
    //返回扩展后左侧字符总数
    private static int expand(char[] c, int i, int j){
        int left = i -1;
        int right = j + 1;
        int len = 0;
        while(left >= 0 && right < c.length){
            if(c[left] == c[right]){
                left--;
                right++;
                len ++;
            }else{
                break;
            }
        }
        return len;
    }
}
