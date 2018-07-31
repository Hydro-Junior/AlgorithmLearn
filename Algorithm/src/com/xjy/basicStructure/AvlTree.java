package com.xjy.basicStructure;

public class AvlTree <T extends Comparable<? super T>>{
	private static final int ALLOWED_IMBALANCE = 1; //最大允许高度差为1
	private AvlNode<T> root;
	private  static class AvlNode<T>{
		T element;
		AvlNode<T> left;
		AvlNode<T> right;
		int height;
		AvlNode(T theElement){
			this(theElement,null,null);
		}
		AvlNode(T theElement, AvlNode<T> lt,AvlNode<T> rt){
			element = theElement;
			left = lt;
			right = rt;
			height = 0;
		}
	}
	private int height(AvlNode<T> t) {
		return t == null?-1:t.height;
	}
	public AvlTree(){
		root = null;
	}
	//左左型旋转
	private AvlNode<T> rotateWithLeftChild(AvlNode<T> k2){
		
		//1. 选出k2节点（旧的父节点）的左孩子（新的父节点）
		AvlNode<T> k1 = k2.left;
		//2. 把新父节点的右孩子传给旧父节点的左孩子
		k2.left = k1.right;
		//3. 把旧父节点作为新父节点的右孩子
		k1.right = k2;
		//调整高度
		k2.height = Math.max(height(k2.left), height(k2.right))+1;
		k1.height = Math.max(height(k1.left), k2.height)+1;
		return k1;
	}
	//右右型旋转
		private AvlNode<T> rotateWithRightChild(AvlNode<T> k2){
			
			//1. 选出k2节点（旧的父节点）的右孩子（新的父节点）
			AvlNode<T> k1 = k2.right;
			//2. 把新父节点的左孩子传给旧父节点的右孩子
			k2.right = k1.left;
			//3. 把旧父节点作为新父节点的左孩子
			k1.left = k2;
			//调整高度
			k2.height = Math.max(height(k2.left), height(k2.right))+1;
			k1.height = Math.max(k2.height,height(k1.right))+1;
			return k1;
		}
	//左右型旋转
	private AvlNode<T> doubleWithLeftChild(AvlNode<T> k3){
		k3.left = rotateWithRightChild(k3.left);
		return rotateWithLeftChild(k3);
	}
	//右左型旋转
	private AvlNode<T> doubleWithRightChild(AvlNode<T> k3){
		k3.right = rotateWithLeftChild(k3.right);
		return rotateWithRightChild(k3);
	}
	//最关键：平衡操作
	private AvlNode<T> balance(AvlNode<T> t){
		if(t == null) {
			return t;
		}
		//左边高的情况
		if(height(t.left)-height(t.right) > ALLOWED_IMBALANCE) {
			//左左型(同时考虑一样深的情形)
			if(height(t.left.left) >= height(t.left.right)) {
				t = rotateWithLeftChild(t);
			}else {
			//左右型
				t = doubleWithLeftChild(t);
			}
		}
		//右边高的情况
		else if(height(t.right)-height(t.left) > ALLOWED_IMBALANCE){
			//右右型(同时考虑一样深的情形)
			if(height(t.right.right) >= height(t.right.left)) {
				t = rotateWithRightChild(t);
			}else {
			//右左型
				t = doubleWithRightChild(t);
			}
		}
		t.height = Math.max(height(t.left), height(t.right)) + 1;
		return t;
	}
	//插入节点
	private AvlNode<T> insert(T x,AvlNode<T> t){
		if(t == null) {
			return new AvlNode<>(x,null,null);
		}
		int compareResult = x.compareTo(t.element);
		if(compareResult < 0) {
			t.left = insert(x,t.left);
		}else if(compareResult > 0) {
			t.right = insert(x,t.right);
		}else {
			//Duplicate,do nothing
		}
		return balance(t);
	}
	public boolean isEmpty() {
		return root == null;
	}
	public void makeEmpty() {
		root = null;
	}
	public T findMin() throws Exception {
		if(isEmpty()) {
			throw new Exception();
		}
		return findMin(root).element;
	}
	private AvlNode<T> findMin(AvlNode<T> t){
		if(t == null) {
			return null;
		}else if(t.left == null) {
			return t;
		}
		return findMin(t.left);
	}
	public T findMax() throws Exception {
		if(isEmpty()) {
			throw new Exception();
		}
		return findMax(root).element;
	}
	public AvlNode<T> findMax(AvlNode<T> t){
		//非递归实现
		if(t != null)
			while(t.right != null) {
				t = t.right;
			}
		return t;
	}
	public void insert(T x) {
		root = insert(x,root);
	}
	//删除节点
	private AvlNode<T> remove(T x , AvlNode<T> t){
		if(t == null) {
			return t;
		}
		int compareResult = x.compareTo(t.element);
		if(compareResult < 0) {
			t.left = remove(x,t.left);
		}else if(compareResult > 0) {
			t.right = remove(x , t.right);
		}else if(t.left != null && t.right != null) {
			t.element = findMin(t.right).element;
			t.right = remove(t.element,t.right);
		}else {
			t = (t.left != null) ? t.left : t.right;
		}
		return balance(t);
	}
	
	public boolean contains(T x){
		return contains(x,root);
	}
	public boolean contains(T x ,AvlNode<T> t) {
		if(t == null) {
			return false;
		}
		int compareResult = x.compareTo(t.element);
		if(compareResult < 0) {
			return contains(x,t.left);
		}else if(compareResult > 0) {
			return contains(x,t.right);
		}else {
			return true;
		}
	}
}
