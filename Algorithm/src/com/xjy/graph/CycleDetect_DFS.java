package com.xjy.graph;
//环检测：naive的想法是节点被重复访问即有环，实际这是个明显的误区，那么有环的条件是什么？（不包括自环和平行边）
// ———>如果在遍历的过程中，发现某个节点有一条边指向已经访问过的节点，并且这个已访问过的节点不是当前节点的父节点（这里的父节点表示dfs遍历顺序中的父节点），则表示存在环。
//(其他思路也可：结合顶点和边的个数考虑，但效率和简单性不如dfs)
public class CycleDetect_DFS {
	private boolean[] marked;
	private boolean hasCycle;
	
	public CycleDetect_DFS(Graph G) {
		marked = new boolean[G.V()];
		for(int s = 0; s < G.V(); s++) {
			if(!marked[s]) dfs(G,s,s);
		}
	}
	private void dfs(Graph G, int v, int u) {
		marked[v] = true;
		for(int w: G.adj(v)){
			if(!marked[w]) {
				dfs(G,w,v);
			}else if(w != u) { //这个节点被访问过且不是它爹
				hasCycle = true;
			}
		}
	}
	public boolean hasCycle(){return hasCycle;}
}
