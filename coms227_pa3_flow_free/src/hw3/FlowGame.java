package hw3;

import api.Cell;
import api.Flow;
import hw3.Util;

/**
 * Game state for a Flow Free game.
 */
public class FlowGame
{
	/**
	 * An Array of flows differing in color.
	 */
	private Flow[] flows;

	/**
	 * The number of columns in a Flow Game.
	 */
	private int width;

	/**
	 * The number of rows in a Flow Game.
	 */
	private int height;

	/**
	 * The current cell being selected in the Flow Game.
	 */
	private Cell currentCell;

	/**
	 * Constructs a FlowGame to use the given array of Flows and
	 * the given width and height.  Client is responsible for ensuring that all
	 * cells in the given flows have row and column values within
	 * the given width and height.
	 * @param givenFlows
	 *   an array of Flow objects
	 * @param givenWidth
	 *   width to use for the game
	 * @param givenHeight
	 *   height to use for the game
	 */
	public FlowGame(Flow[] givenFlows, int givenWidth, int givenHeight)
	{
		flows = givenFlows;
		width = givenWidth;
		height = givenHeight;
		currentCell = null;
	}

	/**
	 * Constructs a FlowGame from the given descriptor.
	 * @param descriptor
	 *   array of strings representing initial endpoint positions
	 */
	public FlowGame(String[] descriptor)
	{
		flows = Util.createFlowsFromStringArray(descriptor);
		width = descriptor[0].length();
		height = descriptor.length;
		currentCell = null;
	}

	/**
	 * Returns the width for this game.
	 * @return
	 *  width for this game
	 */
	public int getWidth()
	{
		return width;
	}

	/**
	 * Returns the height for this game.
	 * @return
	 *   height for this game
	 */
	public int getHeight()
	{
		return height;
	}

	/**
	 * Returns the current cell for this game, possible null.
	 * @return
	 *   current cell for this game
	 */
	public Cell getCurrent()
	{
		return currentCell;
	}

	/**
	 * Returns all flows for this game.  Client should not modify
	 * the returned array or lists.
	 * @return
	 *   array of flows for this game
	 */
	public Flow[] getAllFlows()
	{
		return flows;
	}

	/**
	 * Returns the number of occupied cells in all flows (including endpoints).
	 * @return
	 *   occupied cells in this game
	 */
	public int getCount()
	{
		int count = 0;
		for (int i = 0; i < flows.length; i++) {
			
			count += flows[i].getCells().size();
		}
		
		return count;
	}

	/**
	 * Returns true if all flows are complete and all cells are occupied.
	 * @return
	 *   true if all flows are complete and all cells are occupied
	 */
	public boolean isComplete()
	{	
		return getCount() == (width*height);
	}

	/**
	 * Attempts to set the "current" cell to be an existing cell at the given
	 * row and column.  When using a GUI, this method is typically 
	 * invoked when the mouse is pressed.  
	 * <ul>
	 *   <li>Any endpoint can be selected as the current cell.  Selecting an 
	 *   endpoint clears the flow associated with that endpoint.
	 *   <li>A non-endpoint cell can be selected as the current cell only if 
	 *   it is the last cell in a flow. 
	 * </ul>
	 * If neither of the above conditions is met, this method does nothing.
	 * 
	 * @param row
	 *   given row
	 * @param col
	 *   given column
	 */
	public void startFlow(int row, int col)
	{
		//Starts flow at an endpoint and clears any flow associated with it
		for (int i = 0; i < flows.length; i++) {
			for (int j = 0; j < 2; j++) {
				
				if (flows[i].getEndpoint(j).positionMatches(row, col)) {
					
					currentCell = flows[i].getEndpoint(j);
					flows[i].getCells().clear();
					flows[i].add(currentCell);
					
					return;
				}
			}
		}

		//Continues the flow at the end of a flow
		for (int i = 0; i < flows.length; i++) {
			for (int j = 0; j < flows[i].getCells().size(); j++) {

				//Mouse clicked on a flow
				if (flows[i].getCells().get(j).positionMatches(row,col)) {

					//Removes any extra part of the flow and sets new currentCell
					int flowLength = flows[i].getCells().size();
					for (int extraFlow = flowLength-1; extraFlow > j; extraFlow--) {
						
						flows[i].getCells().remove(extraFlow);
					}
					currentCell = new Cell(row, col, flows[i].getColor());
					
					return;
				}
			}
		}
	}

