package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geo.Point;

class PointTests {

	/*
	 * Constructor test
	 */
	@Test
	void constructTest() {
		Point p = new Point(1,1);
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 1);
	}
	
	/*
	 * Setting the x of a point test
	 */
	@Test
	void setXTest() {
		Point p = new Point(1,1);
		p.setX(3);
		assertEquals(3, p.getX());
	}
	
	/*
	 * Setting the y of a point test
	 */
	@Test
	void setYTest() {
		Point p = new Point(1,1);
		p.setY(5);
		assertEquals(5, p.getY());
	}
	
	/*
	 * String representation test
	 */
	@Test
	void toStringTest() {
		Point p = new Point(1,1);
		assertEquals("(1, 1)", p.toString());
	}
	
	/*
	 * Check if two points are equal test
	 */
	@Test
	void equalsTest1() {
		Point p1 = new Point(1,1);
		Point p2 = new Point(1,1);
		assertEquals(true, p1.equals(p2));
	}
	
	/*
	 * Check if two points are not equal test
	 */
	@Test
	void equalsTest2() {
		Point p1 = new Point(1,1);
		Point p2 = new Point(-1,1);
		assertEquals(false, p1.equals(p2));
	}
	
	/*
	 * Check if p1 is less than p2 test
	 */
	@Test
	void compareTest1() {
		Point p1 = new Point(-1,1);
		Point p2 = new Point(1,1);
		assertEquals(-1, p1.compareTo(p2));
	}

	/*
	 * Check if p1 is less than p2 test
	 */
	@Test
	void compareTest2() {
		Point p1 = new Point(1,-1);
		Point p2 = new Point(1,1);
		assertEquals(-1, p1.compareTo(p2));
	}
	
	/*
	 * Check if p1 is equal to p2 test
	 */
	@Test
	void compareTest3() {
		Point p1 = new Point(1,1);
		Point p2 = new Point(1,1);
		assertEquals(0, p1.compareTo(p2));
	}
	
	/*
	 * Check if p1 is greater than p2 test
	 */
	@Test
	void compareTest4() {
		Point p1 = new Point(1,1);
		Point p2 = new Point(-1,1);
		assertEquals(1, p1.compareTo(p2));
	}
	
	/*
	 * Check if p1 is greater than p2 test
	 */
	@Test
	void compareTest5() {
		Point p1 = new Point(1,1);
		Point p2 = new Point(1,-1);
		assertEquals(1, p1.compareTo(p2));
	}

}
