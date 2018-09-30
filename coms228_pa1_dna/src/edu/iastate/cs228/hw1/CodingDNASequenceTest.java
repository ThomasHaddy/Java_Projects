package edu.iastate.cs228.hw1;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * JUnit for the CodingDNASequence class
 * 
 * @author Thomas Haddy 9/13/17
 *
 */
public class CodingDNASequenceTest {

	@Test
	public void testCodingDNASequenceConstructor() {

		char[] cdnaarrTest0 = {'a', 'A', 'c', 'C', 'g', 'G', 't', 'T'};
		char[] cdnaarrTest1 = {'Z', 'H', 'c', 'C', 'T', 'A'};
		char[] cdnaarrTest2 = {'1', '2', '!', 't', 'T', 'A'};

		//Case 0 normal
		CodingDNASequence seq0 = new CodingDNASequence(cdnaarrTest0);
		assertArrayEquals(seq0.getSeq(), cdnaarrTest0);

		//Case 1 should throw exception (wrong letters)
		try {
			CodingDNASequence seq1 = new CodingDNASequence(cdnaarrTest1);
			assertArrayEquals(seq1.getSeq(), cdnaarrTest1);
		}
		catch (IllegalArgumentException e) {
			assertEquals(e.getClass(), IllegalArgumentException.class);
			assertEquals("Invalid sequence letter for class edu.iastate.cs228.hw1.CodingDNASequence", e.getMessage());
		}
		//Case 2 should throw exception (numbers and symbols)
		try {
			CodingDNASequence seq2 = new CodingDNASequence(cdnaarrTest2);
			assertArrayEquals(seq2.getSeq(), cdnaarrTest2);
		}
		catch (IllegalArgumentException e) {
			assertEquals(e.getClass(), IllegalArgumentException.class);
			assertEquals("Invalid sequence letter for class edu.iastate.cs228.hw1.CodingDNASequence", e.getMessage());
		}
	}

	@Test
	public void testCheckStartCodon() {

		//Case where seqarr is < 3
		char[] badLen = {'A', 'T'};
		CodingDNASequence seq0 = new CodingDNASequence(badLen);

		assertEquals(false, seq0.checkStartCodon());

		//Out of order case
		char[] badarr = {'A', 'T', 'C'};
		CodingDNASequence seq1 = new CodingDNASequence(badarr);

		assertEquals(false, seq1.checkStartCodon());

		//Normal case
		char[] arr = {'A', 'T', 'G', 'G', 'T'};
		CodingDNASequence seq2 = new CodingDNASequence(arr);

		assertTrue(seq2.checkStartCodon());
	}

	@Test
	public void testTranslate() {

		//Illegal case: checkStartCodon() returned false
		try {
			char[] badarr = {'A', 'T', 'C', 'G', 'T'};
			CodingDNASequence seq0 = new CodingDNASequence(badarr);
			seq0.translate();
		}
		catch(RuntimeException e) {
			assertEquals(e.getClass(), RuntimeException.class);
			assertEquals("No start codon", e.getMessage());
		}

		//Normal case with extra codon strip of 2 at end
		char[] dna = {'A', 'T', 'G', 'G', 'T'};
		CodingDNASequence seq1 = new CodingDNASequence(dna);
		char[] expected = {'M'};

		assertArrayEquals(expected, seq1.translate());

		//Normal case with '$' getting returned (TAA) which ends method
		char[] dna$ = {'A', 'T', 'G', 'T', 'A', 'A', 'T', 'G', 'C'};
		CodingDNASequence seq2 = new CodingDNASequence(dna$);

		assertArrayEquals(expected, seq2.translate());

		//Normal case
		char[] norm = {'A', 'T', 'G', 'G', 'T', 'A'};
		CodingDNASequence seq3 = new CodingDNASequence(norm);
		char[] normExpected = {'M', 'V'};

		assertArrayEquals(normExpected, seq3.translate());
	}
}
