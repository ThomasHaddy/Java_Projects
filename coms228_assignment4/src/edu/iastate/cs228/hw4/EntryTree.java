package edu.iastate.cs228.hw4;

import java.util.Arrays;

/**
 * @author Thomas Haddy 11/15/2017
 *
 *         An entry tree class Add Javadoc comments to each method
 */
public class EntryTree<K, V> {

	/**
	 * dummy root node made public for grading
	 */
	protected Node root;

	/**
	 * prefixlen is the largest index such that the keys in the subarray keyarr from
	 * index 0 to index prefixlen - 1 are, respectively, with the nodes on the path
	 * from the root to a node. prefixlen is computed by a private method shared by
	 * both search() and prefix() to avoid duplication of code.
	 */
	protected int prefixlen;

	/**
	 * Inner class used to make a node with a given key and value.
	 */
	protected class Node implements EntryNode<K, V> {

		/**
		 * Link to the first child node
		 */
		protected Node child;

		/**
		 * Link to the parent node
		 */
		protected Node parent;

		/**
		 * Link to the previous sibling
		 */
		protected Node prev;

		/**
		 * Link to the next sibling
		 */
		protected Node next;

		/**
		 * The key for this node
		 */
		protected K key;

		/**
		 * The value at this node
		 */
		protected V value;

		public Node(K aKey, V aValue) {
			key = aKey;
			value = aValue;
			child = null;
			parent = null;
			prev = null;
			next = null;
		}

		@Override
		public EntryNode<K, V> parent() {
			return parent;
		}

		@Override
		public EntryNode<K, V> child() {
			return child;
		}

		@Override
		public EntryNode<K, V> next() {
			return next;
		}

		@Override
		public EntryNode<K, V> prev() {
			return prev;
		}

		@Override
		public K key() {
			return key;
		}

		@Override
		public V value() {
			return value;
		}
	}

	/**
	 * Constructs an EntryTree with a root containing null elements.
	 */
	public EntryTree() {
		root = new Node(null, null);
	}

	/**
	 * Returns the value of the entry with a specified key sequence, or null if this
	 * tree contains no entry with the key sequence.
	 * 
	 * @param keyarr
	 *            Given specified key sequence
	 * @return Returns entry value
	 */
	public V search(K[] keyarr) {

		if (!checkForValidKeys(keyarr))
			return null;

		Node found = traverseToNode(keyarr);
		return found.value;
	}

	/**
	 * The method returns an array of type K[] with the longest prefix of the key
	 * sequence specified in keyarr such that the keys in the prefix label the nodes
	 * on the path from the root to a node. The length of the returned array is the
	 * length of the longest prefix.
	 * 
	 * @param keyarr
	 *            Given key array
	 * @return Returns the longest prefix of keyarr
	 */
	public K[] prefix(K[] keyarr) {

		if (!checkForValidKeys(keyarr))
			return null;

		Node cur = root.child;
		prefixlen = 0;
		for (int i = 0; i < keyarr.length; i++) {

			while (cur != null) {
				if (cur.key.equals(keyarr[i])) {
					cur = cur.child;
					prefixlen = i + 1;
					break;
				}
				cur = cur.next;
			}
			if (cur == null)
				break;
		}
		if (prefixlen == 0 && root.child != null)
			return null;
		return Arrays.copyOf(keyarr, prefixlen);
	}

	/**
	 * Traverses to the last element of keyarr that corresponds to a node.
	 * 
	 * @param keyarr
	 *            Given key array
	 * @return Returns the node with a key equal to the last element in keyarr.
	 */
	private Node traverseToNode(K[] keyarr) {

		Node cur = root;
		Node futureCur = root.child;

		for (int i = 0; i < keyarr.length; i++) {

			while (futureCur != null) {
				if (futureCur.key.equals(keyarr[i])) {
					cur = futureCur;
					futureCur = futureCur.child;
					break;
				}
				futureCur = futureCur.next;

			}
		}
		return cur;
	}

