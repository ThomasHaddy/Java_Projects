package geo;

public class Vertex {

	private String name;
	private Point p;
	private HalfEdge incidentEdge;

	public Vertex(String name, Point p, HalfEdge incidentEdge) {
		this.name = name;
		this.p = p;
		this.incidentEdge = incidentEdge;
	}

	public Point getPoint() {
		return p;
	}

	public HalfEdge getIncidentEdge() {
		return incidentEdge;
	}
	
	public void setIncidentEdge(HalfEdge e) {
		this.incidentEdge = e;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		if (incidentEdge == null)
			return name + " " + p.toString() + " nil";
		return name + " " + p.toString() + " " + incidentEdge.toString();
	}

}
