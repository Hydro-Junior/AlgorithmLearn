package com.xjy.graph;
/**
 * 二分图检测
 * （双色问题：能够用两种颜色将图的所有顶点着色，使得任意一条边的两个端点的颜色都不相同）
 * @author Mr.Xu
 * 思路：访问过的节点的颜色和它的深搜父节点不一致
 */
public class BipartiteDetect_DFS {
	private boolean[] marked;
	private boolean[] color;
	private boolean isBipartite = true;
	
	public BipartiteDetect_DFS(Graph G) {
		marked = new boolean[G.V()];
		color = new boolean[G.V()];
		for(int s = 0 ; s < G.V(); s++) {
			if(!marked[s])
				dfs(G,s);
		}
	}
	public void dfs(Graph G, int v) {
		marked[v] = true;
		for(int w : G.adj(v)) {
			if(!marked[w]) {
				color[w] = !color[v];
				dfs(G,w);
			}else if(color[w] == color[v]) isBipartite = false;
		}
	}
	public boolean isBipartite() {return isBipartite;}
}
