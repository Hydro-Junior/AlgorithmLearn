package com.xjy.graph;

import java.util.ArrayList;

//简单无向图
public class Graph {
	private final int V;//顶点
	private int E;//边
	private ArrayList<Integer>[] adj;//领接表
	
	public Graph(int V) {
		this.V = V;
		this.E = 0;
		adj =  (ArrayList<Integer>[])new ArrayList[V];
		for(int v = 0 ; v < V; v++) {
			adj[v] = new ArrayList<Integer>();
		}
	}
	public int V() {return V;}
	public int E() {return E;}
	public void addEdge(int v, int w) {
		adj[v].add(w);
		adj[w].add(v);//注意这种对称在有向图中不一定存在
		E++;
	}
	public Iterable<Integer> adj(int V){
		return adj[V];
	}
}
