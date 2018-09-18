/**
 * 
 */
package com.xjy.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/**
 * @Description 
 * BFS结合入度数组indegree[]实现拓扑排序
 * 先将所有入度为0的顶点入队，再依次出队，将每次出队的顶点相邻顶点的入度减1，如果入度为0，将该顶点入队。
 * 用这种方法进行环检测也很方便，最终的count数与实际顶点数不同即存在环。
 * @author Mr.Xu
 * @date 2018年9月17日 上午10:23:43
 *
 */
public class TopologicalBFS {
	private int[] indegree; //记录每个顶点的入度
	private ArrayList<Integer> order;
	private int count;
	private DiGraph G;
	
	public TopologicalBFS(DiGraph graph) {
		this.G = graph;
		indegree = new int[G.V()];
		//统计所有顶点的入度
		DiGraph Gr = G.reverse();
		for(int i = 0 ; i < G.V(); i++) {
			indegree[i] = Gr.adj(i).size();
		}
		Deque<Integer> queue = new ArrayDeque<>();
		//将所有入度为0的入队
		for(int i = 0 ; i < G.V();i ++) {
			if(indegree[i] == 0) queue.offer(i);
		}
		count = 0;
		order = new ArrayList<>();
		while(!queue.isEmpty()) {
			int v = queue.poll();
			order.add(v);
			count ++;
			for(int w : G.adj(v)) {
				if(--indegree[w] == 0) queue.offer(w);
			}
		}
	}
	
	
	public boolean hasCycle() {
		return count == G.V();
	}
	public Iterable<Integer> order(){
		if(hasCycle()) return null;
		else return order;
	}
}
