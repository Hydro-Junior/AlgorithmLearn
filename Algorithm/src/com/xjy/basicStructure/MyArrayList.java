package com.xjy.basicStructure;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayList<T> implements Iterable<T>{
	private static final int DEFAUT_CAPACITY = 10;
	private T[] theItems;
	private int theSize;
	
	//the most important method , so I put it here .
	//adjust the capacity of the list
	public void ensureCapacity(int newCapacity) {
		if(newCapacity < theSize) {
			return;
		}
		T[] old = theItems;
		theItems = (T[])new Object[newCapacity];
		for(int i = 0 ; i < theSize; i++) {
			theItems[i] = old[i];
		}
	}
	
	public int size() {
		return theSize;
	}
	
	public boolean isEmpty() {
		return theSize == 0;
	}
	
	public void trimToSize() {
		ensureCapacity(theSize);
	}
	
	private void doClear() {
		 theSize=0;
		 ensureCapacity(DEFAUT_CAPACITY);
	}
	
	public MyArrayList() {
		doClear();
	}
	
	public void clear() {
		doClear();
	}
	
	public T get(int idx) {
		if(idx < 0 || idx >= size()) {
			throw new ArrayIndexOutOfBoundsException();
		}
		return theItems[idx];
	}
	
	public T set(int idx , T newVal) {
		if(idx < 0 || idx >= size()) {
			throw new ArrayIndexOutOfBoundsException();
		}
		T old =  theItems[idx];
		theItems[idx] = newVal;
		return old;
	}
	
	public void add(int idx , T x) {
		if(theItems.length == size()) {
			ensureCapacity(size() * 2 + 1);
		}
		for(int i = theSize; i > idx ; i--) {
			theItems[i] = theItems[i-1];
		}
		theItems[idx] = x;
		theSize ++;
	}
	
	public boolean add(T x) {
		add(theSize,x);
		return true;
	}
	
	public T remove(int idx) {
		if(idx < 0 || idx >= size()) {
			throw new ArrayIndexOutOfBoundsException();
		}
		T removed =  theItems[idx];
		for(int i = idx ;i < theSize - 1 ; i++) {
			theItems[i] =  theItems[i+1];
		}
		theSize --;
		return removed;
	}
	@Override
	public Iterator<T> iterator() {
		return new MyArrayListIterator();
	}
	private class MyArrayListIterator implements Iterator<T>{
		private int current = 0;
		@Override
		public boolean hasNext() {
			return current < theSize;
		}

		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			return theItems[current++];
		}
		public void remove() {
			MyArrayList.this.remove(--current);
		}
		
	}
	
}
