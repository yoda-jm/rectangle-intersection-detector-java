package vll;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class InputFile {
	@JsonProperty("rects")
	private List<InputRectangle> rectangles;

	public List<RectangleProvider> getRectangles() {
		List<RectangleProvider> res = new ArrayList<RectangleProvider>();

		int i = 1;
		for (InputRectangle r : this.rectangles) {
			InputRectangle ir = new InputRectangle(r);
			ir.setId(i);
			res.add(ir);
			i++;
		}
		return res;
	}
	
	public static List<RectangleProvider> LoadInputFile(String filename)
			throws JsonParseException, JsonMappingException, IOException {
		FileReader fileReader = new FileReader(filename);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(fileReader, InputFile.class).getRectangles();
	}
	

	public static void displayInputFile(List<RectangleProvider> rectangles) {
		// Display input
		System.out.println("Input:");
		for (RectangleProvider r : rectangles) {
			// FIXME:  find a nicest way to deduce that we only
			// have 1 id because we are displaying the input
			// rectangles.
			int id = r.rectangleIds().toArray(new Integer[]{})[0]; 
			System.out.println("\t" + id + ": Rectangle at " + r.rectangle() + ".");
		}
		System.out.println("");
	}
}
