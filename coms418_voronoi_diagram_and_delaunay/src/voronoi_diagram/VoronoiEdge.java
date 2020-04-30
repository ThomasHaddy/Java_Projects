package voronoi_diagram;


public class VoronoiEdge {
	
	private final Point from, to;
	
	public final double m, b; // parameters for line that the edge lies on
	
	public final boolean isVertical;
	
	public Point p1, p2;

	public VoronoiEdge(Point from, Point to) {
		this.from = from;
		this.to = to;
		isVertical = (from.getY() == to.getY()) ? true : false;
		if (isVertical) m = b = 0;
		else {
			m = -1.0 / ((from.getY() - to.getY()) / (from.getX() - to.getX()));
			Point midpoint = Point.midpoint(from, to);
			b = midpoint.getY() - m*midpoint.getX();
		}
	}

	public Point intersection(VoronoiEdge that) {
		if (this.m == that.m && this.b != that.b && this.isVertical == that.isVertical) return null; // no intersection
		double x, y;
		if (this.isVertical) {
			x = (this.from.getX() + this.to.getX()) / 2;
			y = that.m*x + that.b;
		}
		else if (that.isVertical) {
			x = (that.from.getX() + that.to.getX()) / 2;
			y = this.m*x + this.b;
		}
		else {
			x = (that.b - this.b) / (this.m - that.m);
			y = m * x + b;
		}
		return new Point(x, y);
	}
}
