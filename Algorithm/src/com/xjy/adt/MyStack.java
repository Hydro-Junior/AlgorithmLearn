package com.xjy.adt;

public class MyStack <E>{
	private Object element[];
	private int top;
	public MyStack(int size) {
		this.element = new Object[Math.abs(size)];
		this.top = -1;
	}
	public MyStack() {
		this(64);
	}
	public boolean isEmpty() {
		return this.top == -1;
	}
	public void push(E x) {
		if(x == null) {
			return;
		}
		if(this.top == element.length - 1) {
			Object[] temp = this.element;
			this.element = new Object[temp.length * 2];
			for(int i = 0 ; i < temp.length ; i++){
				this.element[i] = temp[i];
			}
		}
		this.top ++;
		this.element[this.top] = x;		
	}
	public E top() {
		return this.top == -1 ? null :(E)this.element[this.top];
	}
	//pop也可动态调整数组大小，这里为简单起见没有使用
	public E pop(){
		return this.top == -1 ? null : (E)this.element[this.top --];
	}
	public E get() {
		return this.top == -1 ? null : (E)this.element[this.top];
	}
}
