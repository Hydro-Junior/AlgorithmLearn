package com.xjy.graph;

import java.util.ArrayList;

/**
 * 加权有向图
 * @author Mr.Xu
 * 
 */
public class EdgeWeightDigraph{
	private final int V;
	private int E;
	private ArrayList<DiEdge>[] adj;
	public EdgeWeightDigraph(int V) {
		this.V = V;
		this.E = 0;
		adj = (ArrayList<DiEdge>[])new ArrayList[V];
		for(int v = 0 ;v < V; v++) {
			adj[v] = new ArrayList<>();
		}
	}
	public int V() {return V;}
	public int E() {return E;}
	public void addEdge(DiEdge e) {
		adj[e.from()].add(e);
		E++;
	}
	public Iterable<DiEdge> adj(int v){
		return adj[v];
	}
	public Iterable<DiEdge> edges(){
		ArrayList<DiEdge> list = new ArrayList<>();
		for(int v = 0 ; v < V; v++) {
			for(DiEdge e : adj[v]) {
				list.add(e);
			}
		}
		return list;
	}
	
}