	/**
	 * Clears the "current" cell. That is, directly after invoking this method,
	 * <code>getCurrent</code> returns null. When using a GUI, this method is 
	 * typically invoked when the mouse is released.
	 */
	public void endFlow()
	{
		currentCell = null;
	}

	/**
	 * Attempts to add a new cell to the flow containing the current cell.  
	 * When using a GUI, this method is typically invoked when the mouse is 
	 * dragged.  In order to add a cell, the following conditions must be satisfied:
	 * <ol>
	 *   <li>The current cell is non-null
	 *   <li>The given position is horizontally or vertically adjacent to the 
	 *   current cell
	 *   <li>The given position either is not occupied OR it is occupied by 
	 *   an endpoint for the flow that is not already in the flow
	 * </ul>
	 * If the three conditions are met, a new cell with the given row/column 
	 * and correct color is added to the current flow, and the current cell 
	 * is updated to be the new cell.
	 * 
	 * @param row
	 *   given row for the new cell
	 * @param col
	 *   given column for the new cell
	 */
	public void addCell(int row, int col)
	{
		//Gets the current flow that current cell is in
		int currentFlow = 0;
		for (int i = 0; i < flows.length; i++) {
			
			if (currentCell != null && flows[i].getColor().equals(currentCell.getColor())) {
				
				currentFlow = i;
			}
		}

		//Checks if the new cell can be added to the flow
		if (!isOccupied(row, col) && currentCell != null && isAdjacent(row, col, currentCell) && !flows[currentFlow].isComplete()) {
			
			flows[currentFlow].add(new Cell(row, col, flows[currentFlow].getColor()));
			currentCell = new Cell(row, col, flows[currentFlow].getColor());
		}
	}

	/**
	 * Returns true if the given position is occupied by a cell in a flow in
	 * this game (possibly an endpoint).
	 * @param row
	 *   given row
	 * @param col
	 *   given column
	 * @return
	 *   true if any cell in this game has the given row and column, false otherwise
	 */
	public boolean isOccupied(int row, int col)
	{
		//Looks for endpoints of different colors
		for (int i = 0; i < flows.length; i++) {
			for (int j = 0; j < 2; j++) {
				
				if (flows[i].getEndpoint(j).positionMatches(row,col) && !flows[i].getEndpoint(j).getColor().equals(currentCell.getColor())) {
					
					return true;
				}
			}
		}

		//Looks for flows of different color that have been made
		for (int i = 0; i < flows.length; i++) {
			for (int j = 0; j < flows[i].getCells().size(); j++) {
				
				if (flows[i].getCells().get(j).positionMatches(row,col)) {
					
					return true;
				}
			}
		}
		
		return false;
	}

	/**
	 * This helper method determines if a flow can move in the following directions: Right, left, up, down
	 * The return makes sure that any move does not go beyond 1 space in any cardinal direction
	 * and ensures that it doesn't move diagonally (Diagonally counts as 2 spaces in this case),
	 * because both the row and column increase or decrease
	 * @param row
	 * 		Current row
	 * @param col
	 * 		Current column
	 * @param c
	 * 		Current cell
	 * @return
	 * 		Boolean stating if the flow can move in a certain direction	
	 */
	private static boolean isAdjacent(int row, int col, Cell c) {
		
		return Math.abs(c.getRow()-row) + Math.abs(c.getCol()-col) < 2;
	}

}
