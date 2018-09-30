package edu.iastate.cs228.hw1;

/**
 * The public class ProteinSequence extends the class Sequence.
 * This class checks if a protein sequence contains the correct letters.
 * 
 * @author Thomas Haddy 9/13/17
 *
 */
public class ProteinSequence extends Sequence
{

	/**
	 * The super constructor checks if psarr is valid. If it is false, it throws an IllegalArgumentException.
	 * Otherwise the constructor saves a copy of psarr in the field of its superclass Sequence.
	 * @param psarr
	 * 		Given protein sequence
	 */
	public ProteinSequence(char[] psarr)
	{
		super(psarr);
	}

	/**
	 * This method returns true if the character aa is not the following:
	 * {B, b, J, j, O, o, U, u, X, x, Z, z}. Otherwise it returns false.
	 */
	@Override
	public boolean isValidLetter(char aa)
	{
		if (!Character.isAlphabetic(aa)) {
			return false;
		}
		return !(aa=='B' || aa=='b' || aa=='J' || aa=='j' || aa=='O' || aa=='o' || aa=='U' || aa=='u' || aa=='X' || aa=='x' || aa=='Z' || aa=='z');
	}
}
