package com.xjy.dfs;

/**
 * 星钻采集（此题除了暴力深搜之外似乎有更好地解法，计蒜客上只通过了测试用例）
 * 腾讯的增值服务很多，有红钻、黄钻、绿钻等。产品经理龙龙正在准备为新产品推出一种“星钻”，钻上可以有不同的星数。
 * 现在有 N 个“星钻”，第 i 个“星钻”的星数为 Si，价格是 Wi，得到它需要付出的时间为 Ci。现在你有 M 块钱。
 * 求在给定钱数的范围内，最少需要多长时间，可以得到 7 个星数不同的“星钻”。

输入格式

第一行输入一个整数 T（1 ≤ T ≤ 20），表示数据组数。

每组数据的第一行输入两个整数 N（1 ≤ N ≤ 100）和 M（1 ≤ M ≤ 109）。

接下来的 N 行，每行三个正整数 Si, Wi, Ci（1 ≤ Si, Wi, Ci ≤ 109）。
3
8 8
1 1 2
1 2 1
2 1 1
3 1 1
4 1 1
5 1 1
6 1 1
7 1 1
10 12
1 1 2
2 2 2
2 3 1
3 1 2
4 2 1
4 2 2
5 1 1
6 1 1
7 2 2
7 3 1
4 5
1 1 1
2 1 2
3 1 1
4 1 1

输出格式

输出一个整数表示最少需要多少时间，如果无解则输出 -1。
 */
import java.util.*;
public class CollectDiamonds{
    static int[] minT; //记录每组目前为止最优的解
    static int[] tempSumT; //记录当前所花的总时间
    static int[] count; //记录当前采集钻石的个数
    static int[] m;   //每组手头的钱
    static int[] n;   //每组的星钻总个数
    static int[] fee; //记录当前所花费用
    static boolean[] solved; //标记这组问题是否有解
    static int[][] S; //点数
    static int[][] W; //价格
    static int[][] C; //时间代价
    static int[][] had; //标记是否已有这个星钻
    static int[][] book;//标记这组是否在选择内
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int gs = sc.nextInt();//数据组数
        minT = new int[gs];
        for(int k = 0 ; k < gs; k++){
            minT[k] = (int) 1e11;
        }
        S = new int[gs][];
        W = new int[gs][];
        C = new int[gs][];
        had = new int[gs][100];
        book = new int[gs][];
        n = new int[gs];
        m = new int[gs];
        fee = new int[gs];
        tempSumT = new int[gs];
        count = new int[gs];
        solved = new boolean[gs];
        for(int k = 0 ; k < gs ; k++){
            n[k] = sc.nextInt();
            m[k] = sc.nextInt();
            S[k] = new int[n[k]];
            W[k] = new int[n[k]];
            C[k] = new int[n[k]];
            book[k] = new int[n[k]];
            for(int i = 0 ; i < n[k]; i++){
                S[k][i] = sc.nextInt();
                W[k][i] = sc.nextInt();
                C[k][i] = sc.nextInt();
            }
        }
        for(int k = 0 ;  k < gs ; k++){
            dfs(k,0);
            if(!solved[k]){
                System.out.println(-1);
            }else{
                System.out.println(minT[k]);
            }
        }
    }
    public static void dfs(int k , int step){
        if(step <= n[k] && count[k] == 7){
            if(fee[k] > m[k]){
                return;
            }else{
               if(tempSumT[k] < minT[k]){
                   minT[k] = tempSumT[k];
               }
               solved[k] = true;
               return;
               
            }
        }
        if(step == n[k] && count[k] < 7){
            return;
        }
        for(int j = 0 ; j < n[k]; j++){
        	//思考：以下方法相当于暴力搜索！这里的had和book能否舍弃一个？或者说如何调整(剪枝)得到更优化的方案？
            if(had[k][S[k][j]] == 0 && book[k][j] == 0){
                count[k] ++;
                fee[k] += W[k][j];
                tempSumT[k] += C[k][j];
                book[k][j] = 1;
                had[k][S[k][j]] = 1;
                dfs(k,step + 1);
                book[k][j] = 0;
                had[k][S[k][j]] = 0;
                count[k] --;
                fee[k] -= W[k][j];
                tempSumT[k] -= C[k][j];
            }
        }
        
    }
}
