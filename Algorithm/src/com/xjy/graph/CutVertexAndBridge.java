package com.xjy.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * 割点与桥（Articulation Point/Cut Vertex and Brigde/Cut Edge）
 * 符合如下条件的顶点和边：删除它们都会增加原无向图的连通分量
 * 意义：割点与桥揭示了图的弱点和瓶颈
 * 有割点不一定有桥，但有桥的话必有割点，桥必然是割点依附的边
 * 采用算法：Tarjan
 * 
 * @author Mr.Xu
 * 分析：
 * 1. 如果顶点U至少存在一个孩子节点，是必须通过父节点U才能访问到U的祖先顶点，那么去掉顶点U后，顶点U的祖先顶点和孩子就不连通了，说明U是一个割点。
 * 2. 对于没有祖先的根顶点，如果从根节点出发1次dfs能访问所有顶点，它就不是割点，反之就是。
 * 3. 对于顶点v，至少存在一个孩子w满足low[w] >= dnf[v](不经过它不能到祖先)，若有low[w]> dnf[v],w-v是桥（先有割点后有桥）
 */
public class CutVertexAndBridge {
	private boolean[] marked; //是否已被访问
	//tarjan求割点3数组
	private int[] low; //节点能访问的最小时间戳
	private int[] dfn; //时间戳
	private int[] parent; //表示父节点编号
	
	private int index; // 访问顺序
	//结果保存
	private boolean[] isCutV ;  
	private List<Integer> listV;
	private List<int[]> listE;
	
	public CutVertexAndBridge(Graph G) {
		marked = new boolean[G.V()];
		low = new int[G.V()];
		dfn = new int[G.V()];
		parent = new int[G.V()];
		
		isCutV = new boolean[G.V()];
		listV = new ArrayList<>();
		listE = new ArrayList<>();
		dfs(G);
	}
	//从根节点出发的dfs
	private void dfs(Graph G) {
		int childTree = 0;//单独根节点的子树数量
		marked[0] = true;
		index = 1;
		parent[0] = -1;
		for(int v : G.adj(0)) {
			if(!marked[v]) {
				marked[v] = true;
				parent[v] = 0;
				dfs(G,v);
				//根节点相连的边是否有桥
				if(low[v] > dfn[0]) {
					listE.add(new int[] {0,v});
				}
			}
			childTree++;
		}
		if(childTree >= 2) {
			isCutV[0] = true; //根节点为割点
		}
	}
	private void dfs(Graph G, int v) {
		
		dfn[v] = low[v] = ++index;
		for(int w : G.adj(v)) {
			if(!marked[w]) {
				marked[w] = true;
				parent[w] = v;
				dfs(G,w);
				low[v] = Math.min(low[v],low[w]);
				//判断割点
				if(low[w] >= dfn[v]) {
					isCutV[v] = true;
					listV.add(v);
					//判断桥
					if(low[w] > dfn[v]) {
						listE.add(new int[] {v,w});//约定把父节点放在前一个
					}
				}
			}else if(parent[v] != w && dfn[w] < dfn[v]){//w被访问过且w不是v的直接父节点，用反向边更新low[v]值
				low[v] = Math.min(low[v], dfn[w]);
			}
			
		}
	}
	public boolean isCutVertex(int v) {
		return isCutV[v];
	}
	//返回所有桥
	public List<int[]> cutEdges(){
		return this.listE;
	}
	//返回所有割点
	public List<Integer> cutVertexs() {
		return this.listV;
	}
	
	public static void main(String[] args) {
		Graph G = new Graph(6);
		G.addEdge(0, 1);G.addEdge(0, 3);G.addEdge(0, 1);G.addEdge(1, 2);G.addEdge(3, 2);
		G.addEdge(2, 4);G.addEdge(2, 5);G.addEdge(4, 5);
		CutVertexAndBridge vab = new CutVertexAndBridge(G);
		System.out.println(vab.listV);
	}
}
/*class Edge{
	int from;
	int to;
	public Edge(int x, int y) {
		from = x;
		from = y;
	}
	@Override
	public int hashCode() {
		return from + to;
	}
	@Override
	public boolean equals(Object obj) {
		Edge e = (Edge)obj;
		return e.from == this.from && e.to == this.to || e.to == this.from && e.from == this.to;
	}
}*/
