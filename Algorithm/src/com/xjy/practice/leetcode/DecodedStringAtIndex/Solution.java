package com.xjy.practice.leetcode.DecodedStringAtIndex;

/**
 * @Author: Mr.Xu
 * @Date: Created in 17:30 2019/4/7
 * @Description:leetcode880
 *  Input: S = "leet2code3", K = 10
    Output: "o"
    Explanation:
    The decoded string is "leetleetcodeleetleetcodeleetleetcode".
    The 10th letter in the string is "o".
精彩的字符串解析过程：
    容易使人想到 （（x + a1） * b1 + a2) * b2...的形式，
    难点在于反过来找K（求余和减法），需要一定的想象力。
 */
class Solution {
    public String decodeAtIndex(String S, int K) {
        long cur = 0;
        int i = 0;
        char[] cs = S.toCharArray();
        while(cur < K){
            if(isDigit(cs[i])){
                cur *= cs[i] - '0';
            }else{
                cur += 1;
            }
            i ++;
        }
        while(--i >= 0){
            if(isDigit(cs[i])){
                cur /= cs[i] - '0'; K %= cur;
            }else if(K % cur-- == 0){
                return String.valueOf(cs[i]);
            }
        }
        return "";
    }
    private boolean isDigit(char c){
        return c <= '9' && c >= '1';
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        String input = new String("ha2se6");
        String str = s.decodeAtIndex(input,15);
        System.out.println(str);
    }
}
