package hw4;

import java.awt.Color;
import color.ColorGenerator;
import main.Config;

import java.util.Random;

/**
 * Creates 6 dark rainbow colors.
 * @author Thomas Haddy
 *
 */
public class RainbowColorGenerator implements ColorGenerator {

	/**
	 * The color red.
	 */
	private static final Color RED = new Color(0.25f, 0.0f, 0.0f);
	
	/**
	 * The color orange.
	 */
	private static final Color ORANGE = new Color(0.25f, 0.125f, 0.0f);
	
	/**
	 * The color yellow.
	 */
	private static final Color YELLOW = new Color(0.25f, 0.25f, 0.0f);
	
	/**
	 * The color green.
	 */
	private static final Color GREEN = new Color(0.0f, 0.25f, 0.0f);
	
	/**
	 * The color blue.
	 */
	private static final Color BLUE = new Color(0.0f, 0.0f, 0.25f);
	
	/**
	 * The color purple.
	 */
	private static final Color PURPLE = new Color(0.25f, 0.0f, 0.25f);
	
	/**
	 * The number generator for creating colors.
	 */
	private Random r = Config.RANDOM;
	
	/**
	 * Randomly selects, with equal probability, one of the six colors.
	 */
	@Override
	public Color createColor() {
		int next = r.nextInt(6);
		switch(next){
		case 0:
			return RED;
		case 1:
			return ORANGE;
		case 2:
			return YELLOW;
		case 3:
			return GREEN;
		case 4:
			return BLUE;
		default:
			return PURPLE;
		}
	}
}
