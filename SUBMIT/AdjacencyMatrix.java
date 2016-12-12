/* The adjacency matrix represents the directed graph having as nodes the compounds
 * and as edges the machines that transform one compound to the other. The direction of the 
 * edges represents the transformation from one compound to the other.*/
public class AdjacencyMatrix {

	private MachineObject [] [] matrix;

	public AdjacencyMatrix(int numberOfCompounds) {
		matrix = new MachineObject [numberOfCompounds] [numberOfCompounds];
		// the matrix is initialized with the highest possible value as machine cost
		for (int i = 0; i < numberOfCompounds; i++) {
			for (int j = 0; j < numberOfCompounds; j++) {
				matrix [i] [j] = new MachineObject(); 
			}
		}
	}

	public MachineObject getElement(int row, int col) {
		return matrix [row] [col];
	}

	public void setElement(int row, int col, MachineObject tuple) {
		matrix [row] [col] = tuple;
	}

	public MachineObject [] [] getMatrix() {
		return matrix;
	}

	public void orderMatrix() { // BubbleSort is used to order the matrix elements.
		for (int i = 0; i < matrix.length; i++) {
			int j;
			boolean flag = true;
			MachineObject temp;
			while (flag) {
				flag= false;
				for( j=0;  j < matrix.length -1;  j++ ) {
					if (matrix [i][j].getCost() > matrix[i][j+1].getCost()) {
						temp = matrix [i][j];
						matrix [i][j] = matrix[i][j+1];
						matrix[i][j+1] = temp;
						flag = true;  
					} 
				} 
			} 
		} 
	}
}
