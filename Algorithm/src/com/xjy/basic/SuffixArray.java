package com.xjy.basic;

import com.xjy.sort.Quick3way;

/**
 * @Author: Mr.Xu
 * @Date: Created in 15:17 2018/10/24
 * @Description: 后缀数组：可用于高效解决LRS（最长重复子串）等字符串处理问题
 * 这里只是后缀数组的基本实现（其实也就是把所有的后缀取出来排个序，很简单），其优化可进一步参考https://algs4.cs.princeton.edu/home/，有改进的实现
 *
 */
public class SuffixArray {
    private final String[] suffixes;
    private final int N;
    public SuffixArray(String s){
        N = s.length();
        suffixes = new String[N];
        for(int i = 0 ; i < N; i++){
            suffixes[i] = s.substring(i);
        }
        Quick3way.sort(suffixes);
    }
    public int length(){
        return N;
    }
    //后缀数组中第i个值
    public String select(int i){return suffixes[i];}

    //后缀suffix[i]在原字符串中的索引（其长度预示着索引，字符串本身的索引是0）
    public int index(int i){return N - suffixes[i].length();}

    //返回后缀数组第i项和前一项的公共前缀长度
    public int lcp(int i){
        return lcp(suffixes[i] , suffixes[i-1]);
    }
    //小于key的后缀数量（二分查找实现）
    public int rank(String key){
        int lo = 0, hi = N-1;
        while(lo < hi){
            int mid = lo + (hi - lo)/2;
            int cmp = key.compareTo(suffixes[mid]);
            if(cmp > 0){lo = mid + 1;}
            else if (cmp < 0) {hi = mid - 1;}
            else return mid;
        }
        return lo;
    }
    //最长公共前缀(个数)
    private static int lcp(String s,String t){
        int n = Math.min(s.length(),t.length());
        for(int i = 0; i < n ;i++){
            if(s.charAt(i) != t.charAt(i)) return i;
        }
        return n;
    }
    //求解文本的最长连续子字符串
    public static String LRS(String text){
        int N = text.length();
        SuffixArray sa = new SuffixArray(text);
        String lrs = "";
        for(int i=1; i< N; i++){
            int length = sa.lcp(i);
            if(length > lrs.length()){
                lrs = sa.select(i).substring(0,length);
            }
        }
        return lrs;
    }
    //测试 LRS
    public static void main(String[] args) {
        System.out.println(LRS("to be or not to be"));
        System.out.println(LRS("555555555555"));
    }
}
