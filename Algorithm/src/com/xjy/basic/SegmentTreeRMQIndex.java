/**
 * 
 */
package com.xjy.basic;

/**
 * @Description 
 * 线段树实现RMQ，查询时返回对应的下标而非数值，这种情况在实际中更为常见。
 * @author Mr.Xu
 * @date 2018年10月9日 下午2:34:27
 *
 */
public class SegmentTreeRMQIndex {
	int[] data; //传入的数组
	int[] st; // 线段树的数组,存储数组的下标值
	
	/**
	 ******************************
	 *	辅助方法
	 ******************************
	 */
	//返回较小值的下标
	private int minVal(int[] data, int i, int j) {
		//先排除下标不合法的情况
		if(i == -1) return j;
		if(j == -1) return i;
		return (data[i] < data[j])? i : j;
	}
	//得到中值
	private int getMid(int s, int e) {return s + (e-s)/2;}
	
	/**
	 * 查询区间最小值的下标
	 * @param ss 线段树节点的左边界
	 * @param se 线段树节点的右边界
	 * @param qs 查找范围的左边界
	 * @param qe 查找范围的右边界
	 * @param index 线段树当前节点的index
	 * @return
	 */
	private int RMQ(int ss, int se,int qs, int qe,int index) {
		if(qs <= ss && qe >= se) return st[index];
		if(se < qs || ss > qe) return -1;
		int mid = getMid(ss,se);
		return minVal(data,RMQ(ss,mid,qs,qe,2*index+1),RMQ(mid+1,se,qs,qe,2*index+2));
	}
	//对外查询接口
	public int RMQ(int qs, int qe) {
		if(qs < 0 || qe > data.length || qs > qe) {
			throw new IllegalArgumentException("Illegal Input!");
		}
		return RMQ(0,data.length-1,qs,qe,0);
	}
	//构造函数，输入一个数组
	public SegmentTreeRMQIndex(int[] data) {
		this.data = data;
		int n = data.length;
		int x = (int)Math.ceil(Math.log(n)/Math.log(2));//线段树的高度
		int max_size = 2 * (int)Math.pow(2, x) - 1;
		st = new int[max_size];
		constructSTUtil(data,0,n-1,st,0);//递归地初始化线段树
	}
	//递归地建立线段树
	private int constructSTUtil(int[] data,int ss, int se, int[] st, int si) {
		if(ss == se) return st[si] = ss;
		int mid = getMid(ss,se);
		st[si] = minVal(data,constructSTUtil(data,ss,mid,st,si*2+1),constructSTUtil(data, mid+1, se, st, si*2+2));
		return st[si];
	}
	/**
	 * simple test
	 * @param args
	 */
	public static void main(String[] args) {
		int[] hist = new int[] {81,43,43,93,94,42, 15, 7, 2 ,97, 77, 20, 36, 59 ,71, 76, 73, 40, 4 ,6 };
		SegmentTreeRMQIndex rmq = new SegmentTreeRMQIndex(hist);
		System.out.println(rmq.RMQ(0,20));
	}
	
}
