package com.xjy.string.match;

import org.junit.Test;

/**
 * @Author: Mr.Xu
 * @Date: Created in 8:56 2018/10/23
 * @Description: 利用KMP（Knuth-Morris-Pratt）算法完成字符串匹配
 * 在出现不匹配时，暴力算法会回退文本指针，并从头比较比较；KMP的想法是在出现不匹配时，保证文本指针不回退。
 * 可以在脑海构建这样一幅动图，不匹配时，模式字符串在文本字符串下向前滑动（匹配时不需要滑动，继续下一个），
 * 而具体滑动到什么位置，由模式字符串本身就可以确定，并用数组保存这些“下一个位置”，这也是我所理解的KMP的核心。
 *
 * 如何构建这个数组？
 * 普林斯顿的《算法》采用的是有限状态自动机（DFA）的方式，dfa[txt.charAt(i)][j]是在比较了txt.charAt(i)和pat.charAt(j)之后，应该和txt.charAt(i+1)比较的模式字符位置
 * 但个人认为更容易理解且实用的只需要一个一维数组"next[]"，利用最长相同前后缀的原则实现，本类将采用此种方式。
 * 构建next数组时间复杂度为O(m),m为模式子串长度，匹配过程时间复杂度为O(n)，总共复杂度为O(m+n)
 * reference:
 * https://github.com/mission-peace/interview/
 * https://www.youtube.com/watch?v=GTJr8OvyEVQ&t=0s&list=PLrmLmBdmIlpvxhscYQdvfFNWU_pdkG5de&index=5
 *
 */
public class KMP {
    private char[] pat; //待匹配的模式字符串
    private int[] next; //当下标为j，表示位置j+1发生不匹配时，应该转移到的位置（公共前缀的下一个位置），因此如果next[j] = 0(-1的下一个) ,必然是要从头开始匹配
    /**
     * 构造方法，构建next数组(最巧妙的数组操作之一)
     * @param pattern  传入的模式子串
     */
    public KMP(String pattern){
        this.pat = pattern.toCharArray();
        int m = pat.length;
        next = new int[m];
        int i = 0 , j = 1;
        while(j < m){
            if(pat[i] == pat[j]){
                next[j] = i + 1;
                i++;j++;
            }else{
                if(i != 0) i = next[i - 1];//定位到当前匹配子串的“子最长前后缀”(next数组构建过程中唯一的难点，当然也是亮点)，继续进行匹配
                    // 这是跳跃式的，几乎不占时间复杂度，所以总的时间复杂度依然是O(m)
                else{
                    next[j++] = 0;
                }
            }
        }
    }

    /**
     * @param txt 文本
     * @return 是否有子串与模式串匹配
     */
    public boolean match(String txt){
        boolean found = false;
        for(int i = 0, j = 0; i < txt.length(); ){
            if(txt.charAt(i) == pat[j]){
                j++;i++;
            }else {
                if(j != 0) j = next[j-1];//注意这里i要暂时停止前进
                else i++;//第一个就不匹配，i需要前进
            }
            //这个不能放在前面，否则文本在才最后出现匹配的情况将不会被发现
            if(j == next.length) {
                found = true;
                break;
            }
        }
        return found;
    }
    //test kmp
    public static void main(String[] args) {
        KMP kmp = new KMP("abcdabcy");
        String txt = "abcxabcdabcdabcy";
        System.out.println(kmp.match(txt));
    }

}
