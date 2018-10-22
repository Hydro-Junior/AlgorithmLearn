package com.xjy.graph;

import java.util.Arrays;
import java.util.Stack;

/**
 * 利用Tarjan算法求解图的强联通分量，核心是lowLink数组和栈
 * 仔细理解后，其实tarjan算法比kosaraju更为直接，dfn标记深搜次序，在一次深搜中更新low值，并通过栈来记录,dfn[i]=low[i]（未更新low）的属于一次弹出的终点
 * 脑中画面描述：一直沿着可行的道路走，当走到已走过的站，你会收获一堆礼物，于是你开始走回头路，并把礼物回馈给走过的路。
 * @author Mr.Xu
 * Reference to： https://www.youtube.com/watch?v=TyWtx7q2D7Y（视频中的图解很到位），
 * https://en.wikipedia.org/wiki/Tarjan%27s_strongly_connected_components_algorithm
 */
public class TarjanSCC {
	public int[] id; //强连通分量标识符
	private boolean[] marked; //标记是否被访问（可以把是否访问过直接用dfn表示，比如-1，但为了表达更清晰，还是用marked数组）
	//tarjan三要素
	private int[] dfn; //记录深搜的顺序（时间戳）
	private int[] low; //标记深搜过程中可达顶点的最低dfn
	private boolean[] onStack; //是否在当次递归栈中

	private Stack<Integer> stack; //用于存储访问后的节点，弹出强连通分量
	private int sccCount;//强连通分量个数
	private int index ;//深搜的顺序
	
	public TarjanSCC(DiGraph G) {
		id = new int[G.V()];
		low = new int[G.V()];
		dfn = new int[G.V()];
		onStack = new boolean[G.V()];
		marked = new boolean[G.V()];
		stack = new Stack<>();
		index = 0;
		
		for(int i = 0 ; i < G.V(); i++) {
			if(!marked[i]) {
				dfs(G,i);
			}
		}
	}
	private void dfs(DiGraph G ,int v) {
		stack.push(v);
		onStack[v] = true;
		marked[v] = true;
		dfn[v] = low[v] = ++index;
		//考虑可达顶点
		for(int w : G.adj(v)) {
			if(!marked[w]) {
				dfs(G,w);
				//下面这句话写在dfs递归语句的后面，可以生动地理解为回来的路上要做的操作！
				low[v] = Math.min(low[w], low[v]);//（传递礼物）递归返回时更新值（普通相邻点访问结束后返回更新low）<v通过树边到达w>
			}else if(onStack[w]) {//存在有向环
				low[v] = Math.min(dfn[w], low[v]);//（收获了礼物）整个算法的精髓（已被访问且在栈中的顶点返回更新low）<v通过返祖边或横叉边到达w>
			}
		}
		//判断v是否为此次搜索树的根（搜完之后要做的操作：拿出此时栈中的强联通分量）
		if(low[v] == dfn[v]) {
			int w ;
			do {
				w = stack.pop();
				onStack[w] = false;
				id[w] = sccCount;//此时用id表示同属于某个强连通分量
			}while(w != v);
			sccCount++;
		}
	}
	public int count() {return sccCount;}
	//测试
	public static void main(String[] args) {
		DiGraph G = new DiGraph(8);
		G.addEdge(3, 4);G.addEdge(3, 7);G.addEdge(7, 3);G.addEdge(7, 5);G.addEdge(4, 5);
		G.addEdge(6, 4);G.addEdge(5, 6);G.addEdge(5, 0);G.addEdge(6, 0);G.addEdge(6, 2);
	    G.addEdge(2, 0);G.addEdge(0, 1);G.addEdge(1, 2);
		
	    TarjanSCC tarjanSCC = new TarjanSCC(G);
	    
	    System.out.println(tarjanSCC.count()); 
	    for(int i = 0 ; i < tarjanSCC.count();i++) {
	    	for(int j = 0; j< G.V();j++) {
	    		if(tarjanSCC.id[j] == i) System.out.print(j+" ");
	    	}
	    	System.out.println();
	    }
	}
}
