package com.xjy.practice.leetcode.StringPathInMatrix;

/**
 * @Author: Mr.Xu
 * @Date: Created in 11:24 2019/3/22
 * @Description:用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径,个人解法：回溯。
 */
public class Solution {
    static int R,C;
    static boolean[][] marked;
    static final int[][] dirs = {{0,1},{0,-1},{-1,0},{1,0}};
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str)
    {
        if(matrix == null || matrix.length == 0) return false;
        if(str== null || str.length == 0) return true;
        R = rows; C = cols;
        marked = new boolean[R][C];
        for(int i = 0 ; i < R; i ++){
            for(int j = 0; j < C; j++){
                if(get(i,j,matrix) == str[0]){
                    marked[i][j] = true;
                    if(dfs(i, j, str, matrix, 0)){
                        return true;
                    }
                    marked[i][j] = false;
                }
            }
        }
        return false;
    }
    private static boolean dfs(int i, int j, char[] str, char[] matrix, int idx){
        if(idx+1 >= str.length) return true;
        boolean res = false;
        for(int[] dir : dirs){
            int x = i + dir[0];
            int y = j + dir[1];
            if(x < 0 || x >= R || y < 0 || y >= C || marked[x][y]) continue;
            if(get(x,y,matrix) == str[idx+1]){
                marked[x][y] = true;
                res = res || dfs(x,y,str,matrix,idx+1);
                marked[x][y] = false;
            }
        }
        return res;
    }
    private static char get(int r, int c, char[] matrix){
        return matrix[C * r + c];
    }

}

/**
 * 另一种写法
 */
class Solution2 {
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        int flag[] = new int[matrix.length];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (helper(matrix, rows, cols, i, j, str, 0, flag))
                    return true;
            }
        }
        return false;
    }

    private boolean helper(char[] matrix, int rows, int cols, int i, int j, char[] str, int k, int[] flag) {
        int index = i * cols + j;
        if (i < 0 || i >= rows || j < 0 || j >= cols || matrix[index] != str[k] || flag[index] == 1)
            return false;
        if(k == str.length - 1) return true;
        flag[index] = 1;
        if (helper(matrix, rows, cols, i - 1, j, str, k + 1, flag)
                || helper(matrix, rows, cols, i + 1, j, str, k + 1, flag)
                || helper(matrix, rows, cols, i, j - 1, str, k + 1, flag)
                || helper(matrix, rows, cols, i, j + 1, str, k + 1, flag)) {
            return true;
        }
        flag[index] = 0;
        return false;
    }
}
