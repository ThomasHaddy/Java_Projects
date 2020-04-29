package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import data_structures.DCEL;
import geo.Face;
import geo.HalfEdge;
import geo.Point;
import geo.Vertex;

class DCELTests {
	
	/*
	 * Testing iterating through the HalfEdge list
	 */
	@Test
	void halfEdgeTest() {
		
		//Initializing
		Vertex v1 = new Vertex(new Point(0,0));
		Vertex v2 = new Vertex(new Point(1,0));
		Vertex v3 = new Vertex(new Point(0,1));

		HalfEdge e12 = new HalfEdge(v1,v2);
		HalfEdge e21 = new HalfEdge(v2,v1);
		HalfEdge e23 = new HalfEdge(v2,v3);
		HalfEdge e32 = new HalfEdge(v3,v2);
		HalfEdge e31 = new HalfEdge(v3,v1);
		HalfEdge e13 = new HalfEdge(v1,v3);

		Face f1 = new Face(null, null);
		Face f2 = new Face(null, null);

		//Setting dependencies
		v1.setIncidentEdge(e12);
		v2.setIncidentEdge(e23);
		v3.setIncidentEdge(e31);
		
		
		DCEL dcel = new DCEL();
		dcel.addVertex(v1);
		dcel.addVertex(v2);
		dcel.addVertex(v3);
		
		dcel.addHalfEdge(e12);
		dcel.addHalfEdge(e21);
		dcel.addHalfEdge(e23);
		dcel.addHalfEdge(e32);
		dcel.addHalfEdge(e31);
		dcel.addHalfEdge(e13);
		
		dcel.addFace(f1);
		dcel.addFace(f2);
		
		System.out.println(dcel.toString());
	}

}
