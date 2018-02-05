package com.xjy.basicStructure;

public class AvlTree <T extends Comparable<? super T>>{
	private static final int ALLOWED_IMBALANCE = 1; //�������߶Ȳ�Ϊ1
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
	//��������ת
	private AvlNode<T> rotateWithLeftChild(AvlNode<T> k2){
		
		//1. ѡ��k2�ڵ㣨�ɵĸ��ڵ㣩�����ӣ��µĸ��ڵ㣩
		AvlNode<T> k1 = k2.left;
		//2. ���¸��ڵ���Һ��Ӵ����ɸ��ڵ������
		k2.left = k1.right;
		//3. �Ѿɸ��ڵ���Ϊ�¸��ڵ���Һ���
		k1.right = k2;
		//�����߶�
		k2.height = Math.max(height(k2.left), height(k2.right))+1;
		k1.height = Math.max(height(k1.left), k2.height)+1;
		return k1;
	}
	//��������ת
		private AvlNode<T> rotateWithRightChild(AvlNode<T> k2){
			
			//1. ѡ��k2�ڵ㣨�ɵĸ��ڵ㣩���Һ��ӣ��µĸ��ڵ㣩
			AvlNode<T> k1 = k2.right;
			//2. ���¸��ڵ�����Ӵ����ɸ��ڵ���Һ���
			k2.right = k1.left;
			//3. �Ѿɸ��ڵ���Ϊ�¸��ڵ������
			k1.left = k2;
			//�����߶�
			k2.height = Math.max(height(k2.left), height(k2.right))+1;
			k1.height = Math.max(k2.height,height(k1.right))+1;
			return k1;
		}
	//��������ת
	private AvlNode<T> doubleWithLeftChild(AvlNode<T> k3){
		k3.left = rotateWithRightChild(k3.left);
		return rotateWithLeftChild(k3);
	}
	//��������ת
	private AvlNode<T> doubleWithRightChild(AvlNode<T> k3){
		k3.right = rotateWithLeftChild(k3.right);
		return rotateWithRightChild(k3);
	}
	//��ؼ���ƽ�����
	private AvlNode<T> balance(AvlNode<T> t){
		if(t == null) {
			return t;
		}
		//��߸ߵ����
		if(height(t.left)-height(t.right) > ALLOWED_IMBALANCE) {
			//������(ͬʱ����һ���������)
			if(height(t.left.left) >= height(t.left.right)) {
				t = rotateWithLeftChild(t);
			}else {
			//������
				t = doubleWithLeftChild(t);
			}
		}
		//�ұ߸ߵ����
		else if(height(t.right)-height(t.left) > ALLOWED_IMBALANCE){
			//������(ͬʱ����һ���������)
			if(height(t.right.right) >= height(t.right.left)) {
				t = rotateWithRightChild(t);
			}else {
			//������
				t = doubleWithRightChild(t);
			}
		}
		t.height = Math.max(height(t.left), height(t.right)) + 1;
		return t;
	}
	//����ڵ�
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
		//�ǵݹ�ʵ��
		if(t != null)
			while(t.right != null) {
				t = t.right;
			}
		return t;
	}
	public void insert(T x) {
		root = insert(x,root);
	}
	//ɾ���ڵ�
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
