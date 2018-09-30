package edu.iastate.cs228.hw3;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class reverseTest {

	private AdaptiveList<String> list; //list used for all testing
	
	//initialize the commonly used instance variables
	@Before
	public void init() {
		list = new AdaptiveList<>();
	}
	
	//tests the reverse
		@Test
		public void ReverseTest1() {
			list.add("oi");
			list.add("beg");
			list.add("eeg");
			list.reverse();
			String result = list.toStringArray().substring(49); //get the list at the end
			String expected = "[eeg, beg, oi]";
			assertEquals("After calling reverse(), \"eeg, beg, oi\" should be new list.", expected, result);
		}
		
		@Test
		public void ReverseTest2() {
			list.add("oi");
			list.add("beg");
			list.reverse();
			String result = list.toStringArray().substring(49); //get the list at the end
			String expected = "[beg, oi]";
			assertEquals("After calling reverse(), \"beg, oi\" should be new list.", expected, result);
		}
		
		@Test
		public void ReverseTest3() {
			list.add("oi");
			list.add("beg");
			list.add("oi");
			list.add("beg");
			list.reverse();
			String result = list.toStringArray().substring(49); //get the list at the end
			String expected = "[beg, oi, beg, oi]";
			assertEquals("After calling reverse(), \"beg, oi, beg, oi\" should be new list.", expected, result);
		}
		
		@Test
		public void ReverseTest4() {
			list.add("oi");
			list.add("beg");
			list.add("lol");
			list.add("koi");
			list.add("hi");
			list.add("poi");
			list.add("boi");
			list.add("mee");
			list.reverse();
			String result = list.toStringArray().substring(49); //get the list at the end
			String expected = "[mee, boi, poi, hi, koi, lol, beg, oi]";
			assertEquals("After calling reverse(), \"mee, boi, poi, hi, koi, lol, beg, oi\" should be new list.", expected, result);
		}
		
		

}
