package com.xjy.graph;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 深搜下顶点的3种排序：前序、后序、逆后序 
 * 一幅有向无环图的拓扑排序即为所有顶点的逆后序排列(拓扑排序的前提是不能存在有向环)，值得反复思索
 * <若一个箭头指向某个节点，在后序下它一定比指向它的那个节点先返回，那么反过来（逆后序）就必然是一个拓扑排序，不会有箭头往回指，
 * 而这一点先序是不能保证的，先序一开始就可能选择有箭头指向它的节点作为起点，所以不能保证拓扑排序>
 * @author Mr.Xu
 * Reference to: Algorithm 4th
 */
public class DFSOrder {
	private boolean[] marked;
	private ArrayList<Integer> pre;//前序
	private ArrayList<Integer> post;//后序
	private Stack<Integer> reversePost;//逆后序
	
	public DFSOrder(DiGraph G) {
		pre = new ArrayList<>();
		post = new ArrayList<>();
		reversePost = new Stack<>();
		marked = new boolean[G.V()];
		
		for(int v = 0; v < G.V(); v++) {
			if(!marked[v]) dfs(G,v);
		}
	}
	
	private void dfs(DiGraph G, int v) {
		pre.add(v);
		marked[v] = true;
		for(int w : G.adj(v)) {
			if(!marked[w]) dfs(G,w);
		}
		post.add(v);
		reversePost.push(v);
	}
	public Iterable<Integer> pre(){return pre;}
	public Iterable<Integer> post(){return post;}
	public Iterable<Integer> reversePost(){
		ArrayList<Integer> rp = new ArrayList<>();
		while(!reversePost.isEmpty()) {
		     rp.add(reversePost.pop());
		}
		return rp;
	}
}
