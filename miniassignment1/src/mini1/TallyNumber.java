package mini1;

/**
 * A TallyNumber is a representation of positive integers based on three symbols, a line, a star, and zero. 
 * The line has value 1, the star has value 5, and the zero has value zero. (0, |, *)
 * A TallyNumber group is any collection of these three symbols without whitespace. 
 * The number represented by a TallyNumber group is just the sum of the values of the symbols.
 * @author Thomas Haddy, 3/6/17
 *
 */
public class TallyNumber {

	/**
	 * Stores the integer value of a TallyNumber
	 */
	private int tallyValue;
	
	/**
	 * Stores the String representation of a TallyNumber
	 */
	private String tallyStr;
	
	/**
	 * Constructs a TallyNumber with the given integer value.
	 * @param givenValue
	 */
	public TallyNumber(int givenValue) {
		tallyValue = Math.max(-1,givenValue);
		
		if (tallyValue == -1) {
			tallyValue = -1;
			tallyStr = "";
			return;
		}
		else if (tallyValue == 0) {
			tallyValue = 0;
			tallyStr = "0";
			return;
		}
		normalize();
	}
	
	/**
	 * Constructs a TallyNumber with the given string representation.
	 * @param givenString
	 */
	public TallyNumber(String givenString) {
		tallyStr = givenString;
		
		if (tallyStr==null || tallyStr.equals("") || !isValid(tallyStr)) {
			tallyStr = "";
			tallyValue = -1;
			return;
		}
		tallyValue = evaluateString(tallyStr);
	}
	
	/**
	 * Gets the integer value of this TallyNumber.
	 * @return
	 * 		TallyNumber integer value.
	 */
	public int getIntValue() {
		return tallyValue;
	}
	
	/**
	 * Returns the string representation of this TallyNumber.
	 * @return
	 * 		TallyNumber String value.
	 */
	public String getStringValue() {
		return tallyStr;
	}
	
	/**
	 * Adds the integer value of the given TallyNumber to the current TallyNumber.
	 * Also, the TallyNumber String is normalized
	 * @param other
	 */
	public void add(TallyNumber other) {
		tallyValue += other.getIntValue();
		normalize();
		
	}
	
	/**
	 * Converts this TallyNumber's representation to its normalized form without changing the value.
	 * Each TallyNumber String gets returned in the form of base 10 with a space separating the powers of 10. 
	 * Example: "||**|*|". Result: "| *||||" 
	 */
	public void normalize() {
		tallyStr = "";
		String tempVal = tallyValue + "";
		int strRepNum = 0;
		
		for (int i = 0; i < tempVal.length(); i++) {
			strRepNum = Integer.parseInt(tempVal.charAt(i)+"");
			
			if (i == tempVal.length()-1) {
				tallyStr += evaluateGroup(strRepNum);
			}
			else {
				tallyStr += evaluateGroup(strRepNum) + " ";
			}
		}
	}
	
	/**
	 * Adds the other TallyNumber String to the current TallyNumber String by concatenating the corresponding TallyNumber groups.
	 * A group is the TallyNumber String separated by the spaces (' '). 
	 * Example: "||| ***" Group: "|||"
	 * If the number of groups are different, the shorter TallyNumber gets concatenated to the longer TallyNumber
	 * Example: TallyNumber 1: "||| ***", TallyNumber 2: "* |". Result: "|||* ***|".
	 * Also, it adds the given TallyNumber integer value to the current TallyNumber integer value.
	 * @param other
	 * 		The second TallyNumber object.
	 */
	public void combine(TallyNumber other) {
		
		tallyValue += other.getIntValue();
		
		String arr1[] = tallyStr.split(" ");
		String arr2[] = other.tallyStr.split(" ");
		int smallerArrayPos = 0;
		String[] result;
		
		//If the second array length of TallyNumber String groups is greater than the first
		if (arr1.length > arr2.length) {
			result = new String[arr1.length];
			for (int i = 0; i < arr1.length; i++) {
				
				if (i >= arr1.length - arr2.length) {
					result[i] = arr1[i]+arr2[smallerArrayPos];
					smallerArrayPos++;
				}
				else {
					result[i] = arr1[i];
				}
			}
		}
		//If the first array length of TallyNumber String groups is greater than the second
		else if (arr2.length > arr1.length){
			result = new String[arr2.length];
			for (int i = 0; i < arr2.length; i++) {
				
				if (i >= arr2.length - arr1.length) {
					result[i] = arr1[smallerArrayPos]+arr2[i];
					smallerArrayPos++;
				}
				else {
					result[i] = arr2[i];
				}
			}
		}
		//Otherwise the number of TallyNumber groups are equal
		else {
			result = new String[arr1.length];
			for (int i = 0; i < arr1.length; i++) {
				result[i] = arr1[i]+arr2[i];
			}
		}
		
		//Mutate tallyStr to contain the result[] array elements with spaces
		tallyStr = "";
		for (int i = 0; i < result.length; i++) {
			
			if (i == result.length-1) {
				tallyStr += result[i];
			}
			else {
				tallyStr += result[i] + " ";
			}
		}
	}
	
	/**
	 * This helper method determines if the givenString passed into the constructor is valid.
	 * It must only contain the following: '0', '|', '*', ' '
	 * @param s
	 * 		String representation of TallyNumber
	 * @return
	 * 		Returns whether or not the String is valid
	 */
	private static boolean isValid(String s) {
		
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			
			if (!(c == ' ' || c == '|' || c == '*' || c == '0')) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * This method evaluates a TallyNumber not in a normalized form
	 * It returns a TallyNumber group's integer value.
	 * @param s
	 * 		The not normalized String.
	 * @return
	 * 		The integer value of the TallyNumber.
	 */
	private static int evaluateString(String s) {
		
		int resultCount = 0;
		int whitespace = getWhitespace(s);
		int intCount = 0;
		
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == ' ') {
				resultCount += (intCount*Math.pow(10, whitespace));
				whitespace--;
				intCount = 0;
			}
			else if (c == '*') {
				intCount += 5;
			}
			else if (c == '|') {
				intCount++;
			}
		}
		return resultCount += intCount;
	}
	
	/**
	 * This helper method evaluates a group TallyNumber and returns the String representation of a number between
	 * 1 and 9. Used in normalize().
	 * @param group
	 * 		Number between 1 and 9
	 * @return
	 * 		String representation of tally number 1-9
	 */
	private static String evaluateGroup(int group) {
		
		String result = "";
		
		if (group==0) {
			return "0";
		}
		
		while (group > 0) {
			if (group >= 5) {
				result += '*';
				group -= 5;
			}
			else if (group > 0) {
				result += '|';
				group--;
			}
		}
		return result;
	}
	
	/**
	 * This is a helper method used in evaluateString() to keep track of the number of whitespace. 
	 * @param s
	 * 		String representation of a TallyNumber
	 * @return
	 * 		Number of whitespace
	 */
	private static int getWhitespace(String s) {
		int whitespace = 0;
		for (int i = 0; i < s.length(); i++) {
			if (Character.isWhitespace(s.charAt(i))) {
				whitespace++;
			}
		}
		return whitespace;	
	}
}
