package edu.iastate.cs228.hw3;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

/**
 * A class full of tests for this assignment. The tests are arranged alphabetically
 * by method name in general (except for the constructor tests, which happen first).
 * Tests for the iterator will appear in another test file.
 * @author Luke Schoeberle
 */
public class AdaptiveListTests {
	
	private AdaptiveList<String> list; //list used for all testing
	private Collection<String> otherList; //used for containsAll, addAll (any collection methods)
	
	//initialize the commonly used instance variables
	@Before
	public void init() {
		list = new AdaptiveList<>();
		otherList = Arrays.asList(null,"u","i","u","hg", null); //some random list
	}
	
	//tests the initial size
	@Test
	public void DefaultConstructorTest1() {
		int initSize = list.size();
		int expected = 0;
		assertEquals("The initalSize should be zero.", expected, initSize);
	}
	
	//tests the initial array state (up to date or out of date)
	@Test
	public void DefaultConstructorTest2() {
		boolean arrayUTD = list.getarrayUTD();
		boolean expected = false;
		assertEquals("The array should initially not be up to date.", expected, arrayUTD);
	}
	
	//tests the initial linked list state
	@Test
	public void DefaultConstructorTest3() {
		boolean linkedUTD = list.getlinkedUTD();
		boolean expected = true;
		assertEquals("The linked list should initially be up to date.", expected, linkedUTD);
	}
	
	//tests to see if the collection constructor adds the other list correctly
	@Test
	public void CollectionConstructorTest1() {
		list = new AdaptiveList<>(otherList);
		Iterator<String> oIter = otherList.iterator(), lIter = list.iterator();
		boolean areEqual = true;
		while(oIter.hasNext() && lIter.hasNext()) {
			String oString = oIter.next(), lString = lIter.next();
			if(oString != lString || (oString != null && !oString.equals(lString))) {
				areEqual = false; //compare by contents (being careful of nulls also)
				break;
			}
		}
		boolean expected = true;
		assertEquals("After calling the collection constructor, the list should have the"
					+ " same contents as the collection passed as a parameter.", expected, areEqual);
	}
		
	//tests the initial array state 
	@Test
	public void CollectionConstructorTest2() {
		list = new AdaptiveList<>(otherList);
		DefaultConstructorTest2(); //use the same test for the default constructor
	}
	
	//tests the initial linked list state
	@Test
	public void CollectionConstructorTest3() {
		list = new AdaptiveList<>(otherList);
		DefaultConstructorTest3(); //use the same test for the default constructor
	}
	
	//tests to see if adding to the end works correctly
	@Test
	public void AddTest1() {
		list.add("oi");
		String result = list.toStringLinked().substring(55); //get the list at the end
		String expected = "(oi)";
		assertEquals("After calling add(\"oi\"), \"oi\" should be added to the list.", expected, result);
	}
	
	//tests to see if adding to the end changes the size correctly
	@Test
	public void AddTest2() {
		list.add("oi");
		int newSize = list.size();
		int expected = 1;
		assertEquals("After calling add(\"oi\") on the empty list, the size should have increased to 1.", expected, newSize);
	}
	
	//tests to see if adding to the end puts the array out of date
	@Test
	public void AddTest3() {
		list.add("oi");
		boolean newSize = list.getarrayUTD(); 
		boolean expected = false;
		assertEquals("After calling add, the array should be out of date.", expected, newSize);
	}
	
	//tests to see if adding to the end puts the linked list up to date
	@Test
	public void AddTest4() {
		list.add("oi");
		boolean newSize = list.getlinkedUTD(); 
		boolean expected = true;
		assertEquals("After calling add, the linked list should be up to date.", expected, newSize);
	}
	//tests the return value of add (should always be true)
	@Test
	public void AddTest5() {
		boolean added = list.add("lol");
		assertTrue("After adding an element, add should always return true.", added);
	}	
	
	//tests to see if adding a null value works correctly
	@Test
	public void AddTest6() {
		list.add(null);
		String result = list.toStringLinked().substring(55); //get the list at the end
		String expected = "(-)";
		assertEquals("After calling add(null) on the empty list, null should be added to the list.", expected, result);
	}
	
	//tests to see if adding two items changes the list correctly
	@Test
	public void AddTest7() {
		list.add("oi");
		list.add("pk");
		String result = list.toStringLinked().substring(55); //get the list at the end
		String expected = "(oi, pk)";
		assertEquals("After calling add(\"oi\") and add(\"pk\" to the empty list, both of the strings "
				+ 	"should be at the end of the list.", expected, result);
	}
	
	//tests to see if adding to the end changes the list size correctly
	@Test
	public void AddTest8() {
		list.add("oi");
		list.add("pk");
		int newSize = list.size();
		int expected = 2;
		assertEquals("After calling add(\"oi\") and add(\"pk\") on the empty list, the size should have "
				   + "increased to 2.", expected, newSize);
	}
	
	//tests to see if adding explicitly changes the list size correctly
	@Test
	public void AddPosTest1() {
		list.add(0,"oi");
		int newSize = list.size();
		int expected = 1;
		assertEquals("After calling add(0,\"oi\") on the empty list, the size should have "
				   + "increased to 1.", expected, newSize);
	}
	
