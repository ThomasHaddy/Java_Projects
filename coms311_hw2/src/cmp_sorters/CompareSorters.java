package cmp_sorters;

import java.util.Random;
//import java.util.Arrays;

public class CompareSorters {

	public static void main(String[] args) {

		int[] arr3000 = new int[3000];
		int[] arr30000 = new int[30000];
		int[] arr300000 = new int[300000];

		SelectionSort sel = new SelectionSort();
		InsertionSort ins = new InsertionSort();
		BubbleSort bub = new BubbleSort();

		//Sorted arrays runtime
		System.out.println("Runtimes are expressed in milliseconds, or one thousandth of a second.\n");
		System.out.println("Sorted Array Runtime--------------------------------------------------------------------------------------------------------------------\n");
		arr3000 = makeArr(arr3000, 1);
		arr30000 = makeArr(arr30000, 1);
		arr300000 = makeArr(arr300000, 1);
		//System.out.println("Sorted array: " + Arrays.toString(arr3000) + "\n");
		
		sel.selectionSort(arr3000);
		sel.selectionSort(arr30000);
		sel.selectionSort(arr300000);
		System.out.println();
		
		ins.insertionSort(arr3000);
		ins.insertionSort(arr30000);
		ins.insertionSort(arr300000);
		System.out.println();
		
		bub.bubbleSort(arr3000);
		bub.bubbleSort(arr30000);
		bub.bubbleSort(arr300000);
		System.out.println();
		
		//Reversed arrays runtime
		System.out.println("Reversed Array Runtime------------------------------------------------------------------------------------------------------------------\n");
		
		arr3000 = makeArr(arr3000, 2);
		arr30000 = makeArr(arr30000, 2);
		arr300000 = makeArr(arr300000, 2);		
		//System.out.println("Before selection sort: " + Arrays.toString(arr3000) + "\n");

		sel.selectionSort(arr3000);
		sel.selectionSort(arr30000);
		sel.selectionSort(arr300000);
		System.out.println();
		//System.out.println("\nAfter selection sort: " + Arrays.toString(arr3000) + "\n");
		
		arr3000 = makeArr(arr3000, 2);
		arr30000 = makeArr(arr30000, 2);
		arr300000 = makeArr(arr300000, 2);
		//System.out.println("Before insertion sort: " + Arrays.toString(arr3000) + "\n");
		
		ins.insertionSort(arr3000);
		ins.insertionSort(arr30000);
		ins.insertionSort(arr300000);
		System.out.println();
		//System.out.println("\nAfter insertion sort: " + Arrays.toString(arr3000) + "\n");
		
		arr3000 = makeArr(arr3000, 2);
		arr30000 = makeArr(arr30000, 2);
		arr300000 = makeArr(arr300000, 2);
		//System.out.println("Before bubble sort: " + Arrays.toString(arr3000) + "\n");
		
		bub.bubbleSort(arr3000);
		bub.bubbleSort(arr30000);
		bub.bubbleSort(arr300000);
		System.out.println();
		//System.out.println("\nAfter bubble sort: " + Arrays.toString(arr3000) + "\n");
		
		//Random arrays runtime
		System.out.println("Random Array Runtime--------------------------------------------------------------------------------------------------------------------\n");
		
		arr3000 = makeArr(arr3000, 3);
		arr30000 = makeArr(arr30000, 3);
		arr300000 = makeArr(arr300000, 3);
		//System.out.println("Before selection sort: " + Arrays.toString(arr3000) + "\n");

		sel.selectionSort(arr3000);
		sel.selectionSort(arr30000);
		sel.selectionSort(arr300000);
		System.out.println();
		//System.out.println("\nAfter selection sort: " + Arrays.toString(arr3000) + "\n");
		
		arr3000 = makeArr(arr3000, 3);
		arr30000 = makeArr(arr30000, 3);
		arr300000 = makeArr(arr300000, 3);
		//System.out.println("Before insertion sort: " + Arrays.toString(arr3000) + "\n");
		
		ins.insertionSort(arr3000);
		ins.insertionSort(arr30000);
		ins.insertionSort(arr300000);
		System.out.println();
		//System.out.println("\nAfter insertion sort: " + Arrays.toString(arr3000) + "\n");
		
		arr3000 = makeArr(arr3000, 3);
		arr30000 = makeArr(arr30000, 3);
		arr300000 = makeArr(arr300000, 3);
		//System.out.println("Before bubble sort: " + Arrays.toString(arr3000) + "\n");
		
		bub.bubbleSort(arr3000);
		bub.bubbleSort(arr30000);
		bub.bubbleSort(arr300000);
		//System.out.println("\nAfter bubble sort: " + Arrays.toString(arr3000) + "\n");
	}

	//Key 1: sorted, key 2: reverse sorted, key 3: random
	public static int[] makeArr(int[] arr, int key) {

		Random rand = new Random();
		
		for (int i = 0; i < arr.length; i++) {
			if (key==1)
				arr[i] = i;
			else if (key==2)
				arr[i] = arr.length-i-1;
			else if (key==3) {
				arr[i] = rand.nextInt();
			}
			else
				System.out.println("Key must be between 1 and 3");
		}
		return arr;
	}

}
