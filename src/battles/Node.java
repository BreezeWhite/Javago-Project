package battles;

import java.io.Serializable;

import mathematics.Vector2i;

public class Node implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5146909891992966061L;
	public Vector2i tile;
	public Node parent;
	public double fCost, gCost, hCost;
	
	public Node(Vector2i tile, Node parent, double gCost, double hCost) {
		this.tile = tile;
		this.parent = parent;
		this.gCost = gCost;
		this.hCost = hCost;
		fCost = this.gCost + this.hCost;
	}

}
