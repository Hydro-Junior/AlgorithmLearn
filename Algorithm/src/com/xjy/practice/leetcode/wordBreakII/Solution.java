package com.xjy.practice.leetcode.wordBreakII;

import java.util.*;

/**
 * @Author: Mr.Xu
 * @Date: Created in 11:20 2019/3/14
 * @Description:
 */
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> st = new HashSet<>();
        for(String ss : wordDict){
            st.add(ss);
        }
        List<String> res = new ArrayList<>();
        for(int i = 1 ; i <= s.length(); i++){
            List<String> tmp = new ArrayList<>();
            String first = s.substring(0,i);
            if(st.contains(first)){
                if(i == s.length()) {
                    res.add(s);
                    return res;
                }
                if(dfs(i,s.length(),tmp,st,s)){
                    tmp.add(first);
                    addStrings(tmp,res);
                }
            }
        }
        return res;
    }
    boolean dfs(int lo, int hi,List<String> tmp,Set<String> st,String s){
        if(lo >= hi){
            return true;
        }
        for(int i = lo+1; i < hi; i++){
            String pre = s.substring(lo,i);
            if(st.contains(pre) && dfs(i,hi,tmp,st,s)){
                tmp.add(pre);
            }
        }
        return false;
    }
    void addStrings(List<String> tmp, List<String> res){
        StringBuilder sb = new StringBuilder();
        for(int i = tmp.size()-1; i >= 0; i--){
            if(i == 0) sb.append(tmp.get(i));
            else sb.append(tmp.get(i)+" ");
        }
        res.add(sb.toString());
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<String> list = Arrays.asList(new String[]{"cat","cats","and","sand","dog"});
        solution.wordBreak("catsanddog",list);
    }
}
