/**
 * 
 */
package com.xjy.graph;

import java.util.Stack;

import com.xjy.adt.MyIndexMinPQ;

/**
 * @Description 
 * Dijkstra算法求单元最短路径问题（贪心思想，且适用于边权重非负的情况）。
 * 核心：每次从优先队列（最小堆）中弹出距源点最小的可达点，那么它的最短路径必然已经确定，因为如果它要从其他点中转，经过的点的dist(离源点距离)都比它当前的dist大。
 * 随后通过松弛这个顶点，更新它可达点的dist。如此往复，直到优先队列为空。
 * 时间复杂度：（最坏情况下ElogV）
 * @author Mr.Xu
 * @date 2018年9月13日 下午3:18:00
 *
 */
public class DijkstraSP {
	private DiEdge[] edgeTo;
	private double[] distTo;
	private MyIndexMinPQ<Double> pq;//采用索引优先队列，关联顶点编号与距离
	
	public DijkstraSP(EdgeWeightDigraph G ,int s) {
		edgeTo = new DiEdge[G.V()];
		distTo = new double[G.V()];
		pq = new MyIndexMinPQ<>(G.V());
		//距离初始化
		for(int v = 0 ; v < G.V(); v++) {
			distTo[v] = Double.POSITIVE_INFINITY;
		}
		distTo[s] = 0.0;
		pq.insert(s, 0.0);
		while(!pq.isEmpty()) {
			relax(G,pq.delMin());
		}
	}
	private void relax(EdgeWeightDigraph G, int v) {
		for(DiEdge e : G.adj(v)) {
			int w = e.to();
			//松弛边
			if(distTo[v] + e.weight() < distTo[w]) {
				distTo[w] = distTo[v] + e.weight();
				edgeTo[w] = e;
				//两种情况：1.w已存在于优先队列，更新distTo[w] 2.w不存在，添加到优先队列
				if(pq.contains(w))pq.changeKey(w, distTo[w]);
				else pq.insert(w, distTo[w]);
				
			}
		}
	}
	public double distTo(int v) {
		return distTo[v];
	}
	public boolean hasPathTo(int v) {
		return distTo[v] < Double.POSITIVE_INFINITY;
	}
	public Stack<DiEdge> pathTo(int v){
		if(!hasPathTo(v)) return null;
		Stack<DiEdge> path  = new Stack<>();
		for(DiEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
			path.push(e);
		}
		return path;
	}
}
