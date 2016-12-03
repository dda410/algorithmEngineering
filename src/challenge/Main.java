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

	/* The matrix is built from the parsed input. */
	public static AdjacencyMatrix buildAdjacencyMatrix(ArrayList<String []> input, int numberOfCompounds) {
		AdjacencyMatrix matrix = new AdjacencyMatrix(numberOfCompounds + 1);
		for (String [] items : input) { 
			int row = Integer.parseInt(items [1].substring(1));
			int col = Integer.parseInt(items [2].substring(1));
			int cost = Integer.parseInt(items [3]);
			/* If a machine performs the same transformation of another
			 * it is added only if it is cheaper than the current one. */
			if (cost < matrix.getElement(row, col).getCost() ) {
				matrix.setElement(row, col, new MachineTuple(items [0], cost, row, col));
			}
		}
		return matrix;
	}

	/* Recursive function acting greedy to calculate all the possible Hamiltonian circuits and finding the cheapest once. */
	public static ArrayList<MachineTuple> getCheapestMachines2(AdjacencyMatrix matrix, ArrayList<MachineTuple> cheapestMachines, 
			int numberOfCompounds, int row, int startingRow, int recursionDepth, ArrayList<Integer> visitedRows) {
		// Exit condition: an Hamiltonian circuit has been found.
		if (recursionDepth == numberOfCompounds) {
			for (int i = 0; i < numberOfCompounds; i++) {
				System.out.println("This is row: "+row+"\nThis is numberOfCompounds: "+numberOfCompounds+"\nThis recursion depth: "+recursionDepth);
				if (matrix.getElement(row, i).getCol() == startingRow && matrix.getElement(row, i).getName() != null) {
					cheapestMachines.add(matrix.getElement(row, i));
					System.out.println("This is element: "+matrix.getElement(row, i).getName());
					System.out.println("This is cheapest machines.size(): "+cheapestMachines.size());
					return cheapestMachines;
				}
			}
			return cheapestMachines;
		}
		/* If the recursion sub-tree of the current node has not yield to a result it is 
		 * calculated the subtree of the cheapest next link (machine).*/
		for (int i = 0; i < numberOfCompounds + 1; i++) {
			MachineTuple element = matrix.getElement(row, i);
			if (element.getName() == null) {
				return cheapestMachines;
			}
			if (! visitedRows.contains(element.getCol())) {
				System.out.println("This is element: "+element.getName());
				visitedRows.add(row);
				cheapestMachines.add(element);
				cheapestMachines = getCheapestMachines2(matrix, cheapestMachines, numberOfCompounds, element.getCol(), startingRow, ++recursionDepth, visitedRows);
				System.out.println("This is cheapest machine size in recursion unwind: "+ cheapestMachines.size());
				if (cheapestMachines.size() == (numberOfCompounds + 1) ) {
					System.out.println("cheapestMachines.size() == (numberOfCompounds + 1) LAST CALL");
					break;
				}
				--recursionDepth;
			}			
		}
		System.out.println("last return");
		return cheapestMachines;
	}

	/* This function gets the shortest Hamiltonian circuit containing all the compounds: 
	 * equivalent to say the less expensive set of machines that can transform each given compound to another. */
	public static ArrayList<MachineTuple> getCheapestMachines(int numberOfCompounds, AdjacencyMatrix matrix) {
		ArrayList<MachineTuple> cheapestMachines = new ArrayList<MachineTuple> ();
		ArrayList<Integer> visitedNodes = new ArrayList<Integer> ();
		for (int i = 0; i < numberOfCompounds + 1; i++) {
			cheapestMachines = getCheapestMachines2(matrix, cheapestMachines, numberOfCompounds, i, i, 0, visitedNodes);
			System.out.println("This is cheapestMachines size: " + cheapestMachines.size());
			if (cheapestMachines.size() == numberOfCompounds + 1) {
				break;
			}
		}
		return cheapestMachines;
	}

	public static void main(String[] args) {
		parseArguments(args);
		Scanner in = null;
		ArrayList<String []> inputLines = new ArrayList<String []>();
		int numberOfCompounds = 0; // The total number of compounds is needed to build the adjacency matrix.
		try {
			in = new Scanner(new File(args[0]));
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		// Parsing the input inside the loop.
		while (in.hasNextLine()) {
			Scanner line = new Scanner(in.nextLine());
			// The 4 elements composing an input line are parsed.
			String [] items = new String [4];
			for (int i = 0; i < 4; i++) {
				items [i] = line.next();
			}
			numberOfCompounds = getNumberOfCompounds(Integer.parseInt(items [1].substring(1)), Integer.parseInt(items [2].substring(1)), numberOfCompounds);
			// The ordered collections of the parsed input is stored in the inputLines variable.
			inputLines.add(items);
		}
		System.out.println("This is numberOfCompounds "+numberOfCompounds);
		AdjacencyMatrix matrix = buildAdjacencyMatrix(inputLines, numberOfCompounds);
		matrix.printMatrix();
		matrix.orderMatrix();
		matrix.printMatrix();
		ArrayList<MachineTuple> result = getCheapestMachines(numberOfCompounds, matrix);
		for (int i = 0; i < numberOfCompounds + 1; i++) {
			System.out.println(result.get(i).getName()+":"+result.get(i).getCost());
		}
	}
}
