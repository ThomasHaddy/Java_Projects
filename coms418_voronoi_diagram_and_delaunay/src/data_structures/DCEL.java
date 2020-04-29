package data_structures;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import geo.Vertex;
import geo.HalfEdge;
import geo.Face;

public class DCEL {
	
	private LinkedList<Vertex> vertices;
	
	private LinkedList<HalfEdge> halfEdges;
	
	private LinkedList<Face> faces;
	
	public DCEL () {
		vertices = new LinkedList<Vertex>();
		halfEdges = new LinkedList<HalfEdge>();
		faces = new LinkedList<Face>();
		
		//Add in unbounding face uf
		faces.add(new Face(null,null));
	}
	
	public LinkedList<Vertex> getVertices() {
		return vertices;
	}
	
	public LinkedList<HalfEdge> getHalfEdges() {
		return halfEdges;
	}
	
	public LinkedList<Face> getFaces() {
		return faces;
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
	
	public ListIterator<Vertex> getVertexIterator(int index) {
		//return vertices.ListIterator<Vertex>(index);
		return null;
	}
	
	public Iterator<HalfEdge> getHalfEdgeIterator() {
		return halfEdges.iterator();
	}
	
	public Iterator<Face> getFaceIterator() {
		return faces.iterator();
	}
	
	public String getVertexName(int index) {
		int in = index+1;
		return "v" + in;
	}
	
	public String getFaceName(int index) {
		
		//Unbounded face will always be the first index
		if (index == 0) {
			return "uf";
		}
		int in = index + 1;
		return "f" + in;
		
	}
	
	public String getHalfEdgeName(int index) {
		
		Vertex origin = vertices.get(index).getIncidentEdge().getOrigin();
		Vertex to = vertices.get(index).getIncidentEdge().getTwin().getOrigin();
		
		int edgeIndex = 0, originIndex = 0, toIndex = 0;
		for (Vertex v : vertices) {
			if (v.getPoint().equals(origin.getPoint()))
				originIndex = edgeIndex;
			else if (v.getPoint().equals(to.getPoint()))
				toIndex = edgeIndex;
			edgeIndex++;
		}
		return "e" + originIndex + "," + toIndex;
	}
	
	@Override
	public String toString() {
		
		StringBuilder dcelStr = new StringBuilder();
		
		//Get the vertices
		int vertexIndex = 0;
		if (!vertices.isEmpty()) {
			Vertex v = vertices.getFirst();
			while (vertexIndex < vertices.size()) {
				dcelStr
				.append(getVertexName(vertexIndex))
				.append("  (" + v.getX() + ", " + v.getY() + ")");
				
				if (v.getIncidentEdge() != null) {
					dcelStr
					.append(getHalfEdgeName(vertexIndex));
				}
				else {
					dcelStr
					.append("  nil");
				}
				
				v = vertices.get(vertexIndex);
				vertexIndex++;
				dcelStr.append("\n");
				
			}
			dcelStr.append("\n");
		}
		
		//Get the faces
		int faceIndex = 0;
		if (!faces.isEmpty()) {
			Face f = faces.getFirst();
			while (faceIndex < faces.size()) {
				dcelStr
				.append(getFaceName(faceIndex));
				//Check if inner component is null
				if (f.getInnerComp() == null) {
					dcelStr
					.append("  nil");
				}
				//inner component is not null. Find it
				else {
					//TODO: Get HalfEdge
				}
				f = faces.get(faceIndex);
				faceIndex++;
				dcelStr.append("\n");
			}
		}
		dcelStr.append("\n");
		
		//Get the half edges
		
		return dcelStr.toString();
	}
	
}
