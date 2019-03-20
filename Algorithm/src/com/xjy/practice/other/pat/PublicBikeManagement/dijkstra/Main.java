package com.xjy.practice.other.pat.PublicBikeManagement.dijkstra;

/**
 * @Author: Mr.Xu
 * @Date: Created in 10:19 2018/11/6
 * @Description: dijkstra解法
 * https://www.nowcoder.com/pat/5/problem/4324
 * PAT advanced 1018
 */
import java.util.*;
import java.io.*;
public class Main{
    public static void main(String[] args) throws IOException{
        Reader.init(System.in);
        int cap = Reader.nextInt();
        int V = Reader.nextInt()+1;
        int end = Reader.nextInt();
        int E = Reader.nextInt();
        Graph G = new Graph(V);
        int[] cur = new int[V];
        for(int i = 1; i < V; i++){
            cur[i] = Reader.nextInt();
        }
        for(int i = 0 ; i < E; i++){
            G.addEdge(new Edge(Reader.nextInt(),Reader.nextInt(),Reader.nextInt()));
        }
        //dijkstra
        int[] dist = new int[V];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[0] = 0;
        int[] sent = new int[V];
        Arrays.fill(sent,Integer.MAX_VALUE);
        sent[0] = 0;
        int[] recycled = new int[V];
        Arrays.fill(recycled,Integer.MAX_VALUE);
        recycled[0] = 0;
        int[] parent = new int[V];
        PriorityQueue<Pair> pq = new PriorityQueue<>((p,q)->p.dist-q.dist);
        boolean[] marked = new boolean[V];
        pq.offer(new Pair(0,0));
        while(!pq.isEmpty()){
            int pv = pq.poll().v;
            if(marked[end]) break;
            if(marked[pv])continue;
            marked[pv] = true;
            for(Edge edge : G.adj[pv]){
                int w = edge.another(pv);
                if(dist[pv] + edge.cost <= dist[w]){
                    int curSent = 0;
                    int curRecycled = 0;
                    if(cur[w] < cap/2){//需要派送
                        if(recycled[pv] >= cap/2 - cur[w]){
                            curRecycled = recycled[pv] - (cap/2 - cur[w]);
                            curSent = sent[pv];
                        }else{
                            curRecycled = 0;
                            curSent = sent[pv] + cap/2 - cur[w] -recycled[pv];
                        }
                    } else if(cur[w] > cap/2){//需要回收
                        curRecycled = recycled[pv] + cur[w] - cap/2;
                        curSent = sent[pv];
                    }else{
                        curRecycled = recycled[pv];
                        curSent = sent[pv];
                    }
                    if(dist[pv] + edge.cost < dist[w]){
                        dist[w] = dist[pv] + edge.cost;
                        parent[w] = pv;
                        sent[w] = curSent;
                        recycled[w] = curRecycled;
                        pq.offer(new Pair(w,dist[w]));
                    }else if(curSent < sent[w]){
                        sent[w] = curSent;
                        recycled[w] = curRecycled;
                        parent[w] = pv;
                    }
                }
            }
        }
        //获取路径
        ArrayList<Integer> path = new ArrayList<>();
        path.add(end);
        int finalSent = sent[end];
        int finalRecycled = recycled[end];
        while(parent[end] != 0){
            path.add(parent[end]);
            end = parent[end];
        }
        System.out.print(finalSent + " " + 0);
        for(int i = path.size()-1; i >= 0; i--){
            System.out.print("->"+path.get(i));
        }
        System.out.print(" " + finalRecycled);
    }
}
class Pair{
    int v;
    int dist;
    Pair(int v, int dist){
        this.v = v;
        this.dist = dist;
    }
}
class Graph{
    int V ;
    ArrayList<Edge>[] adj;
    Graph(int v){
        this.V = v;
        adj = new ArrayList[v];
        for(int i = 0 ;i < v; i++){
            adj[i] = new ArrayList<>();
        }
    }
    public void addEdge(Edge e){
        adj[e.w].add(e);
        adj[e.v].add(e);
    }
}
class Edge{
    int w;
    int v;
    int cost;
    public Edge(int w, int v, int cost){
        this.w = w;
        this.v = v;
        this.cost = cost;
    }
    public int another(int p){
        if(p == w) return v;
        else return w;
    }
}
class Reader{
    static BufferedReader reader;
    static StringTokenizer tokenizer;
    static void init(InputStream input){
        reader = new BufferedReader(new InputStreamReader(input));
        tokenizer = new StringTokenizer("");
    }
    static String next() throws IOException{
        while(!tokenizer.hasMoreTokens()){
            tokenizer = new StringTokenizer(reader.readLine());
        }
        return tokenizer.nextToken();
    }
    static int nextInt() throws IOException{
        return Integer.parseInt(next());
    }
    static double nextDouble() throws IOException{
        return Double.parseDouble(next());
    }
}