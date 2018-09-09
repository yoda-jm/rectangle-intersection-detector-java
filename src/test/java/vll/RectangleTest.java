package vll;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Vincent Le Ligeour
 *
 */
public class RectangleTest {
	@Test
	public void testBuild() {
		Point origin = new Point(1,1);
		int width = 3;
		int height = 2;
		Rectangle r = new Rectangle(origin, width, height);
		Point A = r.getA();
		Point C = r.getC();
		Point expectedC = origin.translate(width, height);
		
		if(!A.equals(origin)) {
			fail(A + " and " + origin + " should be equal");
		}
		if(!C.equals(expectedC)) {
			fail(C + " and " + expectedC + " should be equal");
		}
	}
}
