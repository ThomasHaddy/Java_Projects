package edu.iastate.cs228.hw3;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

//import temp.AdaptiveList;

/**
 * A class full of tests for this assignment's iterator. The tests are arranged by logical ordering
 * (next and previous first, and so on), except for the constructor tests, which happen first.
 * Tests for the list itself appear in a different file. I would recommend that
 * you make sure that you pass the other tests before you use these ones.
 * @author Luke Schoeberle
 */
public class AdaptiveListIteratorTests {
	
	private AdaptiveList<String> list; //list used for all testing
	private ListIterator<String> iterAtBegin;
	private ListIterator<String> iterAtMiddle;
	private ListIterator<String> iterAtEnd;
	
	//initialize the commonly used instance variables
	@Before
	public void init() {
		list = new AdaptiveList<>(Arrays.asList(null,"u","i","u","hg", null)); //initialized to this random list
		iterAtBegin = list.listIterator();
		iterAtMiddle = list.listIterator(2);
		iterAtEnd = list.listIterator(6);
	}
	
	//tests the next state of the initial iterator at the beginning
	@Test
	public void DefaultConstructorTest1() {
		int nextIndex = iterAtBegin.nextIndex();
		int expected = 0;
		assertEquals("The iterator should begin at position 0 for the default constructor (nextIndex = 0).", expected, nextIndex);
		assertTrue("The iterator should initially have a next value.", iterAtBegin.hasNext());
	}
	
	//tests the previous state of the initial iterator at the beginning
	@Test
	public void DefaultConstructorTest2() {
		int nextIndex = iterAtBegin.previousIndex();
		int expected = -1;
		assertEquals("The iterator should begin at position 0 for the default constructor (prevIndex = -1).", expected, nextIndex);
		assertFalse("The iterator should initially not have a previous value.", iterAtBegin.hasPrevious());
	}
	
	//tests the next state of the middle iterator at the beginning
	@Test
	public void MiddleConstructorTest1() {
		int nextIndex = iterAtMiddle.nextIndex();
		int expected = 2;
		assertEquals("The iterator should begin at position 2 for the default constructor (nextIndex = 2).", expected, nextIndex);
		assertTrue("The iterator should initially have a next value.", iterAtMiddle.hasNext());
	}
	
	//tests the previous state of the middle iterator at the beginning
	@Test
	public void MiddleConstructorTest2() {
		int nextIndex = iterAtMiddle.previousIndex();
		int expected = 1;
		assertEquals("The iterator should begin at position 2 for the default constructor (prevIndex = 1).", expected, nextIndex);
		assertTrue("The iterator should initially have a previous value.", iterAtMiddle.hasPrevious());
	}
	
	//tests the next state of the end iterator at the beginning
	@Test
	public void EndConstructorTest1() {
		int nextIndex = iterAtEnd.nextIndex();
		int expected = 6;
		assertEquals("The iterator should begin at position 2 for the default constructor (nextIndex = 6).", expected, nextIndex);
		assertFalse("The iterator should initially not have a next value.", iterAtEnd.hasNext());
	}
	
	//tests the previous state of the end iterator at the beginning
	@Test
	public void EndConstructorTest2() {
		int nextIndex = iterAtEnd.previousIndex();
		int expected = 5;
		assertEquals("The iterator should begin at position 2 for the default constructor (prevIndex = 5).", expected, nextIndex);
		assertTrue("The iterator should initially have a previous value.", iterAtEnd.hasPrevious());
	}
	
	//tests the return value of next with a null value (initialIterator)
	@Test
	public void NextTest1() {
		String next1 = iterAtBegin.next();
		String expected1 = null;
		assertEquals("The next value of the initial iterator should be null.", expected1, next1);
	}
	
	//tests the return value of next with a non-null value (initialIterator)
	@Test
	public void NextTest2() {
		iterAtBegin.next();
		String next2 = iterAtBegin.next();
		String expected2 = "u";
		assertEquals("The next value after that for the initial iterator should be \"u\".", expected2, next2);
	}
	