	//tests to see if adding explicitly changes the list correctly
	@Test
	public void AddPosTest2() {
		list.add(0,"oi");
		String newSize = list.toStringLinked().substring(55);
		String expected = "(oi)";
		assertEquals("After calling add(0,\"oi\") on the empty list, oi should have been "
				   + "added to the list.", expected, newSize);
	}	
	
	//tests to see if adding explicitly at the end changes the list size correctly
	@Test
	public void AddPosTest3() {
		list.add("beg");
		list.add(1,"oi");
		int newSize = list.size();
		int expected = 2;
		assertEquals("After calling add(1,\"oi\") on the a list with 1 element, the size should have "
				   + "increased to 2.", expected, newSize);
	}
	
	//tests to see if adding explicitly at the end changes the list correctly
	@Test
	public void AddPosTest4() {
		list.add("beg");
		list.add(1,"oi");
		String newSize = list.toStringLinked().substring(55);
		String expected ="(beg, oi)";
		assertEquals("After calling add(1,\"oi\") on the a list with 1 element, oi should have been "
				   + "added to the end.", expected, newSize);
	}
	
	//tests to see if adding explicitly at the beginning changes the list size correctly
	@Test
	public void AddPosTest5() {
		list.add("beg");
		list.add(0,"oi");
		int newSize = list.size();
		int expected = 2;
		assertEquals("After calling add(0,\"oi\") on the a list with 1 element, the size should have "
				   + "increased to 2.", expected, newSize);
	}
	
	//tests to see if adding explicitly at the beginning changes the list correctly
	@Test
	public void AddPosTest6() {
		list.add("beg");
		list.add(0,"oi");
		String newSize = list.toStringLinked().substring(55);
		String expected ="(oi, beg)";
		assertEquals("After calling add(0,\"oi\") on the a list with 1 element, oi should have been "
				   + "added to the beginning.", expected, newSize);
	}
	
	
	//tests to see if negative indices throw exceptions
	@Test (expected = IndexOutOfBoundsException.class)
	public void AddPosTest7() {
		list.add(-1,"oi");
	}
	
	//tests to see if large indices throw exceptions
	@Test (expected = IndexOutOfBoundsException.class)
	public void AddPosTest8() {
		list.add(1,"oi");
	}
	
	//checks to see if there is no null pointer exception for add
	@Test
	public void AddPosTest9() {
		list.add(0, null);
	}
	
	//tests to see if an empty collection causes a null pointer exception
	@Test (expected = NullPointerException.class)
	public void AddAllTest1() {
		list.addAll(null);
	}
	
	//tests to see if addAll changes the empty list correctly
	@Test
	public void AddAllTest2() {
		list.addAll(otherList);
		String newSize = list.toStringLinked().substring(55);
		String expected ="(-, u, i, u, hg, -)";
		assertEquals("After calling addAll(otherList) on the empty list, the list should "
				   + "be equal to otherList.", expected, newSize);
	}
	
	//tests to see if addAll changes the empty list size correctly
	@Test
	public void AddAllTest3() {
		list.addAll(otherList);
		int newSize = list.size();
		int expected = otherList.size();
		assertEquals("After calling addAll(otherList) on the empty list, the list should "
				   + "have the same size as otherList.", expected, newSize);
	}
	
	//tests to see if addAll changes the list correctly
	@Test
	public void AddAllTest4() {
		list.add("poi");
		list.addAll(otherList);
		String newSize = list.toStringLinked().substring(55);
		String expected ="(poi, -, u, i, u, hg, -)";
		assertEquals("After calling addAll(otherList) on the list with 1 element, the list should "
				   + "have otherList at the end.", expected, newSize);
	}
	
	//tests to see if addAll changes the list size correctly
	@Test
	public void AddAllTest5() {
		list.add("poi");
		list.addAll(otherList);
		int newSize = list.size();
		int expected = otherList.size() + 1;
		assertEquals("After calling addAll(otherList) on the list with 1 element, the list should "
				   + "have increased in size by otherList's size.", expected, newSize);
	}
	
	//tests the return value of addAll(pos) (false)
	@Test
	public void AddAllTest6() {
		boolean added = list.addAll(new AdaptiveList<String>());
		assertFalse("After adding an empty list, addAll should return false", added);
	}
	
	//tests the return value of addAll(pos) (true)
	@Test
	public void AddAllTest7() {
		boolean added = list.addAll(otherList);
		assertTrue("After adding a non-empty list, addAll should return true.", added);
	}
	
	//tests to see if addAll() changes the list correctly with the list itself
	@Test
	public void AddAllTest8() {
		list.add("poi");
		list.addAll(list);
		String newSize = list.toStringLinked().substring(55);
		String expected ="(poi, poi)";
		assertEquals("After calling addAll(list) on the list with 1 element, the list should "
				   + "have added itself to the end.", expected, newSize);
	}
	
