package challenge;

/* The machine tuple is composed by a label, representing the machine
 * name and by an integer, representing the transformation cost from 
 * one compound to another. In the directed graph where the compounds
 * are the nodes the machine tuples represents the directed edges between 
 * them. */
public class MachineTuple {
	
	private String name;
	private int cost;
	
	public MachineTuple(String n, int c) {
		name = n;
		cost = c;
	}
	
	public int getCost() {
		return cost;
	}
	
	public String getName() {
		return name;
	}
}
