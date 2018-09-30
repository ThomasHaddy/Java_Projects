package edu.iastate.cs228.hw1;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * JUnit for the ProteinSequence class
 * 
 * @author Thomas Haddy 9/13/17
 *
 */
public class ProteinSequenceTest {

	@Test
	public void testProteinSequenceConstructor() {

		char[] psarrTest0 = {'A', 'M', 'v', 't', 'T', 'A'};
		char[] psarrTest1 = {'u', 'Z', 'b', 'C', 'T', 'A'};
		char[] psarrTest2 = {'a', '&', ' ', 't', 'T', 'A'};

		//Normal case
		ProteinSequence seq0 = new ProteinSequence(psarrTest0);
		assertArrayEquals(seq0.getSeq(), psarrTest0);

		//Case 1 should throw exception (Incorrect letters)
		try {
			ProteinSequence seq1 = new ProteinSequence(psarrTest1);
			assertArrayEquals(seq1.getSeq(), psarrTest1);
		}
		catch (IllegalArgumentException e) {
			assertEquals(e.getClass(), IllegalArgumentException.class);
			assertEquals("Invalid sequence letter for class edu.iastate.cs228.hw1.ProteinSequence", e.getMessage());
		}
		//Case 2 should throw exception (Non-letters)
		try {
			ProteinSequence seq2 = new ProteinSequence(psarrTest2);
			assertArrayEquals(seq2.getSeq(), psarrTest2);
		}
		catch (IllegalArgumentException e) {
			assertEquals(e.getClass(), IllegalArgumentException.class);
			assertEquals("Invalid sequence letter for class edu.iastate.cs228.hw1.ProteinSequence", e.getMessage());
		}
	}

	@Test
	public void testIsValidLetter() {

		//Valid
		char[] psarrTest0 = {'A', 'M', 'v', 't', 'T', 'A'};
		ProteinSequence seq0 = new ProteinSequence(psarrTest0);
		for (int i = 0; i < psarrTest0.length; i++) {
			assertTrue(seq0.isValidLetter(psarrTest0[i]));
		}

		//Invalid
		ProteinSequence test = new ProteinSequence(psarrTest0);
		assertEquals(false, test.isValidLetter('Z'));
		assertEquals(false, test.isValidLetter('&'));
		assertEquals(false, test.isValidLetter('x'));
		assertEquals(false, test.isValidLetter(' '));
	}

}
