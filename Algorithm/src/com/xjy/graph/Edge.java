package com.xjy.graph;
/**
 * 普通图adj中只需保存顶点，但是当牵涉到边有权重时，需要用到Edge类
 * @author Mr.Xu
 *
 */
public class Edge implements Comparable<Edge>{
	private final int v;
	private final int w;
	private final double weight;
	public Edge(int v, int w, double weight) {
		this.v = v;
		this.w = w;
		this.weight= weight;
	}
	public double weight() {
		return weight;
	}
	public int either() {return v;}
	
	public int other(int vertex) {
		if(vertex == v) return w;
		else if(vertex == w) return v;
		else throw new RuntimeException("Inconsistent Edge");
	}
	
	@Override
	public int compareTo(Edge that) {
		if(this.weight() < that.weight())return -1;
		else if(this.weight() > that.weight())return 1;
		else return 0;
	}
	 
}
