package dcel;

/**
 * DCEL representation of a Vertex. A Vertex includes a Point p, and Incident Edge e.
 * A Vertex must have a unique name and a Point p.
 * @author Thomas Haddy
 *
 */
public class Vertex {

	private Point p;

	private HalfEdge incidentEdge;

	public Vertex() {}
	
	public Vertex(Point p) {
		this.p = p;
		this.incidentEdge = null;
	}

	public Vertex(Point p, HalfEdge incidentEdge) {
		this.p = p;
		this.incidentEdge = incidentEdge;
	}

	public Point getPoint() {
		return p;
	}

	public void setPoint(Point p) {
		this.p = p;
	}

	public HalfEdge getIncidentEdge() {
		return incidentEdge;
	}

	public void setIncidentEdge(HalfEdge e) {
		this.incidentEdge = e;
	}

	public double getX() {
		return p.getX();
	}

	public double getY() {
		return p.getY();
	}

}
