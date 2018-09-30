package edu.iastate.cs228.hw3;

public class Main {
	public static void main(String[] args) {
		AdaptiveList<String> seq = new AdaptiveList<String>(); // code
		seq.add("B");
		seq.add("A");
		seq.add("C");
		System.out.println("After the three seq.add() operations:");
		System.out.println("linkedUTD: " + seq.getlinkedUTD());
		System.out.println("arrayUTD: " + seq.getarrayUTD());
		System.out.println(seq.toString()); // show both linked list and array
		System.out.println( seq.get(1) );
		System.out.println("After the seq.get(1) operation:");
		System.out.println("linkedUTD: " + seq.getlinkedUTD());
		System.out.println("arrayUTD: " + seq.getarrayUTD());
		System.out.println(seq.toString());
		System.out.println( seq.set(1, "D") );
		System.out.println("After the seq.set(1, ’D’) operation:");
		System.out.println("linkedUTD: " + seq.getlinkedUTD());
		System.out.println("arrayUTD: " + seq.getarrayUTD());
		System.out.println(seq.toString());
		seq.add("E");
		System.out.println("After the seq.add(’E’) operation:");
		System.out.println("linkedUTD: " + seq.getlinkedUTD());
		System.out.println("arrayUTD: " + seq.getarrayUTD());
		System.out.println(seq.toString());
		System.out.println("Any change in array: " + seq.reverse());
		System.out.println("linkedUTD: " + seq.getlinkedUTD());
		System.out.println("arrayUTD: " + seq.getarrayUTD());
		System.out.println(seq.toString());
		//	After the three seq.add() operations:
		//	linkedUTD: true
		//	arrayUTD: false
		//	A sequence of items from the most recent array:
		//	[]
		//	A sequence of items from the most recent linked list:
		//	(B, A, C)
		//	A
		//	After the seq.get(1) operation:
		//	linkedUTD: true
		//	arrayUTD: true
		//	A sequence of items from the most recent array:
		//	[B, A, C]
		//	A sequence of items from the most recent linked list:
		//	(B, A, C)
		//	A
		//	After the seq.set(1, ’D’) operation:
		//	linkedUTD: false
		//	arrayUTD: true
		//	A sequence of items from the most recent array:
		//	[B, D, C]
		//	A sequence of items from the most recent linked list:
		//	3
		//	(B, A, C)
		//	After the seq.add(’E’) operation:
		//	linkedUTD: true
		//	arrayUTD: false
		//	A sequence of items from the most recent array:
		//	[B, D, C]
		//	A sequence of items from the most recent linked list:
		//	(B, D, C, E)
		//	Any change in array: true
		//	linkedUTD: false
		//	arrayUTD: true
		//	A sequence of items from the most recent array:
		//	[E, C, D, B]
		//	A sequence of items from the most recent linked list:
		//	(B, D, C, E)
	}
}
