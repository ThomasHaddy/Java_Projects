package edu.iastate.cs228.hw2;

/**  
 * @author Thomas Haddy 10/5/17
 */

import java.util.Comparator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.IllegalArgumentException; 
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This abstract class is extended by SelectionSort, InsertionSort, MergeSort, and QuickSort.
 * It stores the input (later on the sorted) sequence and records the employed sorting algorithm, 
 * the comparison method, and the time spent on sorting. 
 */
public abstract class AbstractSorter
{
	/**
	 * Array of points operated on by a sorting algorithm. 
	 * The number of points is given by points.length.
	 */
	protected Point[] points;   

	/**
	 * "selection sort", "insertion sort",  
	 * "merge sort", or "quick sort". Initialized by a subclass constructor.
	 */
	protected String algorithm = null;

	/**
	 * true if last sort was done by polar angle and false if by x-coordinate 
	 */
	protected boolean sortByAngle;    

	/**
	 * "select.txt", "insert.txt", "merge.txt", or "quick.txt"
	 */
	protected String outputFileName;   

	/**
	 * execution time in nanoseconds. 
	 */
	protected long sortingTime; 	   

	/**
	 * comparator which compares polar angle if sortByAngle == true 
	 * and x-coordinate if sortByAngle == false 
	 */
	protected Comparator<Point> pointComparator;  

	/**
	 * lowest point in the array, or in case of a tie, the leftmost of the lowest points. 
	 * This point is used as the reference point for polar angle based comparison.
	 */
	private Point lowestPoint; 	   

	/**
	 * No implementation needed. Provides a default super constructor to subclasses. 
	 * Removable after implementing SelectionSorter, InsertionSorter, MergeSorter, and QuickSorter.
	 */
	protected AbstractSorter() {}

	/**
	 * This constructor accepts an array of points as input. Copy the points into the array points[]. 
	 * Sets the instance variable lowestPoint.
	 * 
	 * @param  pts  input array of points 
	 * @throws IllegalArgumentException  when pts == null or pts.length == 0.
	 */
	protected AbstractSorter(Point[] pts) throws IllegalArgumentException
	{
		if (pts == null || pts.length == 0) {
			throw new IllegalArgumentException();
		}

		//Sets the lowestPoint
		points = new Point[pts.length];
		Point min = pts[0];
		for (int i = 0;i < pts.length; i++) {
			points[i] = pts[i];
			if (points[i].getY() < min.getY()) {
				min = points[i];
			}
			if (points[i].getY() == min.getY() && points[i].getX() < min.getX()) {
				min = points[i];
			}
		}
		lowestPoint = min;
	}


	/**
	 * This constructor reads points from a file. Sets the instance variable lowestPoint
	 * 
	 * @param  inputFileName
	 * 		name of the input file
	 * @throws FileNotFoundException
	 * 		when the input file was not found
	 * @throws InputMismatchException
	 * 		when the input file contains an odd number of integers
	 */
	protected AbstractSorter(String inputFileName) throws FileNotFoundException, InputMismatchException
	{
		File file = new File(inputFileName);
		Scanner scan = new Scanner(file);

		int pointsLength = findPointsLength(inputFileName);
		points = new Point[pointsLength];

		int pos = 0;
		while (scan.hasNextInt()) {
			int x = scan.nextInt();
			int y = scan.nextInt();

			points[pos] = new Point(x, y);
			pos++;
		}

		//Sets the lowestPoint
		Point min = points[0];
		for (int i = 0;i < points.length; i++) {
			if (points[i].getY() < min.getY()) {
				min = points[i];
			}
			if (points[i].getY() == min.getY() && points[i].getX() < min.getX()) {
				min = points[i];
			}
		}
		lowestPoint = min;
		scan.close();
	}


	/**
	 * Sorts the elements in points[]. 
	 * 
	 *     a) in the non-decreasing order of x-coordinate if order == 1
	 *     b) in the non-decreasing order of polar angle w.r.t. lowestPoint if order == 2 
	 *        (lowestPoint will be at index 0 after sorting)
	 * 
	 * Sets the instance variable sortByAngle based on the value of order. Calls the method 
	 * setComparator() to set the variable pointComparator and use it in sorting.    
	 * Records the sorting time (in nanoseconds) using the System.nanoTime() method. 
	 * (Assign the time to the variable sortingTime.)  
	 * 
	 * @param order  1   by x-coordinate 
	 * 			     2   by polar angle w.r.t lowestPoint 
	 *
	 * @throws IllegalArgumentException if order is less than 1 or greater than 2
	 */
	public abstract void sort(int order) throws IllegalArgumentException; 


