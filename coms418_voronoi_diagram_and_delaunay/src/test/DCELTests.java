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
	 * Constructor 1 test. Testing the spec's given DCEL (A triangle)
	 */
	@Test
	void constructTest1() {
		
		//Initializing DCEL
		Vertex v1 = new Vertex("v1", new Point(0,0), null);
		Vertex v2 = new Vertex("v2", new Point(1,0), null);
		Vertex v3 = new Vertex("v3", new Point(0,1), null);
		
		HalfEdge e1 = new HalfEdge(v1,v2);
		HalfEdge e2 = new HalfEdge(v2,v3);
		HalfEdge e3 = new HalfEdge(v3,v1);
		
		Face f1 = new Face("f1", e2, null);
		Face f2 = new Face("f2", null, e1.getTwin());
		
		//Setting DCEL incidents and dependencies
		v1 = new Vertex(v1.getName(), v1.getPoint(), e1);
		v2 = new Vertex(v2.getName(), v2.getPoint(), e2);
		v3 = new Vertex(v3.getName(), v3.getPoint(), e3);
		
		e1 = new HalfEdge(v1, e1.getTwin(), f1, e2, e3);
		e2 = new HalfEdge(v2, e2.getTwin(), f1, e3, e1);
		e3 = new HalfEdge(v3, e3.getTwin(), f1, e1, e2);
		
		e1.getTwin().setIncidentFace(f2);
		e2.getTwin().setIncidentFace(f2);
		e3.getTwin().setIncidentFace(f2);
		
		DCEL dcel = new DCEL();
		dcel.addVertex(v1);
		dcel.addVertex(v2);
		dcel.addVertex(v3);
		
		dcel.addHalfEdge(e1);
		dcel.addHalfEdge(e2);
		dcel.addHalfEdge(e3);
		
		dcel.addFace(f1);
		dcel.addFace(f2);
		
		System.out.println(dcel.toString());
	}

}
