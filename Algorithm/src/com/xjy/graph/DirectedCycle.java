package com.xjy.graph;

import java.util.Stack;

//有向环的检测：结合DFS和Stack实现
public class DirectedCycle {
	private boolean[] marked;
	private int[] edgeTo; //前一个节点
	private Stack<Integer> cycle; //用栈保存环
	private boolean[] onStack; //一次深搜递归栈上的顶点（一组连通分量）
	
	public DirectedCycle(DiGraph G) {
		onStack = new boolean[G.V()];
		edgeTo = new int[G.V()];
		marked = new boolean[G.V()];
		for(int v = 0 ; v < G.V(); v++) {
			if(!marked[v]) dfs(G,v);
		}
	}
	private void dfs(DiGraph G, int v) {
		marked[v] = true;
		onStack[v] = true;
		for(int w : G.adj(v)) {//w可达
			if(hasCycle()) return;
			else if(!marked[w]) 
			{edgeTo[w] = v ; dfs(G,w);}
			//w已经被访问过，进一步检查它在不在此次递归栈中
			else if(onStack[w]) {//w已存在于路径中
				cycle = new Stack();
				for(int i = v; i != w; i= edgeTo[i]) {
					cycle.push(i);
				}
				cycle.push(w);
				cycle.push(v);//路径：... w -> ... -> v -> w
			}
		}
		onStack[v] = false;//取消栈标记
	}
	private boolean hasCycle() {
		return cycle != null;
	}
}
