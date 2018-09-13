package com.xjy.adt;
/**
 * 基于线性探测的散列表
 * @author Mr.Xu
 *
 */
public class LinearProbingHashST <Key,Value>{
	private int N;
	private int M = 16;//线性探测表的大小
	private Key[] keys;//键
	private Value[] vals;//值
	public LinearProbingHashST() {
		keys = (Key[])new Object[M];
		vals = (Value[])new Object[M];
	}
	public LinearProbingHashST(int cap) {
		M = cap;
		keys = (Key[])new Object[M];
		vals = (Value[])new Object[M];
	}
	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff)%M;
	}
	private int size() {
		return N;
	}
	private void resize(int cap) {
		LinearProbingHashST<Key,Value> t;
		t = new LinearProbingHashST<Key,Value>(cap);
		for(int i=0;i < M;i++) {
			if(keys[i] != null) {
				t.put(keys[i],vals[i]);
			}
		}
		keys = t.keys;
		vals = t.vals;
		M = t.M;
	}
	
	public void put(Key key,Value val) {
		if(N >= M/2) resize(2*M);
		int i;
		for(i=hash(key);keys[i]!=null;i=(i+1)%M) {
			if(keys[i].equals(key)) {
				vals[i] = val;
				return;
			}
		}
		keys[i]=key;
		vals[i]=val;
		N++;
	}
	public Value get(Key key) {
		for(int i=hash(key);keys[i]!=null;i=(i+1)%M) {
			if(keys[i].equals(key)) {
				return vals[i];
			}
		}
		return null;
	} 
	public boolean contains(Key key) {
		return get(key)!=null;
	}
	//删除较为麻烦，要确保删除后键簇还是相连的，不然被删除键的位置为空后，get不了同一键簇的某些值
	public void delete(Key key) {
		if(!contains(key)) {
			return;
		}
		int i = hash(key);
		while(!keys[i].equals(key)) {
			i = (i+1)% M;
		}
		keys[i]= null;
		vals[i]= null;
		i = (i+1)%M;
		while(keys[i] != null) {
			Key keyToRedo = keys[i];
			Value valToRedo = vals[i];
			keys[i] = null;
			vals[i] = null;
			N--;//由于put中要加，所以要注意N-1
			put(keyToRedo,valToRedo);
			i=(i+1)%M;
		}
		N--;
		if(N>0 && N == M/8) {
			resize(M/2);
		}
	
	}
	
}
