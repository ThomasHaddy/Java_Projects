package edu.iastate.cs228.hw5;
// This class is complete.

import java.util.*;

/**
 * An edge class as a pair class with two components of types V and C, where V
 * is a vertex type and C is a cost type.
 */

public class DiGraph<V> {

	// symbol table: key = string vertex, value = set of neighboring vertices
	private HashMap<V, HashSet<Edge<V, Integer>>> map;

	// number of edges
	private int E;

	// create an empty graph
	public DiGraph() {
		map = new HashMap<V, HashSet<Edge<V, Integer>>>();
	}

	// read a graph from an input stream
	/*
	 * public DiGraph(In in, String delimiter) { st = new ST<String, SET<String>>();
	 * while (!in.isEmpty()) { String line = in.readLine(); String[] names =
	 * line.split(delimiter); for (int i = 1; i < names.length; i++) {
	 * addEdge(names[0], names[i]); } } }
	 */

	// return number of vertices and edges
	/*
	 * public int V() { return st.size(); } public int E() { return E; }
	 * 
	 * // return the degree of vertex v public int degree(String v) { if
	 * (!st.contains(v)) return 0; else return st.get(v).size(); }
	 */

	// add t to f's set of neighbors.
	public void addEdge(V f, V t, Integer c) {
		if (!hasEdge(f, t))
			E++;
		if (!hasVertex(f))
			addVertex(f);
		map.get(f).add(new Edge<V, Integer>(t, c));
		if (!hasVertex(t))
			addVertex(t);
	}

	// add a new vertex f with no neighbors (if vertex does not yet exist)
	public void addVertex(V f) {
		if (!hasVertex(f))
			map.put(f, new HashSet<Edge<V, Integer>>());
	}

	// return iterator over all vertices in graph
	public Iterable<V> vertices() {
		return map.keySet();
	}

	// return an iterator over the neighbors of vertex f
	public Iterable<Edge<V, Integer>> adjacentTo(V f) {
		// return empty set if vertex isn't in graph
		if (!hasVertex(f))
			return new HashSet<Edge<V, Integer>>();
		else
			return map.get(f);
	}

	// is f a vertex in the graph?
	public boolean hasVertex(V f) {
		return map.containsKey(f);
	}

	// is v-w an edge in the graph?
	public boolean hasEdge(V f, V t) {
		if (!hasVertex(f))
			return false;
		for (Edge<V, Integer> e : map.get(f)) {
			if (t.equals(e.getVertex()))
				return true;
		}
		return false;
	}

	// not very efficient, intended for debugging only
	public String toString() {
		String s = "";
		for (V f : map.keySet()) {
			s += f.toString() + ": ";
			for (Edge<V, Integer> e : map.get(f)) {
				s += "[" + e.getVertex().toString() + ", " + e.getCost().toString() + "] ";
			}
			s += "\n";
		}
		return s;
	}

}