	//tests the return value of next again (middleIterator)
	@Test
	public void NextTest3() {
		String next1 = iterAtMiddle.next();
		String expected1 = "i";
		assertEquals("The next value of the middle iterator should be i.", expected1, next1);
	}
	
	//tests the return value of next once more (middleIterator)
	@Test
	public void NextTest4() {
		iterAtMiddle.next();
		String next2 = iterAtMiddle.next();
		String expected2 = "u";
		assertEquals("The next value after that for the middleIterator should be \"u\".", expected2, next2);
	}
	
	//tests the exception from next (using endIterator)
	@Test (expected = NoSuchElementException.class)
	public void NextTest5() {
		iterAtEnd.next();
	}
	
	//tests how next affects hasNext (middleIterator)
	@Test
	public void NextTest6() {
		iterAtMiddle.next();
		iterAtMiddle.next();
		iterAtMiddle.next();
		boolean hasNext1 = iterAtMiddle.hasNext();
		iterAtMiddle.next();
		boolean hasNext2 = iterAtMiddle.hasNext();
		assertTrue("This one is not at the end, so it should be true.", hasNext1);
		assertFalse("This one is at the end, so it should be false.", hasNext2);		
	}
	
	//tests how next affects nextIndex (middleIterator)
	@Test
	public void NextTest7() {
		iterAtMiddle.next();
		iterAtMiddle.next();
		iterAtMiddle.next();
		int nextIndex1 = iterAtMiddle.nextIndex();
		iterAtMiddle.next();
		int nextIndex2 = iterAtMiddle.nextIndex();
		assertEquals("This one is one from the end, so it should be 5.", 5, nextIndex1);
		assertEquals("This one is at the end, so it should be 6.", 6, nextIndex2);		
	}
	
	//tests to see if hasNext works correctly with a loop (initialIterator)
	@Test
	public void HasNextTest() {
		while(iterAtBegin.hasNext())
			iterAtBegin.next(); //makes sure that no exceptions are called by next as a result
	}
	
	//tests to see if nextIndex works correctly with a loop (initialIterator)
	@Test
	public void NextIndexTest() {
		while(iterAtBegin.nextIndex() < 6)
			iterAtBegin.next(); //makes sure that no exceptions are called by next as a result
	}
	
	//hasNext and nextIndex have already been tested implicitly, so no more tests are needed there
	
	//tests the return value of previous with a null value (endIterator)
	@Test
	public void PrevTest1() {
		String next1 = iterAtEnd.previous();
		String expected1 = null;
		assertEquals("The previous value of the end iterator should be null.", expected1, next1);
	}
	
	//tests the return value of next with a non-null value (initialIterator)
	@Test
	public void PrevTest2() {
		iterAtEnd.previous();
		String next2 = iterAtEnd.previous();
		String expected2 = "hg";
		assertEquals("The previous value after that for the end iterator should be \"hg\".", expected2, next2);
	}
	
	//tests the return value of previous again (middleIterator)
	@Test
	public void PrevTest3() {
		String next1 = iterAtMiddle.previous();
		String expected1 = "u";
		assertEquals("The previous value of the middle iterator should be u.", expected1, next1);
	}
	
	//tests the return value of previous once more (middleIterator)
	@Test
	public void PrevTest4() {
		iterAtMiddle.previous();
		String next2 = iterAtMiddle.previous();
		String expected2 = null;
		assertEquals("The previous value after that for the middle iterator should be null.", expected2, next2);
	}
	
	//tests the exception from next (using initialIterator)
	@Test (expected = NoSuchElementException.class)
	public void PrevTest5() {
		iterAtBegin.previous();
	}
	
	//tests how previous affects hasPrevious (middleIterator)
	@Test
	public void PrevTest6() {
		iterAtMiddle.previous();
		boolean hasNext1 = iterAtMiddle.hasPrevious();
		iterAtMiddle.previous();
		boolean hasNext2 = iterAtMiddle.hasPrevious();
		assertTrue("This one is not at the front end, so it should be true.", hasNext1);
		assertFalse("This one is at the front end, so it should be false.", hasNext2);		
	}
	