	//tests to see if addAll() changes the list correctly with the list itself
	@Test
	public void AddAllTest9() {
		list.add("poi");
		list.add("lol");
		list.addAll(0, list);
		String newSize = list.toStringLinked().substring(55);
		String expected ="(poi, lol, poi, lol)";
		assertEquals("After calling addAll(list) on the list with 2 elements, the list should "
				   + "have added itself at the end.", expected, newSize);
	}
	
	//tests to see if an empty collection causes a null pointer exception
	@Test (expected = NullPointerException.class)
	public void AddAllPosTest3() {
		list.addAll(null);
	}	
	
	//tests to see if addAll(pos) changes the list size correctly
	@Test
	public void AddAllPosTest1() {
		list.add("poi");
		list.addAll(0, otherList);
		int newSize = list.size();
		int expected = otherList.size() + 1;
		assertEquals("After calling addAll(0, otherList) on the list with 1 element, the list should "
				   + "have increased in size by otherList's size.", expected, newSize);
	}
	
	//tests to see if addAll(pos) changes the list correctly
	@Test
	public void AddAllPosTest2() {
		list.add("poi");
		list.addAll(0, otherList);
		String newSize = list.toStringLinked().substring(55);
		String expected ="(-, u, i, u, hg, -, poi)";
		assertEquals("After calling addAll(0,otherList) on the list with 1 element, the list should "
				   + "have added otherList at the front.", expected, newSize);
	}
	
	//tests to see if addAll(pos) changes the list correctly
	@Test
	public void AddAllPosTest4() {
		list.add("poi");
		list.add("ouy");
		list.addAll(1, otherList);
		String newSize = list.toStringLinked().substring(55);
		String expected ="(poi, -, u, i, u, hg, -, ouy)";
		assertEquals("After calling addAll(1,otherList) on the list with 2 elements, the list should "
				   + "have added otherList in the middle.", expected, newSize);
	}
	//tests to see if addAll(pos) changes the list correctly
	@Test
	public void AddAllPosTest5() {
		list.add("poi");
		list.add("ouy");
		list.addAll(1, otherList);
		int newSize = list.size();
		int expected = otherList.size() + 2;
		assertEquals("After calling addAll(1,otherList) on the list with 2 elements, the list should "
				   + "have increased in size by otherList.size().", expected, newSize);
	}
	
	//tests for negative index exceptions
	@Test (expected = IndexOutOfBoundsException.class)
	public void AddAllPosTest6() {
		list.addAll(-9, otherList);
	}
	
	//tests for large index exceptions
	@Test (expected = IndexOutOfBoundsException.class)
	public void AddAllPosTest7() {
		list.addAll(9, otherList);
	}
	
	//tests the return value of addAll(pos) (false)
	@Test
	public void AddAllPosTest8() {
		boolean added = list.addAll(0, new AdaptiveList<String>());
		assertFalse("After adding an empty list, addAll should return false.", added);
	}
	
	//tests the return value of addAll(pos) (true)
	@Test
	public void AddAllPosTest9() {
		boolean added = list.addAll(0, otherList);
		assertTrue("After adding a non-empty list, addAll should return true.", added);
	}
	
	//tests to see if addAll(pos) changes the list correctly with the list itself at the beginning
//	@Test
//	public void AddAllPosTest10() {
//		list.add("poi");
//		list.addAll(0, list);
//		String newSize = list.toStringLinked().substring(55);
//		String expected ="(poi, poi)";
//		assertEquals("After calling addAll(0, list) on the list with 1 element, the list should "
//				   + "have added itself at the front.", expected, newSize);
//	}
	
	//tests to see if addAll(pos) changes the list correctly with the list itself in the middle
//	@Test
//	public void AddAllPosTest11() {
//		list.add("poi");
//		list.add("981");
//		list.addAll(1, list);
//		String newSize = list.toStringLinked().substring(55);
//		String expected ="(poi, poi, 981, 981)";
//		assertEquals("After calling addAll(1, list) on the list with 1 element, the list should "
//				   + "have added itself in the middle.", expected, newSize);
//	}
	
	//no clear tests needed (already provided)
	
	//tests if the empty list contains anything
	@Test
	public void ContainsTest1() {
		boolean not = list.contains("879");
		assertFalse("An empty list should always return false for contains.", not);
	}
	
	//tests if contains works correctly for an immediate add before
	@Test
	public void ContainsTest2() {
		list.add("879");
		boolean not = list.contains("879");
		assertTrue("After adding(879), the list should contain 879.", not);
	}
	
	//tests if contains works correctly with more than one add before
	@Test
	public void ContainsTest3() {
		list.add("879");
		list.add("567");
		list.add("4");
		boolean not = list.contains("879");
		assertTrue("After adding(879), even before other add calls, the list should contain 879.", not);
	}
	
	//tests if contains returns false with more than one add before
	@Test
	public void ContainsTest4() {
		list.add("879");
		list.add("567");
		list.add("4");
		boolean not = list.contains("o");
		assertFalse("After adding a bunch of non \"o\" elements, the list should not contain \"o\".", not);
	}
	
	//tests if contains returns false with no null elements
	@Test
	public void ContainsTest5() {
		list.add("879");
		list.add("567");
		list.add("4");
		boolean not = list.contains(null);
		assertFalse("After adding a bunch of non-null elements, the list should not contain null. The "
				  + "code should also not throw a null pointer exception in this case.", not);
	}
	
