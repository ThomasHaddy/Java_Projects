package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * The Parser is responsible for parsing the .txt.file given that represents a DCEL into a machine-readable format.
 * The main goal is to turn the .txt into a usable data structure.
 * 
 * @author Thomas Haddy
 *
 */
public class Parser {

	private Scanner in;
	private List<Point> points;

	/*
	 * Constructs a parser that takes in a .txt
	 * 
	 */
	public Parser(List<Point> points, String path) throws FileNotFoundException {

		this.points = points;
		File file = new File(path);
		in = new Scanner(file);
	}
	
	public List<Point> getPoints() {
		return points;
	}
	
	public void parseInputFile() {
		
		while (in.hasNextInt()) {
			int x = in.nextInt();
			int y = in.nextInt();
			Point p = new Point(x,y);
			points.add(p);
		}
	}

	/*
	 * Prints each site being parsed
	 */
	public void printInputFile() {

		while (in.hasNextInt()) {
			int x = in.nextInt();
			int y = in.nextInt();
			Point p = new Point(x,y);
			System.out.println(p.toString());
		}      
	}
}
