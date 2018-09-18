/**
 * 
 */
package com.xjy.graph;

import java.util.Stack;

/**
 * @Description 
 *	无环加权有向图 的最短路径算法:按照拓扑顺序放松顶点！明显减低时间复杂度，可在线性时间内解决单点最短路径问题，并能处理负权边。
 *  按拓扑排序，被放松过的顶点已达最优值，不可能再次被放松，而后面的顶点的距离值也只可能变小
 *  也可用来解决最长路径问题，比较典型的是 并行任务调度问题。
 *  reference to : Algorithm 4th
 * @author Mr.Xu
 * @date 2018年9月15日 下午5:26:08
 *
 */
public class AcyclicDAGSP {
	private DiEdge[] edgeTo; // 记录最短路径
	private double[] distTo; //源点到每个顶点的最短路径
	private Stack<Integer> path;//拓扑排序（加权有向无环图版）
	private boolean[] marked;
	
	public AcyclicDAGSP(EdgeWeightDigraph G , int s) {
		edgeTo = new DiEdge[G.V()];
		distTo = new double[G.V()];
		path = new Stack<>();
		marked = new boolean[G.V()];
		
		for(int v = 0; v < G.V(); v++) {
			distTo[v] = Double.POSITIVE_INFINITY;
		}
		distTo[s] = 0.0;
		//深搜拓扑排序
		for(int v = 0 ; v < G.V(); v++ ) {
			if(!marked[v]) dfs(G,v);
		}
		//按序放松
		while(!path.isEmpty()) {
			relax(G,path.pop());
		}
		
	}
	private void dfs(EdgeWeightDigraph G, int v) {
		marked[v] = true;
		for(DiEdge e : G.adj(v)) {
			int w = e.to();
			if(!marked[w]) {
				dfs(G,w);
				path.push(v);
			}
		}
	}
	private void relax(EdgeWeightDigraph G, int v) {
		for(DiEdge e : G.adj(v)) {
			int w = e.to();
			//计算最长路径只需改变不等式的方向，并将初始值设为Double.NEGATIVE_INFINITY
			if(distTo[w] > distTo[v] + e.weight()) {
				distTo[w] = distTo[v] + e.weight();
				edgeTo[w] = e;
			}
		}
	}
	public double distTo(int v) {return distTo[v];}
	public boolean hasPathTo(int v) {return distTo[v] < Double.POSITIVE_INFINITY;}
	public Stack<DiEdge> pathTo(int v){
		if(!hasPathTo(v)) return null;
		Stack<DiEdge> path  = new Stack<>();
		for(DiEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
			path.push(e);
		}
		return path;
	}
	
}
