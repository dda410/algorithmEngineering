package challenge;

/* The machine tuple is composed by a label, representing the machine
 * name and by an integer, representing the transformation cost from 
 * one compound to another. In the directed graph where the compounds
 * are the nodes the machine tuples represents the directed edges between 
 * them. */
public class MachineTuple {

	private String name;
	private int cost, row, col;

	public MachineTuple(String n, int c, int row, int col) {
		name = n;
		cost = c;
		this.row = row;
		this.col = col;
	}

	public MachineTuple() {
		this(null , Integer.MAX_VALUE, 0, 0);
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public int getCost() {
		return cost;
	}

	public String getName() {
		return name;
	}
}