	//tests if contains returns true with a null element
	@Test
	public void ContainsTest6() {
		list.add("879");
		list.add(null);
		list.add("4");
		boolean not = list.contains(null);
		assertTrue("After adding a null element, the list should contain null. The "
				  + "code should also not throw a null pointer exception in this case.", not);
	}
	
	//tests if contains returns true with the null elements appearing multiple times
	@Test
	public void ContainsTest7() {
		list.add("879");
		list.add(null);
		list.add(null);
		boolean not = list.contains(null);
		assertTrue("After adding some null elements, the list should contain null. The "
				  + "code should also not throw a null pointer exception in this case.", not);
	}
	
	//tests if contains returns true with the elements appearing multiple times
	@Test
	public void ContainsTest8() {
		list.add("879");
		list.add("879");
		list.add("4");
		boolean not = list.contains("879");
		assertTrue("After adding more than one of an element, the list should contain that element.", not);
	}
	
	//tests to see if containsAll works correctly with an empty list
	@Test
	public void ContainsAllTest1() {
		boolean not = list.containsAll(otherList);
		assertFalse("An empty list should always return false for containsAll.", not);
	}
	
	//tests to see if containsAll works correctly after adding the otherList
	@Test
	public void ContainsAllTest2() {
		list.addAll(otherList);
		boolean not = list.containsAll(otherList);
		assertTrue("A list that just added otherList should return true for containsAll.", not);
	}
	
	//tests to see if containsAll works correctly after adding more than the otherList
	@Test
	public void ContainsAllTest3() {
		list.add("y");
		list.addAll(otherList);
		boolean not = list.containsAll(otherList);
		assertTrue("A list that just added otherList should return true for containsAll.", not);
	}
	
	//tests to see if containsAll works correctly after removing a duplicate element from otherList
	@Test
	public void ContainsAllTest4() {
		list.addAll(otherList);
		list.remove(0);
		boolean not = list.containsAll(otherList);
		assertTrue("A list that just added otherList but removed a duplicate element should "
				  + "return true for containsAll.", not);
	}
	
	//tests to see if containsAll works correctly after removing a unique element from otherList
	@Test
	public void ContainsAllTest5() {
		list.addAll(otherList);
		list.remove(2);
		boolean not = list.containsAll(otherList);
		assertFalse("A list that just added otherList but removed a unique element should "
				  + "return false for containsAll.", not);
	}
	
	//tests to see if containsAll works correctly with itself (as an empty list)
	@Test
	public void ContainsAllTest6() {
		boolean not = list.containsAll(list);
		assertTrue("A list should always contain itself.", not);
	}
	
	//tests to see if containsAll works correctly with itself (a non-empty list)
	@Test
	public void ContainsAllTest7() {
		list.add("8");
		list.add("cat");
		boolean not = list.containsAll(list);
		assertTrue("A list should always contain itself.", not);
	}
	
	//tests to see if containsAll throws exceptions correctly
	@Test (expected = NullPointerException.class)
	public void ContainsAllTest8() {
		list.containsAll(null);
	}
	
	//no equals tests needed (already provided)
	
	//tests to see if get returns the correct value
	@Test
	public void GetTest1() {
		list.add("poi");
		String listWord = list.get(0);
		String expected = "poi";
		assertEquals("A list that just added poi should return poi at index 0.", expected, listWord);
	}
	
	//tests to see if get uses the array correctly
	@Test
	public void GetTest2() {
		list.add("poi");
		list.get(0);
		String toString = list.toStringArray().substring(49);
		String expected = "[poi]";
		assertEquals("The array should be updated after a get call.", expected, toString);
		assertTrue("The array should be updated after a get call.", list.getarrayUTD());
	}
	
	//tests to see if get does not change the linked list
	@Test
	public void GetTest3() {
		list.add("poi");
		list.get(0);
		String toString = list.toStringLinked().substring(55);
		String expected = "(poi)";
		assertEquals("The linked list should not be modified by a get call.", expected, toString);
		assertTrue("The linkedList should be still be updated after a get call.", list.getlinkedUTD());
	}
	
	//tests for negative index exception
	@Test (expected = IndexOutOfBoundsException.class)
	public void GetTest4() {
		list.get(-5);
	}
	
	//tests for negative index exception
	@Test (expected = IndexOutOfBoundsException.class)
	public void GetTest5() {
		list.get(5);
	}
	
	//no explicit tests needed for getlinkedUTD, getArrayUTD, or hashCode (already provided)
	
	//tests to see if indexOf returns the correct value for values not in the list
	@Test
	public void IndexOfTest1() {
		int not = list.indexOf("anything");
		int expected = -1;
		assertEquals("The empty list should always return -1 in indexOf.", expected, not);
	}
	
	//tests to see if indexOf returns the correct value for a match
	@Test
	public void IndexOfTest2() {
		list.add("anything");
		int not = list.indexOf("anything");
		int expected = 0;
		assertEquals("IndexOf should return the zero-based index of the requested element "
					+ "on the one element list.", expected, not);
	}
	
