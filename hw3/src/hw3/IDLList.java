package hw3;

import java.util.ArrayList;

/**
 * This class implements a doubly linked list of Nodes.
 * 
 * @author Peilin Feng
 * @date 10/10/2022
 */

public class IDLList<E> {
	private Node<E> head;
	private Node<E> tail;
	private int size;
	private ArrayList<Node<E>> indices;

	/**
	 * This constructor creates an empty list.
	 */
	public IDLList() {
		indices = new ArrayList<Node<E>>();
		head = null;
		tail = null;
		size = 0;
	}
	
	/**
	 * This method add a new node to the index of the list.
	 * @param index the index of the node
	 * @param elem the element of the node
	 * @return true if the node is added successfully, false otherwise
	 */
	public boolean add (int index, E elem)  {
		if ( index > indices.size() || index < 0) { // if the index is out of range
			return false;
		}
		Node<E> node = new Node<E>(elem);
		indices.add(index, node);
		if (index != 0 && index != indices.size() - 1) { // if the index is not the first or the last
			Node<E> nextNode = indices.get(index + 1);
			node.next = nextNode;
			nextNode.prev = node;
			Node<E> prevNode = indices.get(index -1);
			node.prev = prevNode;
			prevNode.next = node;
		} else if (index == 0 && indices.size() > 1) { // if the index is the first
			node.next = indices.get(1);
			indices.get(1).prev = node;
		} else if ( index == indices.size() - 1 && indices.size() > 1){ // if the index is the last
			node.prev = indices.get(index - 1);
			indices.get(index - 1).next = node;
		}
		
		head = indices.get(0);
		tail = indices.get(indices.size() - 1);
		return true;
	}
	
	/**
	 * This method add a new node to the front of the list.
	 * @param elem the element of the node
	 * @return true if the node is added successfully, false otherwise
	 */
	public boolean add(E elem) {
		Node<E> node = new Node<E>(elem);
		node.next = head;
		head = node;
		node.prev = null;
		indices.add(0, node);
		if ( indices.size() > 1) {
			indices.get(1).prev = indices.get(0);
		}
		tail = indices.get(indices.size() - 1);
		return true;
	}
	
	/**
	 * This method add a new node to the end of the list.
	 * @param elem the element of the node
	 * @return true if the node is added successfully, false otherwise
	 */
	public boolean append (E elem) {
		Node<E> node = new Node<E>(elem);
		tail.next = node;
		tail = node;
		indices.add(indices.size(), node);
		tail.prev = indices.get(indices.size() - 2);
		indices.get(indices.size() - 2).next = tail;
		return true;
	}

	/**
	 * This method get the element of the node at the index.
	 * @param index the index of the node
	 * @return the element of the node at the index
	 */
	public E get(int index) {
		if (index < 0 || index >= indices.size()) { // if the index is out of range
			return null;
		}
		return indices.get(index).data;
	}
	
	/**
	 * This method is get head of the list.
	 * @return data of the head
	 */
	public E getHead() {
		head = indices.get(0);
		return head.data;
	}

	/**
	 * This method is get tail of the list.
	 * @return data of the tail
	 */
	public E getLast() {
		tail = indices.get(indices.size() - 1);
		return tail.data;
	}
	
	/**
	 * This method is get the size of the list.
	 * @return size of the list
	 */
	public int size() {
		size = indices.size();
		return size;
	}
	
	/**
	 * This method is remove the node at the first of the list
	 * @return data of the node
	 */
	public E remove() {
		Node<E> remove = head;
		head = head.next;
		head.prev = null;
		indices.remove(0);
		E data = remove.data;
		remove = null;
		tail = indices.get(indices.size() - 1);
		return data;
	}
	
	/**
	 * This method is remove the node at the last of the list
	 * @return data of the node
	 */
	public E removeLast() {
		Node<E> remove = tail;
		tail = tail.prev;
		tail.next = null;
		indices.remove(indices.size() - 1);
		E data = remove.data;
		remove = null;
		head = indices.get(0);
		return data;
	}
	
	/**
	 * This method is remove the node at the index of the list
	 * @param index the index of the node
	 * @return data of the node
	 */
	public E removeAt(int index) {
		if ( index == 0) { // if the index is the first
			return remove();
		} else if (index == indices.size() - 1) { // if the index is the last
			return removeLast();
		} else if ( index < 0 || index >= indices.size()) { // if the index is out of range
			return null;
		}
		Node<E> remove = indices.get(index);
		Node<E> prev = remove.prev;
		Node<E> next = remove.next;
		prev.next = next;
		next.prev = prev;
		E data = remove.data;
		remove = null;
		indices.remove(index);
		head = indices.get(0);
		tail = indices.get(indices.size() - 1);
		return data;
	}
	
	/**
	 * This method is remove the node which has same value with the given element
	 * @param elem the node which has same value with the given element will be removed
	 * @return true if the node is removed successfully, false otherwise
	 */
	public boolean remove(E elem) {
		boolean isRmove = false;
		int index = -1;
		for (int i = 0; i < indices.size(); i++) {
			if ( elem.equals( indices.get(i).data)) { // if the node has same value with the given element
				index = i;
				break;
			}
		}
		if ( index != - 1) { // if the node is found
			
			Node<E> remove = indices.get(index);
			Node<E> prev = remove.prev;
			Node<E> next = remove.next;
			if ( prev == null) { // if the node is the first
				System.out.println(next.data);
				next.prev = null;
				remove = null;
				isRmove = true;
			} else if (next == null) { // if the node is the last
				prev.next = null;
				remove = null;
				isRmove = true;
			} else { // if the node is in the middle
				System.out.println(remove.data);
				next.prev = prev;
				prev.next = next;
				remove = null;
				isRmove = true;
			}
			head = indices.get(0);
			tail = indices.get(indices.size() - 1);
			indices.remove(index);
			remove = null;
		}
		return isRmove;
	}
	
	/**
	 * This method print the list
	 * @return the string of the list
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		head = indices.get(0);
		
		Node<E> newHead = head;
		while (newHead.next != null) {
			sb.append(String.valueOf(newHead.data)+ ",");
			sb.append(" ");
			newHead = newHead.next;
		}
		sb.append(String.valueOf(newHead.data));
		sb.append("]");
		return sb.toString();
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
