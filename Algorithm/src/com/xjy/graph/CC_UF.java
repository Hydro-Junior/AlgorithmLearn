package com.xjy.graph;

//解决图中节点的动态联通性（找出连通分量CC）通常有两种思路：UnionFind 和  DFS，这是其一
// 1.uf的核心思想就是找“根”，每个数组记录的是一条链接，最终满足 p = id[p]的即为根
// 2.在某些输入下（如 有序输入0 1、0 2、 0 3、0 4...），找根的路径会很长，可通过加权平衡——>通过记录树的大小，把较小的树连到较大的树(避免大树举家移根消耗资源)，
//   如此，节点深度最多为logN。
// 3.在找根的过程中将遇到的节点链接到根（路径压缩）
public class CC_UF {
	private int[] id; //父链接数组
	private int[] sz; //每个根节点的大小
	private int count; //连通分量个数
	
	public CC_UF(int N) {
		count = N;
		id = new int[N];
		for(int i = 0 ; i < N; i++) id[i] = i;
		sz = new int[N];
		for(int i = 0 ; i < N; i++) sz[i] = 1;
	}
	
	public int count() { return count;}
	
	public boolean connected(int p , int q) {return find(p) == find(q);}
	
	public int find(int p) {
		while(p != id[p]) p = id[p];
		return p;
		/*路径压缩版本
		if(id[p] == p) return p;
		else return id[p] = find(id[p]);*/
	}
	public void union(int p , int q) {
		int i = find(p);
		int j = find(q);
		if(i == j) return;
		//小树挂大树
		if(sz[i] < sz[j]) {id[i] = j; sz[j] += sz[i];}
		else {id[j] = i; sz[i] += sz[j];}
		count--;
	}
	
	
}
