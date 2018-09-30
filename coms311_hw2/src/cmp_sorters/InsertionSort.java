package cmp_sorters;

public class InsertionSort {
	
	public void insertionSort(int[] arr) {
		
		long start = System.currentTimeMillis();
		
		for (int i = 0; i < arr.length; i++) {
			int j = i;
			while (j > 0 && arr[j-1] > arr[j]) {
				int temp = arr[j];
				arr[j] = arr[j-1];
				arr[j-1] = temp;
				j = j-1;
			}
		}
		
		long insRuntime = System.currentTimeMillis() - start;
		System.out.println("Insertion sort runtime for an arr of " + arr.length + " size: " + insRuntime);
	}
}
