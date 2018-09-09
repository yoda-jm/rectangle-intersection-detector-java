package vll;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class RectangleIntersection implements RectangleProvider {
	private final Rectangle res;
	private final HashSet<Integer> ids;

	public RectangleIntersection(RectangleProvider r1, RectangleProvider r2)
			throws EmptyRectangleIntersectionException, RecursiveRectangleIntersectionException {
		this.res = RectangleIntersection.intersection(r1.rectangle(), r2.rectangle());

		Set<Integer> ids1 = r1.rectangleIds();
		Set<Integer> ids2 = r2.rectangleIds();
		Set<Integer> intersection = new HashSet<Integer>(ids1);
		intersection.retainAll(ids2);
		if (!intersection.isEmpty()) {
			throw new RecursiveRectangleIntersectionException();
		}
		this.ids = new HashSet<Integer>();
		this.ids.addAll(ids1);
		this.ids.addAll(ids2);
	}

	static private Rectangle intersection(Rectangle r1, Rectangle r2) throws EmptyRectangleIntersectionException {
		Point A1 = r1.getA();
		Point C1 = r1.getC();
		Point A2 = r2.getA();
		Point C2 = r2.getC();

		if (C1.getX() <= A2.getX()) {
			// r1 is completely on left
			throw new EmptyRectangleIntersectionException();
		} else if (A1.getX() >= C2.getX()) {
			// r1 is completely on right
			throw new EmptyRectangleIntersectionException();
		} else if (C1.getY() <= A2.getY()) {
			// r1 is completely on top
			throw new EmptyRectangleIntersectionException();
		} else if (A1.getY() >= C2.getY()) {
			// r1 is completely on bottom
			throw new EmptyRectangleIntersectionException();
		}
		
		// there is an intersection
		// We need to find the following information:
		// - the upper left corder of the rectangle,
		// - its width,
		// - its height.
		
		// Lets find A3, the upper left corner of the intersection, by contruction
		// it has the following coordinates:
		// - the "biggest X" between both upper left corners,
		// - the "biggest Y" between both upper left corders.
		Point A3 = new Point(Math.max(A1.getX(), A2.getX()), Math.max(A1.getY(), A2.getY()));
		// lets find its width, we have 2 cases:
		// - R1 finished before, then it is C1.X - A3.X
		// - R1 finished after, then it is C2.X - A3.X
		int width = Math.min(C1.getX(), C2.getX()) - A3.getX();
		// lets find its height, it is the same principle
		int height = Math.min(C1.getY(), C2.getY()) - A3.getY();
		// return the resulting rectangle.
		return new Rectangle(A3, width, height);
	}

	@Override
	public Rectangle rectangle() {
		return res;
	}

	@Override
	public Set<Integer> rectangleIds() {
		return this.ids;
	}
	
	public static List<RectangleIntersection> computeIntersections(List<RectangleProvider> rectangles) {
		List<RectangleIntersection> intersections = new ArrayList<RectangleIntersection>();
		Set<Set<Integer>> seen = new HashSet<Set<Integer>>();
		Queue<RectangleProvider> rects1 = new ArrayDeque<>(rectangles);
		while (!rects1.isEmpty()) {
			RectangleProvider r1 = rects1.remove();
			Queue<RectangleProvider> rects2 = new ArrayDeque<RectangleProvider>(rects1);
			while (!rects2.isEmpty()) {
				RectangleProvider r2 = rects2.remove();
				try {
					RectangleIntersection inter = new RectangleIntersection(r1, r2);
					Set<Integer> ids = inter.rectangleIds();
					
					// check if we already built this rectangle
					if (seen.contains(ids)) {
						// we already have this intersection, skip it !
						continue;
					}
					
					// add this intersection to the list of intersections 
					intersections.add(inter);
					// mark this intersection as seen
					seen.add(ids);
					// adds to the list of rectangles (for further intersection dectection)
					rects1.add(inter);
				} catch (EmptyRectangleIntersectionException e) {
					// Just continue, rectangles do not intersect
				} catch (RecursiveRectangleIntersectionException e) {
					// Just continue, this intersection has already been found.
				}
			}	
		}
		return intersections;
	}
	
	public static void displayIntersections(List<RectangleIntersection> intersections) {
		System.out.println("Intersections:");
		int count = 1;
		for(RectangleIntersection inter: intersections) {
			Set<Integer> ids = inter.rectangleIds();
			Rectangle r = inter.rectangle();
			System.out.println("\t" + count + ": Between rectangle " + RectangleProvider.idsToString(ids)
			+ " at " + r + ".");
			count++;
		}
	}
}
