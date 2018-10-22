package com.xjy.basic;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description 
 * 单词查找树的构建：典型的递归运用
 * @author Mr.Xu
 * @date 2018年9月20日 上午10:35:26
 *
 */
public class TrieST <Value>{
	private static int R = 256; //基数
	private Node root; //字典树的根节点
	private int n; //键的个数
	
	public TrieST() {}
	public TrieST(int r) {R = r;}
	
	private static class Node{//节点写成静态内部类（嵌套类）
		private Object val;
		private Node[] next = new Node[R];//在创建嵌套类时，并不需要其外围类的对象，当然，不能从静态内部类的对象中访问外部类的非静态成员
	}
	/**
	 * 对外的查找方法
	 * @param key
	 * @return
	 */
	public Value get(String key) {
		Node x = get(root,key,0);
		if(x == null) return null;
		return (Value)x.val;
	}
	/**
	 * 查找3情况：1.有值 2.无值 3.空链接
	 * 参数d是一个亮点，记录递归的层数，标记是否已到字符串末，字典树的深度和d一一对应，根节点对应d为0
	 * @return
	 */
	private Node get(Node x, String key, int d) {
		if(x == null) return null; //空链接情况
		if(d == key.length())return x; //已查找到最后一个字符
		char c = key.charAt(d);
		return get(x.next[c],key,d+1);//第n层对应的是前n个字母匹配成功，也就是字符串下标走到n-1
	}
	/**
	 * 对外的插入方法
	 * @param key
	 * @param val
	 */
	public void put(String key, Value val) {
		if(key == null) throw new IllegalArgumentException();
		if(val == null) delete(key);//如果值为null，相当于删除这个key
		else root = put(root,key,val,0);
	}
	/**
	 * 两种情况：是否遇到空链接
	 */
	private Node put(Node x, String key, Value val, int d) {
		if(x == null) x = new Node();//new一个Node实际上是new一个Value对象和R条空链接
		if(d == key.length()) {
			if(x.val == null) n++;
			x.val = val; 
			return x;
		}
		char c = key.charAt(d);
		x.next[c] = put(x.next[c],key,val,d+1);
		return x;
	}
	//对外的删除方法
	public void delete(String key) {
		if(key == null)throw new IllegalArgumentException();
		root = delete(root,key,0);
	}
	//同样利用d和递归，删除某个key
	private Node delete(Node x,String key,int d) {
		if(x == null) return null;
		if(d == key.length()) {
			if(x.val != null) n--;
			x.val = null;
		}else {
			char c = key.charAt(d);
			x.next[c] = delete(x.next[c],key,d+1);
		}
		if(x.val != null)return x;
		//如果节点有链接不为空，则将其返回，否则返回空（即将该节点置空）
		for(int c = 0 ; c < R; c++) {
			if(x.next[c] != null) return x;
		}
		return null;
	}
	//是否包含
	public boolean contains(String key) {
		if(key == null)throw new IllegalArgumentException();
		return get(key)!= null;
	}
	//有效key的个数。
	public int size() {return n;}
	//字典树是否为空
	public boolean isEmpty() {return size() == 0;}
	
	/**
	 *******************************************************
	 * 至此，对单词查找树最基本的CURD功能已实现，接下来实现一部分扩展的API，需要借助队列
	 *******************************************************
	 */
	
	/**
	 * 从某个节点开始往下收集字符串
	 * @param x 起始节点
	 * @param pre 起始节点对应前缀
	 * @param q 保存结果的队列
	 */
	private void collect(Node x, String pre,Queue<String> q) {
		if(x == null) return;
		if(x.val != null) q.offer(pre);
		for(char c = 0; c < R; c++) {
			collect(x.next[c], pre+c, q);
		}
	}
	/**
	 * 添加通配符,这里只是针对"."符号
	 */
	private void collect(Node x,String pre, String pat,Queue<String> q) {
		int d = pre.length();//注意此处的d不再是递归深度，而是当前pre的长度
		if(x == null) return;
		if(d == pat.length() && x.val != null)q.offer(pre);
		if(d == pat.length())return;
		char next = pat.charAt(d);
		for(char c = 0; c < R; c++) {
			if(next == '.' || next == c)
				collect(x.next[c],pre+c,pat,q);
		}
	}
	/**
	 * 收集字典树中所有以某个字符串pre开头的key值
	 * @param pre
	 * @return
	 */
	public Iterable<String> keysWithPrefix(String pre){
		Queue<String> q = new LinkedList<String>();
		collect(get(root,pre,0),pre,q);
		return q;
	}
	public Iterable<String> keys(){return keysWithPrefix("");}
	
	/**
	 * 通配符匹配
	 */
	public Iterable<String> keysThatMatch(String pat){
		Queue<String> q = new LinkedList<>();
		collect(root,"",pat,q);
		return q;
	}
	/**
	 * 最长前缀子串问题
	 */
	public String longestPrefixOf(String s) {
		int length = search(root,s,0,0);
		return s.substring(0, length);
	}
	//搜索，返回子串终止位置
	private int search(Node x, String s, int d,int length) {
		if(x == null) return length;
		if(x.val != null) length = d;
		if(d == s.length()) return length;
		char c = s.charAt(d);
		return search(x.next[c],s,d+1,length);//先更新d，length不急着更新，因为下层可能是个空节点
	}
	
}