	//tests how previous affects previousIndex (middleIterator)
	@Test
	public void PrevTest7() {
		iterAtMiddle.previous();
		int nextIndex1 = iterAtMiddle.previousIndex();
		iterAtMiddle.previous();
		int nextIndex2 = iterAtMiddle.previousIndex();
		assertEquals("This one is not at the front end, so it should be 0.", 0, nextIndex1);
		assertEquals("This one is at the front end, so it should be -1.", -1, nextIndex2);		
	}
	
	//tests to see if hasPrevious works correctly with a loop (endIterator)
	@Test
	public void HasPreviousTest() {
		while(iterAtEnd.hasPrevious())
			iterAtEnd.previous(); //makes sure that no exceptions are called by previous as a result
	}
	
	//tests to see if previousIndex works correctly with a loop (endIterator)
	@Test
	public void PreviousIndexTest() {
		while(iterAtEnd.previousIndex() >= 0)
			iterAtEnd.previous(); //makes sure that no exceptions are called by previous as a result
	}
	
	//hasPrevious and previousIndex have already been tested implicitly, so no more tests are needed there
	
	//tests if add changes the list correctly (initialIterator)
	@Test
	public void AddTest1() {
		iterAtBegin.add("Begin");
		String toString = list.toStringLinked().substring(55);
		String expected = "(Begin, -, u, i, u, hg, -)";
		assertEquals("The add method should add Begin before the cursor.", expected, toString);
	}
	
	//tests if add changes the list correctly after two calls (initialIterator)
	@Test
	public void AddTest2() {
		iterAtBegin.add("Begin");
		iterAtBegin.add("Next");
		String toString = list.toStringLinked().substring(55);
		String expected = "(Begin, Next, -, u, i, u, hg, -)";
		assertEquals("The add method should add begin and then Next before the cursor.", expected, toString);
	}
	
	//tests if add changes the list correctly after two calls with a next in between (initialIterator)
	@Test
	public void AddTest3() {
		iterAtBegin.add("Begin");
		iterAtBegin.next();
		iterAtBegin.add("Next");
		String toString = list.toStringLinked().substring(55);
		String expected = "(Begin, -, Next, u, i, u, hg, -)";
		assertEquals("The add method should add Begin, go past the null, and then add Next before the "
					+ "cursor.", expected, toString);
	}
	
	//tests if add changes the list correctly at the end (endIterator)
	@Test
	public void AddTest4() {
		iterAtEnd.add("Begin");
		String toString = list.toStringLinked().substring(55);
		String expected = "(-, u, i, u, hg, -, Begin)";
		assertEquals("The add method should add Begin at the end.", expected, toString);
	}
	
	//tests if add changes the list correctly at the end twice (endIterator)
	@Test
	public void AddTest5() {
		iterAtEnd.add("Begin");
		iterAtEnd.add("Last");
		String toString = list.toStringLinked().substring(55);
		String expected = "(-, u, i, u, hg, -, Begin, Last)";
		assertEquals("The add method should add Begin at the end and then add Last also.", expected, toString);
	}
	
	//tests if add changes the list correctly with a null value (middleIterator)
	@Test
	public void AddTest6() {
		iterAtMiddle.add(null);
		String toString = list.toStringLinked().substring(55);
		String expected = "(-, u, -, i, u, hg, -)";
		assertEquals("The add method should add null values just like other values.", expected, toString);
	}
	
	//tests if add increases the value of nextIndex (middleIterator) (so the same value would be returned by next)
	@Test
	public void AddTest7() {
		iterAtMiddle.add("Something");
		int nextIndex = iterAtMiddle.nextIndex();
		int expected = 3;
		assertEquals("The nextIndex should be increased by one as a result of an add call.", expected, nextIndex);
	}
	
	//tests if add increases the value of previousIndex (middleIterator) (so the same value would be returned by next)
	@Test
	public void AddTest8() {
		iterAtMiddle.add("Something");
		int nextIndex = iterAtMiddle.previousIndex();
		int expected = 2;
		assertEquals("The previousIndex should be increased by one as a result of an add call.", expected, nextIndex);
	}
	