	/**
	 * Outputs performance statistics in the format: 
	 * 
	 * <sorting algorithm> <size>  <time>
	 * 
	 * For instance, 
	 * 
	 * selection sort   1000	  9200867
	 * 
	 * Use the spacing in the sample run in Section 2 of the assignment description. 
	 */
	public String stats()
	{
		if (algorithm.equals("selection sort") || algorithm.equals("insertion sort")) {
			return algorithm + "      " + points.length + spacesBetweenSizeAndTime(points.length) + sortingTime;
		}
		return algorithm + "          " + points.length + spacesBetweenSizeAndTime(points.length) + sortingTime;
	}


	/**
	 * Write points[] to a string.  When printed, the points will appear in order of increasing
	 * index with every point occupying a separate line.  The x and y coordinates of the point are 
	 * displayed on the same line with exactly one blank space in between. 
	 */
	@Override
	public String toString()
	{
		String output = "";
		for (Point p : points) {
			output += p.getX() + " " + p.getY()+"\n";
		}
		return output;
	}


	/** 
	 * This method, called after sorting, writes point data into a file by outputFileName. It will 
	 * be used for Mathematica plotting to verify the sorting result.  The data format depends on 
	 * sortByAngle.  It is detailed in Section 4.1 of the assignment description assn2.pdf. 
	 * 
	 * @throws FileNotFoundException  when the outPutFileName was not found
	 */
	public void writePointsToFile() throws FileNotFoundException
	{
		File outFile = new File(outputFileName);
		PrintWriter out = new PrintWriter(outFile);

		//PolarAngle
		if (sortByAngle) {
			out.println(lowestPoint.getX() + " " + lowestPoint.getY());

			for (Point p : points) {
				String pt = p.getX()+ " " + p.getY();
				out.println(pt + " " + lowestPoint.getX() + " " + lowestPoint.getY() + " " + pt);
			}
		}
		//X-coordinate
		else {
			out.print(toString());
		}
		out.close();
	}	


	/**
	 * Generates a comparator on the fly that compares by polar angle if sortByAngle == true
	 * and by x-coordinate if sortByAngle == false. Set the protected variable pointComparator
	 * to it. Need to create an object of the PolarAngleComparator class and call the compareTo() 
	 * method in the Point class, respectively for the two possible values of sortByAngle.  
	 */
	protected void setComparator() 
	{
		if (sortByAngle) {
			pointComparator = new PolarAngleComparator(lowestPoint);
		}
		else {
			pointComparator = new Comparator<Point>() {

				@Override
				public int compare(Point p1, Point p2) {
					return p1.compareTo(p2);
				}
			};
		}
	}

	/**
	 * Swap the two elements indexed at i and j respectively in the array points[]. 
	 * 
	 * @param i
	 * 		position of first element
	 * @param j
	 * 		position of second element
	 */
	protected void swap(int i, int j)
	{
		Point temp = points[i];
		points[i] = points[j];
		points[j] = temp;
	}

	/**
	 * Gets the length of points[] length when the user passes in a text file
	 * @param inputFileName
	 * 		Name of the input file
	 * @return
	 * 		length of points[] from text file
	 * @throws FileNotFoundException
	 * 		if inputFileName does not exist
	 * @throws InputMismatchException
	 * 		if the text file contains an odd number of integers
	 */
	private static int findPointsLength(String inputFileName) throws FileNotFoundException, InputMismatchException {
		File file = new File(inputFileName);
		Scanner temp = new Scanner(file);

		int length = 0;
		while (temp.hasNextInt()) {
			temp.nextInt();
			length++;
		}
		temp.close();

		if(length % 2 == 1) {
			throw new InputMismatchException();
		}

		return length / 2;
	}
	
	/**
	 * Gets the number of spaces between size and time. The spaces vary according to how large the size variables are.
	 * @return  the number of spaces between size and time
	 */
	private static String spacesBetweenSizeAndTime(int lengthOfSize) {
		String castedLength = lengthOfSize+"";
		int numSpaces = 12 - castedLength.length();
		
		String result = "";
		for (int i = 0; i < numSpaces; i++) {
			result+= " ";
		}
		return result;
	}
}
