package test;

import static org.junit.jupiter.api.Assertions.*;

import geo.HalfEdge;
import geo.Point;
import geo.Vertex;

import org.junit.jupiter.api.Test;

class VertexTests {

	/*
	 * Constructor test
	 */
	@Test
	void constructTest() {
		Vertex v1 = new Vertex("v1", new Point(1,1), null);
		assertEquals(v1.getPoint(), new Point(1,1));
		assertEquals("v1 (1, 1) nil", v1.toString());
	}
	
	/*
	 * get Point corresponding to Vertex test
	 */
	@Test
	void getPointTest() {
		Vertex v1 = new Vertex("v1", new Point(2,1), null);
		Vertex v2 = new Vertex("v1", new Point(1,2), null);
		
		assertEquals(v1.getPoint(), new Point(2,1));
		assertEquals(v2.getPoint(), new Point(1,2));
		assertEquals(false, v1.equals(v2));
	}
	
	/*
	 * Get half edge corresponding to Vertex test
	 */
	@Test
	void getIncidentEdgeTest() {
		
		Vertex v1 = new Vertex("v1", new Point(2,1), null);
		Vertex v2 = new Vertex("v2", new Point(1,2), null);
		HalfEdge e1 = new HalfEdge(v1,v2);
		HalfEdge e2 = new HalfEdge(v2,v1);
		v1.setIncidentEdge(e1);
		v2.setIncidentEdge(e2);
		
		assertEquals("e1,2", v1.getIncidentEdge().toString());
		assertEquals("e2,1", v2.getIncidentEdge().toString());
	}
	
	/*
	 * Getting the name of the vertex test
	 */
	@Test
	void getnameTest() {
		Point p1 = new Point(1,1);
		Point p2 = new Point(1,1);
		assertEquals(true, p1.equals(p2));
	}
	
	/*
	 * String representation test
	 */
	@Test
	void toStringTest() {
		Vertex v1 = new Vertex("v1", new Point(2,1), null);
		assertEquals("v1 (2, 1) nil", v1.toString());
	}

}
