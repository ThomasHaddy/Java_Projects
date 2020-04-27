package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import util.Point;
import util.Sites; 

/**
 * The Parser is responsible for parsing the .txt.file given that represents a set of points(sites).
 * The main goal is to turn the .txt into a usable data structure.
 * 
 * @author Thomas Haddy
 *
 */
public class Importer {

	/*
	 * Constructs a parser that takes in a .txt
	 * 
	 */
	public Importer(List<Point> data) throws FileNotFoundException {
		
	}
	
	public void parseInputFile(File input) throws FileNotFoundException {
		
	}

	/*
	 * Prints each site being parsed
	 */
	public void printInputFile(File input) throws FileNotFoundException {
		
		Scanner sc = new Scanner(input);
		
		while (sc.hasNextLine()) {
			System.out.println(sc.nextLine());
		}
		
		sc.close();
	}
	
}
