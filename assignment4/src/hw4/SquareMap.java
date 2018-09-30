package hw4;

import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;

import graph.Cell;
import graph.GraphMap;

/**
 * A square lattice where each cell has 4 neighbors set up in a checker board pattern.
 * 
 * @author Thomas Haddy
 *
 */
public class SquareMap extends GraphMap {
	
	/**
	 * Gets the width of the window in pixels for rendering, including the border area.
	 */
	@Override
	public int getPixelWidth() {
		
		int borderDistanceTotal = getDistance();
		int numSquaresWidth = getCells()[0].length * getDistance();
		
		return numSquaresWidth + borderDistanceTotal;
	}

	/**
	 * Gets the height of the window in pixels for rendering, including the border area.
	 */
	@Override
	public int getPixelHeight() {
		
		int borderDistanceTotal = getDistance();
		int numSquaresHeight = getCells().length * getDistance();
	
		return numSquaresHeight + borderDistanceTotal;
	}

	/**
	 * Create an array of neighbors for the cell with given column and row.
	 */
	@Override
	public Cell[] createNeighbors(int col, int row) {
		
		ArrayList<Cell> cells = new ArrayList<>();
		Cell[][] map = getCells();
		boolean outOfBounds = row < 0 || col < 0 || row > getCells().length || col > getCells()[0].length;
		
		for (int i = 0; i < getCells().length; i++) {
			for (int j = 0; j < getCells()[0].length; j++) {
				
				//Checks if i and j are adjacent to the (col, row) position.
				if (Math.abs(row-i) + Math.abs(col-j) == 1 && !outOfBounds) {
					cells.add(map[i][j]);
				}
			}
		}
		return cells.toArray(new Cell[cells.size()]);
	}

	/**
	 * Get the column and row indices for the cell closest to a given
	 * pixel (x, y) coordinate, returned as a Point object in which
	 * x is the column and y is the row.
	 */
	@Override
	protected Point selectClosestIndex(int x, int y) {
		
		int borderDistance = getDistance() / 2;
		return (new Point((x - borderDistance) / getDistance(), (y - borderDistance) / getDistance()));
	}

	/**
	 * Create a polygon for the cell with the given column and row.
	 */
	@Override
	public Polygon createPolygon(int col, int row) {
		
		int x1,x2,y1,y2;
		int borderDistance = getDistance() / 2;

		x1 = borderDistance + (col * getDistance());
		x2 = borderDistance + ((col+1) * getDistance());
		y1 = borderDistance + (row * getDistance());
		y2 = borderDistance + ((row+1) * getDistance());
		
		int[] xPoints = {x1, x2, x2, x1};
		int[] yPoints = {y1, y1, y2, y2};
		
		return new Polygon(xPoints, yPoints, 4);
	}
}
