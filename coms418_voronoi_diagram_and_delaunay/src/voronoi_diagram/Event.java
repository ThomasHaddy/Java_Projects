package voronoi_diagram;

public class Event implements Comparable<Event> {
	
	private final Point p;

	public Event(Point p) {
		
		this.p = p;
	}
	
	public Point getPoint() {
		
		return p;
	}

	@Override
	public int compareTo(Event o) {
		
		return Point.yCompareTo(this.p, o.p);
	}
}