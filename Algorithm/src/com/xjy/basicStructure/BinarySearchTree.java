package com.xjy.basicStructure;

public class BinarySearchTree <T extends Comparable<? super T>>{
	private static class BinaryNode<T>{
		T element;
		BinaryNode<T> left;
	    BinaryNode<T> right;
		BinaryNode(T theElement){
			this(theElement,null,null);
		}
		BinaryNode(T theElement,BinaryNode<T> lt, BinaryNode<T> rt){
			element = theElement;
			left = lt;
			right = rt;
		}
	}
	private BinaryNode<T> root;
	public BinarySearchTree() {
		root = null;
	}
	public void makeEmpty() {
		root = null;
	}
	public boolean isEmpty() {
		return root == null;
	}
	public boolean contains(T x){
		return contains(x,root);
	}
	public boolean contains(T x ,BinaryNode<T> t) {
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
	public T findMin() throws Exception {
		if(isEmpty()) {
			throw new Exception();
		}
		return findMin(root).element;
	}
	private BinaryNode<T> findMin(BinaryNode<T> t){
		if(t == null) {
			return null;
		}else if(t.left == null) {
			return t;
		}
		return findMin(t.left);
	}
	//����д��removeMin������δ����
	private BinaryNode<T> removeMin(BinaryNode<T> t){
		if(t == null) {
			return null;
		}else {
			while(t.left.left != null) {
				t = t.left;
			}
			BinaryNode<T> t2 = t.left;
			t.left = t2.right;
			return t2;
		}
	}
	public T findMax() throws Exception {
		if(isEmpty()) {
			throw new Exception();
		}
		return findMax(root).element;
	}
	public BinaryNode<T> findMax(BinaryNode<T> t){
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
	//�ݹ�ʵ�ֲ��뷽��
	private BinaryNode<T> insert(T x , BinaryNode<T> t){
		if(t == null) {
			return new BinaryNode<>(x ,null, null);
		}
		int compareResult = x.compareTo(t.element);
		if(compareResult < 0) {
			t.left = insert(x,t.left);
		}else if(compareResult > 0) {
			t.right = insert(x,t.right);
		}else {
			//duplicate,do nothing
		}
		return t;
	}
	public void remove(T x) {
		root = remove(x,root);
	}
	//Ч�ʲ����ߣ������������������Բ��Һ�ɾ������������С�Ľڵ㣬�Ľ����飺дһ��removeMin����
	private BinaryNode<T> remove(T x, BinaryNode<T> t){
		if(t == null) {
			return t;
		}
		int compareResult = x.compareTo(t.element);
		if(compareResult < 0) {
			t.left = remove(x,t.left);
		}else if(compareResult > 0) {
			t.right = remove(x,t.right);
		}else if(t.left != null && t.right != null) {//Ԫ��ƥ�䣬��������������Ϊ��
			//����иĽ��ռ�
			t.element = findMin(t.right).element;//�ҵ�����������С��Ԫ��
			t.right = remove(t.element,t.right);//ɾ����������ԭ����С��Ԫ��
			
		}else {//Ԫ��ƥ�䣬������������������һ��Ϊ��
			//�����������Ϊ�գ���ֵ������������ֵ������
			t = (t.left != null)?t.left:t.right;
		}
		return t;
	}
	public void printTree() {
		if(isEmpty()) {
			System.out.println("Empty tree");
		}else {
			printTree(root);
		}
	}
	//�������
	private void printTree(BinaryNode<T> t) {
		if(t != null) {
			printTree(t.left);
			System.out.println(t.element);
			printTree(t.right);
		}
		
	}
}
