package com.xjy.graph;

import java.util.Stack;
/**
 * 利用深度优先搜索查找到各个节点的路径
 * @author Mr.Xu
 *
 */
public class FindPaths_DFS {
	private boolean[] marked;
	private int[] edgeTo; //路径上的前一个节点
	private final int s; //起点
	
	public FindPaths_DFS(Graph G,int s){
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		this.s = s;
		dfs(G,s);
	}
	private void dfs(Graph G, int v) {
		marked[v] = true;
		for(int w : G.adj(v)) {
			if(!marked[w]) {
				edgeTo[w] = v;//记录前一个节点
				dfs(G,w);
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
	//测试
	public static void main(String[] args) {
		Graph G = new Graph(6);
		G.addEdge(0, 2);
		G.addEdge(1, 2);
		G.addEdge(3, 2);
		G.addEdge(3, 4);
		G.addEdge(3, 5);
		FindPaths_DFS search = new FindPaths_DFS(G, 0);
		for(int v = 0; v < G.V(); v++) {
			System.out.println(0 + "to" + v +":");
			if(search.hasPathTo(v)) {//如果源点到顶点v有路径
				//遍历栈
				Stack<Integer> path = search.pathTo(v);
				while(!path.isEmpty()) {
					if(path.size() == 1) System.out.print(path.pop());
					else System.out.print(path.pop()+"-");
				}
			}
			System.out.println();
		}
	}
}
