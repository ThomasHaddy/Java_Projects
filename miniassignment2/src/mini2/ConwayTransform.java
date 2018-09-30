package mini2;

import api.ITransform;

/**
 * Transformation implementing Conway's Game of Life.
 * All cells have value 0 or 1. The new value is based on the center 
 * cell along with the sum S of its eight neighbors, according to 
 * the rules:
 * <ul>
 *   <li>if S is less than 2 the result is 0
 *   <li>if S is greater than 3 the result is 0
 *   <li>if the center cell is 1 and S is 2 or 3, the result is 1
 *   <li>if the center cell is 0 and S is exactly 3, the result is 1
 * </ul>
 * See
 * http://en.wikipedia.org/wiki/Conway%27s_Game_of_Life
 *
 * <p>
 * The radius is 1 and the <code>isWrapped()</code> method 
 * always returns true.
 */
public class ConwayTransform implements ITransform
{

	@Override
	public int apply(int[][] elements)
	{
		if (2*getRadius() + 1 != elements.length || 2*getRadius() + 1 != elements[0].length) {
			throw new IllegalArgumentException();
		}
		
		// TODO
		int sum = 0;
		for (int i = 0; i < elements.length; i++) {
			for (int j = 0; j < elements[0].length; j++) {
				if (!(i == 1 && j == 1)) {
					sum += elements[i][j];
				}
				
			}
		}
		
		int result = 0;
		if (sum < 2) {
			result = 0;
		}
		if (elements[1][1]==1 && (sum == 2 || sum == 3)) {
			result = 1;
		}
		if (sum > 3) {
			result = 0;
		}
		if (elements[1][1]==0 && sum == 3) {
			result = 1;
		}
		
		return result;
	}

	@Override
	public int getRadius()
	{
		return 1;
	}

	@Override
	public boolean isWrapped()
	{
		return true;
	}

}
