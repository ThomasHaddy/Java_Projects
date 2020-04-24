package main;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import util.Parser;
import util.Point;

public class Runner {

	public static void main(String[] args) throws FileNotFoundException {

		System.out.println("Running App...");

		List<Point> data = new ArrayList<Point>();
		Parser parser = new Parser(data, args[0]);
		parser.parseInputFile();

		for (Point p : data) {
			System.out.println(p.toString());
		}
			
	}
}
