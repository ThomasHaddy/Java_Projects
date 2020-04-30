package voronoi_diagram;

import stdlib.StdDraw;
import java.awt.Color;

public class BreakPoint {
	
	private final Voronoi voronoi;
	
	private final Point left, right;
	
	private VoronoiEdge e;
	
	private boolean isEdgeLeft;
	
	private final Point edgeBegin;

	private double cacheSweepLoc;
	
	private Point cachePoint;

	public BreakPoint(Point left, Point right, VoronoiEdge e, boolean isEdgeLeft, Voronoi voronoi) {
		
		this.voronoi = voronoi;
		this.left = left;
		this.right = right;
		this.e = e;
		this.isEdgeLeft = isEdgeLeft;
		this.edgeBegin = this.getPoint();
	}
	
	public Point getLeft() {
		
		return left;
	}
	
	public Point getRight() {
		
		return right;
	}
	
	public Point getEdgeBegin() {
		
		return edgeBegin;
	}

	public void draw() {
		
		Point p = this.getPoint();
		p.draw(Color.BLUE);
		StdDraw.setPenColor(Color.BLUE);
		StdDraw.line(edgeBegin.getX(), edgeBegin.getY(), p.getX(), p.getY());
		StdDraw.setPenColor();
		if (isEdgeLeft && e.p2 != null) {
			StdDraw.line(edgeBegin.getX(), edgeBegin.getY(), e.p2.getX(), e.p2.getY());
		}
		else if (!isEdgeLeft && e.p1 != null) {
			StdDraw.line(edgeBegin.getX(), edgeBegin.getY(), e.p1.getX(), e.p1.getY());
		}
	}

	public void finish(Point vert) {
		
		if (isEdgeLeft) {
			this.e.p1 = vert;
		}
		else {
			this.e.p2 = vert;
		}
	}

	public void finish() {
		
		Point p = this.getPoint();
		if (isEdgeLeft) {
			this.e.p1 = p;
		}
		else {
			this.e.p2 = p;
		}
	}

	public Point getPoint() {
		
		double l = voronoi.getSweepLoc();
		if (l == cacheSweepLoc) {
			return cachePoint;
		}
		cacheSweepLoc = l;

		double x,y;
		// Handle the vertical line case
		if (left.getY() == right.getY()) {
			x = (left.getX() + right.getX()) / 2; // x coordinate is between the two sites
			// comes from parabola focus-directrix definition:
			y = (Math.pow(x - left.getX(),2) + Math.pow(left.getY(),2) - Math.pow(l,2)) / (2* (left.getY() - l));
		}
		else {
			// This method works by intersecting the line of the edge with the parabola of the higher point
			// I'm not sure why I chose the higher point, either should work
			double px = (left.getY() > right.getY()) ? left.getX() : right.getX();
			double py = (left.getY() > right.getY()) ? left.getY() : right.getY();
			double m = e.m;
			double b = e.b;

			double d = 2*(py - l);

			// Straight up quadratic formula
			double A = 1;
			double B = -2*px - d*m;
			double C = Math.pow(px,2) + Math.pow(py,2) - Math.pow(l,2) - d*b;
			int sign = (left.getY() > right.getY()) ? -1 : 1;
			double det = Math.pow(B,2) - 4 * A * C;
			// When rounding leads to a very very small negative determinant, fix it
			if (det <= 0) {
				x = -B / (2 * A);
			}
			else {
				x = (-B + sign * Math.sqrt(det)) / (2 * A);
			}
			y = m*x + b;
		}
		cachePoint = new Point(x, y);
		return cachePoint;
	}

	public VoronoiEdge getEdge() {
		
		return this.e;
	}
	
	@Override
	public String toString() {
		
		return String.format("%s \tleft: %s\tright: %s", this.getPoint(), this.left, this.right);
	}
}
