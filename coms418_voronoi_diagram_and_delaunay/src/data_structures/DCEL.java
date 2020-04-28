package data_structures;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import geo.Vertex;
import geo.HalfEdge;
import geo.Face;

public class DCEL {

	public static class Exporter {
		
	}
	
	private List<Vertex> vertices;
	
	private List<HalfEdge> halfEdges;
	
	private List<Face> faces;
	
	public DCEL () {
		vertices = new LinkedList<Vertex>();
		halfEdges = new LinkedList<HalfEdge>();
		faces = new LinkedList<Face>();
	}
	
	public LinkedList<Vertex> getVertices() {
		return (LinkedList<Vertex>) vertices;
	}
	
	public LinkedList<HalfEdge> getHalfEdges() {
		return (LinkedList<HalfEdge>) halfEdges;
	}
	
	public LinkedList<Face> getFaces() {
		return (LinkedList<Face>) faces;
	}
	
	public void addVertex(Vertex v) {
		vertices.add(v);
	}
	
	public void addHalfEdge(HalfEdge e) {
		halfEdges.add(e);
	}
	
	public void addFace(Face f) {
		faces.add(f);
	}
	
	public Vertex removeVertex(Vertex v) {
		
		if (vertices.isEmpty())
			return null;
		
		Vertex toRemove = null;
		int index = 0;
		for (Vertex vi : vertices) {
			
			if (vi.getName().equals(v.getName())) {
				toRemove = vi;
				vertices.remove(index);
			}
			index++;
		}
		return toRemove;
	}
	
	public HalfEdge removeHalfEdge(HalfEdge e) {
		
		if (halfEdges.isEmpty())
			return null;
		
		HalfEdge toRemove = null;
		int index = 0;
		for (HalfEdge ei : halfEdges) {
			
			if (ei.toString().equals(e.toString())) {
				toRemove = ei;
				halfEdges.remove(index);
			}
			index++;
		}
		return toRemove;
	}
	
	public Face removeFace(Face f) {
		
		if (faces.isEmpty())
			return null;
		
		Face toRemove = null;
		int index = 0;
		for (Face fi : faces) {
			
			if (fi.getName().equals(f.getName())) {
				toRemove = fi;
				faces.remove(index);
			}
			index++;
		}
		return toRemove;
	}
	
	public Iterator<Vertex> getVertexIterator() {
		return vertices.iterator();
	}
	
	public Iterator<HalfEdge> getHalfEdgeIterator() {
		return halfEdges.iterator();
	}
	
	public Iterator<Face> getFaceIterator() {
		return faces.iterator();
	}
	
	@Override
	public String toString() {
		
		String rep = "";
		
		for (Vertex v : vertices) {
			rep += v.toString() + "\n";
		}
		rep += "\n";
		
		for (Face f : faces) {
			rep += f.toString() + "\n";
		}
		rep += "\n";
		
		for (HalfEdge e : halfEdges) {
			rep += 	e.toString() + " "
					+ e.getOrigin().getName() + " "
					+ e.getTwin().getName() + " "
					+ e.getIncidentFace().getName() + " "
					+ e.getNext().getName() + " "
					+ e.getPrev().getName() + "\n";
			
			rep += e.getTwin().toString() + " "
					+ e.getOrigin().getName() + " "
					+ e.getTwin().getName() + " "
					+ e.getIncidentFace().getName() + " "
					+ e.getNext().getName() + " "
					+ e.getPrev().getName() + "\n";
		}
		rep += "\n";
		
		return rep;
	}
	
}
