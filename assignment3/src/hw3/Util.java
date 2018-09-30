package hw3;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

import api.Flow;
import api.Cell;

/**
 * Utility class with methods for creating Flows from string descriptors
 * and reading string descriptors from a file.  A string descriptor is
 * an array of strings, all with the same length, in which an alphabetic
 * character at a given row and column represents a cell at that
 * row and column.  The color of the cell is determined from the character
 * by the Cell constructor.  A descriptor is invalid if not all strings
 * are the same length or if there is an alphabetic character that 
 * does not appear exactly twice.
 */
public class Util
{

	/**
	 * Creates an array of Flow objects based on the string descriptor.
	 * If the descriptor is invalid, this method returns null.
	 * @param descriptor
	 *   array of strings
	 * @return
	 *   array of Flow objects determined by the descriptor 
	 */
	public static Flow[] createFlowsFromStringArray(String[] descriptor)
	{
		if (!isValid(descriptor)) {
			
			System.out.println("The Flow Game you are attempting to create contains an error.");
			return null;
		}	

		ArrayList<Flow> flows = new ArrayList<>();
		Cell c1, c2;
		for (int i = 0; i < descriptor.length; i++) {
			for (int j = 0; j < descriptor[0].length(); j++) {
				
				char currentChar = descriptor[i].charAt(j);
				
				//If it has reached a color letter
				if (currentChar != '-') {
					
					c1 = new Cell(i, j, currentChar);
					c2 = findSecondLetter(descriptor, currentChar);
					Flow flow = new Flow(c1, c2);
					
					//If the 2 end points are not in the same position, and c1 and c2 aren't null add them to flows
					if (c2 != null && c1 != null && !c1.positionMatches(c2.getRow(), c2.getCol())) {
						flows.add(flow);
					}
				}
			}
		}

		Flow[] arrFlows = flows.toArray(new Flow[flows.size()]);
		return arrFlows;
	}

	/**
	 * Reads the given file and constructs a list of FlowGame objects, one for
	 * each descriptor in the file.  Descriptors in the file are separated by 
	 * some amount of whitespace, but the file need not end with whitespace and
	 * may have extra whitespace at the beginning.  Invalid descriptors in the file
	 * are ignored, so the method may return an empty list.
	 * @param filename
	 *   name of the file to read
	 * @return
	 *   list of FlowGame objects created from the valid descriptors in the file
	 * @throws FileNotFoundException
	 */
	public static ArrayList<FlowGame> readFile(String filename) throws FileNotFoundException
	{
		File file = new File(filename);
		Scanner scan = new Scanner(file);
		ArrayList<FlowGame> flowGames = new ArrayList<>();
		ArrayList<String> flowGameString = new ArrayList<>();

		int height = 0;
		int width = 0;
		while(scan.hasNextLine()) {
			
			String line = scan.nextLine().trim();
			
			//If the scanner is on a Flow Game line
			if (!line.isEmpty()) {
				
				flowGameString.add(line);
				width = line.length();
				height = flowGameString.size();
			}

			//If Scanner reached end of FlowGame with whitespace or it's at the end of the file
			if ((line.isEmpty() || !scan.hasNextLine())) {
				
				String[] arrFlowGameString = flowGameString.toArray(new String[flowGameString.size()]);
				
				//Checks if the Flow Game is valid
				if (!isValid(arrFlowGameString)) {
					
					System.out.println("Text file contains an error.");
					scan.close();
					return null;
				}
				
				//Make Flow Game
				Flow[] flows = createFlowsFromStringArray(arrFlowGameString);
				flowGames.add(new FlowGame(flows, width, height));

				//reset
				width = 0;
				height = 0;
				flows = null;
				flowGameString.clear();
			}
		}
		
		scan.close();
		return flowGames;
	}

	/**
	 * This helper method finds the second letter in a Flow Game text file
	 * after a non-empty cell color char('R', 'G', 'Y', etc.) has been found
	 * @param descriptor
	 * 		String array representing Flow Game
	 * @param c
	 * 		Character representing the color of a flow. Used to find next occurrence of said character
	 * @return
	 * 		Returns the second Cell endpoint with its position and color that will be used to create a flow
	 */
	private static Cell findSecondLetter(String[] descriptor, char c) {

		Cell cell = null;
		int letterCount = 0;
		for (int i = 0; i < descriptor.length; i++) {
			for (int j = 0; j < descriptor[0].length(); j++) {
				
				char currentChar = descriptor[i].charAt(j);
				
				if (c==currentChar) {
					letterCount++;
				}
				
				if (c==currentChar && letterCount==2) {
					cell = new Cell(i, j, c);
				}
			}
		}
		
		return cell;
	}

	/**
	 * This helper method is used to determine if a Flow Game text file meets
	 * the specified requirements.
	 * @param descriptor
	 * 		String array representing a Flow Game
	 * @return
	 * 		True if the String Array representing a Flow Game is valid, false otherwise.
	 */
	private static boolean isValid(String[] descriptor) {

		//Check for length
		for (int i = 0; i < descriptor.length-1; i++) {
			
			if (descriptor[i].length() != descriptor[i+1].length()) {
				return false;
			}
		}

		//Checks for invalid letters
		for (int i = 0; i < descriptor.length; i++) {
			for (int j = 0; j < descriptor[0].length(); j++) {
				
				char c = descriptor[i].charAt(j);
				
				if (!isValidLetter(c)) {
					return false;
				}
			}
		}	
		
		return true;
	}
	
	/**
	 * Helper method used with isValid() to determine if the letters being passed into
	 * a text file are valid character representations of colors.
	 * @param c
	 * 		Current letter
	 * @return
	 * 		True is the current letter is valid, false otherwise.
	 */
	private static boolean isValidLetter(char c) {
		
		if (c == '-' || c == 'R' || c == 'G' || c == 'B' || c == 'C' || c == 'Y' || 
			c == 'M' || c == 'O' || c == 'P' || c == 'S' || c == 'V' || c == 'F' || c == 'X')
		{
			return true;
		}
		return false;
	}
}
