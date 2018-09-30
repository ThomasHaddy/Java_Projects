package edu.iastate.cs228.hw1;

/**
 * The public class DNASequence extends the class Sequence. DNASequence manipulates seqarr in Sequence
 * by checking if it is a valid DNA sequence, and it provides methods to reverse-complement the sequence.
 * 
 * @author Thomas Haddy 9/13/17
 *
 */
public class DNASequence extends Sequence
{

	/**
	 * If dnaarr has a character on which the method isValidLetter()
	 * returns false, then it throws an IllegalArgumentException.
	 * Otherwise, the constructor saves a copy of dnaarr in the field of its superclass.
	 * @param dnaarr
	 * 		A character array representing a DNA sequence
	 */
	public DNASequence(char[] dnaarr)
	{
		super(dnaarr);
	}

	/**
	 * The method returns true if the character argument is equal to one of the eight
	 * characters ’a’, ’A’, ’c’, ’C’, ’g’, ’G’, ’t’ and ’T’. Otherwise, it returns false. This
	 * method overrides the one in its superclass.
	 */
	@Override
	public boolean isValidLetter(char let)
	{
		if (!Character.isAlphabetic(let)) {
			return false;
		}
		return let=='a' || let=='c' || let=='g' || let=='t' || let=='A' || let=='C' || let=='G' || let=='T';
	}

	/**
	 * The method produces the reverse complement of the DNA sequence saved in the
	 * char array seqarr, and returns a char array with the resulting sequence. DNA is
	 * double-stranded with one strand being the reverse complement of the other strand.
	 * The reverse complement of a DNA sequence is obtained by reversing the sequence
	 * (turning it by 180 degrees or making the right end left) and complementing each base
	 * (changing A, C, G and T into T, G, C, and A, respectively and changing a, c, g, and
	 * t into t, g, c and a, respectively).
	 * @return
	 * 		The reverse complement of the DNA sequence seqarr
	 */
	public char[] getReverseCompSeq()
	{
		//This block reverses the DNA
		char[] newDNA = seqarr.clone();
		for (int i = 0; i < newDNA.length / 2; i++) {
			char tempLetter = newDNA[i];
			newDNA[i] = newDNA[newDNA.length - i - 1];
			newDNA[newDNA.length - i - 1] = tempLetter;
		}

		//This block compliments the reversed DNA
		for (int i = 0; i < newDNA.length; i++) {
			switch(newDNA[i]) {
			case 'A': newDNA[i] = 'T'; break;
			case 'C': newDNA[i] = 'G'; break;
			case 'G': newDNA[i] = 'C'; break;
			case 'T': newDNA[i] = 'A'; break;
			case 'a': newDNA[i] = 't'; break;
			case 'c': newDNA[i] = 'g'; break;
			case 'g': newDNA[i] = 'c'; break;
			case 't': newDNA[i] = 'a'; break;
			}
		}
		return newDNA;
	}

	/**
	 * The method calls getReverseCompSeq() and saves the result in a temporary array
	 * and then copies the array back into the char array seqarr.
	 */
	public void reverseComplement()
	{
		char[] tempArr = getReverseCompSeq();
		seqarr = tempArr;
	}
}
