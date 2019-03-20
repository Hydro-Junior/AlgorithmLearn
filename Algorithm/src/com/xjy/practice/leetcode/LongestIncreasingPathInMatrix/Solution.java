package com.xjy.practice.leetcode.LongestIncreasingPathInMatrix;

/**
 * @Author: Mr.Xu
 * @Date: Created in 15:40 2019/3/20
 * @Description:矩阵中最长递增序列，普通深搜超时，怎么优化？记录已经得到的最大值
 *
 */
class Solution {
    static int M ;
    static int L,W;
    //static boolean[][] marked;
    public static final int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        L = matrix.length; W = matrix[0].length;
        //marked = new boolean[L][W];
        int[][] cache = new int[L][W];
        M = 1;
        for(int i = 0 ; i < L ; i++){
            for(int j = 0; j < W; j++){
               // marked[i][j] = true;
                int len = dfs(i,j,matrix,cache);
                M = Math.max(M,len);
                //marked[i][j] = false;
            }
        }
        return M;
    }
    private static int dfs(int i, int j, int[][] matrix, int[][] cache){
        if(cache[i][j] != 0) return cache[i][j];
        int max = 1;
        for(int[] d : dir){
            int x = i + d[0], y = j + d[1];
            if(x < 0 || x >= L || y < 0 || y >= W  || matrix[x][y] <= matrix[i][j]) continue;
            int len = 1 + dfs(x, y, matrix,cache);
            max = Math.max(max, len);
        }
        cache[i][j] = max;
        return max;
    }
   /* private static void dfs(int i, int j, int[][] matrix, int n){
        if(n > M) M = n;
        for(int d  = 0; d < dir.length; d++){
            int x = i + dir[d][0];
            int y = j + dir[d][1];
            if(x >= 0 && x < L && y >= 0 && y < W && !marked[x][y]){
                marked[x][y] = true;
                dfs(x,y,matrix,n+1);
                marked[x][y] = false;
            }
        }
    }*/

    public static void main(String[] args) {
        int[][] input =  new int[][]{{9,9,4},{6,6,8},{2,1,1}};
        int[][] input2 = new int[][]{{1}};
        Solution s = new Solution();
        int res = s.longestIncreasingPath(input2);
        System.out.println(res);
    }
}