	//tests if add does not change the result of next (initialIterator)
	@Test
	public void AddTest9() {
		String initialNext = iterAtBegin.next();
		iterAtBegin.previous();
		iterAtBegin.add("Nothing");
		String finalNext = iterAtBegin.next();
		assertEquals("An add call should not change the result of next.", initialNext, finalNext);
	}
	
	//tests if add does change the result of previous (endIterator)
	@Test
	public void AddTest10() {
		String initialPrev = iterAtEnd.previous();
		iterAtEnd.next();
		iterAtEnd.add("Nothing");
		String finalPrev = iterAtEnd.previous();
		assertNotSame("An add call should change the result of previous.", initialPrev, finalPrev);
	}
	
	//tests if add does not fail after remove
	@Test
	public void AddTest11() {
		iterAtEnd.previous();
		iterAtEnd.remove();
		iterAtEnd.add("Nothing");
	}
	
	//tests if add does not fail after set
	@Test
	public void AddTest12() {
		iterAtEnd.previous();
		iterAtEnd.set("p");
		iterAtEnd.add("Nothing");
	}
	
	//tests if remove changes the list correctly after one next call (initialIterator)
	@Test
	public void RemoveTest1() {
		iterAtBegin.next();
		iterAtBegin.remove();
		String toString = list.toStringLinked().substring(55);
		String expected = "(u, i, u, hg, -)";
		assertEquals("The remove method should remove the null item that was just passed over (by next or previous).", expected, toString);
	}
	
	//tests if remove changes the list correctly after one next and one previous call (initialIterator)
	@Test
	public void RemoveTest2() {
		iterAtBegin.next();
		iterAtBegin.previous();
		iterAtBegin.remove();
		String toString = list.toStringLinked().substring(55);
		String expected = "(u, i, u, hg, -)";
		assertEquals("The remove method should remove the null item that was just passed over (by next or previous).", expected, toString);
	}
	
	//tests if remove changes the list correctly after two next calls (initialIterator)
	@Test
	public void RemoveTest3() {
		iterAtBegin.next();
		iterAtBegin.next();
		iterAtBegin.remove();
		String toString = list.toStringLinked().substring(55);
		String expected = "(-, i, u, hg, -)";
		assertEquals("The remove method should remove the u that was just passed over (next or previous).", expected, toString);
	}
	
	//tests if remove changes the list correctly after two next and one previous call (initialIterator)
	@Test
	public void RemoveTest4() {
		iterAtBegin.next();
		iterAtBegin.next();
		iterAtBegin.previous();
		iterAtBegin.remove();
		String toString = list.toStringLinked().substring(55);
		String expected = "(-, i, u, hg, -)";
		assertEquals("The remove method should remove the u item that was just passed over (next or previous).", expected, toString);
	}
	
	//tests if remove changes the list correctly after one previous call (endIterator)
	@Test
	public void RemoveTest5() {
		iterAtEnd.previous();
		iterAtEnd.remove();
		String toString = list.toStringLinked().substring(55);
		String expected = "(-, u, i, u, hg)";
		assertEquals("The remove method should remove the null item that was just passed over (next or previous).", expected, toString);
	}
	
	//tests if remove changes the list correctly after two previous calls (endIterator)
	@Test
	public void RemoveTest6() {
		iterAtEnd.previous();
		iterAtEnd.previous();
		iterAtEnd.remove();
		String toString = list.toStringLinked().substring(55);
		String expected = "(-, u, i, u, -)";
		assertEquals("The remove method should remove the hg that was just passed over (next or previous).", expected, toString);
	}
	
	//tests if remove changes the list correctly after two previous calls and one next call (endIterator)
	@Test
	public void RemoveTest7() {
		iterAtEnd.previous();
		iterAtEnd.previous();
		iterAtEnd.next();
		iterAtEnd.remove();
		String toString = list.toStringLinked().substring(55);
		String expected = "(-, u, i, u, -)";
		assertEquals("The remove method should remove the hg that was just passed over (next or previous).", expected, toString);
	}
	
