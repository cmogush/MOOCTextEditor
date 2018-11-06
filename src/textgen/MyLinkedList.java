package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// DONE
		size = 0;
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	
	{
		// DONE
		
		//Create Node
		LLNode<E> node = new LLNode(element);
		
		
		if(element == null) {
			throw new NullPointerException("element object cannot store null pointer");
		}
		
		//add links to new node
		node.next = tail;
		node.prev = node.next.prev;
		
		//replace bordering nodes links to point to new node
		node.prev.next = node;
		node.next.prev = node;
		
		size();
		
		return false;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// DONE		
		return getNode(index).data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// DONE
		
		if(element == null) {
			throw new NullPointerException("element object cannot store null pointer");
		}
		
		//get node at index
		LLNode<E> currentNode = getNode(index);
		
		//create new node
		LLNode<E> node = new LLNode(element);
		
		//add links to new node
		node.next = currentNode;
		node.prev = node.next.prev;
		
		//replace bordering nodes links to point to new node
		node.prev.next = node;
		node.next.prev = node;
		
		size();
	}


	/** Return the size of the list */
	public int size() 
	{
		// DONE
		LLNode<E> currentNode = head.next;
		int count = 0;
		while(currentNode != tail) {
			count++;
			currentNode = currentNode.next;
		}
		size = count;
		return count;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// DONE
		
		//get node at index
		LLNode<E> node = getNode(index);
		
		//remove node from list by changing pointers on neighboring nodes
		node.prev.next = node.next;
		node.next.prev = node.prev;
		
		size(); //update list size
		
		return node.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	
	public E set(int index, E element) 
	{
		// DONE
		
		if(element == null) {
			throw new NullPointerException("element object cannot store null pointer");
		}
		
		//get node at index
		LLNode<E> node = getNode(index);
		
		node.data = element;
		
		return null;
	}   
	
	private LLNode<E> getNode(int index){
		size();
		if(!(size() == 0 && index == 0)){
			if(index < 0 || index >= size()) {
				throw new IndexOutOfBoundsException("Index must be within the size of the list");
			}
		}
		
		LLNode<E> currentNode = this.head;
		for(int x = 0; x <= index; x++) {
			currentNode = currentNode.next;
		}
		return currentNode;
	}
}


class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor
	
	public LLNode(E e, LLNode<E> prev, LLNode<E> next) {
		this.data = e;
		this.prev = prev;
		this.next = next;
	}

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
