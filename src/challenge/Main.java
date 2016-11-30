package challenge;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
	
	public static void printArrayList(ArrayList<String []> a) {	//debugging method
		int lineNumber = 1;
		for (String [] line : a) {
			for (int i = 0; i < 4; i++)
				System.out.println("This is element " + i + ": " + line[i] + " of line " + lineNumber);
			lineNumber++;
		}
	}
	
	private static void parseArguments(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: Main <Input_file>");
			System.exit(1);
		}
	}
	
	public static int getNumberOfCompounds(int comp1, int comp2, int numberOfCompounds) {
		int max = (comp1 < comp2) ? comp2 : comp1;
		return (numberOfCompounds < max) ? max : numberOfCompounds;
	}
	
	public static AdjacencyMatrix buildAdjacencyMatrix(ArrayList<String []> input, int numberOfCompounds) {
		AdjacencyMatrix matrix = new AdjacencyMatrix(numberOfCompounds + 1);
		for (String [] items : input) { 
			int row = Integer.parseInt(items [1].substring(1));
			int col = Integer.parseInt(items [2].substring(1));
			int cost = Integer.parseInt(items [3]);
			/* If a machine performs the same transformation of another
			 * it is added only if it is cheaper than the current one */
			if (cost < matrix.getElement(row, col).getCost() ) {
				matrix.setElement(row, col, new MachineTuple(items [0], cost));
			}
		}
		return matrix;
	}

	public static void main(String[] args) {
		parseArguments(args);
		Scanner in = null;
		ArrayList<String []> inputLines = new ArrayList<String []>();
		// the total number of compounds is needed in order to build the adjacency matrix
		int numberOfCompounds = 0;
		try {
			in = new Scanner(new File(args[0]));
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		while (in.hasNextLine()) {
			Scanner line = new Scanner(in.nextLine());
			// items store the 4 elements composing an input line
			String [] items = new String [4];
			for (int i = 0; i < 4; i++) {
				items [i] = line.next();
			}
			numberOfCompounds = getNumberOfCompounds(Integer.parseInt(items [1].substring(1)), Integer.parseInt(items [2].substring(1)), numberOfCompounds);
			// the arrayList input lines is an ordered collection of parsed items that compose the input lines
			inputLines.add(items);
		}
		AdjacencyMatrix matrix = buildAdjacencyMatrix(inputLines, numberOfCompounds);
		matrix.printMatrix();
	}
}
