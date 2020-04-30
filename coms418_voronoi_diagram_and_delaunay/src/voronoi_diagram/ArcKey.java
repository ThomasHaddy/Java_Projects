package voronoi_diagram;



public abstract class ArcKey implements Comparable<ArcKey> {
	protected abstract Point getLeft();
	protected abstract Point getRight();

	public int compareTo(ArcKey that) {
		Point myLeft = this.getLeft();
		Point myRight = this.getRight();
		Point yourLeft = that.getLeft();
		Point yourRight = that.getRight();

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