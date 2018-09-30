package hw4;

import java.awt.Color;
import graph.Cell;
import state.State;

/**
 * An impassable state with the color white. Doesn't do anything. A "#" in the map file.
 * 
 * @author Thomas Haddy
 *
 */
public class Wall implements State {

	/**
	 * Does nothing.
	 */
	@Override
	public void handle(Cell cell) {}

	/**
	 * Gets the color white.
	 */
	@Override
	public Color getColor() {
		return Color.WHITE;
	}

	/**
	 * Gets the impassable wall state.
	 */
	@Override
	public boolean isPassable() {
		return false;
	}

	/**
	 * Gets the character '#' representing a wall
	 */
	@Override
	public char toChar() {
		return '#';
	}
}
