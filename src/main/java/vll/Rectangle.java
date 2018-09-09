package vll;

/**
 * The Rectangle class represents a rectangle in a 2D coordinate system.
 * It is define by its origin (upper left corner {@link Point}), a width and a height.
 * Width and height are expected to be strictly positive but no
 * defensive programming is done in order to keep code clean
 * and readable.
 * 
 * @author Vincent Le Ligeour
 *
 */
public class Rectangle {
	private final Point origin;
	private final int width;
	private final int height;

	/**
	 * Creates a rectangle ABCD with the ABCD defined as followed:
	 * - A: origin.X, origin.Y,
	 * - B: origin.X + width, origin.Y
	 * - C: origin.X + width, origin.Y + height
	 * - D: origin.X, origin.Y + height
	 * @param origin A point (top left of the rectangle)
	 * @param width Width of the rectangle
	 * @param height Height of the rectangle
	 */
	public Rectangle(Point origin, int width, int height) {
		this.origin = origin;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * 
	 * @return the upper left corner of the rectangle
	 */
	public Point getA() {
		// we could translate origin if we want to manage
		// negative height and width.
		return this.origin;
	}
	
	/**
	 * 
	 * @return the lower right corner of the rectangle
	 */
	public Point getC() {
		// we could translate origin if we want to manage
		// negative height and width.
		return this.origin.translate(this.width, this.height);
	}

	public String toString() {
		return this.origin + ", w=" + this.width + ", h=" + this.height;
	}
}
