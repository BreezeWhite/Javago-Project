package mathematics;

import java.awt.Dimension;
import java.io.Serializable;

public class Vector2d implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -220210288721464649L;

	public Vector2d() {
		set(0, 0);
	}
	
	public Vector2d(Dimension dimensions) {
		set(dimensions.width, dimensions.height);
	}

	public Vector2d(double x, double y) {
		set(x, y);
	}

	public Vector2d(Vector2d vector) {
		set(vector.x, vector.y);
	}
	
	public Vector2d add(Vector2d vector) {
		this.x += vector.x;
		this.y += vector.y;
		return this;
	}
	
	public boolean equals(Object object) {
		if(!(object instanceof Vector2d)) {
			return false;
		}
		Vector2d vector = (Vector2d)object;
		return vector.getX() == getX() && vector.getY() == getY();
	}
	
	public static double getDistance(Vector2d a, Vector2d b) {
		double x = a.getX() - b.getX();
		double y = a.getY() - b.getY();
		return Math.sqrt(x * x + y * y);
	}
	
	public static double getRelDistance(Vector2d a, Vector2d b) {
		double x = a.getX() - b.getX();
		double y = a.getY() - b.getY();
		return x * x + y * y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public Vector2d multiply(double factor) {
		this.x *= factor;
		this.y *= factor;
		return this;
	}
	
	public void set(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2d setX(double x) {
		this.x = x;
		return this;
	}
	
	public Vector2d setY(double y) {
		this.y = y;
		return this;
	}
	
	public Vector2d subtract(Vector2d vector) {
		this.x -= vector.x;
		this.y -= vector.y;
		return this;
	}
	
	public Vector2d subtract(Vector2i vector) {
		this.x -= vector.getX();
		this.y -= vector.getY();
		return this;
	}

	private double x, y;

}