	//tests to see if indexOf is case-sensitive (uses the equals method)
	@Test
	public void IndexOfTest3() {
		list.add("Anything");
		int not = list.indexOf("anything");
		int expected = -1;
		assertEquals("IndexOf should use the equals methods (case-sensitive for Strings). So a list with "
					+ "just \"anything\" should not find \"Anything\" at that index.", expected, not);
	}
	
	//tests to see if indexOf returns the first correct value
	@Test
	public void IndexOfTest4() {
		list.add("anything");
		list.add("anything");
		int not = list.indexOf("anything");
		int expected = 0;
		assertEquals("IndexOf should return the first index of the requested element.", expected, not);
	}
	
	//tests to see if indexOf returns the first correct value
	@Test
	public void IndexOfTest5() {
		list.add("a");
		list.add("b");
		list.add("anything");
		list.add("c");
		list.add("anything");
		int not = list.indexOf("anything");
		int expected = 2;
		assertEquals("IndexOf should return the first index of the requested element.", expected, not);
	}
	
	//tests to see if no null pointer exceptions are thrown
	@Test
	public void IndexOfTest6() {
		list.indexOf(null);
	}
	
	//tests to see if isEmpty returns true correctly
	@Test
	public void IsEmptyTest1() {
		boolean isEmpty = list.isEmpty();
		assertTrue("The list should initially be empty.", isEmpty);
	}
	
	//tests to see if isEmpty returns true correctly
	@Test
	public void IsEmptyTest2() {
		list.add("something");
		boolean isEmpty = list.isEmpty();
		assertFalse("The list should not be empty after adding an element.", isEmpty);
	}
	
	//tests to see if isEmpty returns true correctly
	@Test
	public void IsEmptyTest3() {
		list.add("s");
		list.remove(0);
		boolean isEmpty = list.isEmpty();
		assertTrue("The list should be empty after adding and then removing the one element.", isEmpty);
	}
	
	//not tests needed for iterator, listiterator, listiterator(pos) (already provided)
	
	//tests to see if lastIndexOf returns the correct value for values not in the list
	@Test
	public void LastIndexOfTest1() {
		int not = list.lastIndexOf("anything");
		int expected = -1;
		assertEquals("The empty list should always return -1 in indexOf.", expected, not);
	}

	// tests to see if lastIndexOf returns the correct value for a match
	@Test
	public void LastIndexOfTest2() {
		list.add("anything");
		int not = list.lastIndexOf("anything");
		int expected = 0;
		assertEquals(
				"LastIndexOf should return the zero-based index of the requested element " + "on the one element list.",
				expected, not);
	}

	// tests to see if lastIndexOf is case-sensitive (uses the equals method)
	@Test
	public void LastIndexOfTest3() {
		list.add("Anything");
		int not = list.lastIndexOf("anything");
		int expected = -1;
		assertEquals("LastIndexOf should use the equals methods (case-sensitive for Strings). So a list with "
				+ "just \"anything\" should not find \"Anything\" at that index.", expected, not);
	}

	// tests to see if lastIndexOf returns the last correct value
	@Test
	public void LastIndexOfTest4() {
		list.add("anything");
		list.add("anything");
		int not = list.lastIndexOf("anything");
		int expected = 1;
		assertEquals("lastIndexOf should return the last index of the requested element.", expected, not);
	}

	// tests to see if lastIndexOf returns the last correct value
	@Test
	public void LastIndexOfTest5() {
		list.add("a");
		list.add("b");
		list.add("anything");
		list.add("c");
		list.add("anything");
		int not = list.lastIndexOf("anything");
		int expected = 4;
		assertEquals("LastIndexOf should return the first index of the requested element.", expected, not);
	}

	// tests to see if no null pointer exceptions are thrown
	@Test
	public void LastIndexOfTest6() {
		list.lastIndexOf(null);
	}

	// tests to see if remove(pos) works correctly on the list for a valid index
	@Test
	public void RemovePosTest1() {
		list.add("poi");
		list.remove(0);
		String newSize = list.toStringLinked().substring(55);
		String expected = "()";
		assertEquals("A list that just added and then removed poi should be empty.", expected, newSize);
	}

	// tests to see if remove(pos) changes the list size correctly for a valid
	// index
	@Test
	public void RemovePosTest2() {
		list.add("poi");
		list.remove(0);
		int newSize = list.size();
		int expected = 0;
		assertEquals("A list that just added and then removed poi should be have size 0.", expected, newSize);
	}

	// tests to see if remove(pos) works correctly on the list for a non-zero
	// valid index
	@Test
	public void RemovePosTest3() {
		list.add("poi");
		list.add("uty");
		list.remove(1);
		String newSize = list.toStringLinked().substring(55);
		String expected = "(poi)";
		assertEquals(
				"A list of size 2 that just removed the second element should have only the " + "first element left.",
				expected, newSize);
	}

	// tests to see if remove(pos) works correctly on the list size for a
	// non-zero valid index
	@Test
	public void RemovePosTest4() {
		list.add("poi");
		list.add("uty");
		list.remove(1);
		int newSize = list.size();
		int expected = 1;
		assertEquals("A list of size 2 that just removed the second element should have size 1.", expected, newSize);
	}

