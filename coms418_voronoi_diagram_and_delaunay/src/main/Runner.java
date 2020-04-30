package main;

import java.io.FileNotFoundException;
import java.util.*;

import stdlib.StdDraw;
import voronoi_diagram.*;

/**
 * The Runner is responsible for the different options of running the program
 * @author Thomas Haddy
 *
 */
public class Runner {

	public static void main(String[] args) {
		
		//Args[0]: File - reads from sites.txt
		//Args[1]: Path - path of input .txt
		if (args[0].equals("File") && args.length == 2) {
			
			//Initialize sites
			ArrayList<Point> sites = new ArrayList<Point>();
			
			//Import the input .txt file containing the sites
			try {
				Importer.printInputFile(args[1]);
				Importer.parseInputFile(sites, args[1]);
			} catch (FileNotFoundException e) {
				System.out.println("Did not find file in path" + args[1]);
			}
			
			//Draw the Voronoi Diagram
			sites = Importer.normalize(sites);
			StdDraw.setCanvasSize(1920, 1080);
			StdDraw.setScale(-1.1, 1.1);
			Voronoi v = new Voronoi(sites, true);
			v.show();
		}
		//Args[0]: Random - generates random points
		//Args[1]: N - Number of sites
		else if (args[0].equals("Random") && args.length == 2) {
			
			//Get the number of Random sites
			int n = Integer.parseInt(args[1]);
			ArrayList<Point> sites = new ArrayList<Point>();
			Random rnd = new Random();
			for (int i = 0; i < n; i++) {
				sites.add(new Point(rnd.nextDouble(), rnd.nextDouble()));
			}
			
			//Draw the Voronoi Diagram
			StdDraw.setCanvasSize(1920, 1080);
			StdDraw.setScale(-0.1, 1.1);
			Voronoi v = new Voronoi(sites, true);
			v.show();
		}
		else {
			
		}
	}
	
}
