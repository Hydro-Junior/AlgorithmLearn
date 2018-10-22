package com.xjy.classify.dfs;

import java.util.Scanner;

/**
 * 暴力搜索的话第三组超时
 * @author Mr.Xu
 *Input:
There are multiple cases.

For each case, the first input line contains two integers n and m, which represent the number of numbers
 in the code and the number of attempts made by Siny.

Then follow m lines, each containing space-separated si and ci which correspondingly indicate 
Siny's attempt (a line containing n numbers which are 0 or 1) and the system's response 
(an integer from 0 to 5 inclusively),  n：6-35,m 1-10.

Output:

Print the single number which indicates how many possible combinations of code exist that proves the system is working with no classify.
 */
public class DelightfulWorld {
	public static int m,n;
    public static String[] s ;
    public static int[] c;
    public static char[] tmpCAry;
    public static boolean isMatched;
    public static int count;
    public static int minC=10;
    public static int minIndex;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            m = sc.nextInt();
            n = sc.nextInt();
            s = new String[n];
            c = new int[n];
            tmpCAry = new char[m];
            
            for(int i=0 ; i < n ; i++){
                s[i] = sc.next();
                c[i] = sc.nextInt();
                
                if(c[i] < minC) {
                	minC = c[i];
                	minIndex = i;
                }
            }
            //改进尝试：找出匹配度最小的组，将01串翻转，赋值给tmpCAry
            for(int i=0 ; i < m ; i++) {
            	tmpCAry[i] = (s[minIndex].charAt(i)== '0'?'1':'0');
            }
            dfs(0);
            System.out.println(count);
            //恢复
            count = 0;
            minC = 10;
            minIndex = 0;
            
        }
    }
    public static  void dfs(int i){
        if(i == m){
            isMatched = true;
            for(int j = 0 ; j < n; j++){
                if(match(tmpCAry,s[j]) != c[j]){
                    isMatched = false;
                    break;
                }
            }
            if(isMatched){
                count ++;
            }
            return;
        } 
        tmpCAry[i] = '0';
        dfs(i+1);
        tmpCAry[i] = '1';
        dfs(i+1);  
    }
    public static int match(char[] s1 , String s2){
        int sameNum = 0;
        /*if(s1.length != s2.length()){
            return 0;
        }*/
        for(int i = 0 ; i < s1.length; i++){
            if(s1[i] == s2.charAt(i)){
                sameNum++;
            }
        }
        return sameNum;
    }
}
