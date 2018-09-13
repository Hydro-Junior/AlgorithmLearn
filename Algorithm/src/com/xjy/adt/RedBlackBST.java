package com.xjy.adt;
/**
 * Description
 * 红黑树的结构本质来自于2-3树，2-3树是一种完美平衡树，其所有空节点到根节点的距离相等，红黑树无非就是把2-3树中的3节点（存放
 * 两个键值，可有三个子节点）转化为用红链接相连的两个2节点，这样代码量更少，数据结构显得更精巧。
 * 特点：
 * 红链接均为左链接（统一，好操作）
 * 没有任何一个结点同时和两条红链接相连（就像2-3树中不允许有4结点一样）
 * 完美黑色平衡，即任意空连接到根结点的路径上黑链接的数量相同
 * 注：编码时标记颜色在结点处，对应指向它的链接的颜色
 * 	（由于删除操作较难，以下代码尚未实现）
 * @author Mr.Xu
 * reference : Algorithm 4th
 * @param <Key>
 * @param <Value>
 */
public class RedBlackBST <Key extends Comparable<? super Key> , Value>{
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	private Node root;
	public RedBlackBST() {
		
	}
	private class Node{
		Key key;
		Value value;
		Node left,right;
		int N;//这棵子树中的节点总数
		boolean color;
		Node(Key k,Value v,int n,boolean color) {
			this.key = k;
			this.value = v;
			this.N = n;
			this.color = color;
		}
	}
	private boolean isRed(Node x) {
		if(x==null) {return false;}
		return x.color == RED;
	} 
	
	//右红链接左旋（适用于出现右红链接的情况）
	private Node rotateLeft(Node h) {
		//左旋
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		//颜色处理
		x.color = h.color;//上面的链接颜色不变，也就是新父节点保留旧父节点的颜色
		h.color = RED;//左旋的条件为右链接为红色，转过来之后左链接为红色
		x.N = h.N;
		h.N = 1+size(h.left)+size(h.right);
		return x;
		
	}
	//左红链接右旋（适用于连续两个左红链接的情况）
	private Node rotateRight(Node h) {
		//右旋
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		//颜色处理
		x.color = h.color;
		h.color = RED;
		x.N = h.N;
		h.N = 1 + size(h.left)+ size(h.right);
		return x;
	}
	//适用于左右两个红链接的情况（相当于2-3树中的3节点，把中间的挤上去）
	private void flipColors(Node h) {
		h.left.color = BLACK;
		h.right.color = BLACK;
		h.color = RED;
	}
	//返回红黑树的大小
	private int size(Node x) {
		if(x == null) {
			return 0;
		}
		return x.N;
	}
	public int size() {
		return root.N;
	}
	public boolean isEmpty() {
		return root == null;
	}
	
	public void put(Key k , Value v) {
		root = put(k,v,root);
		root.color = BLACK;
	}
	//往节点中存放键值对
	public Node put(Key k , Value v, Node p) {
		if(p == null) {
			return new Node(k,v,1,RED);
		}
		int cmp = k.compareTo(p.key);
		if(cmp > 0) {
			p.right = put(k,v,p.right);
		}else if(cmp < 0) {
			p.left = put(k,v,p.left);
		}else {
			p.value = v;
		}
		//应对三种变化，保持红黑树的平衡
		if(isRed(p.right) && !isRed(p.left)) {
			rotateLeft(p);
		}
		if(isRed(p.left) && isRed(p.left.left)) {
			rotateRight(p);
		}
		if(isRed(p.left) && isRed(p.right)) {
			flipColors(p);
		}
		return p;
	}
	//取出键对应的值
	 public Value get(Key k) {
		 if (k == null) throw new IllegalArgumentException("argument to get() is null");
		 return get(k,root);
	 }
	 private Value get(Key k , Node p) {
		 //非递归式
		 while(p != null) {
			 int cmp = k.compareTo(p.key);
			 if(cmp < 0)   	  p = p.left;
			 else if(cmp > 0) p = p.right;
			 else 			return p.value;
		 }
		 return null;
	}
	public boolean contains(Key k) {
		return get(k) != null;
	}
}
