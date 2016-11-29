package challenge;

import javax.print.attribute.standard.NumberOfDocuments;

public class AdjacencyMatrix {
	
	private machineTuple [] [] matrix;
	
	/* The adjacency matrix represents the directed graph having as nodes the compounds
	 * and as edges the machines that transform one compound to the other. The direction of the 
	 * edges represents the transformation from one compound to the other.*/
	public AdjacencyMatrix(int numberOfCompounds) {
		matrix = new machineTuple [numberOfCompounds] [numberOfCompounds];
		// the matrix is initialized with the highest possible value as machine cost
		for (int i = 0; i < numberOfCompounds; i++) {
			for (int j = 0; j < numberOfCompounds; j++) {
				matrix [i] [j] = new machineTuple(null , Integer.MAX_VALUE); 
			}
		}
	}
	
	public machineTuple getElement(int row, int col) {
		return matrix [row] [col];
	}
	
	public void setElement(int row, int col, machineTuple tuple) {
		matrix [row] [col] = tuple;
	}
	
	public void printMatrix() {  // debugging method
		System.out.print("        C0");
		for (int i = 1; i < matrix.length; i++) {
			System.out.print("              C" + i);
		}
		System.out.print("\n\n");
		for (int i = 0; i < matrix.length; i++) {
			System.out.print("C" + i + "      ");
			for (int j = 0; j < matrix.length; j++) {
				machineTuple m = this.getElement(i, j);
				if (m.getName() == null) {
					System.out.print("NO_EDGE         ");
				} else {
					System.out.print(m.getName() + ":" + m.getCost() + " ");
				}
				if (m.getName()!=null) {
					System.out.print("       ");
					if (j < matrix.length -1 && this.getElement(i, j+1).getName()==null) {
						System.out.print("");
					}
				}
				if (m.getName() != null && (Integer.parseInt( m.getName().substring(1) ) ) < 10) {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}
}
