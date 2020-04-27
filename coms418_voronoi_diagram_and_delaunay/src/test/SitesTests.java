package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.LinkedList;

import util.Point;
import util.Sites;
import util.Sites.Importer;

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
		
		assertEquals(new Point(0,0), sites.get(0));
		assertEquals(new Point(1,1), sites.get(1));
		assertEquals(new Point(2,3), sites.get(2));
		assertEquals(new Point(3,2), sites.get(3));
		assertEquals(new Point(4,4), sites.get(4));
		assertEquals(new Point(4,4), sites.get(5));
	}
	
	/*
	 * Sorting test
	 */
	@Test
	void sortTest() {
		Sites sites = new Sites(new LinkedList<Point>());
		sites.add(new Point(3,2));
		sites.add(new Point(0,0));
		sites.add(new Point(4,5));
		sites.add(new Point(2,3));
		sites.add(new Point(4,4));
		sites.add(new Point(1,1));
		
		LinkedList<Point> sorted = sites.sortPoints();
		
		//Test sorted
		assertEquals(new Point(0,0), sorted.get(0));
		assertEquals(new Point(1,1), sorted.get(1));
		assertEquals(new Point(2,3), sorted.get(2));
		assertEquals(new Point(3,2), sorted.get(3));
		assertEquals(new Point(4,4), sorted.get(4));
		assertEquals(new Point(4,5), sorted.get(5));
		
		//Make sure original data structure isn't changed
		assertEquals(new Point(3,2), sites.get(0));
		assertEquals(new Point(0,0), sites.get(1));
		assertEquals(new Point(4,5), sites.get(2));
		assertEquals(new Point(2,3), sites.get(3));
		assertEquals(new Point(4,4), sites.get(4));
		assertEquals(new Point(1,1), sites.get(5));
	}
	
	/*
	 * Import txt file test
	 */
	@Test
	void importerTest() throws FileNotFoundException {
		
		Sites sites1 = new Sites(new LinkedList<Point>());
		sites1.add(new Point(2,-3));
		sites1.add(new Point(-10,9));
		sites1.add(new Point(0,0));
		sites1.add(new Point(-5,-2));
		sites1.add(new Point(4,-7));
		
		Sites sites2 = new Sites(new LinkedList<Point>());
		Importer.parseInputFile(sites2, "C:/Users/Thomas/Documents/Java_Projects/coms418_voronoi_diagram_and_delaunay/test.txt");
		
		assertEquals(sites1.toString(), sites2.toString());
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
