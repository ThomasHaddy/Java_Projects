package geo;

/**
 * Point has a double x coordinate and double y coordinate
 * @author Thomas
 *
 */
public class Point implements Comparable<Point>{

	public double x;
	public double y;

	public Point(double x, double y) {

		this.x = x;
		this.y = y;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public double dist(Point a, Point b) {
		return Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2));
	}
	
	public static Point midpoint(Point p1, Point p2) {
        double x = (p1.x + p2.x) / 2;
        double y = (p1.y + p2.y) / 2;
        return new Point(x, y);
    }
	
	@Override
	public String toString() {
		if (this.x % 1 == 0 && this.y % 1 == 0)
			return String.format("(%d, %d)", (int)this.x, (int)this.y);
		return String.format("(%.2f, %.2f)", this.x, this.y);
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
