package edu.iastate.cs228.hw4;

//import java.util.Arrays;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

/**
 * @author Thomas Haddy 11/12/2017
 * 
 *         An application class
 */
public class Dictionary {
	public static void main(String[] args) throws FileNotFoundException {
		EntryTree<Character, String> et = new EntryTree<>();

		File file = new File(args[0]);
		Scanner sc = new Scanner(file);

		while (sc.hasNextLine()) {
			String method = sc.next();
			Character[] toAdd;
			String keys;
			switch (method) {
			case "add":
				keys = sc.next();
				String value = sc.next();
				toAdd = keys.chars().mapToObj(c -> (char) c).toArray(Character[]::new);

				System.out.println("Command: " + method + "  " + keys + "  " + value);
				System.out.println("Result from an add: " + et.add(toAdd, value) + "\n");
				break;
			case "showTree":
				System.out.println("Command: " + method + "\n");
				System.out.println("Result from a showTree:");
				et.showTree();
				System.out.println();
				break;
			case "remove":
				keys = sc.next();
				toAdd = keys.chars().mapToObj(c -> (char) c).toArray(Character[]::new);

				System.out.println("Command: " + method + "  " + keys);
				System.out.println("Result from a remove: " + et.remove(toAdd) + "\n");
				break;
			case "search":
				keys = sc.next();
				toAdd = keys.chars().mapToObj(c -> (char) c).toArray(Character[]::new);

				System.out.println("Command: " + method + "  " + keys);
				System.out.println("Result from a search: " + et.search(toAdd) + "\n");
				break;
			case "prefix":
				keys = sc.next();
				toAdd = keys.chars().mapToObj(c -> (char) c).toArray(Character[]::new);
				String result = "";
				Character[] temp = et.prefix(toAdd);
				for (int i = 0; i < temp.length; i++) {
					result += temp[i];
				}

				System.out.println("Command: " + method + "  " + keys);
				System.out.println("Result from a prefix: " + result + "\n");
				break;
			default:
				System.out.println("Not a valid method");
				break;
			}
		}
		sc.close();
	}
}
