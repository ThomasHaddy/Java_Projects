package voronoi_diagram;

public abstract class ArcKey implements Comparable<ArcKey> {
	
	protected abstract Point getLeftPoint();
	
	protected abstract Point getRightPoint();

	public int compareTo(ArcKey that) {
		Point myLeft = this.getLeftPoint();
		Point myRight = this.getRightPoint();
		Point yourLeft = that.getLeftPoint();
		Point yourRight = that.getRightPoint();

		// If one arc contains the query then we'll say that they're the same
		if (((that.getClass() == ArcQuery.class) || (this.getClass() == ArcQuery.class)) &&
				((myLeft.getX() <= yourLeft.getX() && myRight.getX() >= yourRight.getX()) ||
						(yourLeft.getX() <= myLeft.getX() && yourRight.getX() >= myRight.getX() ))) {
			return 0;
		}

		if (myLeft.getX() == yourLeft.getX() && myRight.getX() == yourRight.getX()) return 0;
		if (myLeft.getX() >= yourRight.getX()) return 1;
		if (myRight.getX() <= yourLeft.getX()) return -1;

		return Point.midpoint(myLeft, myRight).compareTo(Point.midpoint(yourLeft, yourRight));
	}
}