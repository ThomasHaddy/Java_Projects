package prob4;


public class Main {

	public static void main(String[] args) {
		int[] nums = {1, 1, 0, 1};
		System.out.println("1101 base 2 = " + baseToDec(nums, 2) + " base 10");
		System.out.println("100 base 10 = " + decToBase(100, 7) + " base 7");
	}
	
	public static int decToBase(int m, int k) {
		
		int tmp = m;
		int length = 0;
		while (tmp > 0) {
			tmp /= k;
			length++;
		}
		
		int total = 0;
		while (length > 0) {
			total += (m % k)*Math.pow(10, length-1);
			length--;
			m /= k;
		}
		return total;
	}
	
	public static int baseToDec(int[] arr, int k) {
		
		int total = 0;
		for (int i = 0; i < arr.length; i++) {
			total += arr[arr.length-i-1]*Math.pow(k, i);
		}
		return total;
	}
}
