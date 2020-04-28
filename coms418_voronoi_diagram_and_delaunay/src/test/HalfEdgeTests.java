package test;

import static org.junit.jupiter.api.Assertions.*;

import geo.HalfEdge;
import geo.Point;
import geo.Vertex;

import org.junit.jupiter.api.Test;

class HalfEdgeTests {

	/*
	 * Constructor 1 test
	 */
	@Test
	void constructTest1() {
		Vertex v1 = new Vertex("v1", new Point(1,1), null);
		Vertex v2 = new Vertex("v2", new Point(2,2), null);
		HalfEdge e = new HalfEdge(v1,v2);
		v1.setIncidentEdge(e);
		v2.setIncidentEdge(e.getTwin());
		
		assertEquals("e1,2", e.toString());
		assertEquals("v1 (1, 1) e1,2", e.getOrigin().toString());
		assertEquals("v2 (2, 2) e2,1", e.getTwin().getOrigin().toString());
	}
	
	/*
	 * Constructor 2 test
	 */
	@Test
	void constructTest2() {
		
	}
	
	/*
	 * get origin for edge test
	 */
	@Test
	void getOriginTest() {
		Vertex v1 = new Vertex("v1", new Point(1,1), null);
		Vertex v2 = new Vertex("v2", new Point(2,2), null);
		HalfEdge e = new HalfEdge(v1,v2);
		v1.setIncidentEdge(e);
		v2.setIncidentEdge(e.getTwin());
		
		assertEquals("v1 (1, 1) e1,2", e.getOrigin().toString());
		assertEquals("v2 (2, 2) e2,1", e.getTwin().getOrigin().toString());
	}
	
	/*
	 * String representation test
	 */
	@Test
	void toStringTest() {
		Vertex v1 = new Vertex("v1", new Point(1,1), null);
		Vertex v2 = new Vertex("v2", new Point(2,2), null);
		HalfEdge e = new HalfEdge(v1,v2);
		
		assertEquals("e1,2", e.toString());
	}

}
