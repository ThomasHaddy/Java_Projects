package test;

import static org.junit.jupiter.api.Assertions.*;

import geo.Face;
import geo.HalfEdge;
import geo.Point;
import geo.Vertex;

import org.junit.jupiter.api.Test;

class FaceTests {

	/*
	 * Constructor 1 test
	 */
	@Test
	void constructTest1() {
		Vertex v1 = new Vertex("v1", new Point(1,1), null);
		Vertex v2 = new Vertex("v2", new Point(2,2), null);
		HalfEdge e = new HalfEdge(v1,v2);
		Face f = new Face("f1", e, null);
		
		assertEquals("f1", f.getName());
		assertEquals("e1,2", f.getInnerComp().toString());
		assertEquals("v1 (1, 1) nil", f.getInnerComp().getOrigin().toString());
		assertEquals("v2 (2, 2) nil", f.getInnerComp().getTwin().getOrigin().toString());
	}
	
	/*
	 * String representation test
	 */
	@Test
	void toStringTest() {
		Vertex v1 = new Vertex("v1", new Point(1,1), null);
		Vertex v2 = new Vertex("v2", new Point(2,2), null);
		HalfEdge e = new HalfEdge(v1,v2);
		Face f1 = new Face("f1", null, null);
		Face f2 = new Face("f2", e, null);
		Face f3 = new Face("f3", null, e);
		
		assertEquals("f1 nil nil", f1.toString());
		assertEquals("f2 e1,2 nil", f2.toString());
		assertEquals("f3 nil e1,2", f3.toString());
	}

}
