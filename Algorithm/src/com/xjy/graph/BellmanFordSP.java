/**
 * 
 */
package com.xjy.graph;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/**
 * @Description 
 * Bellman-Ford算法解决普通的无负权环图的单源最短路径问题，并能检测出负权环
 * 算法描述：每次放松所有边，共放松V-1轮即可。
 * 通俗解释为什么放松V-1轮即可：因为最短路径必为简单路径，最多有V-1条边，每次放松所有边的话，无论按什么顺序，只要放松V-1轮，
 * 即便每次更新一个顶点的dist，这条最短路径上的顶点也都会得到放松。(实际上，第i轮放松可以得到距源点的最短路径由i条边构成的（或少于i条边）所有顶点的最短路径！)
 * （详细证明可用归纳法或参考算法导论相关章节）
 * 简单化代码（时间复杂度 ： O(EV))）：
 * 		for(int pass = 0 ; pass < G.V()-1; pass++){
 * 			for(int v = 0 ; v < G.V(); v++){
 * 				for(DiEdge e: G.adj(v)){
 * 					relax(e);
 * 				}
 * 			}
 * 		}
 * 本类中采用队列优化后的Bellman-Ford算法，提高其普适性。（距离值发生改变的顶点才放到队列中参与下次放松，避免重复计算）
 * @author Mr.Xu
 * @date 2018年9月17日 下午5:10:51
 *
 */
public class BellmanFordSP {
	private double[] distTo;//单源距离
	private DiEdge[] edgeTo;//路径中该顶点的入边
	private boolean[] onQ; //顶点是否在队列中
	private Queue<Integer> queue; //待放松的顶点
	private int cost; //relax的调用次数
	private Iterable<DiEdge> cycle; //保存负权重环
	
	public BellmanFordSP(EdgeWeightDigraph G, int s) {
		distTo = new double[G.V()];
		edgeTo = new DiEdge[G.V()];
		onQ = new boolean[G.V()];
		queue = new ArrayDeque<>();
		for(int v = 0 ; v < G.V(); v++) {
			distTo[v] = Double.POSITIVE_INFINITY;
		}
		distTo[s] = 0.0;
		queue.offer(s);
		onQ[s] = true;
		while(!queue.isEmpty() && !hasNegativeCycle()) {
			int v = queue.poll();
			onQ[v] = false;
			relax(G,v);
		} 
	}
	//Bellman-Ford的放松操作
	private void relax(EdgeWeightDigraph G,int v) {
		for(DiEdge e : G.adj(v)) {
			int w = e.to();
			if(distTo[w] > distTo[v] + e.weight()) {
				distTo[w] = distTo[v] + e.weight();
				edgeTo[w] = e;
			}
			if(!onQ[w]) {
				queue.offer(w);
				onQ[w] = true;
			}
		}
		//每调用V次方法，检测一次
		if(cost++ % G.V() == 0) {
			findNegativeCycle();
			if(hasNegativeCycle())return;
		}
	}
	public double distTo(int v) {return distTo[v];}
	public boolean hasPathTo(int v) {return distTo[v] != Double.POSITIVE_INFINITY;}
	public Stack<DiEdge> pathTo(int v){
		if(!hasPathTo(v)) return null;
		Stack<DiEdge> res = new Stack<>();
		for(DiEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
			res.push(e);
		}
		return res;
	}
	//找负权重环
	private void findNegativeCycle() {
		int V = edgeTo.length;
		EdgeWeightDigraph spt;
		spt = new EdgeWeightDigraph(V);
		//将所有边放松V轮后当且仅当队列非空时有向图中才存在从起点可达的负权重环，此时edgeTo[]数组所表示的子图中必然含有这个负权重环。
		for(int v = 0 ; v < V ; v++) {
			if(edgeTo[v] != null) {
				spt.addEdge(edgeTo[v]);
			}
		}
		EdgeWeightDiCycle finder = new EdgeWeightDiCycle(spt);
		cycle = finder.cycle();
	}
	private boolean hasNegativeCycle() {return cycle != null;}
	public Iterable<DiEdge> negativeCycle(){
		return cycle;
	}
	
}
//有向加权图的环检测
class EdgeWeightDiCycle{
	private boolean[] onStack;
	private boolean[] marked;
	private Stack<DiEdge> cycle;
	private DiEdge[] edgeTo;
	public EdgeWeightDiCycle(EdgeWeightDigraph G) {
		marked = new boolean[G.V()];
		onStack = new boolean[G.V()];
		for(int v = 0 ; v < G.V(); v++) {
			if(!marked[v]) dfs(G,v);
		}
	}
	private void dfs(EdgeWeightDigraph G,int v) {
		marked[v] = true;
		onStack[v] = true;
		for(DiEdge e : G.adj(v)) {
			if(cycle != null) return;
			int w = e.to();
			if(!marked[w]) {
				edgeTo[w] = e;
				dfs(G,w);
			}
			else if(onStack[w]) {
				cycle = new Stack<DiEdge>();
                DiEdge f = e;
                while (f.from() != w) {
                    cycle.push(f);
                    f = edgeTo[f.from()];
                }
                cycle.push(f);

                return;
			}
		}
		onStack[v] = false;
	}
	public Stack<DiEdge> cycle(){return cycle;}
}
