package main;

import java.io.FileNotFoundException;
import java.util.LinkedList;

import data_structures.Sites;
import data_structures.Sites.Importer;
import geo.Point;

public class Runner {

	public static void main(String[] args) throws FileNotFoundException {

		System.out.println("Running App...");

		Sites sites = new Sites(new LinkedList<Point>());
		Importer.printInputFile(args[0]);
		Importer.parseInputFile(sites, args[0]);
		
		System.out.println(sites.toString());
	}
}
