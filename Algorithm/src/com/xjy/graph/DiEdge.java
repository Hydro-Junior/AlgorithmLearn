package com.xjy.graph;
/**
 * 有向图中的权重边
 * @author Mr.Xu
 *
 */
public class DiEdge {
	private final int v; //起点
	private final int w; //终点
	private final double weight;
	
	public DiEdge(int v, int w, double weight) {
		this.v = v;
		this.w = w;
		this.weight = weight;
	}
	public double weight() {
		return weight;
	}
	public int from() {return v;}
	public int to() {return w;}
	public String toString() {return String.format("%d->%d %.2f" , v,w,weight);}
}
