package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;

import util.Point;
import util.Sites;

import org.junit.jupiter.api.Test;

class SitesTests {

	/*
	 * Constructor for Sites test
	 */
	@Test
	void constructorTest() {
		LinkedList<Point> points = new LinkedList<>();
		points.add(new Point(0,0));
		points.add(new Point(1,1));
		points.add(new Point(2,3));
		points.add(new Point(3,2));
		points.add(new Point(4,4));
		points.add(new Point(4,4));
		Sites sites = new Sites(points);
		
		assertEquals(new Point(0,0), sites.getSites().get(0));
		assertEquals(new Point(1,1), sites.getSites().get(1));
		assertEquals(new Point(2,3), sites.getSites().get(2));
		assertEquals(new Point(3,2), sites.getSites().get(3));
		assertEquals(new Point(4,4), sites.getSites().get(4));
		assertEquals(new Point(4,4), sites.getSites().get(5));
	}
	
	/*
	 * Sorting test
	 */
	@Test
	void sortTest() {
		LinkedList<Point> points = new LinkedList<>();
		points.add(new Point(3,2));
		points.add(new Point(0,0));
		points.add(new Point(4,5));
		points.add(new Point(2,3));
		points.add(new Point(4,4));
		points.add(new Point(1,1));
		Sites sites = new Sites(points);
		LinkedList<Point> sorted = sites.sortPoints();
		
		//Test sorted
		assertEquals(new Point(0,0), sorted.get(0));
		assertEquals(new Point(1,1), sorted.get(1));
		assertEquals(new Point(2,3), sorted.get(2));
		assertEquals(new Point(3,2), sorted.get(3));
		assertEquals(new Point(4,4), sorted.get(4));
		assertEquals(new Point(4,5), sorted.get(5));
		
		//Make sure original data structure isn't changed
		assertEquals(new Point(3,2), sites.getSites().get(0));
		assertEquals(new Point(0,0), sites.getSites().get(1));
		assertEquals(new Point(4,5), sites.getSites().get(2));
		assertEquals(new Point(2,3), sites.getSites().get(3));
		assertEquals(new Point(4,4), sites.getSites().get(4));
		assertEquals(new Point(1,1), sites.getSites().get(5));
	}

	/*
	 * String representation test
	 */
	@Test
	void toStringTest() {
		LinkedList<Point> points = new LinkedList<>();
		points.add(new Point(3,2));
		points.add(new Point(0,0));
		points.add(new Point(4,5));
		points.add(new Point(2,3));
		points.add(new Point(4,4));
		points.add(new Point(1,1));
		Sites sites = new Sites(points);
		
		assertEquals("{(3, 2), (0, 0), (4, 5), (2, 3), (4, 4), (1, 1)}", sites.toString());
	}
	
}
