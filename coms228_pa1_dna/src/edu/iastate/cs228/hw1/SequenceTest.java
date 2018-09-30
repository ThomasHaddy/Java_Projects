package edu.iastate.cs228.hw1;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * JUnit test for the Sequence class
 * 
 * @author Thomas Haddy 9/13/17
 *
 */
public class SequenceTest {

	@Test
	public void testSequenceConstructor() {

		char[] sarrTest0 = {'a', 'b', 'c', 't', 'T', 'A'};
		char[] sarrTest1 = {'a', ' ', '$', '!', 'T', 'A'};
		char[] sarrTest2 = {'1', '2', '7', 't', 'T', 'A'};

		//Case 0 normal
		Sequence seq0 = new Sequence(sarrTest0);
		assertArrayEquals(seq0.getSeq(), sarrTest0);

		//Case 1 should throw exception (symbols)
		try {
			Sequence seq1 = new Sequence(sarrTest1);
			assertArrayEquals(seq1.getSeq(), sarrTest1);
		}
		catch (IllegalArgumentException e) {
			assertEquals(e.getClass(), IllegalArgumentException.class);
			assertEquals("Invalid sequence letter for class edu.iastate.cs228.hw1.Sequence", e.getMessage());
		}
		//Case 2 should throw exception (numbers)
		try {
			Sequence seq2 = new Sequence(sarrTest2);
			assertArrayEquals(seq2.getSeq(), sarrTest2);
		}
		catch (IllegalArgumentException e) {
			assertEquals(e.getClass(), IllegalArgumentException.class);
			assertEquals("Invalid sequence letter for class edu.iastate.cs228.hw1.Sequence", e.getMessage());
		}
	}

	@Test
	public void testSeqLength() {

		char[] seqarrTest0 = {'a', 'b', 'c', 't', 'T', 'A'};
		Sequence seq0 = new Sequence(seqarrTest0);

		assertEquals(seqarrTest0.length, seq0.seqLength());
	}

	@Test
	public void testgetSeq() {

		char[] seqarrTest0 = {'a', 'b', 'c', 't', 'T', 'A'};
		Sequence seq0 = new Sequence(seqarrTest0);

		assertArrayEquals(seqarrTest0, seq0.getSeq());
	}

	@Test
	public void testToString() {

		char[] seqarrTest0 = {'a', 'b', 'c', 't', 'T', 'A'};
		Sequence seq0 = new Sequence(seqarrTest0);

		assertEquals("abctTA", seq0.toString());
	}

	@Test
	public void testEquals() {

		char[] seqarrTest0 = {'a', 'A', 'c', 'C', 'g', 'G', 't', 'T'};
		char[] seqarrTest1 = {'A', 'a', 'C', 'c', 'G', 'g', 'T', 't'};
		char[] seqarrTest2 = {'T', 'C', 'A', 'G', 'A', 'a', 'C', 'g'};
		char[] dnaarrTest0 = {'a', 'A', 'c', 'C', 'g', 'G', 't', 'T'};
		char[] dnaarrTest1 = {'A', 'a', 'c', 'C', 'g', 'G', 't', 'T'};

		Sequence seq0 = new Sequence(seqarrTest0);
		Sequence seq1 = new Sequence(seqarrTest1);
		Sequence seq2 = new Sequence(seqarrTest2);
		DNASequence d0 = new DNASequence(dnaarrTest0);
		DNASequence d1 = new DNASequence(dnaarrTest1);
		Sequence noSeq = null;

		//Testing equals in Sequence with case insensitivity
		assertTrue(seq0.equals(seq1));

		//Testing equals in a subclass of Sequence
		assertTrue(d0.equals(d1));

		//Testing equals when an object is null
		assertEquals(false, seq0.equals(noSeq));

		//Testing a false case
		assertEquals(false, seq0.equals(seq2));
	}

	@Test
	public void testIsValidLetter() {

		char[] sarrTest0 = {'c'};
		Sequence seq0 = new Sequence(sarrTest0);
		//Lowercase valid
		assertEquals(true, seq0.isValidLetter('c'));
		//Uppercase valid
		assertEquals(true, seq0.isValidLetter('C'));
		//Symbol invalid
		assertEquals(false, seq0.isValidLetter('%'));
		//Number invalid
		assertEquals(false, seq0.isValidLetter('1'));
	}

}
