package com.xjy.practice.other.pat.AllRoadsLeadtoRome.dfs;

/**
 * @Author: Mr.Xu
 * @Date: Created in 15:48 2018/11/5
 * @Description: 采用和PublicBikeManagement一样的方法（深搜）出现超时情况,不加剪枝全部超时，加了剪枝部分超时
 * （“条条大路通罗马”，所有点都可以到达我们的终点，因此，深搜回溯遍历的节点将会很多）
 * 可见这类最短路径问题用深搜并不是稳定的选择，以后还是dijkstra优先吧
*/
import com.xjy.practice.other.pat.Reader;

import java.io.IOException;
import java.util.*;
public class Main{
    private static PriorityQueue<Path> pq ;
    private static boolean[] marked;
    private static int leastCost;
    public static void main (String[] args) throws IOException{
        leastCost = Integer.MAX_VALUE;
        Reader.init(System.in);
        int V = Reader.nextInt();
        int E = Reader.nextInt();
        String start = Reader.next();
        String[] city = new String[V];
        int[] benefits = new int[V];
        marked = new boolean[V];
        Graph G = new Graph(V);
        //前减后是升序，后减前是逆序
        pq = new PriorityQueue<>((p,q)->{
            if(p.costs != q.costs) return p.costs - q.costs;
            else if(p.happiness != q.happiness) return q.happiness - p.happiness;
            else{
                if(p.averageHappiness > q.averageHappiness) return -1;
                else if(p.averageHappiness < q.averageHappiness) return 1;
                else return 0;
            }
        });
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
            G.addEdge(new Edge(v, w, cost));
        }
        //Long startTime = System.currentTimeMillis();
        dfs(G , 0, cityIdx.get("ROM"), 0, 0, new Stack<>(), benefits);
        Path best = pq.peek();
        int num = 0;
        while(!pq.isEmpty()){
            Path p = pq.poll();
            if(p.costs == leastCost) num++;
            else break;
        }
        System.out.println(num + " " + best.costs + " " + best.happiness+" "+ (int)best.averageHappiness);
        int prev = 0;
        System.out.print(city[0]);
        for(Edge e : best.es){
            System.out.print("->"+city[e.another(prev)]);
            prev = e.another(prev);
        }
        //long endTime = System.currentTimeMillis();
        //System.out.println("\n" + (endTime -startTime));
    }
    public static void dfs(Graph G, int v, int t, int benefit, int cost,
                           Stack<Edge> stack,int[] benefits){
        if(cost > leastCost) return;
        if(v == t){
            if(cost <= leastCost){
                leastCost = cost;
                pq.offer(new Path(benefit,cost,new ArrayList<>(stack)));
            }
            return;
        }
        marked[v] = true;
        for(Edge e : G.adj[v]){
            int w = e.another(v);
            if(!marked[w]){
                stack.push(e);
                dfs(G, w, t, benefit + benefits[w],cost + e.cost,stack,benefits);
                stack.pop();
            }
        }
        marked[v] = false;
    }
}
class Path{
    int happiness;
    int costs;
    double averageHappiness;
    ArrayList<Edge> es;
    public Path(int benifit, int cost, ArrayList<Edge> ls){
        this.happiness = benifit;
        this.costs = cost;
        this.es = ls;
        this.averageHappiness = (double)benifit / ls.size();
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