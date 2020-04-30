package dcel;

/**
 * DCEL representation of a Half Edge. A Half Edge has a name given from its origin Vertex and Origin(Twin e).
 * Besides its name, a Half Edge e has an origin vertex, Half Edge twin, an Incident Face f, and a Pointer to the next and prev Half Edge.
 * To initialize a Half Edge, there must be at least a given Origin vertex. 
 * You can set its dependencies later once the faces and vertices have been created.
 * @author Thomas Haddy
 *
 */
public class HalfEdge {
	
	private Vertex origin;

	private HalfEdge twin;

	private Face incidentFace;

	private HalfEdge next;

	private HalfEdge prev;

	public HalfEdge() {}
	
	public HalfEdge(Vertex origin, HalfEdge twin, Face incidentFace, HalfEdge next, HalfEdge prev) {

		this.origin = origin;
		this.twin = twin;
		this.incidentFace = incidentFace;
		this.next = next;
		this.prev = prev;
	}
	
	public HalfEdge(Vertex origin, Vertex to) {

		this.origin = origin;
		this.twin = to.getIncidentEdge();
		this.incidentFace = null;
		this.next = this.twin.prev.twin;
		this.prev = this.twin.next.twin;
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
	
}

