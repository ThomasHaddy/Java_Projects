package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Sites {
	
	public static class Importer {
		
		public static void printInputFile(String filePath) throws FileNotFoundException {
			
			File input = new File(filePath);
			Scanner sc = new Scanner(input);
			
			while (sc.hasNextLine()) {
				System.out.println(sc.nextLine());
			}
			
			sc.close();
		}
		
		public static Sites parseInputFile(Sites sites, String filePath) throws FileNotFoundException {
			
			File input = new File(filePath);
			//Pattern pattern = Pattern.compile("\\((.*), (.*)\\)");
			Scanner sc = new Scanner(input);
			//sc.useDelimiter(pattern);

			while (sc.hasNext()) {
				if (sc.hasNextInt()) {
					int x = sc.nextInt();
					sc.next();
					int y = sc.nextInt();
					sites.add(new Point(x,y));
				}
				else {
					sc.next();
				}
			}
			sc.close();
			return sites;
		}
		
	} //End Importer

	private LinkedList<Point> sites;
	
	public Sites(LinkedList<Point> sites) {
		
		this.sites = sites;
	}
	
	public LinkedList<Point> getAllSites() {
		return sites;
	}
	
	public Point get(int index) {
		return sites.get(index);
	}
	
	public void add(Point p) {
		sites.add(p);
	}
	
	public LinkedList<Point> sortPoints() {
		
		LinkedList<Point> sorted = new LinkedList<>();
		for (Point p : sites) {
			sorted.add(p);
		}
		
		Collections.sort(sorted, new Comparator<Point>() {
		     @Override
		     public int compare(Point o1, Point o2) {
		         return o1.compareTo(o2);
		     }
		 });
		return sorted;
	}
	
	@Override
	public String toString() {
		
		String data = "";
		int count = 0;
		
		for (Point p : sites) {
			count++;
			
			if (count < sites.size())
				data += p.toString() + ", ";
			else
				data += p.toString();
		}
		
		return "{"+data+"}";
	}
	
}
