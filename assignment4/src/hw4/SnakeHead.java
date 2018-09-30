package hw4;

import state.State;
import java.awt.Color;
import graph.Cell;
import main.Config;
import state.Snake;
import state.SnakeSegment;

/**
 * An impassable state that follows the mouse's position or 
 * moves randomly if it can't move towards the mouse. If it 
 * still cannot move, the game ends. If the snake can move, 
 * it replaces its current cell with a SnakeSegment before moving. 
 * If it finds Food, it increments its length. Uses the default 
 * snake variables. An "S" in the map file.
 * 
 * @author Thomas Haddy
 *
 */
public class SnakeHead implements State, Snake {

	/**
	 * The length of the snake's segments.
	 */
	private int length;
	
	/**
	 * A timer used to set of the speed of the snake.
	 */
	private int timer;

	/**
	 * Constructs a snake with a head and body. The snake's body follows the head
	 * and has a length of 4.
	 */
	public SnakeHead() {
		length = 4;
		timer = 0;
	}

	/**
	 * Gets the length of the snake.
	 */
	@Override
	public int getLength() {
		return length;
	}

	/**
	 * Attempts to move the snake. If the snake eats food, its length increases by 1.
	 * If the snake has no more moves, the game ends.
	 */
	@Override
	public void handle(Cell cell) {

		Cell newCellCloser = cell.getRandomCloser();
		Cell newCellOpen = cell.getRandomOpen();

		if (timer == Config.MAX_SNAKE_TIMER) {
			timer = 0;
			
			if (newCellCloser != null) {
				if (newCellCloser.getState() instanceof Food) {
					
					length++;
				}
				
				cell.moveState(newCellCloser);
				cell.setState(new SnakeSegment(this));
			}
			else if (newCellOpen != null) {
				if (newCellOpen.getState() instanceof Food) {
					
					length++;
				}
				
				cell.moveState(newCellOpen);
				cell.setState(new SnakeSegment(this));
			}
			else {
				Config.endGame(length);
			}
		}
		
		timer++;
	}

	/**
	 * Gets the color orange.
	 */
	@Override
	public Color getColor() {
		return Color.ORANGE;
	}

	/**
	 * Gets the impassable SnakeHead state.
	 */
	@Override
	public boolean isPassable() {
		return false;
	}

	/**
	 * Gets the character 'S' representing SnakeHead.
	 */
	@Override
	public char toChar() {
		return 'S';
	}
}
