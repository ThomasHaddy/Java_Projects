package voronoi_diagram;

import stdlib.StdDraw;

import java.awt.*;

public class BreakPoint {
	private final Voronoi v;
	protected final Point s1, s2;
	private VoronoiEdge e;
	private boolean isEdgeLeft;
	public final Point edgeBegin;

	private double cacheSweepLoc;
	private Point cachePoint;

	public BreakPoint(Point left, Point right, VoronoiEdge e, boolean isEdgeLeft, Voronoi v) {
		this.v = v;
		this.s1 = left;
		this.s2 = right;
		this.e = e;
		this.isEdgeLeft = isEdgeLeft;
		this.edgeBegin = this.getPoint();
	}

	private static double sq(double d) {
		return d * d;
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
		double l = v.getSweepLoc();
		if (l == cacheSweepLoc) {
			return cachePoint;
		}
		cacheSweepLoc = l;

		double x,y;
		// Handle the vertical line case
		if (s1.getY() == s2.getY()) {
			x = (s1.getX() + s2.getX()) / 2; // x coordinate is between the two sites
			// comes from parabola focus-directrix definition:
			y = (sq(x - s1.getX()) + sq(s1.getY()) - sq(l)) / (2* (s1.getY() - l));
		}
		else {
			// This method works by intersecting the line of the edge with the parabola of the higher point
			// I'm not sure why I chose the higher point, either should work
			double px = (s1.getY() > s2.getY()) ? s1.getX() : s2.getX();
			double py = (s1.getY() > s2.getY()) ? s1.getY() : s2.getY();
			double m = e.m;
			double b = e.b;

			double d = 2*(py - l);

			// Straight up quadratic formula
			double A = 1;
			double B = -2*px - d*m;
			double C = sq(px) + sq(py) - sq(l) - d*b;
			int sign = (s1.getY() > s2.getY()) ? -1 : 1;
			double det = sq(B) - 4 * A * C;
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

	public String toString() {
		return String.format("%s \ts1: %s\ts2: %s", this.getPoint(), this.s1, this.s2);
	}

	public VoronoiEdge getEdge() {
		return this.e;
	}
}
