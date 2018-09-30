package hw4;

import java.awt.Color;
import graph.Cell;
import main.Config;
import state.State;

/**
 * A flashing passable state that uses the default food variables. An "F" in a map file.
 * 
 * @author Thomas Haddy
 *
 */
public class Food implements State {

	/**
	 * A timer used to set of the speed of the flashing color.
	 */
	private int timer;
	
	/**
	 * Updates the cell's food state. It does not move, but it changes color after each call.
	 */
	@Override
	public void handle(Cell cell) {
		
		timer++;
		if (timer == Config.MAX_FOOD_TIMER) {
			timer = 0;
		}
	}

	/**
	 * Changes color and creates a flashing illusion.
	 */
	@Override
	public Color getColor() {
		return Config.FOOD_COLORS[timer];
	}

	/**
	 * Food is always passable.
	 */
	@Override
	public boolean isPassable() {
		return true;
	}

	/**
	 * Gets the character 'F' representing food.
	 */
	@Override
	public char toChar() {
		return 'F';
	}
	
	protected int getTimer() {
		return timer;
	}
}
