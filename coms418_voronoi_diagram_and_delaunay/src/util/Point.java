package util;

public class Point implements Comparable<Point>{

	private int x;
	private int y;
	
	public Point(int x, int y) {
		
		this.x = x;
		this.y = y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	@Override
	public String toString() {
		return "("+this.x+", "+this.y+")";
	}
	
	@Override
	public boolean equals(Object o) {
		
		if (this == o)
			return true;
		
		if (!Point.class.isAssignableFrom(o.getClass()))
            return false;
            
		final Point b = (Point) o;
		if (this.x == b.x && this.y == b.y)
			return true;
		
		return false;
	}
	
		@Override
		public int compareTo(Point other) {
			if (this.x < other.x || (this.x == other.x && this.y < other.y))
				return -1;
			else if (this.x > other.x || (this.x == other.x && this.y > other.y))
				return 1;
			return 0;
		}
	
}
