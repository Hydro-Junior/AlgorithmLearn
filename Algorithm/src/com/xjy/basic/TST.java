/**
 * 
 */
package com.xjy.basic;

/**
 * @Description 
 * 三向单词查找树可以避免R向单词查找树（Trie）带来的过度的空间消耗，每个节点含有一个字符，三条链接（小于、等于、大于当前字符）和一个值。
 * 省空间但速度略逊于R向单词查找树，对于大型字母表，R向所需的空间无法满足时，三向单词查找树是最好的选择。
 * 
 * @author Mr.Xu
 * @date 2018年9月24日 上午10:14:08
 */
public class TST<Value>{
	private Node root;
	private class Node{
		char c;
		Node left,mid,right;
		Value val;
	}
	public Value get(String key) {
		Node x = get(root, key ,0);
		if(x == null) return null;
		return (Value)x.val;
	}
	private Node get(Node x , String key, int d) {
		if(x == null) return null;
		char c = key.charAt(d);
		//注意下面不同分支d的变化
		if(c < x.c ) return get(x.left, key , d);
		else if(c > x.c ) return get(x.right, key ,d);
		else if(d < key.length() - 1) return get(x.mid , key, d+1);//R向Trie的根节点的key是空串，但三向是具体的字符
		else return x;
	}
	public void put(String key , Value val) {
		root = put(root,key,val,0);
	}
	private Node put(Node x , String key, Value val, int d) {
		char c = key.charAt(d);
		if(x == null) {x = new Node();x.c = c;}
		if(c < x.c) x.left = put(x.left,key,val,d);
		else if(c > x.c) x.right = put(x.right,key,val,d);
		else if(d < key.length()-1)
			x.mid = put(x.mid,key,val,d+1);
		else x.val = val;
		return x;
	}
}
