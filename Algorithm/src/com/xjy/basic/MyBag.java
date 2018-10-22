package com.xjy.basic;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;

public class MyBag<E> implements Iterable<E> {
	private Node<E> first;
	private int n;
	private static class Node<E>{
		private E item;
		private Node<E> next;
	}
	public MyBag() {
		first = null;
		n = 0;
	}
	public boolean isEmpty() {
		return first == null;
	}
	public int size() {
		return n;
	}
	public void add(E item) {
		Node<E> oldfirst = first;
		first = new Node<E>();
		first.item = item;
		first.next = oldfirst;
		n++;
	}
	public Iterator<E> iterator(){
		return new MyListIterator<>(first);
	}
	private class MyListIterator<E> implements Iterator<E> {
		private Node<E> current;
	    public MyListIterator(Node<E> first) {
			current = first;
		}
		@Override
		public boolean hasNext() {
			return current != null;
		}
		@Override
		public E next() {
			if(!this.hasNext()) {throw new NoSuchElementException();}
			E item = current.item;
			current = current.next;
			return item;
		}
		//Bag doesn't support method.
		public void remove() { throw new UnsupportedOperationException();  }
		
	}
	/**
	 * have a easy test
	 */
	@Test
	public void bagTest() {
		MyBag<Double> mbs = new MyBag<>();
		for(int i = 0 ; i < 1000; i++) {
			mbs.add(Math.random());
		}
		double sum = 0.0;
		for(double d : mbs) {
			sum += d;
		}
		System.out.println(sum);
	}
	
}
