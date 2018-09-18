package com.xjy.graph;

import java.util.ArrayList;

//有向图
public class DiGraph {
	private final int V;
	private int E;
	private ArrayList<Integer>[] adj;
	
	public DiGraph(int V) {
		this.V = V;
		this.E = 0;
		adj = (ArrayList<Integer>[]) new ArrayList[V];
		for(int v = 0 ; v < V; v++) {
			adj[v] = new ArrayList<Integer>();
		}
	}
	public int V() {return V;}
	public int E() {return E;}
	
	public void addEdge(int v, int w) {
		adj[v].add(w);
		E++;
	}
	public ArrayList<Integer> adj(int v){
		return adj[v];
	}
	//反转有向图(新建图，添加边即可)
	public DiGraph reverse() {
		DiGraph R = new DiGraph(V);
		for(int v = 0 ; v < V; v++) {
			for(int w : adj(v))
				R.addEdge(w, v);
		}
		return R;
	}
	
}
