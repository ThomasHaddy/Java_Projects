package edu.iastate.cs228.hw1;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * JUnit for the DNASequence class
 * 
 * @author Thomas Haddy 9/13/17
 *
 */
public class DNASequenceTest {

	@Test
	public void testDNASequenceConstructor() {

		char[] dnaarrTest0 = {'a', 'A', 'c', 'C', 'g', 'G', 't', 'T'};
		char[] dnaarrTest1 = {'Z', 'H', 'c', 'C', 'T', 'A'};
		char[] dnaarrTest2 = {'1', '2', '!', 't', 'T', 'A'};

		//Case 0 normal
		DNASequence seq0 = new DNASequence(dnaarrTest0);
		assertArrayEquals(seq0.getSeq(), dnaarrTest0);

		//Case 1 should throw exception (wrong letters)
		try {
			DNASequence seq1 = new DNASequence(dnaarrTest1);
			assertArrayEquals(seq1.getSeq(), dnaarrTest1);
		}
		catch (IllegalArgumentException e) {
			assertEquals(e.getClass(), IllegalArgumentException.class);
			assertEquals("Invalid sequence letter for class edu.iastate.cs228.hw1.DNASequence", e.getMessage());
		}
		//Case 2 should throw exception (numbers and symbols)
		try {
			DNASequence seq2 = new DNASequence(dnaarrTest2);
			assertArrayEquals(seq2.getSeq(), dnaarrTest2);
		}
		catch (IllegalArgumentException e) {
			assertEquals(e.getClass(), IllegalArgumentException.class);
			assertEquals("Invalid sequence letter for class edu.iastate.cs228.hw1.DNASequence", e.getMessage());
		}
	}

	@Test
	public void testIsValidLetter() {

		//True cases
		char[] dnaarr0 = {'a', 'A', 'c', 'C', 'g', 'G', 't', 'T'};
		DNASequence d = new DNASequence(dnaarr0);

		for (int i = 0; i < dnaarr0.length; i++) {
			assertTrue(d.isValidLetter(dnaarr0[i]));
		}

		//False cases
		assertEquals(false, d.isValidLetter('Z'));
		assertEquals(false, d.isValidLetter('x'));
		assertEquals(false, d.isValidLetter('%'));
		assertEquals(false, d.isValidLetter(' '));

	}

	@Test
	public void testGetReverseCompSeq() {

		char[] dnaarr0 = {'T', 'C', 'A', 'G', 'A', 'T', 'G', 'G', 'A', 'C', 'A', 'A', 'G', 'G', 'C'};
		DNASequence d = new DNASequence(dnaarr0);

		char[] reversedComp = {'G', 'C', 'C', 'T', 'T', 'G', 'T', 'C', 'C', 'A', 'T', 'C', 'T', 'G', 'A'};
		assertArrayEquals(reversedComp, d.getReverseCompSeq());
	}

	@Test
	public void testreverseComplement() {

		//Uppercase
		char[] dnaarr0 = {'T', 'C', 'A', 'G', 'A', 'T', 'G', 'G', 'A', 'C', 'A', 'A', 'G', 'G', 'C'};

		DNASequence d = new DNASequence(dnaarr0);
		d.reverseComplement();

		char[] reversedComp = {'G', 'C', 'C', 'T', 'T', 'G', 'T', 'C', 'C', 'A', 'T', 'C', 'T', 'G', 'A'};

		assertArrayEquals(reversedComp, d.seqarr);

		//Lowercase
		char[] dnaarr1 = {'t', 'c', 'a', 'g', 'a', 't', 'g', 'g', 'a', 'c', 'a', 'a', 'g', 'g', 'c'};

		DNASequence d1 = new DNASequence(dnaarr1);
		d1.reverseComplement();

		char[] reversedComp1 = {'g', 'c', 'c', 't', 't', 'g', 't', 'c', 'c', 'a', 't', 'c', 't', 'g', 'a'};

		assertArrayEquals(reversedComp1, d1.seqarr);
	}

}
