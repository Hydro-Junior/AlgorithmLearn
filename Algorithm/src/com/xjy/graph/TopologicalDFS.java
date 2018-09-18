/**
 * 
 */
package com.xjy.graph;

/**
 * @Description 
 *	拓扑排序，两种方案：1.深搜逆后序  2.广搜结合入度（这里展示第一种）
 * @author Mr.Xu
 * @date 2018年9月17日 上午10:03:55
 *
 */
public class TopologicalDFS {
	private Iterable<Integer> order;
	public TopologicalDFS(DiGraph G) {
		DirectedCycle cyclefinder = new DirectedCycle(G);
		if(!cyclefinder.hasCycle()) {//没有环才有拓扑排序
			DFSOrder dfo = new DFSOrder(G);
			order = dfo.reversePost();
		}
	}
	public Iterable<Integer> order(){return order;}
	public boolean isDAG() {return order != null;}//是否是有向无环图
}
