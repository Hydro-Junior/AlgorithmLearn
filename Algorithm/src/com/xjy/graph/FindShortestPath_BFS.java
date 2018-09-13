package com.xjy.graph;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

//利用广度优先搜索查找到某节点的最短路径
public class FindShortestPath_BFS {
	private boolean[] marked; //最短路径是否已知
	private int[] edgeTo; //前一个节点
	private final int s; //源节点
	
	public FindShortestPath_BFS(Graph G , int s) {
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		this.s = s;
		bfs(G,s);
	} 
	private void bfs(Graph G, int s) {
		Deque<Integer> queue = new ArrayDeque<Integer>();
		marked[s] = true;
		queue.offer(s);
		while(!queue.isEmpty()) {
			int v = queue.poll();
			for(int w : G.adj(v)) {
				if(!marked[w]) {
					marked[w] = true;
					queue.offer(w);
					edgeTo[w] = v;
				}
			}
		}
	}
	public boolean hasPathTo(int v) {
		return marked[v];
	}
	public Stack<Integer> pathTo(int v){
		if(!hasPathTo(v)) return null;
		//用栈记录路径
		Stack<Integer> path = new Stack<Integer>();
		for(int x = v; x != s; x = edgeTo[x]) {
			path.push(x);
		}
		path.push(s);
		return path;
	}
	
}
