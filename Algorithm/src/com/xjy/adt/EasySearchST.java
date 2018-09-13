package com.xjy.adt;
/**
 * 适用顺序查找的简单符号表(链表实现)
 * 内部只有头结点，当添加时从头结点前插入
 * @author Mr.Xu
 *
 */
public class EasySearchST <Key,Value>{
	private Node first = null;
	private class Node{
		Key key;
		Value value;
		Node next;
		public Node(Key k, Value v,Node next){
			this.key = k;
			this.value = v;
			this.next = next;
		}
	}
	public void put(Key k,Value v) {
		for(Node x = first; x != null; x=x.next) {
			if(k.equals(x.key)) {
				x.value = v;
				return;
			}
		}
		first = new Node(k,v,first);
		
		
	}
	public Value get(Key k) {
		for(Node x = first; x != null; x = x.next) {
			if(x.key.equals(k)) {
				return x.value;
			}
		}
		return null;//未命中
	}
	public boolean contains(Key k) {
		return get(k)!=null;
	}
}
