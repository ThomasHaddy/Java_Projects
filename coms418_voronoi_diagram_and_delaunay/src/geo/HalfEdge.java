package geo;

public class HalfEdge {

	private Vertex origin;

	private HalfEdge twin;

	private Face incidentFace;

	private HalfEdge next;

	private HalfEdge prev;

	public HalfEdge(Vertex origin, HalfEdge twin, Face incidentFace, HalfEdge next, HalfEdge prev) {

		this.origin = origin;
		this.twin = twin;
		this.incidentFace = incidentFace;
		this.next = next;
		this.prev = prev;
	}
	
	public HalfEdge(Vertex origin, Vertex next) {

		this.origin = origin;
		this.twin = new HalfEdge(next, this, null, null, null);
		this.incidentFace = null;
		this.next = null;
		this.prev = null;
	}

	public String getName() {
		return "e" + origin.getName().substring(1) + "," + twin.getOrigin().getName().substring(1);
	}
	
	public Vertex getOrigin() {
		return origin;
	}
	
	public void setOrigin(Vertex origin) {
        this.origin = origin;
    }

	public HalfEdge getTwin() {
		return twin;
	}

	public Face getIncidentFace() {
		return incidentFace;
	}
	
	public void setIncidentFace(Face f) {
		this.incidentFace = f;
	}

	public HalfEdge getNext() {
		return next;
	}

	public void setNext(HalfEdge next) {
		this.next = next;
	}

	public HalfEdge getPrev() {
		return prev;
	}

	public void setPrev(HalfEdge prev) {
		this.prev = prev;
	}

	@Override
	public String toString() {
		return this.getName();
	}
}

