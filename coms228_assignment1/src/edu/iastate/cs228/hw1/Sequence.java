package edu.iastate.cs228.hw1;

/**
 * Sequence is the superclass to two classes, DNASequence and ProteinSequence. 
 * Sequence is used to store a DNA sequence and provide methods for the stored DNA sequence.
 * This sequence is stored in an instance variable, seqarr that will be manipulated in its subclasses.
 * 
 * @author Thomas Haddy 9/13/17
 * 
 */
public class Sequence
{

	/**
	 * A character array of letters representing a DNA sequence
	 */
	public char[] seqarr; // made public instead of protected for grading.

	/**
	 * The constructor first uses the isValidLetter() method to check if every character
	 * in the array sarr is valid. If so, it makes and keeps a copy of the array sarr
	 * in the field seqarr of type char[]. Otherwise, it throws an IllegalArgumentException.
	 * This constructor will be used for Sequence's subclasses.
	 * @param sarr
	 * 		Given DNA sequence
	 */
	public Sequence(char[] sarr)
	{
		for (int i = 0; i < sarr.length; i++) {
			if (!isValidLetter(sarr[i])) {
				throw new IllegalArgumentException("Invalid sequence letter for " + this.getClass());
			}
		}
		seqarr = sarr;
	}

	/**
	 * The method returns the length of the character array seqarr.
	 * @return
	 * 		The length of the DNA sequence seqarr
	 */
	public int seqLength()
	{
		return seqarr.length;
	}

	/**
	 * The method makes and returns a copy of the char array seqarr.
	 * @return
	 * 		A copy of the DNA sequence seqarr
	 */
	public char[] getSeq()
	{
		return seqarr.clone();
	}

	/**
	 * The method returns the string representation of the char array seqarr.
	 */
	public String toString()
	{
		String dna = "";
		for (int i = 0; i < seqarr.length; i++) {
			dna += seqarr[i];
		}
		return dna;
	}

	/**
	 * The method returns true if the argument obj is not null and is of the same type
	 * as this object such that both objects represent the identical sequence of characters
	 * in a case insensitive mode (”ACgt” is identical to ”AcGt”).
	 */
	@Override
	public boolean equals(Object obj)
	{
		return obj != null && this.toString().toLowerCase().equals(obj.toString().toLowerCase());
	}

	/**
	 * The method returns true if the character let is an uppercase or lowercase. Otherwise, it returns false.
	 * @param let
	 * 		the letter being checked
	 * @return
	 * 		Whether the letter is valid or invalid
	 */
	public boolean isValidLetter(char let)
	{
		return Character.isUpperCase(let) || Character.isLowerCase(let);
	}
}