	//tests if remove changes the list correctly after a previous call, remove, add, next, and another remove (endIterator)
	@Test
	public void RemoveTest8() {
		iterAtEnd.previous();
		iterAtEnd.remove();
		iterAtEnd.add("End");
		iterAtEnd.previous();
		iterAtEnd.remove();
		String toString = list.toStringLinked().substring(55);
		String expected = "(-, u, i, u, hg)";
		assertEquals("The remove method should work after a call to next, after add.", expected, toString);
	}
	
	//tests if remove changes the list correctly after a previous call, remove, next, and another remove (endIterator)
	@Test
	public void RemoveTest9() {
		iterAtEnd.previous();
		iterAtEnd.set("o");
		iterAtEnd.remove();
		iterAtEnd.previous();
		iterAtEnd.remove();
		String toString = list.toStringLinked().substring(55);
		String expected = "(-, u, i, u)";
		assertEquals("The remove method should work after a call to set, even with no next in between.", expected, toString);
	}
	
	//tests if remove changes the list correctly after a three add calls, next, and remove (middleIterator)
	@Test
	public void RemoveTest10() {
		iterAtMiddle.add("1");
		iterAtMiddle.add("2");
		iterAtMiddle.add("3");
		iterAtMiddle.next();
		iterAtMiddle.remove();
		String toString = list.toStringLinked().substring(55);
		String expected = "(-, u, 1, 2, 3, u, hg, -)";
		assertEquals("The remove method should work after a call to next, even after three add calls.", expected, toString);
	}
	
	//tests if remove fails without any calls to next or previous
	@Test (expected = IllegalStateException.class)
	public void RemoveTest11() {
		iterAtMiddle.remove();
	}
	
	//tests if remove fails after calling it twice in a row
	@Test (expected = IllegalStateException.class)
	public void RemoveTest12() {
		iterAtMiddle.next();
		iterAtMiddle.remove();
		iterAtMiddle.remove(); //nope
	}
	
	//tests if remove fails after calling it directly after add
	@Test (expected = IllegalStateException.class)
	public void RemoveTest13() {
		iterAtMiddle.next();
		iterAtMiddle.add("p");
		iterAtMiddle.remove(); //nope
	}
	
	//tests if remove still works after a call to set
	@Test
	public void RemoveTest14() {
		iterAtMiddle.next();
		iterAtMiddle.set("00");
		iterAtMiddle.remove(); //should be fine
	}
	
	//tests if set changes the list correctly after one next call (initialIterator)
	@Test
	public void SetTest1() {
		iterAtBegin.next();
		iterAtBegin.set("P");
		String toString = list.toStringLinked().substring(55);
		String expected = "(P, u, i, u, hg, -)";
		assertEquals("The set method should change item that was just passed over to P (by next or previous).", expected, toString);
	}
	
	//tests if set changes the list correctly after one next call and one previous call (initialIterator)
	@Test
	public void SetTest2() {
		iterAtBegin.next();
		iterAtBegin.previous();
		iterAtBegin.set("P");
		String toString = list.toStringLinked().substring(55);
		String expected = "(P, u, i, u, hg, -)";
		assertEquals("The set method should change the null item that was just passed over to P (by next or previous).", expected, toString);
	}
	
	//tests if set changes the list correctly after two next calls (initialIterator)
	@Test
	public void SetTest3() {
		iterAtBegin.next();
		iterAtBegin.next();
		iterAtBegin.set("P");
		String toString = list.toStringLinked().substring(55);
		String expected = "(-, P, i, u, hg, -)";
		assertEquals("The set method should change the u that was just passed over to P (by next or previous).", expected, toString);
	}
	
	//tests if set changes the list correctly after two next calls and a previous call (initialIterator)
	@Test
	public void SetTest4() {
		iterAtBegin.next();
		iterAtBegin.next();
		iterAtBegin.previous();
		iterAtBegin.set("P");
		String toString = list.toStringLinked().substring(55);
		String expected = "(-, P, i, u, hg, -)";
		assertEquals("The set method should change the u that was just passed over to P (by next or previous).", expected, toString);
	}
	
