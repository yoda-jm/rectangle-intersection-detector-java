package vll;

/**
 * The Point class represents a point in a 2D coordinate system.
 * The coordinate system is oriented as follow:
 * - X increase when going to the right,
 * - Y increase when going to the bottom.
 * The orientation is not mandatory, but in some comments top, bottom
 * left or right is used to describe what is done.
 * 
 * @author Vincent Le Ligeour
 *
 */
public class Point {
	private final int X;
	private final int Y;

	public Point(int x, int y) {
		this.X = x;
		this.Y = y;
	}
	
	public boolean equals(Point p) {
		if(this.X != p.X) {
			return false;
		}
		if(this.Y != p.Y) {
			return false;
		}
		return true;
	}

	public int getX() {
		return X;
	}

	public int getY() {
		return Y;
	}

	public Point translate(int dX, int dY) {
		return new Point(this.X + dX, this.Y + dY);
	}

	public String toString() {
		return "(" + this.X + "," + this.Y + ")";
	}
}
