package main;

import java.io.FileNotFoundException;
import java.util.LinkedList;

import util.Point;
import util.Sites;
import util.Sites.Importer;

public class Runner {

	public static void main(String[] args) throws FileNotFoundException {

		System.out.println("Running App...");

		Sites sites = new Sites(new LinkedList<Point>());
		Importer.printInputFile(args[0]);
		Importer.parseInputFile(sites, args[0]);
		
		System.out.println(sites.toString());
	}
}
