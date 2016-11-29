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
			int row = Character.getNumericValue(items [1].charAt(1));
			int col = Character.getNumericValue(items [2].charAt(1));
			int cost = Integer.parseInt(items [3]);
			if (cost < matrix.getElement(row, col).getCost() ) {
				matrix.setElement(row, col, new machineTuple(items [0], cost));
			}
		}
		return matrix;
	}

	public static void main(String[] args) {
		parseArguments(args);
		Scanner in = null;
		ArrayList<String []> inputLines = new ArrayList<String []>();
		int numberOfCompounds = 0;
		try {
			in = new Scanner(new File(args[0]));
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		while (in.hasNextLine()) {
			Scanner line = new Scanner(in.nextLine());
			String [] items = new String [4];
			for (int i = 0; i < 4; i++) {
				items [i] = line.next();
			}
			numberOfCompounds = getNumberOfCompounds(Character.getNumericValue(items [1].charAt(1)), Character.getNumericValue(items [2].charAt(1)), numberOfCompounds);
			inputLines.add(items);
		}
		AdjacencyMatrix matrix = buildAdjacencyMatrix(inputLines, numberOfCompounds);
		matrix.printMatrix();
	}
}
