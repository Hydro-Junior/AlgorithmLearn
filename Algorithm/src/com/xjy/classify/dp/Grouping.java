package com.xjy.classify.dp;

/**
 * @Author: Mr.Xu
 * @Date: Created in 10:17 2018/12/3
 * @Description: 划分问题
 *
 */
public class Grouping {
    /**
     * 【1】有n个无区别的物品，将它们划分成不超过m组，求出划分方法(数模M的余数,这里暂时忽略求模)。
     *
     * 思路：ai为每组的个数，共m组（ai有可能为0），按照题目有 a1+a2+...am = n
     * 如果对于每个i都有 ai > 0, 则 ai - 1 对应了n - m 的 m 划分；如果存在 ai = 0,这就对应了 n 的 m - 1划分
     * 递推关系如下：
     * dp[i][j] = dp[i][j-i] + dp[i-1][j]（当然，如果划分后放入不同的箱子，不能使用这种递推方式）
     */
    public static int divideSameItems(int n, int m){
        int[][] dp = new int[m+1][n+1];
        dp[0][0] = 1;
        for(int i = 1; i <= m; i++){
            for(int j = 0; j <= n ; j++){
                if(j - i >= 0){
                    dp[i][j] = dp[i-1][j] + dp[i][j-i];//通俗来讲，就是分为有空箱子的情况和没有空箱子的情况。
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 【2】多重集组合数
     *  有n种物品，第i种物品有ai个。不同种类的物品可以互相区分但相同种类的无法区分。从这些物品中取出m个的话，有多少种取法？求出方案数模M的余数。
     *  (定义 dp[i+1][j] := 从前i种物品中取出j个的组合总数)
     *  递推关系：dp[i+1][j] = dp[i+1][j-1] + dp[i][j] - dp[i][j-1-ai]
     *  过程：为了从前i种物品中取出j个，可以从前i-1种物品中取出j-k个，再从第i种物品中取出k个添加进来，可得：
     *  dp[i+1][j] = dp[i][j-0] + dp[i][j-1] + dp[i][j-2] + ... dp[i][j-min(j,ai)]
     *  又因为 dp[i+1][j-1] = dp[i][j-1] + dp[i][j-2] + ...dp[i][j-1-min(j-1,ai)]
     *  前后相抵有：dp[i+1][j] = dp[i+1][j-1] + dp[i][j] - dp[i][j-1-ai]
     * @param a 数组，记录每种物品的数量
     * @param m 选出m个
     * @param M 模
     * @return
     */
    public static int chooseFromMultiGroups(int[] a, int m, int M){
        int n = a.length;
        int[][] dp = new int[n+1][m+1];
        for(int i = 0 ; i <= n; i++){
            dp[i][0] = 1; //一个都不取的方法总为一种
        }
        for(int i = 0 ; i < n; i++){
            for(int j = 1; j <= m ; j++){
                if(j - 1 - a[i] >= 0){
                    dp[i+1][j] = (dp[i+1][j-1] + dp[i][j] - dp[i][j-1-a[i]] + M) % M;
                }else{
                    dp[i+1][j] = (dp[i+1][j-1] + dp[i][j]) % M;
                }
            }
        }
        return dp[n][m];
    }

    //test
    public static void main(String[] args) {
        System.out.println(Grouping.divideSameItems(4,3));

        int[] input = new int[]{1,2,3};
        int M = 10000;
        System.out.println(Grouping.chooseFromMultiGroups(input,3,M));
    }
}
