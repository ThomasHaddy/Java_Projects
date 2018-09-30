package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException;
import java.util.InputMismatchException;

/** 
 * @author Thomas Haddy 10/5/17
 */

/**
 * This class implements insertion sort.   
 */
public class InsertionSorter extends AbstractSorter 
{
	/**
	 * The two constructors below invoke their corresponding superclass constructors. They
	 * also set the instance variables algorithm and outputFileName in the superclass.
	 */

	/**
	 * Constructor takes an array of points. 
	 * 
	 * @param pts
	 * 		given array of points   
	 */
	public InsertionSorter(Point[] pts) 
	{
		super(pts);
		super.algorithm = "insertion sort";
		super.outputFileName = "insert.txt";
	}	


	/**
	 * Constructor reads points from a file. 
	 * 
	 * @param inputFileName  name of the input file
	 * @throws FileNotFoundException  when inputFileName file is not found
	 * @throws InputMismatchException  when the input text file contains an odd number of elements
	 */
	public InsertionSorter(String inputFileName) throws InputMismatchException, FileNotFoundException, NumberFormatException
	{
		super(inputFileName);
		super.algorithm = "insertion sort";
		super.outputFileName = "insert.txt";
	}


	/** 
	 * Perform insertion sort on the array points[] of the parent class AbstractSorter.  
	 * 
	 * @param order  1   by x-coordinate 
	 * 			     2   by polar angle 
	 */
	@Override 
	public void sort(int order)
	{
		if (order==1) 
			sortByAngle = false;
		if (order==2)
			sortByAngle = true;

		super.setComparator();

		long start = System.nanoTime();
		for (int i = 1; i < points.length; i++)
		{
			Point currentElement = points[i];
			int k;
			for (k = i - 1; k >= 0 && pointComparator.compare(points[k],  currentElement)==1; k--) { 
				swap(k+1, k);
			}
			points[k + 1] = currentElement;	
		}
		sortingTime = System.nanoTime() - start;
	}		
}
