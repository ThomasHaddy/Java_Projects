package edu.iastate.cs228.hw3;

/**
 * @author Thomas Haddy 10/26/17
 */

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * An implementation of List<E> based on a doubly-linked list with an array for indexed reads/writes
 */
public class AdaptiveList<E> implements List<E>
{
	/**
	 * private member of outer class. Contains data of type E and reference to the previous and future node.
	 */
	public class ListNode // private member of outer class
	{
		/**
		 * Data is each Node
		 */
		public E data;// public members:
		/**
		 * Reference to the next Node
		 */
		public ListNode link; // used outside the inner class
		/**
		 * Reference to the previous Node
		 */
		public ListNode prev; // used outside the inner class

		/**
		 * Constructs a Node with given data and references to the previous and next Node
		 * @param item given data
		 */
		public ListNode(E item)
		{
			data = item;
			link = prev = null;
		}
	}

	/**
	 * Dummy node at beginning. Made public for testing.
	 */
	public ListNode head;
	/**
	 * Dummy node at end. Made public for testing.
	 */
	public ListNode tail;
	/**
	 * Number of data items
	 */
	private int numItems;
	/**
	 * true if the linked list is up-to-date.
	 */
	private boolean linkedUTD;
	/**
	 * the array for storing elements
	 */
	public E[] theArray;
	/**
	 * true if the array is up-to-date.
	 */
	private boolean arrayUTD;

	/**
	 * Constructs an AdaptiveList and sets its instance variables to null, false, or 0. Makes LinkedUTD true
	 */
	public AdaptiveList()
	{
		clear();
	}

	@Override
	public void clear()
	{
		head = new ListNode(null);
		tail = new ListNode(null);
		head.link = tail;
		tail.prev = head;
		numItems = 0;
		linkedUTD = true;
		arrayUTD = false;
		theArray = null;
	}

	/**
	 * Returns if the linkedList is up to date
	 */
	public boolean getlinkedUTD()
	{
		return linkedUTD;
	}

	/**
	 * Returns if the array is up to date
	 */
	public boolean getarrayUTD()
	{
		return arrayUTD;
	}

	/**
	 * Constructs an AdaptiveList and adds the given list to AdaptiveList's DoublyLinkedList
	 * @param c Any class whose super contains Collection(List, ArrayList, etc.)
	 */
	public AdaptiveList(Collection<? extends E> c)
	{
		this();

		for (E e : c) {
			add(e);
		}
		linkedUTD = true;
		arrayUTD = false;
		theArray = null;
	}

	/**
	 * Removes the node from the linked list.
	 * This method should be used to remove a node from the linked list.
	 * @param toRemove Node to remove
	 */
	private void unlink(ListNode toRemove)
	{
		if ( toRemove == head || toRemove == tail )
			throw new RuntimeException("An attempt to remove head or tail");
		toRemove.prev.link = toRemove.link;
		toRemove.link.prev = toRemove.prev;
	}

	/**
	 * Inserts new node toAdd right after old node current.
	 * This method should be used to add a node to the linked list.
	 * @param current Current Node
	 * @param toAdd Node to be added
	 */
	private void link(ListNode current, ListNode toAdd)
	{
		if ( current == tail )
			throw new RuntimeException("An attempt to link after tail");
		if ( toAdd == head || toAdd == tail )
			throw new RuntimeException("An attempt to add head/tail as a new node");
		toAdd.link = current.link;
		toAdd.link.prev = toAdd;
		toAdd.prev = current;
		current.link = toAdd;
	}

	/**
	 * Updates the array part of AdpativeList. Makes theArray up-to-date.
	 */
	@SuppressWarnings("unchecked")
	private void updateArray()
	{
		arrayUTD = true;

		if ( numItems < 0 )
			throw new RuntimeException("numItems is negative: " + numItems);
		if ( ! linkedUTD )
			throw new RuntimeException("linkedUTD is false");

		theArray = (E[]) new Object[numItems];
		for (int i = 0; i < theArray.length; i++) {
			theArray[i] = findNode(i+1).data;
		}
	}

	/**
	 * Updates the DoublyLinkedList part of AdaptiveList. Makes the linked list up-to-date.
	 */
	private void updateLinked()
	{
		linkedUTD = true;

		if ( numItems < 0 )
			throw new RuntimeException("numItems is negative: " + numItems);
		if ( ! arrayUTD )
			throw new RuntimeException("arrayUTD is false");

		if ( theArray == null || theArray.length < numItems )
			throw new RuntimeException("theArray is null or shorter");

		head = new ListNode(null);
		tail = new ListNode(null);
		head.link = tail;
		tail.prev = head;

		numItems = 0;
		for (int i = 0; i < theArray.length; i++) {
			add(theArray[i]);
		}

	}

