/**
 * 
 */
package vll;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Vincent Le Ligeour
 *
 */
public class RectangleIntersectionTest {
	@Test
	public void testIntersectionNegativeCoordinates() {
		Rectangle r1 = new Rectangle(new Point(-1, -3), 50, 50);
		Rectangle r2 = new Rectangle(new Point(-2, -2), 4, 4);
		Rectangle expected = new Rectangle(new Point(-1, -2), 3, 4);
		shouldIntersect(r1, r2, expected);
		shouldIntersect(r2, r1, expected);
	}
	
	@Test
	public void testIntersection1() {
		// +--------+
		// |        |
		// |     +--+---+
		// |     |  |   |
		// +-----+--+   |
		//       |      |
		//       +------+
		Rectangle r1 = new Rectangle(new Point(1,1), 5, 5);
		Rectangle r2 = new Rectangle(new Point(4,4), 5, 5);
		Rectangle expected = new Rectangle(new Point(4,4), 2, 2);
		shouldIntersect(r1, r2, expected);
		shouldIntersect(r2, r1, expected);
	}
	
	@Test
	public void testIntersection2() {
		//    +--------+
		// +--+---+    |
		// |  |   |    |
		// +--+---+    |
		//    +--------+
		Rectangle r1 = new Rectangle(new Point(2,1), 9, 9);
		Rectangle r2 = new Rectangle(new Point(1,2), 4, 2);
		Rectangle expected = new Rectangle(new Point(2,2), 3, 2);
		shouldIntersect(r1, r2, expected);
		shouldIntersect(r2, r1, expected);
	}
	
	@Test
	public void testIntersection3() {
		//       +------+
		//       |      |
		// +-----+--+   |
		// |     |  |   |
		// |     +--+---+
		// |        |
		// +-----+--+
		Rectangle r1 = new Rectangle(new Point(2,2), 5, 5);
		Rectangle r2 = new Rectangle(new Point(3,1), 9, 3);
		Rectangle expected = new Rectangle(new Point(3,2), 4, 2);
		shouldIntersect(r1, r2, expected);
		shouldIntersect(r2, r1, expected);
	}
	
	@Test
	public void testIntersection4() {
		//   +---+
		//   |   |
		// +-+---+--+
		// | |   |  |
		// | +---+  +
		// |        |
		// +--------+
		Rectangle r1 = new Rectangle(new Point(2,1), 2, 3);
		Rectangle r2 = new Rectangle(new Point(1,2), 9, 9);
		Rectangle expected = new Rectangle(new Point(2,2), 2, 2);
		shouldIntersect(r1, r2, expected);
		shouldIntersect(r2, r1, expected);
	}
	
	@Test
	public void testIntersection5() {
		// +--------+
		// | +----+ |
		// | |    | |
		// | +----+ |
		// +--------+
		Rectangle r1 = new Rectangle(new Point(0, 0), 3, 3);
		Rectangle r2 = new Rectangle(new Point(1, 1), 1, 1);
		Rectangle expected = new Rectangle(new Point(1, 1), 1, 1);
		shouldIntersect(r1, r2, expected);
		shouldIntersect(r2, r1, expected);
	}
	
	@Test
	public void testIntersection6() {
		// +--------+
		// |        |
		// | +---+  +
		// | |   |  |
		// +-+---+--+
		//   |   |
		//   +---+
		Rectangle r1 = new Rectangle(new Point(1,1), 5, 5);
		Rectangle r2 = new Rectangle(new Point(2,3), 2, 9);
		Rectangle expected = new Rectangle(new Point(2,3), 2, 3);
		shouldIntersect(r1, r2, expected);
		shouldIntersect(r2, r1, expected);
	}
	
	@Test
	public void testIntersection7() {
		//       +------+
		//       |      |
		// +-----+--+   |
		// |     |  |   |
		// |     +--+---+
		// |        |
		// +-----+--+
		// similar to testIntersection3
	}
	
	@Test
	public void testIntersection8() {
		// +--------+
		// |     +--+---+
		// |     |  |   |
		// |     +--+---+
		// +-----+--+
		Rectangle r1 = new Rectangle(new Point(1,1), 5, 5);
		Rectangle r2 = new Rectangle(new Point(3,2), 9, 1);
		Rectangle expected = new Rectangle(new Point(3,2), 3, 1);
		shouldIntersect(r1, r2, expected);
		shouldIntersect(r2, r1, expected);
	}
	
	@Test
	public void testIntersection9() {
		// +--------+
		// |        |
		// |     +--+---+
		// |     |  |   |
		// +-----+--+   |
		//       |      |
		//       +------+
		// similar to testIntersection1
	}

	public void shouldIntersect(Rectangle r1, Rectangle r2, Rectangle intersection) {
		RectangleProvider rp1 = new TestRectangleProvider(r1, 0);
		RectangleProvider rp2 = new TestRectangleProvider(r2, 1);
		try {
			RectangleIntersection r = new RectangleIntersection(rp1, rp2);
			Rectangle got = r.rectangle();
			isFailingRectangle(got, intersection);
			System.out.println("testInsertion OK: got: " + got + ", expected: " + intersection);
		} catch (EmptyRectangleIntersectionException e) {
			fail("should have intersect");
		} catch (RecursiveRectangleIntersectionException e) {
			fail("should have different ids");
		}
	}
	
	public void isFailingRectangle(Rectangle got, Rectangle expected) {
		if (!got.getA().equals(expected.getA())) {
			fail("rectangle mismatch origin: got " + got.getA() + ", expected " + expected.getA());
		}
		if (!got.getC().equals(expected.getC())) {
			fail("rectangle mismatch: got " + got + ", expected " + expected);
		}
	}
}
