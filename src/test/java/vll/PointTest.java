package vll;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Vincent Le Ligeour
 *
 */
public class PointTest {
	@Test
	public void testEquals() {
		Point p1 = new Point(2, 2);
		Point p2 = new Point(2, 2);
		Point p3 = new Point(2, 3);
		Point p4 = new Point(3, 2);
		if(!p1.equals(p2)) {
			fail(p1 + " and " + p2 + " should be equal");
		}
		if(p1.equals(p3)) {
			fail(p1 + " and " + p3 + " should be different");
		}
		if(p1.equals(p4)) {
			fail(p1 + " and " + p4 + " should be different");
		}
	}
	
	@Test
	public void testTranslate() {
		Point p1 = new Point(2, 2);
		Point p2 = new Point(4, 5);
		int dX = 2;
		int dY = 3;
		Point p3 = p1.translate(dX, dY);
		if(!p3.equals(p2)) {
			fail(p3 + " and " + p2 + " should be equal");
		}
	}
}
