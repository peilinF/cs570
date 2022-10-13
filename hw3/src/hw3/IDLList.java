package hw3;

import java.util.ArrayList;

public class IDLList<E> {
	private Node<E> head;
	private Node<E> tail;
	private int size;
	private ArrayList<Node<E>> indices;
	public IDLList() {
		indices = new ArrayList<Node<E>>();
		head = null;
		tail = null;
		size = 0;
	}
	
	public boolean add (int index, E elem) {
		if ( index > indices.size() - 1) {
			return false;
		}
		Node<E> node = new Node<E>(elem);
		indices.add(index, node);
		Node<E> nextNode = indices.get(index + 1);
		node.next = nextNode;
		nextNode.prev = node;
		Node<E> prevNode = indices.get(index -1);
		node.prev = prevNode;
		prevNode.next = node;
		
		head = indices.get(0);
		tail = indices.get(indices.size() - 1);
		return true;
	}
	public boolean add(E elem) {
		Node<E> node = new Node<E>(elem);
		node.next = head;
		head = node;
		node.prev = node;
		indices.add(0, node);
		tail = indices.get(indices.size() - 1);
		return true;
	}
	
	private class Node<E> {
		E data;
		Node<E> next;
		Node<E> prev;
		
		Node(E data) {
			this.data = data;
			next = null;
			prev = null;
		}
		
		Node(E data, Node<E> prev, Node<E> next) {
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
		
	}

}