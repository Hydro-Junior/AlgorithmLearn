package com.xjy.graph;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Prim算法求加权无向图的最小生成树
 * 贪心算法，从某个顶点出发，选最小的边，其必在最小生成树中，同时借助优先队列降低时间复杂度
 * @author Mr.Xu
 * 本类采用简单点的延时版本（延迟删除优先队列中的无效边）
 */
public class PrimMST {
	private Queue<Integer> mst;
	private PriorityQueue<Edge> pq;
	private boolean[] marked;
	
	public PrimMST(EdgeWeightGraph G) {
		pq = new PriorityQueue<>();
		marked = new boolean[G.V()];
		mst = new LinkedList<>();
		visit(G,0);
		while(!pq.isEmpty()) {
			Edge e = pq.poll();
			int v = e.either(), w = e.other(v);
			if(marked[v] && marked[w]) continue;
			if(!marked[w]) visit(G,w);
			if(!marked[v]) visit(G,v);
		}
	}
	private void visit(EdgeWeightGraph G ,int v) {
		marked[v] = true;
		for(Edge e : G.adj(v)) {
			int w = e.other(v);
			if(!marked[w]) pq.offer(e);
		}
	}
}
