package com.xjy.practice.leetcode.Matrix01;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: Mr.Xu
 * @Date: Created in 10:22 2019/4/9
 * @Description:LeetCode542 01矩阵问题 bfs思路
 */
class Solution {
    static int[][] dir = {{0,1},{0,-1},{-1,0},{1,0}};
    public int[][] updateMatrix(int[][] matrix) {
        int R = matrix.length, C = matrix[0].length;
        for(int i = 0 ; i < R; i++){
            for(int j = 0; j < C; j++){
                if(matrix[i][j] == 1){
                    bfs(matrix,i,j,R,C);
                }
            }
        }
        return matrix;
    }
    private static void bfs(int[][] m, int i, int j, int R, int C){
        int dis = 0;
        Queue<Pair> q = new LinkedList<>();
        boolean stop = false;
        q.offer(new Pair(i,j));
        while(!q.isEmpty()){
            int sz = q.size();
            for(int k = 0 ; k < sz ; k++){
                Pair p = q.poll();
                for(int[] d : dir){
                    int x = p.x + d[0];
                    int y = p.y + d[1];
                    if(x < R && x >= 0 && y < C && y >= 0){
                        if(m[x][y] == 0){
                            stop = true;
                            break;
                        }else{
                            q.offer(new Pair(x,y));
                        }
                    }
                }
                if(stop) break;
            }
            if(stop) break;
            else dis++;
        }
        m[i][j] += dis;
    }
}
class Pair{
    int x;
    int y;
    Pair(int _x, int _y){
        x = _x;
        y = _y;
    }
}