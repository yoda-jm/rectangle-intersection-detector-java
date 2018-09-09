package vll;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

class Main {
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		if(args.length != 1) {
			throw new IllegalArgumentException("usage: please give exacly 1 argument: input json filename");
		}
		// Read input
		List<RectangleProvider> rectangles = InputFile.LoadInputFile(args[0]);

		// Display input
		InputFile.displayInputFile(rectangles);
		
		// Compute intersections
		List<RectangleIntersection> intersections = RectangleIntersection.computeIntersections(rectangles);
		
		// display intersections
		RectangleIntersection.displayIntersections(intersections);
	}
	
	

}
