package mathematics;

import java.awt.Dimension;

public class Vector2i {
	
	public Vector2i() {
		set(0, 0);
	}
	
	public Vector2i(Dimension dimensions) {
		set(dimensions.width, dimensions.height);
	}

	public Vector2i(int x, int y) {
		set(x, y);
	}

	public Vector2i(Vector2i vector) {
		set(vector.x, vector.y);
	}

	public Vector2i(Vector2d vector) {
		set((int)vector.getX(), (int)vector.getY());
	}
	
	public Vector2i add(Vector2i vector) {
		this.x += vector.x;
		this.y += vector.y;
		return this;
	}
	
	public boolean equals(Object object) {
		if(!(object instanceof Vector2i)) {
			return false;
		}
		Vector2i vector = (Vector2i)object;
		return vector.getX() == getX() && vector.getY() == getY();
	}
	
	public static double getDistance(Vector2i a, Vector2i b) {
		double x = a.getX() - b.getX();
		double y = a.getY() - b.getY();
		return Math.sqrt(x * x + y * y);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void set(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2i setX(int x) {
		this.x = x;
		return this;
	}
	
	public Vector2i setY(int y) {
		this.y = y;
		return this;
	}
	
	public Vector2i subtract(Vector2i vector) {
		this.x -= vector.x;
		this.y -= vector.y;
		return this;
	}

	private int x, y;

}
