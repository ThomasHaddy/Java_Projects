package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import voronoi_diagram.Point;

public class Importer {
	
	public static void printInputFile(String filePath) throws FileNotFoundException {
		
		File input = new File(filePath);
		Scanner sc = new Scanner(input);
		
		while (sc.hasNextLine()) {
			System.out.println(sc.nextLine());
		}
		
		sc.close();
	}
	
	public static ArrayList<Point> parseInputFile(ArrayList<Point> sites, String filePath) throws FileNotFoundException {
		
		File input = new File(filePath);
		//Pattern pattern = Pattern.compile("\\((.*), (.*)\\)");
		Scanner sc = new Scanner(input);
		//sc.useDelimiter(pattern);

		while (sc.hasNext()) {
			if (sc.hasNextDouble()) {
				double x = sc.nextDouble();
				sc.next();
				double y = sc.nextDouble();
				sites.add(new Point(x,y));
			}
			else {
				sc.next();
			}
		}
		sc.close();
		return sites;
	}
	
	public static ArrayList<Point> normalize(ArrayList<Point> sites) {
		
		//Get x_min and x_max
		Collections.sort(sites);
		double x_max = sites.get(sites.size()-1).getX();
		double x_min = sites.get(0).getX();
		
		//Get y_min and y_max
		double y_max = Double.MIN_VALUE;
		double y_min = Double.MAX_VALUE;
		for (int i = 0; i < sites.size(); i++) {
			y_max = Math.max(sites.get(i).getY(), y_max);
			y_min = Math.min(sites.get(i).getY(), y_min);
		}	
		
		//Start normalizing sites
		ArrayList<Point> points = new ArrayList<Point>();
		for (int i = 0; i < sites.size(); i++) {
			double norm_x = (sites.get(i).getX() - x_min) / (x_max-x_min);
			double norm_y = (sites.get(i).getY() - y_min) / (y_max-y_min);
			if (norm_x == 0) norm_x = 0.001;
			if (norm_y == 0) norm_y = 0.001;
			Point p = new Point(norm_x, norm_y);
			points.add(p);
		}
		return points;
	}
}
