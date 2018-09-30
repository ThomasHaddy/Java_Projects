package edu.iastate.cs228.hw4;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit Testing for EntryTree
 * 
 * @author Thomas Haddy 11/15/2017
 *
 */
public class EntryTreeTest {

	private EntryTree<Character, String> et;
	Character[] keysEdit = { 'e', 'd', 'i', 't' };
	Character[] keysEdited = { 'e', 'd', 'i', 't', 'e', 'd' };
	Character[] keysEdict = { 'e', 'd', 'i', 'c', 't' };
	Character[] keysEditor = { 'e', 'd', 'i', 't', 'o', 'r' };
	Character[] keysEdition = { 'e', 'd', 'i', 't', 'i', 'o', 'n' };
	Character[] keysEditorial = { 'e', 'd', 'i', 't', 'o', 'r', 'i', 'a', 'l' };
	Character[] keysEdge = { 'e', 'd', 'g', 'e' };
	Character[] keysInvalid = { 'x' };
	Character[] keysNull = { 'e', 'd', 'g', null };

	// initialize the commonly used instance variables.
	@Before
	public void init() {
		et = new EntryTree<>();
	}

	// Search with one added Key.
	@Test
	public void searchTest1() {
		init();
		et.add(keysEdit, "search1");
		String expected = "search1";
		assertEquals(expected, et.search(keysEdit));
	}

	// Search with two added Keys.
	@Test
	public void searchTest2() {
		init();
		et.add(keysEdit, "search2");
		et.add(keysEdict, "search2");
		String expected = "search2";
		assertEquals(expected, et.search(keysEdict));
	}

	// Search coming back as null.
	@Test
	public void searchTest3() {
		init();
		et.add(keysEdit, "search3");
		String expected = null;
		assertEquals(expected, et.search(keysInvalid));
	}

	// Element in keyarr is null. Caught in search.
	@Test(expected = NullPointerException.class)
	public void searchTest4() {
		init();
		et.add(keysNull, "search4");
	}

	// Search should come back null. Searches for word that contains a null value.
	@Test
	public void searchTest5() {
		init();
		et.add(keysEditorial, "search5");
		String expected = null;
		assertEquals(expected, et.search(keysEdit));
	}

	// Prefix with one added item. Prefix is the whole item.
	@Test
	public void prefixTest1() {
		init();
		et.add(keysEdit, "prefix1");
		Character[] expected = { 'e', 'd', 'i', 't' };
		assertArrayEquals(expected, et.prefix(keysEdit));
		int expectedLen = 4;
		assertEquals(expectedLen, et.prefixlen);
	}

	// Prefix with one added item. Prefix of editorial should be edit.
	@Test
	public void prefixTest2() {
		init();
		et.add(keysEditorial, "prefix2");
		Character[] expected = { 'e', 'd', 'i', 't' };
		assertArrayEquals(expected, et.prefix(keysEdit));
		int expectedLen = 4;
		assertEquals(expectedLen, et.prefixlen);
	}

	// Prefix with one added item. Prefix will be something invalid.
	@Test
	public void prefixTest3() {
		init();
		et.add(keysEditorial, "prefix3");
		Character[] expected = null;
		assertArrayEquals(expected, et.prefix(keysInvalid));
		int expectedLen = 0;
		assertEquals(expectedLen, et.prefixlen);
	}

	// Element in keyarr is null. Caught in search.
	@Test(expected = NullPointerException.class)
	public void prefixTest4() {
		init();
		et.prefix(keysNull);
	}

	// Testing add on adding one keyarr to the tree.
	@Test
	public void addTest1() {
		init();
		et.add(keysEdit, "add1");
		String expected = et.root.child.child.child.child.value;
		assertEquals(expected, et.search(keysEdit));
		int expectedLen = 0;
		assertEquals(expectedLen, et.prefixlen);
	}

	// Testing add on adding on to a keyarr to the tree. Shouldn't need a next.
	@Test
	public void addTest2() {
		init();
		et.add(keysEdit, "add2a");
		et.add(keysEdict, "add2b");
		et.add(keysEditor, "add2c");
		String expected = et.root.child.child.child.child.child.child.value;
		assertEquals(expected, et.search(keysEditor));
		int expectedLen = 4;
		assertEquals(expectedLen, et.prefixlen);
	}

	// Testing add on changing a value to the tree.
	@Test
	public void addTest3() {
		init();
		et.add(keysEdit, "add3");
		et.add(keysEdit, "add3Changed");
		String expected = et.root.child.child.child.child.value;
		assertEquals(expected, et.search(keysEdit));
		int expectedLen = 4;
		assertEquals(expectedLen, et.prefixlen);
	}

	// Testing add on multiple calls.
	@Test
	public void addTest4() {
		init();
		et.add(keysEdited, "add4");
		et.add(keysEditorial, "add4");
		et.add(keysEdict, "add4");
		et.add(keysEdge, "add4FindMe");
		String expected = et.root.child.child.child.next.child.value;
		assertEquals(expected, et.search(keysEdge));
		int expectedLen = 2;
		assertEquals(expectedLen, et.prefixlen);
	}

	// Testing add again...
	@Test
	public void addTest5() {
		init();
		et.add(keysEdit, "add5");
		et.add(keysEdition, "add5");
		et.add(keysEditorial, "add5FindMe");
		String expected = et.root.child.child.child.child.child.next.child.child.child.child.value;
		assertEquals(expected, et.search(keysEditorial));
		int expectedLen = 4;
		assertEquals(expectedLen, et.prefixlen);
	}

	// Testing the NullPointer
	@Test(expected = NullPointerException.class)
	public void addTest6() {
		init();
		et.add(keysNull, "add6");
	}

	// Testing a simple removal of the first add. Should just be the root.
	@Test
	public void removeTest1() {
		init();
		et.add(keysEdit, "remove1");
		et.remove(keysEdit);
		String expected = null;
		assertEquals(expected, et.search(keysEdit));
	}

	// Testing the removal of a value in the tree.
	@Test
	public void removeTest2() {
		init();
		et.add(keysEdit, "remove2");
		et.add(keysEdition, "remove2");
		et.remove(keysEdit);
		String expected = null;
		assertEquals(expected, et.search(keysEdit));
	}

	// Testing a remove that affects the above subtree.
	@Test
	public void removeTest3() {
		init();
		et.add(keysEdited, "remove3");
		et.add(keysEditor, "remove3");
		et.add(keysEditorial, "remove3");
		et.remove(keysEditor);
		String expected = null;
		assertEquals(expected, et.search(keysEditor));
	}

	// Testing remove to remove something that isn't there
	@Test
	public void removeTest4() {
		init();
		et.add(keysEdit, "remove5");
		et.remove(keysEdit);
		et.remove(keysEdit);
		String expected = null;
		assertEquals(expected, et.search(keysEdit));
	}

	// Testing the NullPointer
	@Test(expected = NullPointerException.class)
	public void removeTest5() {
		init();
		et.remove(keysNull);
	}
}
