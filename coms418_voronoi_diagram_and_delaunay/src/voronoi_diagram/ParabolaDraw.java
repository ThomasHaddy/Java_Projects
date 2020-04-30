package voronoi_diagram;

import stdlib.StdDraw;

/**
 * 
 * @author Thomas
 *
 */
public class ParabolaDraw {
	private final double a, b, c;

	public ParabolaDraw(Point focus, double directrixY) {
		this.a = focus.getX();
		this.b = focus.getY();
		this.c = directrixY;
	}

	public void draw(double min, double max) {
		min = (min > -2) ? min : -2;
		max = (max < 2) ? max : 2;
		for (double x = min; x < max; x += .001) {
			double y = ((x-a)*(x-a) + (b*b) - (c*c)) / (2*(b-c));
			StdDraw.point(x, y);
		}
	}

	public void draw() {
		this.draw(0, 1);
	}
}