	// tests to see if remove(pos) works correctly with null values
	@Test
	public void RemovePosTest5() {
		list.add(null);
		list.add("uty");
		list.remove(0);
		String newSize = list.toStringLinked().substring(55);
		String expected = "(uty)";
		assertEquals(
				"A list that just removed the null first element should have only the "
						+ "second element left. There should also be no NullPointerExceptions here.",
				expected, newSize);
	}

	// tests to see if remove(pos) works correctly on the list for a non-zero
	// valid index
	@Test
	public void RemovePosTest6() {
		list.add(null);
		list.add(null);
		list.remove(0);
		int newSize = list.size();
		int expected = 1;
		assertEquals("Null items should be treated just like other items in this implementation.", expected, newSize);
	}

	// tests to see if negative indices cause exceptions
	@Test(expected = IndexOutOfBoundsException.class)
	public void RemovePosTest7() {
		list.remove(-8);
	}

	// tests to see if large indices cause exceptions
	@Test(expected = IndexOutOfBoundsException.class)
	public void RemovePosTest8() {
		list.remove(1);
	}

	// tests to see if remove(obj) works correctly on the list for a valid index
	@Test
	public void RemoveObjTest1() {
		list.add("poi");
		list.remove("poi");
		String newSize = list.toStringLinked().substring(55);
		String expected = "()";
		assertEquals("A list that just added and then removed poi should be empty.", expected, newSize);
	}

	// tests to see if remove(obj) changes the list size correctly for a valid
	// index
	@Test
	public void RemoveObjTest2() {
		list.add("poi");
		list.remove(0);
		int newSize = list.size();
		int expected = 0;
		assertEquals("A list that just added and then removed poi should be have size 0.", expected, newSize);
	}

	// tests to see if remove(obj) works correctly on the list for a non-zero
	// valid index
	@Test
	public void RemoveObjTest3() {
		list.add("poi");
		list.add("uty");
		list.remove("uty");
		String newSize = list.toStringLinked().substring(55);
		String expected = "(poi)";
		assertEquals("A list of size 2 that just removed the second element explicitly should have only the "
				+ "first element left.", expected, newSize);
	}

	// tests to see if remove(obj) works correctly on the list size for a
	// non-zero valid index
	@Test
	public void RemoveObjTest4() {
		list.add("poi");
		list.add("uty");
		list.remove("uty");
		int newSize = list.size();
		int expected = 1;
		assertEquals("A list of size 2 that just removed the second element should have size 1.", expected, newSize);
	}

	// tests to see if remove(obj) works correctly with null values
	@Test
	public void RemoveObjTest5() {
		list.add(null);
		list.add("uly");
		list.add(null);
		list.remove(null);
		String newSize = list.toStringLinked().substring(55);
		String expected = "(uly, -)";
		assertEquals("A list that just removed the null first element should have only the "
				+ "second element left. There should also be no NullPointerExceptions here. Additionally, "
				+ "the first null should be removed here.", expected, newSize);
	}

	// tests to see if remove works correctly on the list size with many null values
	@Test
	public void RemoveObjTest6() {
		list.add(null);
		list.add(null);
		list.remove(null);
		int newSize = list.size();
		int expected = 1;
		assertEquals("Null items should be treated just like other items in this implementation.", expected, newSize);
	}

	// tests to see if removeAll works correctly after adding otherList (list)
	@Test
	public void RemoveAllTest1() {
		list.addAll(otherList);
		list.removeAll(otherList);
		int newSize = list.size();
		int expected = 0;
		assertEquals("After adding and removing the same list, the size should be 0.", expected, newSize);
	}

	// tests to see if removeAll works correctly after adding otherList (size)
	@Test
	public void RemoveAllTest2() {
		list.addAll(otherList);
		list.removeAll(otherList);
		String newSize = list.toStringLinked().substring(55);
		String expected = "()";
		assertEquals("After adding and removing the same list, the list should be empty.", expected, newSize);
	}

	// tests to see if removeAll works correctly after adding otherList and another element in otherList (size)
	@Test
	public void RemoveAllTest3() {
		list.add(null);
		list.addAll(otherList);
		list.removeAll(otherList);
		int newSize = list.size();
		int expected = 0;
		assertEquals("After adding and removing the same list to a list with an element in otherList, the size "
				+ "should be 0.", expected, newSize);
	}
	// tests to see if removeAll works correctly after adding otherList and another element in otherList (size)
	@Test
	public void RemoveAllTest4() {
		list.add(null);
		list.addAll(otherList);
		list.removeAll(otherList);
		String newSize = list.toStringLinked().substring(55);
		String expected = "()";
		assertEquals("After adding and removing the same list with an element in otherList, "
				+ "the list should be empty.", expected, newSize);
	}

	// tests to see if removeAll works correctly after adding otherList and another element not in otherList (size)
	@Test
	public void RemoveAllTest5() {
		list.add("something else");
		list.addAll(otherList);
		list.removeAll(otherList);
		int newSize = list.size();
		int expected = 1;
		assertEquals("After adding and removing the same list to a list with an element not in otherList, the size "
				+ "should be 1.", expected, newSize);
	}
	
