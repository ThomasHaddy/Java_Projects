package voronoi_diagram;

import java.awt.Color;
import stdlib.StdDraw;

/**
 * A Point represents an ordered pair (x, y).
 * @author Thomas Haddy
 *
 */
public class Point implements Comparable<Point> {
	
	private final double x; 
	
	private final double y;
	
	public Point(double x, double y) {
		
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		
		return this.x;
	}
	
	public double getY() {
		
		return this.y;
	}

	/**
	 * Gets the midpoint between 2 points
	 * @param a		Point a
	 * @param b		Point b
	 * @return		A new point with (x, y) being the midpoint between a and b
	 */
	public static Point midpoint(Point a, Point b) {
		
		double x = (a.x + b.x) / 2;
		double y = (a.y + b.y) / 2;
		
		return new Point(x, y);
	}

	/**
	 * Compare Points a,b,c to see if they form a clockwise, collinear, or counterclockwise turn
	 * @param a 	First Point
	 * @param b 	Second Point
	 * @param c 	Third Point
	 * @return 		{ -1, 0, +1 } if a,b,c is a Clockwise, Collinear, Counterclockwise turn, respectively.
	 */
	public static int compareTurnDirection(Point a, Point b, Point c) {
		
		double area = (b.x-a.x) * (c.y-a.y) - (b.y-a.y) * (c.x-a.x);
		if      (area < 0) return -1;
		else if (area > 0) return +1;
		else                return  0;
	}

	/**
	 * Get the distance between two Points
	 * @param from	Point from
	 * @param to	Point to
	 * @return		The distance between Point from and to
	 */
	public double distance(Point from, Point to) {
		
		return Math.sqrt(Math.pow(from.x - to.x,2) + Math.pow(from.y - to.y, 2));
	}
	
	/**
	 * Compares two points y-values for the Event Queue
	 * @param p1  	Point p1
	 * @param p2	Point p2
	 * @return		-1 if p1 < p2, 1 if p1 > p2, 0 if p1 = p2
	 */
	public static int yCompareTo(Point p1, Point p2) {
		
		if (p1.y < p2.y) return 1;
		if (p1.y > p2.y) return -1;
		if (p1.x == p2.x && p1.y == p2.y) return 0;
		
		return (p1.x < p2.x) ? -1 : 1;
	}

	/**
	 * Draw a Point on StdDraw
	 */
	public void draw() {
		
		StdDraw.setPenRadius(.01);
		StdDraw.point(x, y);
		StdDraw.setPenRadius();
	}

	/**
	 * Draw a Point with Color c on StdDraw
	 * @param c		Color c
	 */
	public void draw(Color c) {
		
		Color old = StdDraw.getPenColor();
		StdDraw.setPenColor(c);
		this.draw();
		StdDraw.setPenColor(old);
	}

	@Override
	public String toString() {
		
		return String.format("(%.2f, %.2f)", this.x, this.y);
	}
	
	@Override
	public boolean equals(Object o) {
		
		if (this == o)
			return true;

		if (!Point.class.isAssignableFrom(o.getClass()))
			return false;

		final Point b = (Point) o;
		if (this.x == b.x && this.y == b.y)
			return true;

		return false;
	}
	
	@Override
	public int compareTo(Point other) {
		
		if (this.x < other.x || (this.x == other.x && this.y < other.y))
			return -1;
		else if (this.x > other.x || (this.x == other.x && this.y > other.y))
			return 1;
		return 0;
	}
}