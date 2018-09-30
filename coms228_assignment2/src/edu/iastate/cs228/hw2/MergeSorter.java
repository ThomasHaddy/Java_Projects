package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException;
import java.util.InputMismatchException;

/**
 * @author Thomas Haddy 10/5/17
 */

/**
 * This class implements the mergesort algorithm.   
 */
public class MergeSorter extends AbstractSorter
{	
	/**
	 * The two constructors below invoke their corresponding superclass constructors. They
	 * also set the instance variables algorithm and outputFileName in the superclass.
	 */

	/** 
	 * Constructor accepts an input array of points. 
	 * in the array. 
	 *  
	 * @param pts   input array of integers
	 */
	public MergeSorter(Point[] pts) 
	{
		super(pts);
		super.algorithm = "merge sort";
		super.outputFileName = "merge.txt";
	}


	/**
	 * Constructor reads points from a file. 
	 * 
	 * @param inputFileName  name of the input file
	 * @throws FileNotFoundException when inputFileName file is not found
	 * @throws InputMismatchException  when the input text file contains an odd number of elements
	 */
	public MergeSorter(String inputFileName) throws InputMismatchException, FileNotFoundException, NumberFormatException 
	{
		super(inputFileName);
		super.algorithm = "merge sort";
		super.outputFileName = "merge.txt";
	}


	/**
	 * Perform mergesort on the array points[] of the parent class AbstractSorter. 
	 * 
	 * @param order  1   by x-coordinate 
	 * 			     2   by polar angle 
	 *
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
		mergeSortRec(points);
		super.sortingTime = System.nanoTime() - start;
	}


	/**
	 * This is a recursive method that carries out mergesort on an array pts[] of points. One 
	 * way is to make copies of the two halves of pts[], recursively call mergeSort on them, 
	 * and merge the two sorted subarrays into pts[].   
	 * 
	 * @param pts	point array 
	 */
	private void mergeSortRec(Point[] pts)
	{
		if (pts.length > 1) {

			
			//Merge sort the first half
			Point[] firstHalf = new Point[pts.length / 2];
			System.arraycopy(pts, 0, firstHalf, 0, pts.length / 2);
			mergeSortRec(firstHalf);

			//Merge sort the second half
			int secondHalfLength = pts.length - pts.length / 2;
			Point[] secondHalf = new Point[secondHalfLength];
			System.arraycopy(pts, pts.length / 2, secondHalf, 0, secondHalfLength);
			mergeSortRec(secondHalf);

			//Merge firstHAlf with secondHalf into list
			merge(firstHalf, secondHalf, pts);
		}
	}


	/**
	 *  merges two sorted subarrays into pts[]. Gets called many times inside mergeSortRec 
	 *  after each recursive call.
	 * 
	 * @param pts1  first half
	 * @param pts2  second half
	 * @param temp  merge of first and second half
	 */
	private void merge(Point[] pts1, Point[] pts2, Point[] temp) {
		int current1 = 0;
		int current2 = 0;
		int current3 = 0;

		while (current1 < pts1.length && current2 < pts2.length) {
			if (pointComparator.compare(pts1[current1], pts2[current2]) ==-1) {
				temp[current3++] = pts1[current1++];
			}
			else {
				temp[current3++] = pts2[current2++];
			}
		}

		while (current1 < pts1.length) {
			temp[current3++] = pts1[current1++];
		}

		while (current2 < pts2.length) {
			temp[current3++] = pts2[current2++];
		}
	}
}