	// tests to see if removeAll works correctly after adding otherList and another element not in otherList (list)
	@Test
	public void RemoveAllTest6() {
		list.add("something else");
		list.addAll(otherList);
		list.removeAll(otherList);
		String newSize = list.toStringLinked().substring(55);
		String expected = "(something else)";
		assertEquals("After adding and removing the same list to a list with an element not in otherList, "
				+ "the element not in the otherList should be the only element.", expected, newSize);
	}
	
	//tests if removeAll works correctly when used on itself (list)
	@Test
	public void RemoveAllTest7() {
		list.removeAll(list);
		String newSize = list.toStringLinked().substring(55);
		String expected = "()";
		assertEquals("After removing itself, the list should be empty.", expected, newSize);
	}
	
	//tests if removeAll works correctly when used on itself (size)
	@Test
	public void RemoveAllTest8() {
		list.removeAll(list);
		int newSize = list.size();
		int expected = 0;
		assertEquals("After removing itself, the list should be empty.", expected, newSize);
	}
	
	//tests if removeAll throws the correct NullPointerException
	@Test (expected = NullPointerException.class)
	public void RemoveAllTest9() {
		list.removeAll(null);
	}
	
	//tests if removeAll returns the correct values (true)
	@Test
	public void RemoveAllTest10() {
		list.add("something");
		boolean removed = list.removeAll(list);
		assertTrue("After removing at least one element, removeAll should return true.", removed);
	}
	
	//tests if removeAll returns the correct values (false)
	@Test
	public void RemoveAllTest11() {
		boolean removed = list.removeAll(new AdaptiveList<>());
		assertFalse("After removing nothing, addAll should return false.", removed);
	}
	
	// tests to see if retainAll works correctly after adding otherList (list)
	@Test
	public void RetainAllTest1() {
		list.addAll(otherList);
		list.retainAll(otherList);
		int newSize = list.size();
		int expected = otherList.size();
		assertEquals("After adding and then retaining the list, the size should be the same as "
				+ "the added list's size.", expected, newSize);
	}

	// tests to see if retainAll works correctly after adding otherList (size)
	@Test
	public void RetainAllTest2() {
		list.addAll(otherList);
		list.retainAll(otherList);
		String newSize = list.toStringLinked().substring(55);
		String expected = "(-, u, i, u, hg, -)";
		assertEquals("After adding and retaining the same list, the list should be the same as the added list.", expected, newSize);
	}

	// tests to see if retainAll works correctly after adding otherList and another element in otherList (size)
	@Test
	public void RetainAllTest3() {
		list.add(null);
		list.addAll(otherList);
		list.retainAll(otherList);
		int newSize = list.size();
		int expected = otherList.size() + 1;
		assertEquals("After adding and retaining the same list to a list with an element in otherList, the size "
				+ "should be 1 greater than otherList's size.", expected, newSize);
	}
	// tests to see if retainAll works correctly after adding otherList and another element in otherList (size)
	@Test
	public void RetainAllTest4() {
		list.add(null);
		list.addAll(otherList);
		list.retainAll(otherList);
		String newSize = list.toStringLinked().substring(55);
		String expected = "(-, -, u, i, u, hg, -)";
		assertEquals("After adding and retaining the same list with an element in otherList, "
				+ "the list should have all of the elements that were added.", expected, newSize);
	}

	// tests to see if retainAll works correctly after adding otherList and another element not in otherList (size)
	@Test
	public void RetainAllTest5() {
		list.add("something else");
		list.addAll(otherList);
		list.retainAll(otherList);
		int newSize = list.size();
		int expected = otherList.size();
		assertEquals("After adding and removing the same list to a list with an element not in otherList, the size "
				+ "should be the same as otherList's size.", expected, newSize);
	}
	
	// tests to see if retainAll works correctly after adding otherList and another element not in otherList (list)
	@Test
	public void RetainAllTest6() {
		list.add("something else");
		list.addAll(otherList);
		list.retainAll(otherList);
		String newSize = list.toStringLinked().substring(55);
		String expected = "(-, u, i, u, hg, -)";
		assertEquals("After adding and removing the same list to a list with an element not in otherList, "
				+ "the element not in the otherList should have been removed.", expected, newSize);
	}
	
	//tests if retainAll works correctly when used on itself (list)
	@Test
	public void RetainAllTest7() {
		list.add("8");
		list.retainAll(list);
		String newSize = list.toStringLinked().substring(55);
		String expected = "(8)";
		assertEquals("After retaining itself, the list should not have changed.", expected, newSize);
	}
	
	//tests if retainAll works correctly when used on itself (size)
	@Test
	public void RetainAllTest8() {
		list.add("8");
		list.retainAll(list);
		int newSize = list.size();
		int expected = 1;
		assertEquals("After retaining itself, the list's size should not have changed.", expected, newSize);
	}
	
	//tests if retainAll throws the correct NullPointerException
	@Test (expected = NullPointerException.class)
	public void RetainAllTest9() {
		list.retainAll(null);
	}
	