	@Override
	public int size()
	{
		return numItems;
	}

	@Override
	public boolean isEmpty()
	{
		return numItems < 1;
	}

	@Override
	public boolean add(E obj)
	{
		if (!linkedUTD) updateLinked();

		ListNode lastAdded = findNode(numItems);
		ListNode adding = new ListNode(obj);
		link(lastAdded, adding);

		numItems++;
		arrayUTD = false;
		return true;
	}

	@Override
	public boolean addAll(Collection< ? extends E> c)
	{	
		if (!linkedUTD) updateLinked();

		if (c.isEmpty()) {
			return false;
		}

		if (this.equals(c)) {
			Collection< ? extends E> tempList = c;
			tempList = new AdaptiveList<>(c);
			for (E e : tempList) {
				add(e);
			}
			arrayUTD = false;
			return true;
		}

		for (E e : c) {
			add(e);
		}

		arrayUTD = false;
		return true;
	}

	@Override
	public boolean remove(Object obj)
	{
		if (!linkedUTD) updateLinked();

		if (!contains(obj)) {
			return false;
		}
		int pos = indexOf(obj);
		if (pos==0) pos = 1; //If the index is on the head

		unlink(findNode(pos));
		numItems--;
		arrayUTD = false;

		return true;
	}

	/**
	 * Checks the index at the given pos
	 * @param pos given position
	 */
	private void checkIndex(int pos) // a helper method
	{
		if ( pos >= numItems || pos < 0 )
			throw new IndexOutOfBoundsException(
					"Index: " + pos + ", Size: " + numItems);
	}

	/**
	 * Checks the index at the given pos
	 * @param pos given position
	 */
	private void checkIndex2(int pos) // a helper method
	{
		if ( pos > numItems || pos < 0 )
			throw new IndexOutOfBoundsException(
					"Index: " + pos + ", Size: " + numItems);
	}

	/**
	 * Checks the node at the given node
	 * @param cur given node
	 */
	private void checkNode(ListNode cur) // a helper method
	{
		if ( cur == null || cur == tail )
			throw new RuntimeException(
					"numItems: " + numItems + " is too large");
	}

	/**
	 * Finds the node at the given pos
	 * @param pos given position
	 * @return returns the ListNode at given pos
	 */
	private ListNode findNode(int pos)   // a helper method
	{
		if (!linkedUTD) updateLinked();

		ListNode cur = head;
		for ( int i = 0; i < pos; i++ )
		{
			checkNode(cur);
			cur = cur.link;
		}
		checkNode(cur);
		return cur;
	}

	@Override
	public void add(int pos, E obj)
	{
		if (!linkedUTD) updateLinked();

		checkIndex2(pos);

		ListNode toAdd = new ListNode(obj);
		ListNode cur = findNode(pos);
		link(cur, toAdd);
		numItems++;
		arrayUTD = false;
	}

	@Override
	public boolean addAll(int pos, Collection< ? extends E> c)
	{
		if (!linkedUTD) updateLinked();

		checkIndex2(pos);
		if (c.isEmpty()) {
			return false;
		}

		if (this.equals(c)) {
			Collection< ? extends E> tempList = c;
			tempList = new AdaptiveList<>(c);
			int i = pos;
			for (E e : tempList) {
				add(i++, e);
			}
			arrayUTD = false;
			return true;
		}

		int i = pos;
		for (E e : c) {
			add(i++, e);
		}

		arrayUTD = false;
		return true;
	} // addAll 2

	@Override
	public E remove(int pos)
	{
		if (!linkedUTD) updateLinked();
		checkIndex(pos);

		ListNode cur = findNode(pos+1);
		unlink(cur);
		numItems--;
		arrayUTD = false;
		return cur.data;
	}

	@Override
	public E get(int pos)
	{
		if (!arrayUTD) updateArray();
		checkIndex(pos);

		return theArray[pos];
	}

	@Override
	public E set(int pos, E obj)
	{	
		if (!arrayUTD) updateArray();
		checkIndex(pos);

		E temp = theArray[pos];
		theArray[pos] = obj;

		linkedUTD = false;
		return temp; // may need to be revised.
	} 

