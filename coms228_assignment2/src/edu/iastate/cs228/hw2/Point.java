package edu.iastate.cs228.hw2;

/** 
 * @author Thomas Haddy 10/5/17
 */

/**
 * This class is used to represent a Point that has a x and y coordinate
 */
public class Point implements Comparable<Point>
{
	/**
	 * x coordinate
	 */
	private int x;

	/**
	 * y coordinate
	 */
	private int y;

	/**
	 * Default constructor. x and y get default value 0.
	 */
	public Point() {}

	/**
	 * Sets the given x and y to the instance variables x and y
	 * 
	 * @param x  given x
	 * @param y  given y
	 */
	public Point(int x, int y)
	{
		this.x = x;  
		this.y = y;   
	}

	/**
	 * Copies a point and sets its instance variable accordingly
	 * @param p  given Point
	 */
	public Point(Point p) { // copy constructor
		x = p.getX();
		y = p.getY();
	}

	/**
	 * Gets the x coordinate
	 * @return  x
	 */
	public int getX()   
	{
		return x;
	}

	/**
	 * Gets the y coordinate
	 * @return  y
	 */
	public int getY()
	{
		return y;
	}

	/**
	 * Overrides the equals method to return if a Point has the same x and y value as another Point
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (obj == null || obj.getClass() != this.getClass())
		{
			return false;
		}

		Point other = (Point) obj;
		return x == other.x && y == other.y;   
	}

	/**
	 * Compare this point with a second point q in the left-to-right order. 
	 * @param 	q 
	 * @return  -1  if this.x < q.x || (this.x == q.x && this.y < q.y)
	 * 		    0   if this.x == q.x && this.y == q.y 
	 * 			1	otherwise 
	 */
	public int compareTo(Point q)
	{
		if (this.x < q.x || (this.x == q.x && this.y < q.y)) {
			return -1;
		}
		else if (this.x == q.x && this.y == q.y) {
			return 0;
		}
		return 1;
	}	

	/**
	 * Output a point in the standard form (x, y). 
	 */
	@Override
	public String toString() 
	{
		return "(" + getX() + ", " + getY() + ")";
	}
}