	/**
	 * The method locates the node P corresponding to the longest prefix of the key
	 * sequence specified in keyarr such that the keys in the prefix label the nodes
	 * on the path from the root to the node. If the length of the prefix is equal
	 * to the length of keyarr, then the method places aValue at the node P and
	 * returns true. Otherwise, the method creates a new path of nodes (starting at
	 * a node S) labelled by the corresponding suffix for the prefix, connects the
	 * prefix path and suffix path together by making the node S a child of the node
	 * P, and returns true.
	 * 
	 * @param keyarr
	 *            Given key array
	 * @param aValue
	 *            Given value
	 * @return Returns true if add was successful, otherwise false.
	 */
	public boolean add(K[] keyarr, V aValue) {

		if (!checkForValidKeys(keyarr))
			return false;

		K[] prefix = prefix(keyarr);
		int pathLength = prefixlen;
		Node cur = traverseToNode(prefix);

		if (pathLength == keyarr.length)
			cur.value = aValue;

		if (pathLength < keyarr.length && pathLength > 0 && cur.child != null) {
			cur = cur.child;

			while (cur.next != null) {
				cur = cur.next;
			}

			cur.next = new Node(keyarr[pathLength], null);
			cur.next.prev = cur;
			cur.next.parent = cur.parent;
			cur = cur.next;
			pathLength++;
		}

		while (pathLength < keyarr.length) {
			cur.child = new Node(keyarr[pathLength], null);
			cur.child.parent = cur;
			cur = cur.child;
			pathLength++;
		}

		cur.value = aValue;
		return true;
	}

	/**
	 * Removes the entry for a key sequence from this tree and returns its value if
	 * it is present. Otherwise, it makes no change to the tree and returns null.
	 * 
	 * @param keyarr
	 *            Given key array to remove.
	 * @return Returns the value of the last node whose element key is in keyarr.
	 */
	public V remove(K[] keyarr) {

		if (!checkForValidKeys(keyarr) || traverseToNode(keyarr) == null)
			return null;

		Node cur = traverseToNode(keyarr);
		V value = cur.value;
		cur.value = null;

		if (cur.child == null) {
			while (cur.parent != null) {
				cur = cur.parent;

				if (cur.value == null) {
					Node curKid = cur.child;

					if (cur.child.next == null) {
						// erase this node.
						cur.child = null;
						curKid.key = null;
						curKid.value = null;
						curKid.parent = null;

					} else if (cur.child.next != null) {
						// move to the next, and erase the one behind it.
						curKid = cur.child.next;
						curKid.parent = curKid.prev.parent;
						curKid.prev.key = null;
						curKid.prev.value = null;
						curKid.prev.parent = null;
						curKid.prev = null;

						return value;
					}
				} else
					return value;
			}
		}
		return value;
	}

	/**
	 * The method prints the tree on the console in the output format shown in an
	 * example output file.
	 */
	public void showTree() {
		if (root == null)
			return;

		recShowTree(root, "->");
	}

	/**
	 * Recursively shows the tree. Traverses through the tree.
	 * 
	 * @param r
	 *            Current node. Starts at the root
	 * @param level
	 *            Current level. Prints "->"
	 */
	private void recShowTree(Node cur, String level) {

		if (cur == null)
			return;
		else {
			System.out.println(getNumSpaces(cur) + cur.key + level + cur.value);
			cur = cur.child;
			while (cur != null) {
				recShowTree(cur, level);
				cur = cur.next;
			}
		}
	}

	/**
	 * Gets the number of spaces required to output the tree according to its level.
	 * 
	 * @param r
	 *            Current node. Starts at the root
	 * @return Returns a String with the correct number of spaces
	 */
	private String getNumSpaces(Node r) {
		if (r == root)
			return "";

		int numSpaces = 4;
		while (r != root) {
			numSpaces += 2;
			r = r.parent;
		}
		String result = "";
		for (int i = 0; i < numSpaces; i += 2) {
			result += "  ";
		}
		return result;
	}

	/**
	 * Checks to make sure the given keyarr is valid
	 * 
	 * @param keyarr
	 *            Given key array
	 * @return Returns true if valid, otherwise false.
	 */
	private boolean checkForValidKeys(K[] keyarr) {
		if (keyarr == null || keyarr.length == 0)
			return false;

		for (int i = 0; i < keyarr.length; i++) {
			if (keyarr[i] == null)
				throw new NullPointerException("keyarr contains a null element");
		}
		return true;
	}
}
