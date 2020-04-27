package util;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class Sites {

	private LinkedList<Point> sites;
	
	public Sites(LinkedList<Point> sites) {
		
		this.sites = sites;
	}
	
	public LinkedList<Point> getSites() {
		return sites;
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
	
}
