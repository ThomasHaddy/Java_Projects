package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException;
import java.util.InputMismatchException;

/**
 * @author Thomas Haddy 10/5/17
 */

/**
 * This class implements selection sort.   
 */
public class SelectionSorter extends AbstractSorter
{
	/**
	 * The two constructors below invoke their corresponding superclass constructors. They
	 * also set the instance variables algorithm and outputFileName in the superclass.
	 */

	/**
	 * Constructor takes an array of points.
	 *  
	 * @param pts  given points  
	 */
	public SelectionSorter(Point[] pts)  
	{
		super(pts);
		super.algorithm = "selection sort";
		super.outputFileName = "select.txt";
	}	

	/**
	 * Constructor reads points from a file. 
	 * 
	 * @param inputFileName  name of the input file
	 * @throws FileNotFoundException  when inputFileName file is not found
	 * @throws InputMismatchException  when the input text file contains an odd number of elements
	 */
	public SelectionSorter(String inputFileName) throws InputMismatchException, FileNotFoundException, NumberFormatException
	{
		super(inputFileName);
		super.algorithm = "selection sort";
		super.outputFileName = "select.txt";
	}

	/** 
	 * Apply selection sort on the array points[] of the parent class AbstractSorter.  
	 *
	 * @param order  1   by x-coordinate 
	 * 			     2   by polar angle 
	 *
	 */
	@Override 
	public void sort(int order)
	{
		if (order==1)
			super.sortByAngle = false;
		if (order==2)
			super.sortByAngle = true;

		super.setComparator();

		long start = System.nanoTime();
		for (int i = 0; i < points.length - 1; i++)
		{
			int index = i;
			for (int j = i + 1; j < points.length; j++) {
				if (super.pointComparator.compare(points[j], points[index]) == -1) {
					index = j;
				}
			}
			super.swap(i, index);
		}
		super.sortingTime = System.nanoTime() - start;
	}	
}
