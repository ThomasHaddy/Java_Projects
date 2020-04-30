package main;

import java.io.FileNotFoundException;
import java.util.*;
import Voronoi.*;
import stdlib.StdDraw;

public class Runner {

	public static void main(String[] args) {
		
		//Args[0]: File - reads from sites.txt
		//Args[1]: Path - path of input .txt
		if (args[0].equals("File") && args.length == 2) {
			ArrayList<Point> sites = new ArrayList<Point>();
			try {
				Importer.printInputFile(args[1]);
				Importer.parseInputFile(sites, args[1]);
			} catch (FileNotFoundException e) {
				System.out.println("Did not find file in path" + args[1]);
			}
			sites = Importer.normalize(sites);
			StdDraw.setCanvasSize(1024, 1024);
			StdDraw.setScale(-1.1, 1.1);
			Voronoi v = new Voronoi(sites, true);
			v.show();
		}
		//Args[0]: Random - generates random points
		//Args[1]: N - Number of sites
		else if (args[0].equals("Random") && args.length == 2) {
			int N = Integer.parseInt(args[1]);
			ArrayList<Point> sites = new ArrayList<Point>();
			Random rnd = new Random();
			for (int i = 0; i < N; i++) {
				sites.add(new Point(rnd.nextDouble(), rnd.nextDouble()));
			}
			StdDraw.setCanvasSize(1024, 1024);
			StdDraw.setScale(-1.1, 1.1);
			Voronoi v = new Voronoi(sites, true);
			v.show();
		}
		else {
			
		}
	}
	
//	public static void main(String[] args) throws FileNotFoundException {
//
//		System.out.println("Running App...");
//		
////		//Initializing
////		Vertex v1 = new Vertex();
////		Vertex v2 = new Vertex();
////		Vertex v3 = new Vertex();
////		
////		HalfEdge e12 = new HalfEdge();
////		HalfEdge e21 = new HalfEdge();
////		HalfEdge e23 = new HalfEdge();
////		HalfEdge e32 = new HalfEdge();
////		HalfEdge e31 = new HalfEdge();
////		HalfEdge e13 = new HalfEdge();
////		
////		Face f1 = new Face();
////		Face f2 = new Face();
////		
////		//Setting dependencies
////		v1 = new Vertex(new Point(0,0), e12);
////		v2 = new Vertex(new Point(1,0), e23);
////		v3 = new Vertex(new Point(0,1), e31);
////
////		e12 = new HalfEdge(v1, e21, f1, e23, e31);
////		e23 = new HalfEdge(v2,v3);
////		e31 = new HalfEdge(v3,v1);
////		e21 = new HalfEdge(v2,v1);
////		e32 = new HalfEdge(v3,v2);
////		e13 = new HalfEdge(v1,v3);
////
////		f1 = new Face(e12, null);
////		f2 = new Face(null, e21);
////
////		DCEL dcel = new DCEL();
////		dcel.addVertex(v1);
////		dcel.addVertex(v2);
////		dcel.addVertex(v3);
////
////		dcel.addHalfEdge(e12);
////		dcel.addHalfEdge(e21);
////		dcel.addHalfEdge(e23);
////		dcel.addHalfEdge(e32);
////		dcel.addHalfEdge(e31);
////		dcel.addHalfEdge(e13);
////
////		dcel.addFace(f1);
////		dcel.addFace(f2);
////		
////		System.out.println(dcel.toString());
//		
//		
//	}
}