	//tests if set changes the list correctly after one previous call (endIterator)
	@Test
	public void SetTest5() {
		iterAtEnd.previous();
		iterAtEnd.set("P");
		String toString = list.toStringLinked().substring(55);
		String expected = "(-, u, i, u, hg, P)";
		assertEquals("The set method should change the null item that was just passed over to P (by next or previous).", expected, toString);
	}
	
	//tests if set changes the list correctly after one previous call and one next call (endIterator)
	@Test
	public void SetTest6() {
		iterAtEnd.previous();
		iterAtEnd.next();
		iterAtEnd.set("P");
		String toString = list.toStringLinked().substring(55);
		String expected = "(-, u, i, u, hg, P)";
		assertEquals("The set method should change the null item that was just passed over to P (by next or previous).", expected, toString);
	}
	
	//tests if set changes the list correctly after two previous calls (endIterator)
	@Test
	public void SetTest7() {
		iterAtEnd.previous();
		iterAtEnd.previous();
		iterAtEnd.set("P");
		String toString = list.toStringLinked().substring(55);
		String expected = "(-, u, i, u, P, -)";
		assertEquals("The set method should change the hg that was just passed over to P (by next or previous).", expected, toString);
	}
	
	//tests if set changes the list correctly after two previous calls and a next call (endIterator)
	@Test
	public void SetTest8() {
		iterAtEnd.previous();
		iterAtEnd.previous();
		iterAtEnd.next();
		iterAtEnd.set("P");
		String toString = list.toStringLinked().substring(55);
		String expected = "(-, u, i, u, P, -)";
		assertEquals("The set method should change the hg that was just passed over to P (by next or previous).", expected, toString);
	}
	
	//tests if set changes the list correctly after a previous, set, add, previous, set, and remove calls (endIterator)
	@Test
	public void SetTest9() {
		iterAtEnd.previous();
		iterAtEnd.set("P");
		iterAtEnd.add("p");
		iterAtEnd.previous();
		iterAtEnd.set("o");
		iterAtEnd.remove();
		String toString = list.toStringLinked().substring(55);
		String expected = "(-, u, i, u, hg, P)";
		assertEquals("The set method should not fail after a previous/next call.", expected, toString);
	}
	
	//tests if set changes the list correctly after a previous, and 3 set calls (endIterator)
	@Test
	public void SetTest10() {
		iterAtEnd.previous();
		iterAtEnd.set("P");
		iterAtEnd.set("o");
		iterAtEnd.set("last");
		String toString = list.toStringLinked().substring(55);
		String expected = "(-, u, i, u, hg, last)";
		assertEquals("The set method can be called multiple times consecutively.", expected, toString);
	}
	
	//tests if set changes the list correctly after a previous, 3 set calls, previous, add, next, and set again. (endIterator)
	@Test
	public void SetTest11() {
		iterAtEnd.previous();
		iterAtEnd.set("P");
		iterAtEnd.set("o");
		iterAtEnd.set("last");
		iterAtEnd.previous();
		iterAtEnd.add("I");
		iterAtEnd.next();
		iterAtEnd.set("TU");
		String toString = list.toStringLinked().substring(55);
		String expected = "(-, u, i, u, I, TU, last)";
		assertEquals("The set method should work with the rest of the methods also.", expected, toString);
	}
	
	//tests if set fails without any calls to next or previous
	@Test (expected = IllegalStateException.class)
	public void SetTest12() {
		iterAtMiddle.set("p");
	}
	
	//tests if set succeeds when called twice in a row
	@Test 
	public void SetTest13() {
		iterAtMiddle.next();
		iterAtMiddle.set("o");
		iterAtMiddle.set("lol"); //should be good
	}
	
	//tests if set fails after calling it directly after add
	@Test (expected = IllegalStateException.class)
	public void SetTest14() {
		iterAtMiddle.next();
		iterAtMiddle.add("p");
		iterAtMiddle.set("p"); //nope
	}
	
	//tests if set fails after a call to remove
	@Test (expected = IllegalStateException.class)
	public void SetTest15() {
		iterAtMiddle.next();
		iterAtMiddle.remove(); 
		iterAtMiddle.set("00"); //should not work
	}
	
	//List tests appear in the other file
}