package com.xjy.graph;
/**
 * kosaraju算法求解强联通分量
 * @author Mr.Xu
 * Reference to: Algorithm 4th，https://www.cnblogs.com/nullzx/p/6437926.html
 * 证明：http://blog.sina.com.cn/s/blog_4dff87120100r58c.html
 * 按反向图的逆后序深搜，每次递归调用所标记的顶点都在同一个强联通分量中。
 * 通俗想法（不严谨）：有连通分量A和B，A->B,从A开始深搜只用1次，从B开始深搜需要两次，而理应从B开始深搜才能得到正确的强联通分量，如何确定从B开始深搜？
 * 取反 A<-B,可以发现其逆后序无论如何都是从B集合中的顶点开始！
 * 更正规的证明：（详见算法第四版P588，但个人感觉下面这番说法比较好懂）
 * 假设顶点s与t属于第二次DFS森林（注意，第二次是在原图上搜索）的同一棵树，r是这棵树的根结点。那么有以下两个事实：一，原图中由r可达s，这蕴含在逆图中从s到r有一条路径；
 * 二，r在逆图中的后序编号大于s（r是树根，因此r的后序编号比树中所有的其他结点的都大）。现在要证明的是在逆图中从r到s也是可达的。
好，两个事实结合起来：一，假设逆图中r到s不可达，且s到r存在路径，那么这条路径将使s的后序编号比r大，与事实一矛盾，排除；
二，假设逆图中r到s存在路径，正是这条r到s的路径使得r有更大的后序编号，则r与s是强连通的，假设成立（看上去比较勉强，个人认为这应该是一个空证明）。
显然，两个事实导出一个结论：逆图中，r与s互相可达。同理，r与t也互相可达，根据传递性，第二次DFS森林中同一棵树中的所有顶点构成一个强连通分量。
另一方面，会不会一个强连通分量的所有顶点没有出现在第二次DFS森林的同一颗树中呢？答案是：不会。因为根据DFS的性质，如果r与s强连通，那么由r开始的DFS必定能搜到s。
证毕。
 */
public class KosarajuSCC {
	 private boolean[] marked;
	 private int[] id;
	 private int count;
	 
	 public KosarajuSCC(DiGraph G) {
		 marked = new boolean[G.V()];
		 id = new int[G.V()];
		 DFSOrder order = new DFSOrder(G.reverse());
		 for(int s: order.reversePost()) {
			 if(!marked[s]) {
				 dfs(G,s);
				 count++;
			 }
		 }
	 }
	 private void dfs(DiGraph G , int v) {
		 marked[v] = true;
		 id[v] = count;
		 for(int w : G.adj(v)) {
			 if(!marked[w]) dfs(G,w);
		 }
	 }
	 public boolean stronglyConnected(int v, int w) {
		 return id[v] == id[w];
	 }
	 public int id(int v) {return id[v];}
	 public int count() {return count;}
}
