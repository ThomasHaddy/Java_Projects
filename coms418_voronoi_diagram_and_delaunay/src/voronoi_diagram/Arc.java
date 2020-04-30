package voronoi_diagram;

/**
 * 
 * @author Thomas Haddy
 *
 */
public class Arc extends ArcKey {
	
	private final Voronoi v;
	
	public BreakPoint left, right;
	
	public final Point site;

	public Arc(BreakPoint left, BreakPoint right, Voronoi v) {
		
		this.v = v;
		if (left == null && right == null) {
			throw new RuntimeException("cannot make arc with null breakpoints");
		}
		this.left = left;
		this.right = right;
		this.site = (left != null) ? left.s2 : right.s1;
	}

	public Arc(Point site, Voronoi v) {
		
		// Only for creating the first org.ajwerner.voronoi.Arc
		this.v = v;
		this.left = null;
		this.right = null;
		this.site = site;
	}

	public Point getRight() {
		
		if (right != null) return right.getPoint();
		
		return new Point(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
	}

	public Point getLeft() {
		
		if (left != null) return left.getPoint();
		
		return new Point(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
	}

	public void draw() {
		
		Point l = getLeft();
		Point r = getRight();

		Parabola par = new Parabola(site, v.getSweepLoc());
		double min = (l.getX() == Double.NEGATIVE_INFINITY) ? Voronoi.MIN_DRAW_DIM : l.getX();
		double max = (r.getX() == Double.POSITIVE_INFINITY) ? Voronoi.MAX_DRAW_DIM : r.getX();
		par.draw(min, max);
	}

	public String toString() {
		
		Point l = getLeft();
		Point r = getRight();

		return String.format("{%.4f, %.4f}", l.getX(), r.getX());
	}

	public Point checkCircle() {
		
		if ((this.left == null) || (this.right == null)) return null;
		if (Point.compareTurnDirection(this.left.s1, this.site, this.right.s2) != -1) return null;
		
		return (this.left.getEdge().intersection(this.right.getEdge()));
	}
}
