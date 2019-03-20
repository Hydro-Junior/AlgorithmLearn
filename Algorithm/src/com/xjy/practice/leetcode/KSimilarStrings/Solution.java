package com.xjy.practice.leetcode.KSimilarStrings;

import java.util.*;

/**
 * @Author: Mr.Xu
 * @Date: Created in 19:54 2019/3/12
 * @Description:
 */
class Solution {
    public int kSimilarity(String A, String B) {
        Map<Character,Set<Integer>> posmp = new HashMap<>();
        Map<Character,Set<Integer>> settled = new HashMap<>();
        for(int i = 0; i < B.length(); i++){
            char c = B.charAt(i);
            if(posmp.containsKey(c)){
                posmp.get(c).add(i);
            }else{
                Set<Integer> st = new HashSet<>();
                st.add(i);
                posmp.put(c,st);
            }
        }
        char[] ac = A.toCharArray();
        int count = 0;
        for(int i = 0 ; i < ac.length ;){
            char c = ac[i];
            if(posmp.get(c).contains(i) ){
                posmp.get(c).remove(i);
                i++;
            }else if(settled.containsKey(c) && settled.get(c).contains(i)){
                i++;
            }else{
                Iterator<Integer> pit = posmp.get(c).iterator();
                int p = pit.next();
                pit.remove();
                if(settled.containsKey(c)){
                    settled.get(c).add(p);
                }else {
                    Set<Integer> st = new HashSet<>();
                    st.add(p);
                    settled.put(c,st);
                }
                ac[i] = ac[p];
                ac[p] = c;
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.kSimilarity("abac","baca"));
    }
}
