package cmp_sorters;

public class BubbleSort {

	public void bubbleSort(int[] arr) {

		long start = System.currentTimeMillis();
		int n = arr.length;
		boolean swapped;
		do {
			swapped = false;
			for (int i = 1; i < n; i++) {
					if (arr[i-1] > arr[i])
					{
						int temp = arr[i-1];
						arr[i-1] = arr[i];
						arr[i] = temp;
						swapped = true;
					}
				}
			n--;
		} while (swapped);
		long bubRuntime = System.currentTimeMillis() - start;
		System.out.println("Bubble sort runtime for an arr of " + arr.length + " size: " + bubRuntime);
	}
}
