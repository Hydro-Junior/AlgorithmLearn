package com.xjy.basic;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * 单链表实现队列 
 * @author Mr.Xu
 *
 * @param <T>
 */
public class MyQueue<T> implements Iterable<T> {
	private int size;
	private Node<T> first;
	private Node<T> last;

	private static class Node<T> {
		private T data;
		private Node<T> next;
	}

	public MyQueue() {
		size = 0;
		first = null;
		last = null;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public int size() {
		return size;
	}

	public void enqueue(T t) {
		Node<T> oldlast = last;
		last = new Node();
		last.data = t;
		last.next = null;
		if (isEmpty()) {
			first = last;
		} else {
			oldlast.next = last;
		}
		size++;
	}

	public T dequeue() {
		if (first == null) {
			return null;
		} else {
			T item = first.data;
			first = first.next;
			size--;
			return item;
		}

	}

	public T peek() {
		return first.data;
	}

	@Override
	public Iterator<T> iterator() {
		return new MyQueueIterator<>(first);
	}

	public class MyQueueIterator<T> implements Iterator<T> {
		private Node<T> current;

		public MyQueueIterator(Node<T> first) {
			current = first;
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			if (!hasNext())
				throw new NoSuchElementException();
			T data = current.data;
			current = current.next;
			return data;
		}

	}
}
