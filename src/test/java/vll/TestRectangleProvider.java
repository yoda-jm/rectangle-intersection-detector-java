package vll;

import java.util.HashSet;
import java.util.Set;

public class TestRectangleProvider implements RectangleProvider {
	private final Rectangle r;
	private final Integer id;
	
	public TestRectangleProvider(Rectangle r, int id) {
		this.r = r;
		this.id = new Integer(id);
	}
	
	@Override
	public Rectangle rectangle() {
		return r;
	}

	@Override
	public Set<Integer> rectangleIds() {
		Set<Integer> ids = new HashSet<>();
		ids.add(this.id);
		return ids;
	}

}
