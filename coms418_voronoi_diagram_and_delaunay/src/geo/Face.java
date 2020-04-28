package geo;

public class Face {
	
	private HalfEdge innerComp;
    
	private HalfEdge outerComp;
	
	private String name;

    public Face(String name, HalfEdge innerComp, HalfEdge outerComp) {
        this.name = name;
    	this.innerComp = innerComp;
        this.outerComp = outerComp;
    }
    
    public String getName() {
    	return this.name;
    }
    
    public void setName(String name) {
    	this.name = name;
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
    
    @Override
    public String toString() {
    	if (this.innerComp == null && this.outerComp != null)
    		return this.name + " nil " + this.outerComp.toString();
    	else if (this.outerComp == null && this.innerComp != null) {
    		return this.name + " " + this.innerComp.toString() + " nil";
    	}
    	return this.getName() + " nil nil";
    }
}
