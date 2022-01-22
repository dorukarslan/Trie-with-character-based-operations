//-----------------------------------------------------
// Title: Queue class
// Author:Do�uhan Cumao�lu Doruk Arslan
// Description: Queue class for TrieST
//-----------------------------------------------------
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<Item> implements Iterable<Item> {
	private Node<Item> first; // beginning of queue
	private Node<Item> last; // end of queue
	private int n; // number of elements on queue

	// helper linked list class
	private static class Node<Item> {
		private Item item;
		private Node<Item> next;
	}
	// Default constructor.
	public Queue() {
		first = null;
		last = null;
		n = 0;
	}
	// Summary: It checks whether the queue is empty or not
	// Postcondition: It's check that whether the element size is equal to 0
	public boolean isEmpty() {
		return first == null;
	}
	// To return the size of the array;
	public int size() {
		return n;
	}

	public Item peek() {
		if (isEmpty())
			throw new NoSuchElementException("Queue underflow");
		return first.item;
	}
	// Summary: It inserts the given element.
		// Precondition: Given key is not added yet
		// Postcondition: Given key is added to queue
	public void enqueue(Item item) {
		Node<Item> oldlast = last;
		last = new Node<Item>();
		last.item = item;
		last.next = null;
		if (isEmpty())
			first = last;
		else
			oldlast.next = last;
		n++;
	}
	// Summary: Deletes the  element in queue
		// Precondition: Element is in the queue
		// Postcondition: Removed and returned.
	public Item dequeue() {
		if (isEmpty())
			throw new NoSuchElementException("Queue underflow");
		Item item = first.item;
		first = first.next;
		n--;
		if (isEmpty())
			last = null; // to avoid loitering
		return item;
	}

	public String toString() { // To string function 
		StringBuilder s = new StringBuilder();
		for (Item item : this) {
			s.append(item);
			s.append(' ');
		}
		return s.toString();
	}

	public Iterator<Item> iterator() {
		return new LinkedIterator(first);
	}

	private class LinkedIterator implements Iterator<Item> {
		private Node<Item> current;

		public LinkedIterator(Node<Item> first) {
			current = first;
		}

		public boolean hasNext() { // For find does node has a next element or not 
			return current != null;
		}

		

		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}
	}

}
