package com.xjy.basic;
/**
 * 一个简单的基于拉链法的哈希表
 * @author Mr.Xu
 *
 * @param <Key>
 * @param <Value>
 */
public class ChainingHashST <Key,Value>{
	private int N; //键值对总数
	private int M; //散列表的大小
	private EasySearchST<Key, Value>[] st;
	public ChainingHashST() {
		this(997);
	}
	public ChainingHashST(int M) {
		this.N = 0;
		this.M = M;
		st = (EasySearchST<Key, Value>[])new EasySearchST[M];
		for(int i=0 ;i<M;i++) {
			st[i] = new EasySearchST<>();
		}
	}
	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % M;
	}
	public Value get(Key key) {
		return st[hash(key)].get(key);
	}
	public void put(Key key,Value val) {
		int i = hash(key);
		if(!st[i].contains(key)) {
			N++;
		}
		st[i].put(key, val);
	}
	public boolean contains(Key key) {
		return get(key)!=null;
	}
}