	//tests if retainAll returns the correct values (false)
	@Test
	public void RetainAllTest10() {
		boolean removed = list.retainAll(list);
		assertFalse("After retainAll removes nothing, retainAll should return false.", removed);
	}
	
	//tests if retainAll returns the correct values (true)
	@Test
	public void RetainAllTest11() {
		list.add("something");
		boolean removed = list.retainAll(new AdaptiveList<>());
		assertTrue("After retainAll removes at least one element, retainAll should return true.", removed);
	}
	
	@Test
	public void SetTest1() {
		list.add("poi");
		list.set(0, "else");
		String listWord = list.get(0);
		String expected = "else";
		assertEquals("A list that just set index 0 to else should return else at index 0.", expected, listWord);
	}
	
	//tests to see if set changes the array correctly
	@Test
	public void SetTest2() {
		list.add("poi");
		list.set(0, "else");
		String toString = list.toStringArray().substring(49);
		String expected = "[else]";
		assertEquals("The array should be updated after a set call.", expected, toString);
		assertTrue("The array should be updated after a set call.", list.getarrayUTD());
	}
	
	//tests to see if set does not change the linked list and makes it out of date
	@Test
	public void SetTest3() {
		list.add("poi");
		list.set(0, "else");
		String toString = list.toStringLinked().substring(55);
		String expected = "(poi)";
		assertEquals("The linked list should be not be changed by a set call.", expected, toString);
		assertFalse("The linkedList should not still be updated after a set call.", list.getlinkedUTD());
	}
	
	//tests for negative index exception
	@Test (expected = IndexOutOfBoundsException.class)
	public void SetTest4() {
		list.set(-5, "else");
	}
	
	//tests for negative index exception
	@Test (expected = IndexOutOfBoundsException.class)
	public void SetTest5() {
		list.set(5, "something");
	}
	
	//tests for NullPointerExceptions
	@Test
	public void SetTest6() {
		list.add("o");
		list.set(0, null);
		String toString = list.toStringArray().substring(49);
		String expected = "[-]";
		assertEquals("The array should allow nulls in the list, just like in everywhere else.", expected, toString);
	}
	
	//tests to see if set changes the size
	@Test
	public void SetTest7() {
		list.add("o");
		list.set(0, "should not");
		int toString = list.size();
		int expected = 1;
		assertEquals("Set should never change the size of the list.", expected, toString);
	}
	
	// size has already been implicitly tested (frequently), and subList requires no testing
	
	//tests toArray with otherList as the contents
	@Test
	public void ToArrayDefaultTest1() {
		list.addAll(otherList);
		String result = Arrays.toString(list.toArray());
		String expected = "[null, u, i, u, hg, null]";
		assertEquals("This should return the list as an Object array.", expected, result);
	}
	
	//tests toArray with just one element
	@Test
	public void ToArrayDefaultTest2() {
		list.add("lol");
		String result = Arrays.toString(list.toArray());
		String expected = "[lol]";
		assertEquals("This should return the list as an Object array.", expected, result);
	}
	
	//checks if the array generated by toArray is not just the same as the internal array
	@Test
	public void ToArrayDefaultTest3() {
		list.addAll(otherList);
		Object[] toArray = list.toArray();
		boolean isEqual = toArray == list.theArray;
		assertFalse("The array generated by toArray should be distinct from the internal array.", isEqual);
	}
	
	//checks if toArray(array) works correctly for a long list (should be very similar)
	@Test
	public void ToArrayGenericTest1() {
		list.addAll(otherList);
		String[] other = {"pop", "ilk", "ouy"};
		String[] toArray = list.toArray(other);
		String result = Arrays.toString(list.toArray(other));
		String expected = "[null, u, i, u, hg, null]";
		assertEquals("This should return the list as an String array.", expected, result);
		assertFalse("The specified array and the new array should be distinct when the specified array"
					+ "is too small for the list.", other == toArray);
	}
	
	//checks if toArray(array) works correctly for a short list (should be very similar)
	@Test
	public void ToArrayGenericTest2() {
		list.add("o");
		String[] other = {"pop", "ilk", "ouy"};
		String[] toArray = list.toArray(other);
		String result = Arrays.toString(list.toArray(other));
		String expected = "[o, null, ouy]"; 
		assertEquals("This should return the list as an String array.", expected, result);
		assertTrue("The specified array and the new array should not be distinct when the specified array"
					+ "is larger than the list.", other == toArray);
	}
	
	//checks if toArray(array) works correctly for an equal size list (should be very similar)
	@Test
	public void ToArrayGenericTest3() {
		list.add("o");
		String[] other = {"pop"};
		String[] toArray = list.toArray(other);
		String result = Arrays.toString(list.toArray(other));
		String expected = "[o]";
		assertEquals("This should return the list as an String array.", expected, result);
		assertTrue("The specified array and the new array should not be distinct when the specified array"
					+ "is larger than the list.", other == toArray);
	}
	
	// rest of the methods were provided (toString and helper methods)

	//Iterator tests will appear in another file so the tests will be separated
}