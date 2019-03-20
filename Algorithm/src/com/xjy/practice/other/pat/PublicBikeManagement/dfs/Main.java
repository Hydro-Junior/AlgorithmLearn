package com.xjy.practice.other.pat.PublicBikeManagement.dfs;

/**
 * @Author: Mr.Xu
 * @Date: Created in 15:47 2018/11/5
 * @Description:
 */
import com.xjy.practice.other.pat.Reader;

import java.io.IOException;
import java.util.*;
/**
 * https://www.nowcoder.com/pat/5/problem/4324
 * PAT advanced 1018
 * 此题单点最短路径加了点数值更新的花样，采用深搜结合优先队列的方法
 */
public class Main{
    private static int leastTime;
    private static boolean[] marked;
    private static PriorityQueue<Path> pq;
    public static void main(String[] args) throws IOException{
        //读入数据，构建图
        Reader.init(System.in);
        int cap = Reader.nextInt();
        int n = Reader.nextInt();
        int t = Reader.nextInt();
        int m = Reader.nextInt();
        int[] curCap = new int[n+1];
        for(int i = 1; i < n+1; i ++){
            curCap[i] = Reader.nextInt();
        }
        Graph G = new Graph(n+1);
        for(int i = 0; i < m; i++){
            G.addEdge(new Edge(Reader.nextInt(),Reader.nextInt(),Reader.nextInt()));
        }
        leastTime = Integer.MAX_VALUE;
        marked = new boolean[n+1];
        pq = new PriorityQueue<>((p,q)-> {
            if(p.time != q.time) return p.time - q.time;
            else if(p.sent != q.sent) return p.sent - q.sent;
            else {
                return p.recycled - q.recycled;
            }
        });
        int sent = 0;
        int recycled = 0;
        dfs(G, 0, 0, sent, recycled, new Stack<>(), t,curCap,cap/2);
        //得到最优路径
        Path p = pq.poll();
        sent = p.sent;
        recycled = p.recycled;
        System.out.print(sent + " " + 0);
        int prev = 0;
        for(Edge e : p.vs){
            System.out.print("->"+e.another(prev));
            prev = e.another(prev);
        }
        System.out.print(" " + recycled);
    }
    public static void dfs(Graph G, int v, int time, int sent, int recycled, Stack<Edge> stack, int t, int[] curCap, int perfectCap){
        if(v == t){
            if(time <= leastTime){
                leastTime = time;
                pq.offer(new Path(time, sent, recycled, new ArrayList<>(stack)));
            }
            return;
        }
        marked[v] = true;
        for(Edge e : G.adj(v)){
            int w = e.another(v);
            if(!marked[w]){
                stack.push(e);
                int preSent = sent;
                int preRecycled = recycled;
                if(curCap[w] > perfectCap){//多余的车辆，需要回收
                    recycled += curCap[w] - perfectCap;
                }else if(curCap[w] < perfectCap){//缺少车辆
                    int diff = perfectCap - curCap[w];
                    if(recycled > 0){
                        if(diff > recycled){
                            sent += diff - recycled;
                            recycled = 0;
                        }else{
                            recycled -= diff;
                        }
                    }else{
                        sent += diff;
                    }
                }
                dfs(G, w, time + e.val, sent, recycled, stack, t,curCap,perfectCap);
                //回溯
                stack.pop();
                sent = preSent;
                recycled = preRecycled;
            }
        }
        marked[v] = false;
    }
}
class Path{
    int time;
    int sent;
    int recycled;
    ArrayList<Edge> vs ;
    public Path(int time, int sent, int recycled, ArrayList vs){
        this.time = time;
        this.sent = sent;
        this.recycled = recycled;
        this.vs = vs;
    }
}
class Graph{
    int V;
    ArrayList<Edge>[] adj;
    public Graph(int v){
        this.V = v;
        adj = new ArrayList[v];
        for(int i = 0; i < v; i++){
            adj[i] = new ArrayList<>();
        }
    }
    public ArrayList<Edge> adj(int v){
        return adj[v];
    }
    public void addEdge(Edge e){
        adj[e.from].add(e);
        adj[e.to].add(e);
    }
}
class Edge{
    int from;
    int to;
    int val;
    public Edge(int v, int w, int val){
        this.from = v;
        this.to = w;
        this.val = val;
    }
    public int another(int v){
        if(from == v) return to;
        else return from;
    }
}
