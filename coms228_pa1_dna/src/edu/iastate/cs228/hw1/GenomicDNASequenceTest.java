package edu.iastate.cs228.hw1;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * JUnit for the GenomicDNASequence class
 * 
 * @author Thomas Haddy 9/13/17
 *
 */
public class GenomicDNASequenceTest {

	@Test
	public void testGenomicDNASequenceConstructor() {

		char[] gdnaarrTest0 = {'a', 'A', 'c', 'C', 'g', 'G', 't', 'T'};
		char[] gdnaarrTest1 = {'Z', 'H', 'c', 'C', 'T', 'A'};
		char[] gdnaarrTest2 = {'1', '2', '!', 't', 'T', 'A'};

		//Case 0 normal
		GenomicDNASequence seq0 = new GenomicDNASequence(gdnaarrTest0);
		boolean[] iscodingCorrect = new boolean[seq0.iscoding.length];
		assertArrayEquals(seq0.getSeq(), gdnaarrTest0);
		assertArrayEquals(iscodingCorrect, seq0.iscoding);
		assertEquals(iscodingCorrect.length, seq0.iscoding.length);

		//Case 1 should throw exception (wrong letters)
		try {
			GenomicDNASequence seq1 = new GenomicDNASequence(gdnaarrTest1);
			assertArrayEquals(seq1.getSeq(), gdnaarrTest1);
		}
		catch (IllegalArgumentException e) {
			assertEquals(e.getClass(), IllegalArgumentException.class);
			assertEquals("Invalid sequence letter for class edu.iastate.cs228.hw1.GenomicDNASequence", e.getMessage());
		}
		//Case 2 should throw exception (numbers and symbols)
		try {
			GenomicDNASequence seq2 = new GenomicDNASequence(gdnaarrTest2);
			assertArrayEquals(seq2.getSeq(), gdnaarrTest2);
		}
		catch (IllegalArgumentException e) {
			assertEquals(e.getClass(), IllegalArgumentException.class);
			assertEquals("Invalid sequence letter for class edu.iastate.cs228.hw1.GenomicDNASequence", e.getMessage());
		}
	}

	@Test
	public void testMarkCoding() {

		char[] gdnaarrTest0 = {'a', 'A', 'c', 'C', 'g', 'G', 't', 'T'};
		GenomicDNASequence seq0 = new GenomicDNASequence(gdnaarrTest0);

		//Illegal case: first is negative
		try {
			seq0.markCoding(-1, 3);
		}
		catch(IllegalArgumentException e) {
			assertEquals(e.getClass(), IllegalArgumentException.class);
			assertEquals("Coding border is out of bound", e.getMessage());
		}
		//Illegal case: last is negative
		try {
			seq0.markCoding(3, -1);
		}
		catch(IllegalArgumentException e) {
			assertEquals(e.getClass(), IllegalArgumentException.class);
			assertEquals("Coding border is out of bound", e.getMessage());
		}
		//Illegal case: first is greater than seqLength()
		try {
			seq0.markCoding(10, 4);
		}
		catch(IllegalArgumentException e) {
			assertEquals(e.getClass(), IllegalArgumentException.class);
			assertEquals("Coding border is out of bound", e.getMessage());
		}
		//Illegal case: last is greater than seqLength()
		try {
			seq0.markCoding(4, 10);
		}
		catch(IllegalArgumentException e) {
			assertEquals(e.getClass(), IllegalArgumentException.class);
			assertEquals("Coding border is out of bound", e.getMessage());
		}

		//Case where first is greater than last
		char[] demoarr0 = {'A','A','T','G','C','C','A','G'};
		GenomicDNASequence demo0 = new GenomicDNASequence(demoarr0);
		boolean[] result0 = {false, true, true, true, true, true, true, false};
		demo0.markCoding(1, 6);

		assertArrayEquals(result0, demo0.iscoding);

		//Normal case
		char[] demoarr1 = {'A','A','T','G','C','C','A','G'};
		GenomicDNASequence demo1 = new GenomicDNASequence(demoarr1);
		boolean[] result1 = {false, true, true, true, true, true, true, false};
		demo1.markCoding(6, 1);

		assertArrayEquals(result1, demo1.iscoding);
	}

	@Test
	public void testExtractExons() {

		//Testing the example given
		char[] gdnaarrTest0 = {'A', 'A', 'T', 'G', 'C', 'C', 'A', 'G', 'T', 'C', 'A', 'G', 'C', 'A', 'T', 'A', 'G', 'C', 'G', 'T', 'A'};
		GenomicDNASequence demo = new GenomicDNASequence(gdnaarrTest0);
		demo.markCoding(1, 16);

		int[] exonarr = {1, 5, 8, 10, 13, 16};
		char[] expected = {'A', 'T', 'G', 'C', 'C', 'T', 'C', 'A', 'A', 'T', 'A', 'G'};
		char[] actual =  demo.extractExons(exonarr);

		assertArrayEquals(expected, actual);

		//Illegal case: exonpos[] length is 0
		try {
			int[] noLen = new int[0];
			demo.extractExons(noLen);
		}
		catch(IllegalArgumentException e) {
			assertEquals(e.getClass(), IllegalArgumentException.class);
			assertEquals("Empty array or odd number of array elements", e.getMessage());
		}
		//Illegal case: exonpos[] is odd
		try {
			int[] odd = new int[3];
			demo.extractExons(odd);
		}
		catch(IllegalArgumentException e) {
			assertEquals(e.getClass(), IllegalArgumentException.class);
			assertEquals("Empty array or odd number of array elements", e.getMessage());
		}
		//Illegal case: exonpos[] has element greater than or equal to seqLength()
		try {
			int[] toobig = {1, 5, 20};
			demo.extractExons(toobig);
		}
		catch(IllegalArgumentException e) {
			assertEquals(e.getClass(), IllegalArgumentException.class);
			assertEquals("Empty array or odd number of array elements", e.getMessage());
		}
		//Illegal case: exonpos[] has element less than 0
		try {
			int[] toosmall = {1, 5, -5};
			demo.extractExons(toosmall);
		}
		catch(IllegalArgumentException e) {
			assertEquals(e.getClass(), IllegalArgumentException.class);
			assertEquals("Empty array or odd number of array elements", e.getMessage());
		}
		//Illegal case: exonpos[] has element that is greater than its right neighbor
		try {
			int[] notascending = {1, 2, 3, 1};
			demo.extractExons(notascending);
		}
		catch(IllegalArgumentException e) {
			assertEquals(e.getClass(), IllegalArgumentException.class);
			assertEquals("Exon positions are not in order", e.getMessage());
		}
		//Illegal case: iscoding[] is false at some exonpos[] element
		try {
			int[] arr = {0, 2, 17, 18};
			demo.extractExons(arr);
		}
		catch(IllegalStateException e) {
			assertEquals(e.getClass(), IllegalStateException.class);
			assertEquals("Noncoding position is found", e.getMessage());
		}
	}

}
