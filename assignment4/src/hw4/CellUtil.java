package hw4;

import graph.Cell;

/**
 * Calculates the distance from the current cell to the user's mouse cursor.
 * Also calculates the distance from the current cell's neighboring cells
 * to the user's mouse cursor.
 * 
 * @author Thomas Haddy
 *
 */
public class CellUtil
{
	/**
	 * Sets the mouse distance for the given cell and recursively sets
	 * the mouse distance for all neighboring cells that a) do not already 
	 * have a larger mouse distance and b) are open or passable.  Neighboring
	 * cells satisfying these conditions are set to <code>distance - 1</code>.
	 * If the given <code>distance</code> is less than or equal to zero, this 
	 * method does nothing.
	 * @param cell
	 *   the cell whose distance is to be set
	 * @param distance
	 *   the distance value to be set in the given cell
	 */
	public static void calculateMouseDistance(Cell cell, int distance)
	{
		if (distance <= 0) {
			return;
		}
		else if (cell.getMouseDistance() > distance-1) {
			return;
		}
		else if (cell.getState() != null && !cell.getState().isPassable()) {
			cell.clearDistance();
			return;
		}
		else {
			cell.setMouseDistance(distance);
			for (Cell c : cell.getNeighbors()) {
				calculateMouseDistance(c, distance-1);
			}
		}
	}
}
