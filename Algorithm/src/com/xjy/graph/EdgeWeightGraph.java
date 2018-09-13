package com.xjy.graph;

import java.util.ArrayList;

/**
 * 有权重的无向图
 * @author Mr.Xu
 *
 */
public class EdgeWeightGraph {
	private final int V;
	private int E;
	private ArrayList<Edge>[] adj;//由于权重的因素，领接表中的对象改为Edge
	
	public EdgeWeightGraph(int V) {
		this.V = V;
		this.E = 0;
		adj = (ArrayList<Edge>[])new ArrayList[V];
		for(int v = 0 ; v < V; v++) {
			adj[v] = new ArrayList<>();
		}
		
	}
	public int V() {return V;}
	public int E() {return E;}
	
	public void addEdge(Edge e) {
		int v = e.either(),w = e.other(v);
		adj[v].add(e);
		adj[w].add(e);//两个顶点的邻接表都加上同一条边
		E++;
	}
	//返回连接某个顶点的所有边
	public Iterable<Edge> adj(int v){
		return adj[v];
	}
	//返回整个无向图的所有边
	public Iterable<Edge> edges(){
		ArrayList<Edge> b = new ArrayList<>();
		for(int v = 0 ; v < V; v++) {
			for(Edge e : adj[v]) {
				if(e.other(v) > v) b.add(e);//避免重复
			}
		}
		return b;
	}
	

}
