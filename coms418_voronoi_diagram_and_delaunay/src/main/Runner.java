package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import io.Importer;
import util.Point;

public class Runner {

	public static void main(String[] args) throws IOException {

		System.out.println("Running App...");
		
		File file = new File(args[0]);   
		Scanner scanner = new Scanner(file);
		List<Integer> integers = new ArrayList<>();
		while (scanner.hasNext()) {
		    if (scanner.hasNextInt()) {
		        integers.add(scanner.nextInt());
		    } 
		    else {
		        scanner.next();
		    }
		}
		System.out.println(integers);
		
		//System.out.println(ints.size());
		
		//parser.parseInputFile(input);
		//parser.printInputFile(input);
		
	}
}
