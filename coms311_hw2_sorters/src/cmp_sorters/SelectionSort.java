package cmp_sorters;

public class SelectionSort {
	
	public void selectionSort(int[] arr) {
		
		long start = System.currentTimeMillis();
		
		for (int i = 0; i < arr.length - 1; i++)
		{
			int min = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[min]) {
					min = j;
				}
			}
			int temp = arr[i];
			arr[i] = arr[min];
			arr[min] = temp;
		}
		
		long selRuntime = System.currentTimeMillis() - start;
		System.out.println("Selection sort runtime for an arr of " + arr.length + " size: " + selRuntime);
	}
}
