package voronoi_diagram;

/**
 * Arc is responsible for creating arcs during the execution of the VD GUI.
 * @author Thomas Haddy
 */
public class Arc extends ArcKey {
	
	private final Voronoi voronoi;
	
	private BreakPoint left, right;
	
	private final Point site;

	public Arc(BreakPoint left, BreakPoint right, Voronoi voronoi) {
		
		this.voronoi = voronoi;
		if (left == null && right == null) {
			throw new RuntimeException("Arc contains left and right Null BreakPoints");
		}
		this.left = left;
		this.right = right;
		this.site = (left != null) ? left.getRight() : right.getLeft();
	}

	public Arc(Point site, Voronoi voronoi) {
		
		// Only for creating the first org.ajwerner.voronoi.Arc
		this.voronoi = voronoi;
		this.left = null;
		this.right = null;
		this.site = site;
	}
	
	public BreakPoint getBreakPointRight() {
		
		return this.right;
	}
	
	public void setBreakPointRight(BreakPoint right) {
		
		this.right = right;
	}
	 
	public BreakPoint getBreakPointLeft() {
		
		return this.left;
	}
	 
	public void setBreakPointLeft(BreakPoint left) {
		
		this.left = left;
	}
	
	public Point getSite() {
		return site;
	}
	
	public Voronoi getVoronoi() {
		return voronoi;
	}

	public Point getRightPoint() {
		
		if (right != null) return right.getPoint();
		
		return new Point(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
	}

	public Point getLeftPoint() {
		
		if (left != null) return left.getPoint();
		
		return new Point(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
	}

	public void draw() {
		
		Point l = getLeftPoint();
		Point r = getRightPoint();

		ParabolaDraw par = new ParabolaDraw(site, voronoi.getSweepLoc());
		double min = l.getX();
		double max = r.getX();
		par.draw(min, max);
	}

	public Point checkCircle() {
		
		if ((this.left == null) || (this.right == null)) return null;
		if (Point.compareTurnDirection(this.left.getLeft(), this.site, this.right.getRight()) != -1) return null;
		
		return (this.left.getEdge().intersection(this.right.getEdge()));
	}
	
	@Override
	public String toString() {
		
		Point l = getLeftPoint();
		Point r = getRightPoint();

		return String.format("{%.2f, %.2f}", l.getX(), r.getX());
	}
}
