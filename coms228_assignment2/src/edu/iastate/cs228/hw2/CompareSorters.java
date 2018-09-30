package edu.iastate.cs228.hw2;

/**
 * @author Thomas Haddy 10/5/17
 */

import java.io.FileNotFoundException;
import java.util.Scanner; 
import java.util.Random; 

/**
 * This class executes four sorting algorithms: selection sort, insertion sort, mergesort, and
 * quicksort, over randomly generated integers as well integers from a file input. It compares the 
 * execution times of these algorithms on the same input. 
 */
public class CompareSorters 
{
	/**
	 * Repeatedly take integer sequences either randomly generated or read from files. 
	 * Perform the four sorting algorithms over each sequence of integers, comparing 
	 * points by x-coordinate or by polar angle with respect to the lowest point.  
	 * 
	 * @param args
	 * @throws FileNotFoundException
	 * 		when the input file the user provides is not found
	 **/
	public static void main(String[] args) throws FileNotFoundException 
	{		
		// 
		// Conducts multiple sorting rounds. In each round, performs the following: 
		// 
		//    a) If asked to sort random points, calls generateRandomPoints() to initialize an array 
		//       of random points. 
		//    b) Reassigns to elements in the array sorters[] (declared below) the references to the 
		//       four newly created objects of SelectionSort, InsertionSort, MergeSort and QuickSort. 
		//    c) Based on the input point order, carries out the four sorting algorithms in a for 
		//       loop that iterates over the array sorters[], to sort the randomly generated points
		//       or points from an input file.  
		//    d) Meanwhile, prints out the table of runtime statistics.
		// 
		// A sample scenario is given in Section 2 of the assignment description. 
		// 	
		AbstractSorter[] sorters = new AbstractSorter[4];

		// Within a sorting round, every sorter object write its output to the file 
		// "select.txt", "insert.txt", "merge.txt", or "quick.txt" if it is an object of 
		// SelectionSort, InsertionSort, MergeSort, or QuickSort, respectively. 

		Random r = new Random();
		Scanner in = new Scanner(System.in);
		int keys = 0;
		int order = 0;
		int numTrials = 1;

		while (keys != 3) {
			System.out.print("Trial " + numTrials + " => Enter key: ");
			keys = in.nextInt();

			if (keys==1) {
				System.out.print("Enter number of random points: ");
				int numRandPts = in.nextInt();
				Point[] randPoints = generateRandomPoints(numRandPts, r);

				System.out.print("Order used in sorting: ");
				order = in.nextInt();

				System.out.println();
				System.out.println();

				System.out.println("algorithm           size        time (ns)");
				System.out.println("---------------------------------------------" + "\n");

				sorters[0] = new SelectionSorter(randPoints);
				sorters[1] = new InsertionSorter(randPoints);
				sorters[2] = new MergeSorter(randPoints);
				sorters[3] = new QuickSorter(randPoints);

				for(int i = 0; i < sorters.length; i++) {
					sorters[i].sort(order);
					System.out.println(sorters[i].stats());

					sorters[i].writePointsToFile();
				}
				System.out.println("---------------------------------------------" + "\n");

				numTrials++;
			}
			else if (keys==2) {

				System.out.println("Points from a file");
				System.out.println("File name: ");
				String fileName = in.next();

				System.out.print("Order used in sorting: ");
				order = in.nextInt();

				System.out.println();
				System.out.println();

				System.out.println("algorithm           size        time (ns)");
				System.out.println("---------------------------------------------" + "\n");

				sorters[0] = new SelectionSorter(fileName);
				sorters[1] = new InsertionSorter(fileName);
				sorters[2] = new MergeSorter(fileName);
				sorters[3] = new QuickSorter(fileName);

				for(int i = 0; i < sorters.length; i++) {
					sorters[i].sort(order);
					System.out.println(sorters[i].stats());

					sorters[i].writePointsToFile();
				}
				System.out.println("---------------------------------------------" + "\n");

				numTrials++;
			}
			else if(keys==3) {
				in.close();
				return;
			}
			//If the key pressed was something else, ask the same thing
			else {}

		}
		in.close();
	}


	/**
	 * This method generates a given number of random points to initialize randomPoints[].
	 * The coordinates of these points are pseudo-random numbers within the range 
	 * [-50,50] × [-50,50]. Please refer to Section 3 of assignment description document on how such points can be generated.
	 * 
	 * Ought to be private. Made public for testing. 
	 * 
	 * @param numPts  	number of points
	 * @param rand      Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException if numPts < 1
	 */
	public static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException
	{ 
		if (numPts < 1) {
			throw new IllegalArgumentException();
		}

		Point[] points = new Point[numPts];

		for (int i = 0; i < numPts; i++) {
			int x = rand.nextInt(101) - 50;
			int y = rand.nextInt(101) - 50;

			points[i] = new Point(x, y);
		}
		return points;
	}
}
