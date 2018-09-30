package edu.iastate.cs228.hw5;

// This class is complete.
// import java.util.*;
import java.util.Random;

public class UseDFSMaxPath {

	public static void main(String[] args) {

		DiGraph<String> G8 = new DiGraph<String>();
		G8.addEdge("S", "A", 6);
		G8.addEdge("S", "B", 10);
		G8.addEdge("S", "G", -5);
		G8.addEdge("A", "D", 8);
		G8.addEdge("A", "E", 5);
		G8.addEdge("B", "A", -6);
		G8.addEdge("B", "E", 10);
		G8.addEdge("C", "S", 11);
		G8.addEdge("D", "G", 7);
		G8.addEdge("E", "D", 6);
		G8.addEdge("E", "G", 5);
		G8.addEdge("F", "B", -8);
		G8.addEdge("F", "C", 2);
		LinkedStack<String> order = DFS.depthFirstSearch(G8);
		System.out.println("A topological order: ");
		while (!order.isEmpty())
			System.out.println("Vertex: " + order.pop());

		System.out.println();
		LinkedStack<String> path = new LinkedStack<String>();
		System.out.println("Distance of a max path: " + MaxPath.findMaxPath(G8, path));
		System.out.println("Each vertex in the max path: ");
		while (!path.isEmpty())
			System.out.println("Vertex: " + path.pop());

		Random rand = new Random();
		final int RANGE = 20; // constant identifier

		// The next part may take a few seconds and can be commented out on your first
		// try.

		System.out.println();
		DiGraph<Integer> big = new DiGraph<Integer>();
//		int limit = 1000 * 5;
		int limit = 1000 * 1000;
		int size = limit / 10;
		int edges = 0;
		Integer source = null;
//		Integer dest = null;
		Integer delta = RANGE / 2;
		for (int j = 0; j < limit; j++) {
			Integer from = new Integer(j);
			if (source == null)
				source = from;
			for (int k = j + 1; k < limit; k += size) {
				Integer to = new Integer(k);
				Integer cost = rand.nextInt(RANGE) - delta;
				big.addEdge(from, to, cost);
				edges++;
//				dest = to;
			}
		}
		System.out.println("A large graph with the number of edges: " + edges);

		LinkedStack<Integer> topo = DFS.depthFirstSearch(big);
		if (topo == null)
			System.out.println("The graph has a cycle");
		LinkedStack<Integer> ipath = new LinkedStack<Integer>();
		System.out.println("Distance of a max path: " + MaxPath.findMaxPath(big, ipath));
	}
}
