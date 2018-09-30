package edu.iastate.cs228.hw1;


/**
 * The public class GenomicDNASequence extends the class DNASequence.
 * This class is used to extract a number of exons from seqarr.
 * 
 * @author Thomas Haddy
 *
 */
public class GenomicDNASequence extends DNASequence
{

	/**
	 * Representation of which indexes of the DNA sequence will be further manipulated  
	 */
	public boolean[] iscoding; // made public instead of private for grading.

	/**
	 * If the gdnaarr has a character on which the method isValidLetter()
	 * returns false, then it throws an IllegalArgumentException. Otherwise,
	 * the constructor saves a copy of gdnaarr in the field of its
	 * superclass Sequence. And it creates a boolean array of the same length as gdnaarr,
	 * saves its reference in the field iscoding, and sets the boolean array to false at each index.
	 * @param gdnaarr
	 * 		Given genomic DNA sequence
	 */
	public GenomicDNASequence(char[] gdnaarr)
	{
		super(gdnaarr);
		iscoding = new boolean[gdnaarr.length];
	}

	/**
	 * The method sets the start and end indexes of seqarr that will be used to extract its exons.
	 * If first is greater than last, the method reverse-complements the DNA sequence and modifies 
	 * first and last to fit the new sequence 
	 * Lastly, it sets the boolean array iscoding to true at each index between first and last inclusive.
	 * @param first
	 * 		Start index inclusive
	 * @param last
	 * 		End index inclusive
	 */
	public void markCoding(int first, int last)
	{
		int slen = seqLength(); 
		if (first < 0 || last < 0 || first >= slen || last >= slen) {
			throw new IllegalArgumentException("Coding border is out of bound"); 
		}

		if (first > last) {
			reverseComplement();
			first = slen - 1 - first;
			last = slen - 1 - last;
		}

		for (int i = first; i <= last; i++) {
			iscoding[i] = true;
		}
	}

	/**
	 * The integer array argument exonpos is used to specify the start and end positions
	 * of every coding exon in the genomic sequence.
	 * 
	 * The method throws an IllegalArgumentException exception if the length of exonpos is 0 or an odd number. 
	 * It throws an IllegalArgumentException exception if an element in the exonpos is less than 0
	 * or is greater than or equal to the length returned by the seqLength() method.
	 * It throws an IllegalArgumentException if an element in exonpos is greater than its right neighbor element.
	 * It throws an IllegalStateException if the boolean array iscoding is false at a coding exon position.
	 * 
	 * Otherwise, the method takes all the coding exons specified by the array exonpos,
	 * concatenates them in order, and returns the resulting sequence in a new character array. 
	 * Note that the array length is the length of the resulting sequence.
	 * @param exonpos
	 * 		Given start and end positions of every coding exon
	 * @return
	 * 		Concatenation of the extracted exon arrays in order of the genomic sequence
	 */
	public char[] extractExons(int[] exonpos)
	{
		if (exonpos.length==0 || exonpos.length % 2 == 1) {
			throw new IllegalArgumentException("Empty array or odd number of array elements");
		}

		for (int i = 0; i < exonpos.length; i++) {
			if (exonpos[i] < 0 || exonpos[i] >= seqLength()) {
				throw new IllegalArgumentException("Exon position is out bound");
			}
		}

		for (int i = 0; i < exonpos.length-1; i++) {
			if (exonpos[i] > exonpos[i+1]) {
				throw new IllegalArgumentException("Exon positions are not in order");
			}
		}

		for (int i = exonpos[0]; i < exonpos[exonpos.length-1]; i++) {
			if (!iscoding[i]) {
				throw new IllegalStateException("Noncoding position is found");
			}
		}

		//Gets the length of the new array. Has to be even
		int concatExonLen = 0; 
		for (int i = 0; i < exonpos.length; i+=2) {
			concatExonLen += (exonpos[i+1] - exonpos[i]) + 1;
		}

		int startIndex = exonpos[0];
		int endIndex = exonpos[1];
		char[] concatExon = new char[concatExonLen];
		int exonLoc = 0;
		int concatExonPos = 0;

		while (exonLoc < exonpos.length) {
			startIndex = exonpos[exonLoc];
			endIndex = exonpos[exonLoc+1];
			for (int i = startIndex; i <= endIndex; i++) {
				concatExon[concatExonPos] = seqarr[i];
				concatExonPos++;
			}
			exonLoc += 2;	
		}
		return concatExon;
	}
}