	/**
	 * If the number of elements is at most 1, the method returns false.
	 * Otherwise, it reverses the order of the elements in the array
	 * without using any additional array, and returns true.
	 * Note that if the array is modified, then linkedUTD needs to be set to false.
	 * @return Reversed array
	 */
	public boolean reverse()
	{
		if (!arrayUTD) updateArray();

		if (theArray.length <= 1) {
			return false;
		}
		for (int i = 0; i < theArray.length / 2; i++) {
			E temp = theArray[i];
			theArray[i] = theArray[theArray.length - i - 1];
			theArray[theArray.length - i - 1] = temp;
		}

		linkedUTD = false;
		return true;
	}

	@Override
	public boolean contains(Object obj)
	{
		if (!linkedUTD) updateLinked();

		ListNode cur;
		for (cur = head; cur != null; cur = cur.link) {
			if (obj==cur.data && !cur.equals(head) && !cur.equals(tail)|| obj!=null && obj.equals(cur.data)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection< ? > c)
	{
		if (!linkedUTD) updateLinked();

		for ( Object o : c ) {
			if (!contains(o) && o != null) {
				return false;
			}
		}
		return true;
	} // containsAll


	@Override
	public int indexOf(Object obj)
	{
		if (!linkedUTD) updateLinked();

		ListNode cur; 
		int pos = 0;
		for (cur = head; cur!=tail;cur = cur.link, pos++) {
			if (obj==cur.data || obj != null && obj.equals(cur.data)) {
				if (pos==1) return 0; //If the index is the head
				if (pos > 0 && pos != numItems) pos--; //If the index is the tail
				return pos;
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object obj)
	{
		ListIterator<E> iter = listIterator(numItems);
		while ( iter.hasPrevious() )
		{
			E data = iter.previous();
			if ( obj == data || obj != null && obj.equals(data) )
				return iter.nextIndex();
		}
		return -1;
	}

	@Override
	public boolean removeAll(Collection<?> c)
	{
		if ( c == null )
			throw new NullPointerException();
		boolean changed = false;
		ListIterator<E> iter = listIterator();
		while (iter.hasNext())
		{
			E data = iter.next();
			if (c.contains(data))
			{
				iter.remove();
				changed = true;
			}
		}
		return changed;
	}

	@Override
	public boolean retainAll(Collection<?> c)
	{
		if ( c == null )
			throw new NullPointerException();
		boolean changed = false;
		ListIterator<E> iter = listIterator();
		while ( iter.hasNext() )
		{
			E data = iter.next();
			if ( ! c.contains(data) )
			{
				iter.remove();
				changed = true;
			}
		}
		return changed;
	}

	@Override
	public Object[] toArray()
	{
		Object[] arr = new Object[numItems];
		ListIterator<E> iter = listIterator();
		for(int i = 0; i < numItems; i++)
			arr[i] = iter.next();
		return arr;
	}

	@Override
	public <T> T[] toArray(T[] arr)
	{
		if(arr.length < numItems)
			arr = Arrays.copyOf(arr, numItems);
		System.arraycopy(toArray(), 0, arr, 0, numItems);
		if(arr.length > numItems)
			arr[numItems] = null;
		return arr;
	}

	@Override
	public List<E> subList(int fromPos, int toPos)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * The AdaptiveListIterator is used to traverse the AdaptiveList
	 */
	private class AdaptiveListIterator implements ListIterator<E>
	{
		/**
		 * index of next node
		 */
		private int     index;
		/**
		 * node at index - 1. The cursor
		 */
		private ListNode  cur;
		/**
		 * node last visited by next() or previous()
		 */
		private ListNode last;

		/**
		 * Constructs an iterator with the cursor located to the left of head and the index set at 0.
		 */
		public AdaptiveListIterator()
		{
			if (!linkedUTD) updateLinked();

			last = null;
			index = 0;
			cur = findNode(index);
		}

		/**
		 * Constructs an iterator with the cursor located to the left of pos and the index set at pos
		 * @param pos Current position of iterator
		 */
		public AdaptiveListIterator(int pos)
		{
			if (!linkedUTD) updateLinked();

			checkIndex2(pos);
			index = pos;
			last = null;

			cur = findNode(index);
		}

		@Override
		public boolean hasNext()
		{
			return index < numItems;
		}

		@Override
		public E next()
		{
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			if (index >= numItems) {
				throw new RuntimeException("index is greater than or equal to the numItems. Index: " + index + " numItems: " + numItems);
			}
			index++;
			//If cur is null, set last to head. Otherwise, set last to the cursor's link.
			last = cur == null ? head : cur.link;

			cur = findNode(index);

			return cur.data;
		} 

		@Override
		public boolean hasPrevious()
		{
			return index > 0;
		}

		@Override
		public E previous()
		{
			if (!hasPrevious()) {
				throw new NoSuchElementException();
			}
			if (index <= 0) { 
				throw new RuntimeException("index is less than or equal to 0. Index: " + index);
			}
			index--;
			last = cur;
			cur = findNode(index);
			return last.data;
		}

		@Override
		public int nextIndex()
		{
			return index;
		}

		@Override
		public int previousIndex()
		{
			return index-1;
		}

		/**
		 * Removes the Node at the cursor's right position
		 */
		public void remove()
		{
			if (last==null)
				throw new IllegalStateException();

			if (cur==last) {
				if (index <= 0)
					throw new RuntimeException("index is less than or equal to 0. Index: " + index);
				index--;
			}

			numItems--;

			if (last==head)
				unlink(head.link);
			else
				unlink(last);

			last = null;
		}

		/**
		 * Adds a Node with data obj at the cursor's right position 
		 */
		public void add(E obj)
		{
			cur = findNode(index);
			ListNode toAdd = new ListNode(obj);

			ListNode addAt = cur;
			link(addAt, toAdd);
			numItems++;

			index++;
			cur = findNode(index);
			last = null;
		} // add

		@Override
		public void set(E obj)
		{	
			if ( last == null )
				throw new IllegalStateException();
			last.data = obj;
		} // set
	} // AdaptiveListIterator

	@Override
	public boolean equals(Object obj)
	{
		if ( ! linkedUTD ) updateLinked();
		if ( (obj == null) || ! ( obj instanceof List<?> ) )
			return false;
		List<?> list = (List<?>) obj;
		if ( list.size() != numItems ) return false;
		Iterator<?> iter = list.iterator();
		for ( ListNode tmp = head.link; tmp != tail; tmp = tmp.link )
		{
			if ( ! iter.hasNext() ) return false;
			Object t = iter.next();
			if ( ! (t == tmp.data || t != null && t.equals(tmp.data) ) )
				return false;
		}
		if ( iter.hasNext() ) return false;
		return true;
	} // equals

	@Override
	public Iterator<E> iterator()
	{
		return new AdaptiveListIterator();
	}

	@Override
	public ListIterator<E> listIterator()
	{
		return new AdaptiveListIterator();
	}

	@Override
	public ListIterator<E> listIterator(int pos)
	{
		checkIndex2(pos);
		return new AdaptiveListIterator(pos);
	}

	// Adopted from the List<E> interface.
	@Override
	public int hashCode()
	{
		if ( ! linkedUTD ) updateLinked();
		int hashCode = 1;
		for ( E e : this )
			hashCode = 31 * hashCode + ( e == null ? 0 : e.hashCode() );
		return hashCode;
	}

	// You should use the toString*() methods to see if your code works as expected.
	@Override
	public String toString()
	{
		String eol = System.getProperty("line.separator");
		return toStringArray() + eol + toStringLinked();
	}

	/**
	 * Represents the array as a String
	 * @return theArray as a String
	 */
	public String toStringArray()
	{
		String eol = System.getProperty("line.separator");
		StringBuilder strb = new StringBuilder();
		strb.append("A sequence of items from the most recent array:" + eol );
		strb.append('[');
		if ( theArray != null )
			for ( int j = 0; j < theArray.length; )
			{
				if ( theArray[j] != null )
					strb.append( theArray[j].toString() );
				else
					strb.append("-");
				j++;
				if ( j < theArray.length )
					strb.append(", ");
			}
		strb.append(']');
		return strb.toString();
	}

	/**
	 * Returns the String representation of the DoublyLinkedList
	 */
	public String toStringLinked()
	{
		return toStringLinked(null);
	}

	/**
	 * Returns the String representation of the DoublyLinkedList at iter.
	 * @param iter current iterator
	 */
	public String toStringLinked(ListIterator<E> iter)
	{
		int cnt = 0;
		int loc = iter == null? -1 : iter.nextIndex();

		String eol = System.getProperty("line.separator");
		StringBuilder strb = new StringBuilder();
		strb.append("A sequence of items from the most recent linked list:" + eol );
		strb.append('(');
		for ( ListNode cur = head.link; cur != tail; )
		{
			if ( cur.data != null )
			{
				if ( loc == cnt )
				{
					strb.append("| ");
					loc = -1;
				}
				strb.append(cur.data.toString());
				cnt++;

				if ( loc == numItems && cnt == numItems )
				{
					strb.append(" |");
					loc = -1;
				}
			}
			else
				strb.append("-");

			cur = cur.link;
			if ( cur != tail )
				strb.append(", ");
		}
		strb.append(')');
		return strb.toString();
	}
}
