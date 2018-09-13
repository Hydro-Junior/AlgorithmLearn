package com.xjy.graph;
/**
 * 利用深搜找出所有连通分量(其实就是深搜次数了)
 * @author Mr.Xu
 *
 */
public class CC_DFS {
	private boolean[] marked;
	private int[] id;
	private int count;
	
	public CC_DFS(Graph G) {
		marked = new boolean[G.V()];
		id = new int[G.V()];
		for(int s = 0 ; s < G.V(); s++) {
			if(!marked[s]) {//一定是未被访问的，这里小心遗漏
				dfs(G,s);
				count++;
			}
		}
	}
	private void dfs(Graph G, int v) {
		marked[v] = true;
		id[v] = count;
		for(int w : G.adj(v)) {
			if(!marked[w]) dfs(G,w);
		}
	}
	//两个顶点是否连通
	public boolean connected(int v,int w) {
		return id[v] == id[w];
	}
	public int count() {
		return count;
	}
	
	
}
