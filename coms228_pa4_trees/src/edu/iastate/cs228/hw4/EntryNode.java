package edu.iastate.cs228.hw4;

/**
 * An interface for describing basic getter functionality for a node
 * 
 * @author Thomas Haddy 11/15/2017
 * 
 * @param <K>
 *            Generic something
 * @param <V>
 *            Generic something
 */
public interface EntryNode<K, V> {

	/**
	 * Returns the parent node of this node. If there is no parent node,
	 * <code>null</code> is returned.
	 * 
	 * @return
	 */
	public EntryNode<K, V> parent();

	/**
	 * 
	 * <p>
	 * Returns the child node of this node. If a node has multiple children, then
	 * this returns the left-most child. E.g.,
	 * </p>
	 * 
	 * <pre>
	 *          Parent
	 *            |
	 * null <-> Child <-> Sibling <-> Sibling ...
	 * </pre>
	 * <p>
	 * If there is no child node, <code>null</code> is returned.
	 * </p>
	 * 
	 * @return
	 */
	public EntryNode<K, V> child();

	/**
	 * Returns the next sibling of this node. If there is no next sibling,
	 * <code>null</code> is returned.
	 * 
	 * @return
	 */
	public EntryNode<K, V> next();

	/**
	 * Returns the previous sibling of this node. If there is no previous sibling,
	 * <code>null</code> is returned.
	 * 
	 * @return
	 */
	public EntryNode<K, V> prev();

	/**
	 * Returns the (non-null) key for this node.
	 * 
	 * @return
	 */
	public K key();

	/**
	 * Returns the value for this node.
	 * 
	 * @return
	 */
	public V value();
}
