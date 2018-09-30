package hw4;

import graph.Cell;

/**
 * Randomly moves around according to the cycle of food. A "D" in a map file.
 * 
 * @author Thomas Haddy
 *
 */
public class DungeonessCrab extends Food {

	/**
	 * Attempts to move the state to some random open cell.
	 */
	@Override
	public void handle(Cell cell) {
		
		super.handle(cell);
		if (cell.getRandomOpen() != null && getTimer() == 0) {
			cell.moveState(cell.getRandomOpen());
		}
	}

	/**
	 * Gets the character 'D' representing a DungeonessCrab.
	 */
	@Override
	public char toChar() {
		return 'D';
	}
}
