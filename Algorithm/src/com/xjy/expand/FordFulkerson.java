package com.xjy.expand;

import java.util.*;

/**
 * @Author: Mr.Xu
 * @Date: Created in 21:21 2018/10/24
 * @Description: 利用 Ford-Fulkerson 算法解决最大流问题，结合Edmonds-Karp算法(利用BFS确定一条增广路径(augment path))
 * 为方便起见，这里直接用邻接矩阵存储图(剩余网络)，数组值代表capacity（管道的容量），在解题时可优先选用
 * 在com.xjy.graph.flow 包中介绍了邻接表的方式实现（与有向加权图基本一致），更为正规
 * Reference: https://www.youtube.com/watch?v=GiN3jRdgxU4
 */
public class FordFulkerson {
    public int maxFlow(int capacity[][], int source, int sink){
        //初始化剩余网络
        int residualCapacity[][] = new int[capacity.length][capacity[0].length];
        for(int i= 0; i < capacity.length; i++){
           for(int j = 0; j < capacity[0].length; j++){
               residualCapacity[i][j] = capacity[i][j];
           }
        }
        //parent map 用于存储在 BFS 过程中的 到达结点(key) : 前继结点(value)
        Map<Integer,Integer> parent = new HashMap<>();
        //用于保存增广路径的list
        List<List<Integer>>  augmentedPaths = new ArrayList<>();
        int maxFlow = 0;
        while (BFS(residualCapacity,parent,source,sink)){
            List<Integer> augmentedPath = new ArrayList<>();
            int flow = Integer.MAX_VALUE;
            //找到增广路径中的最小剩余容量,并把一系列顶点添加到增广路径列表中
            int v = sink;
            while(v != source){
                augmentedPath.add(v);
                int u = parent.get(v);
                if(flow > residualCapacity[u][v]){
                    flow = residualCapacity[u][v];
                }
                v = u;
            }
            augmentedPath.add(source);
            Collections.reverse(augmentedPath);
            augmentedPaths.add(augmentedPath);
            //把最小容量添加到总容量
            maxFlow += flow;

            // u -> v 剩余容量减去最小容量
            // v -> u 剩余容量加上最小容量
            v = sink;
            while(v != source){
                int u = parent.get(v);
                residualCapacity[u][v] -= flow;
                residualCapacity[v][u] += flow;
                v = u;
            }
        }
        printAugmentedPaths(augmentedPaths);
        return maxFlow;
    }

    private void printAugmentedPaths(List<List<Integer>> augmentedPaths) {
        System.out.println("Augmented paths");
        augmentedPaths.forEach(path -> {
            path.forEach(i -> System.out.print(i + " "));
            System.out.println();
        });
    }

    private boolean BFS(int[][] residualCapacity, Map<Integer, Integer> parent, int source, int sink) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited.add(source);
        boolean foundAugmentedPath = false;
        //see if we can find augmented path from source to sink
        while (!queue.isEmpty()){
            int u = queue.poll();
            for(int v = 0 ; v < residualCapacity.length; v++){
                //explore the vertex only if it is not visited and its residual capacity is greater than 0
                if(!visited.contains(v) && residualCapacity[u][v] > 0){
                    parent.put(v,u);
                    queue.add(v);
                    visited.add(v);
                    //如果v是终点，说明已经找到增广路径
                    if(v == sink){
                        foundAugmentedPath = true;
                        break;
                    }
                }
            }
        }
        return foundAugmentedPath;
    }
    //test
    public static void main(String[] args) {
        FordFulkerson ff = new FordFulkerson();
        int[][] capacity = {{0, 3, 0, 3, 0, 0, 0},
                            {0, 0, 4, 0, 0, 0, 0},
                            {3, 0, 0, 1, 2, 0, 0},
                            {0, 0, 0, 0, 2, 6, 0},
                            {0, 1, 0, 0, 0, 0, 1},
                            {0, 0, 0, 0, 0, 0, 9},
                            {0, 0, 0, 0, 0, 0, 0}};
        System.out.println("\nMaximum flow "+ ff.maxFlow(capacity,0,6));
    }
}
