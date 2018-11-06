package com.xjy.graph;

import java.util.Arrays;

/**
 * @Author: Mr.Xu
 * @Date: Created in 10:35 2018/11/5
 * @Description:Floyd-Warshall算法，求图中任意两点间的最短路问题
 * 很简单的三重循环，如果复杂度要求不高，可以采用
 * 核心思想，动态规划，按最短路是否经过顶点k讨论：
 * d[k][i][j] = min(d[k-1][i][j],d[k-1][i][k] + d[k-1][k][j]),滚动更新使用二维数组即可
 * 在O(|V|^3)时间内求所有两点间的最短路径
 * Floyd-Warshall 算法和Bellman-Ford算法一样，可以处理边是负数的情况，而判断图中是否有负圈，只需检查是否存在d[i][i]是负数即可！
 */
public class FloydWarshall {
    private double[][] dist;
    private int V;
    public FloydWarshall(int v){
        this.V = v;
        dist = new double[V][V];
        /*初始化 读入d[u][v] 表示e=(u,v)边的权重(不存在时设为Double.POSITIVE_INFINITY,不过d[i][i]=0)
            ....
        */
        for(int k = 0; k < V; k++){
            for(int i = 0; i < V; i++){
                for(int j = 0; j < V; j++){
                    dist[i][j] = Math.min(dist[i][j],dist[i][k] + dist[k][j]);
                }
            }
        }
    }
}
