package challenge;

/* The adjacency matrix represents the directed graph having as nodes the compounds
 * and as edges the machines that transform one compound to the other. The direction of the 
 * edges represents the transformation from one compound to the other.*/
public class AdjacencyMatrix {
	
	private MachineTuple [] [] matrix;

	public AdjacencyMatrix(int numberOfCompounds) {
		matrix = new MachineTuple [numberOfCompounds] [numberOfCompounds];
		// the matrix is initialized with the highest possible value as machine cost
		for (int i = 0; i < numberOfCompounds; i++) {
			for (int j = 0; j < numberOfCompounds; j++) {
				matrix [i] [j] = new MachineTuple(); 
			}
		}
	}
	
	public MachineTuple getElement(int row, int col) {
		return matrix [row] [col];
	}
	
	public void setElement(int row, int col, MachineTuple tuple) {
		matrix [row] [col] = tuple;
	}
	
	public MachineTuple [] [] getMatrix() {
		return matrix;
	}
	
	public void orderMatrix() {
		for (int i = 0; i < matrix.length; i++) {
			int j;
			boolean flag = true;
			MachineTuple temp;
			while (flag) {
				flag= false;
				for( j=0;  j < matrix.length -1;  j++ ) {
					if (matrix [i][j].getCost() > matrix[i][j+1].getCost()) {
						temp = matrix [i][j];                //swap elements
						matrix [i][j] = matrix[i][j+1];
						matrix[i][j+1] = temp;
						flag = true;  
					} 
				} 
			} 
		} 
	}

	public void printMatrix() {  // debugging method
		System.out.print("        C0 ");
		for (int i = 1; i < matrix.length; i++) {
			System.out.print("             C" + i);
			if (i < 10) {
				System.out.print(" ");
			}
		}
		System.out.print("\n\n");
		for (int i = 0; i < matrix.length; i++) {
			System.out.print("C" + i + "     ");
			if (i < 10) {
				System.out.print(" ");
			}
			for (int j = 0; j < matrix.length; j++) {
				MachineTuple m = this.getElement(i, j);
				if (m.getName() == null) {
					System.out.print("NO_EDGE         ");
				} else {
					System.out.print(m.getName() + ":" + m.getCost() + " ");
				}
				if (m.getName()!=null) {
					System.out.print("      ");
					if (j < matrix.length -1 && this.getElement(i, j+1).getName() == null) {
						System.out.print("");
					}
				}
				if (m.getName() != null && (Integer.parseInt( m.getName().substring(1) ) ) < 10) {
					System.out.print(" ");
				}
				if (m.getName() != null && (Integer.parseInt( m.getName().substring(1) ) ) < 100) {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}
}
