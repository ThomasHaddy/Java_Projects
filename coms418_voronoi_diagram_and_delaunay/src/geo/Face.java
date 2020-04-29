package geo;

import java.util.LinkedList;
import java.util.List;

/**
 * DCEL representation of a face. A face has a bounded Half Edge inner component and bounded Half Edge outer component.
 * @author Thomas
 *
 */
public class Face {
	
	private HalfEdge innerComp;
    
	private HalfEdge outerComp;

	public Face() {}
	
    public Face(HalfEdge innerComp, HalfEdge outerComp) {
    	this.innerComp = innerComp;
        this.outerComp = outerComp;
    }

    public HalfEdge getInnerComp() {
        return innerComp;
    }

    public void setInnerComp(HalfEdge e) {
        this.innerComp = e;
    }
    
    public HalfEdge getOuterComp() {
        return outerComp;
    }

    public void setOuterComp(HalfEdge e) {
        this.outerComp = e;
    }
    
	public List<HalfEdge> getHalfEdges() {
	        
		List<HalfEdge> halfEdges = new LinkedList<>();
		HalfEdge e = this.innerComp == null ? this.innerComp : this.outerComp;
		halfEdges.add(e);
		
		if (e != null) {
			while (e.getNext() != this.innerComp || e.getNext() != this.outerComp) {
				e = e.getNext();
				halfEdges.add(e);
			}
		}
		return halfEdges;
	}
    
    @Override
    public String toString() {
    	if (this.innerComp == null && this.outerComp != null)
    		return this + " nil " + this.outerComp.toString();
    	else if (this.outerComp == null && this.innerComp != null) {
    		return this + " " + this.innerComp.toString() + " nil";
    	}
    	return this + " nil nil";
    }
}
