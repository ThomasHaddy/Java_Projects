package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException;
import java.util.InputMismatchException;

/**
 * @author Thomas Haddy 10/5/17
 */

/**
 * This class implements the version of the quicksort algorithm presented in the lecture.   
 */
public class QuickSorter extends AbstractSorter
{	
	/**
	 * The two constructors below invoke their corresponding superclass constructors. They
	 * also set the instance variables algorithm and outputFileName in the superclass.
	 */

	/** 
	 * Constructor accepts an input array of points. 
	 *   
	 * @param pts   input array of integers
	 */
	public QuickSorter(Point[] pts)
	{
		super(pts);
		super.algorithm = "quick sort";
		super.outputFileName = "quick.txt";

	}	

	/**
	 * Constructor reads points from a file. 
	 * 
	 * @param inputFileName  name of the input file
	 * @throws FileNotFoundException  when inputFileName file is not found
	 * @throws InputMismatchException  when the input text file contains an odd number of elements
	 */
	public QuickSorter(String inputFileName) throws InputMismatchException, FileNotFoundException, NumberFormatException 
	{
		super(inputFileName);
		super.algorithm = "quick sort";
		super.outputFileName = "quick.txt";
	}

	/**
	 * Carry out quicksort on the array points[] of the AbstractSorter class.  
	 * 
	 * @param order  1   by x-coordinate 
	 * 			     2   by polar angle 
	 *
	 */
	@Override 
	public void sort(int order)
	{	
		if (order == 1)
			super.sortByAngle = false;
		if (order==2)
			super.sortByAngle = true;

		super.setComparator();

		long start = System.nanoTime();
		quickSortRec(0, points.length-1);
		super.sortingTime = System.nanoTime() - start;
	}

	/**
	 * Operates on the subarray of points[] with indices between first and last. 
	 * 
	 * @param first  starting index of the subarray
	 * @param last   ending index of the subarray
	 */
	private void quickSortRec(int first, int last)
	{
		if (first < last) {
			int mid = partition(first, last);
			quickSortRec(first, mid);
			quickSortRec(mid+1, last);
		}
	}

	/**
	 * Operates on the subarray of points[] with indices between first and last.
	 * 
	 * @param first
	 * @param last
	 * @return
	 */
	private int partition(int first, int last)
	{
		Point pivot = points[first];
		int low = first - 1;
		int high = last + 1;

		while(true) {
			do { low++; } while(pointComparator.compare(points[low], pivot)==-1);
			do { high--; } while(pointComparator.compare(points[high], pivot)==1);
			if (low < high) { super.swap(low, high); }
			else return high;
		}
	}	
}
