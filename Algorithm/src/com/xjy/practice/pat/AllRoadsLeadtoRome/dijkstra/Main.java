package com.xjy.practice.pat.AllRoadsLeadtoRome.dijkstra;

/**
 * @Author: Mr.Xu
 * @Date: Created in 16:43 2018/11/5
 * @Description: https://www.nowcoder.com/pat/5/problem/4315
 * PAT advanced 1087
 * 此题的核心要求在于对dijkstra算法的灵活运用，获取有用的附带信息，比如最短路径个数
 * 进一步问题，能否打印所有的最短路径呢？可以考虑再用一个优先队列存储结果集，不过这里没有必要
 */
import com.xjy.practice.pat.Reader;

import java.io.IOException;
import java.util.*;
public class Main{
    public static void main (String[] args)throws IOException{
        Reader.init(System.in);
        int V = Reader.nextInt();
        int E = Reader.nextInt();
        String start = Reader.next();
        String[] city = new String[V];
        int[] benefits = new int[V];
        Graph G = new Graph(V);
        HashMap<String, Integer> cityIdx = new HashMap<>();
        city[0] = start;
        cityIdx.put(start,0);
        for(int i = 1; i < V; i++){
            String cityName = Reader.next();
            city[i] = cityName;
            cityIdx.put(cityName,i);
            benefits[i] = Reader.nextInt();
        }
        for(int i = 0; i < E; i++){
            int v = cityIdx.get(Reader.next());
            int w = cityIdx.get(Reader.next());
            int cost = Reader.nextInt();
            G.addEdge(new Edge(v,w,cost));
        }
        int end = cityIdx.get("ROM");
        //dijkstra
        int[] dist = new int[V];//最短距离
        Edge[] edgeTo = new Edge[V];//最终路径中指向该顶点的边
        int[] routes = new int[V];//最短路径个数
        int[] step = new int[V];//路径中经历的节点个数
        int[] happiness = new int[V];//到达某点的总快乐指数
        boolean[] marked = new boolean[V];//是否已被访问过,不标记是否访问过部分用例会出错，因此很有必要，具体原因待查
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[0] = 0;
        routes[0] = 1;//到达源点的最短路径只有1条
        PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o1.dis - o2.dis;
            }
        });
        pq.offer(new Pair(dist[0],0));
        while(!pq.isEmpty()){
            int v = pq.poll().v;
            if(v == end) break; //到了终点即时退出，这个优化挺重要
            if(marked[v]) continue;
            marked[v] = true;
            for(Edge e : G.adj[v]){
                int w = e.another(v);
                if(marked[w]) continue;
                int ndist = dist[v] + e.cost;
                int nstep = step[v] + 1;
                int nhappiness = happiness[v] + benefits[w];
                boolean update = false;
                if(ndist < dist[w]) {
                    dist[w] = ndist;
                    routes[w] = routes[v];
                    pq.offer(new Pair(dist[w] , w));
                    update = true;
                }else if(ndist == dist[w]){
                    routes[w] += routes[v];
                    if(nhappiness > happiness[w] ||(nhappiness == happiness[w] && nstep < step[w])){
                        update = true;
                    }
                }
                if(update){
                    happiness[w] = nhappiness;
                    step[w] = nstep;
                    edgeTo[w] = e;
                }
            }
        }
        System.out.println(routes[end] + " " + dist[end] + " " +happiness[end] + " "+happiness[end] / step[end]);
        Stack<Integer> path = new Stack<>();
        path.push(end);
        int prev = edgeTo[end].another(end);
        while(prev != 0){
            path.push(prev);
            prev = edgeTo[prev].another(prev);
        }
        System.out.print(city[0]);
        while(!path.isEmpty()){
            System.out.print("->"+city[path.pop()]);
        }
    }
}
class Pair{
    int dis;
    int v;
    public Pair(int dis,int v){
        this.dis = dis;
        this.v = v;
    }
}
class Graph{
    int V;
    ArrayList<Edge>[] adj;
    public Graph(int v){
        this.V = v;
        adj = new ArrayList[v];
        for(int i = 0 ; i < v; i++){
            adj[i] = new ArrayList<>();
        }
    }
    public void addEdge(Edge e){
        adj[e.w].add(e);
        adj[e.v].add(e);
    }
}
class Edge{
    int v;
    int w;
    int cost;
    public Edge(int v,int w,int cost){
        this.v = v;
        this.w = w;
        this.cost = cost;
    }
    public int another(int p){
        if(p == v) return w;
        else return v;
    }
}

