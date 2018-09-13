package com.xjy.graph;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Kruskal算法求最小生成树，每次选取最小的边（需要用到并查集标记已经连通的分量）
 * @author Mr.Xu
 *
 */
public class KruskalMST {
	private Queue<Edge> mst;
	
	public KruskalMST(EdgeWeightGraph G) {
		mst = new LinkedList<>();
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		for(Edge e : G.edges()) {
			pq.offer(e);
		}
		CC_UF uf = new CC_UF(G.V());
		while(!pq.isEmpty() && mst.size() < G.V()-1) {
			Edge e = pq.poll();
			int v = e.either(), w = e.other(v);
			if(uf.connected(v, w))continue;
			uf.union(v, w);
			mst.add(e);
		}
	}
	public Iterable<Edge> mst(){
		return mst;
	}
	
}
