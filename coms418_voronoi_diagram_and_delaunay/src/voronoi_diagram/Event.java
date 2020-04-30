package voronoi_diagram;


public class Event implements Comparable<Event> {
	public final Point p;

	public Event(Point p) {
		this.p = p;
	}

	@Override
	public int compareTo(Event o) {
		return Point.yCompareTo(this.p, o.p);
	}
}