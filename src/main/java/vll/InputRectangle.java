package vll;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class InputRectangle implements RectangleProvider {
	private int x;
	private int y;
	private int width;
	private int height;

	private Integer id;

	@JsonCreator
	public InputRectangle(
		@JsonProperty(value="x", required=true) int x,
		@JsonProperty(value="y", required=true) int y,
		@JsonProperty(value="w", required=true) int w,
		@JsonProperty(value="h", required=true) int h) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
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
