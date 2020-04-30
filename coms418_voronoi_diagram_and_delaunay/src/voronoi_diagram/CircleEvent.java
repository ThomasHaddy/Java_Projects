package voronoi_diagram;

import stdlib.StdDraw;

public class CircleEvent extends Event {
	
	private final Arc arc;
	
	private final Point vertex;

	public CircleEvent(Arc a, Point p, Point vertex) {
		super(p);
		this.arc = a;
		this.vertex = vertex;
	}
	
	public Arc getArc() {
		
		return arc;
	}
	
	public Point getVertex() {
		
		return vertex;
	}

	public void draw() {
		
		this.getPoint().draw(StdDraw.GREEN);
		StdDraw.circle(vertex.getX(), vertex.getY(), (vertex.getY() - getPoint().getY()));
	}
}
