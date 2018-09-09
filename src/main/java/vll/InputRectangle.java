package vll;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InputRectangle implements RectangleProvider {
	@JsonProperty("x")
	private int x;
	@JsonProperty("y")
	private int y;
	@JsonProperty("w")
	private int width;
	@JsonProperty("h")
	private int height;

	private Integer id;

	public InputRectangle() {
		// required by jackson because we have a copy constructor.
	}
	
	public InputRectangle(InputRectangle ir) {
		this.x = ir.x;
		this.y = ir.y;
		this.width = ir.width;
		this.height = ir.height;
		if(this.id != null) {
			this.id = new Integer(ir.id);
		}
	}
	
	@Override
	public Rectangle rectangle() {
		// FIXME: ensure that width and height are > 0
		return new Rectangle(new Point(this.x, this.y), this.width, this.height);
	}

	public void setId(int i) {
		this.id = new Integer(i);
	}

	@Override
	public Set<Integer> rectangleIds() {
		Set<Integer> ids = new HashSet<Integer>();
		ids.add(this.id);
		return ids;
	}
}
