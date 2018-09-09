package vll;

import java.util.Set;

public interface RectangleProvider {
	/**
	 * 
	 * @return the resulting rectangle
	 */
	public Rectangle rectangle();

	/**
	 * 
	 * @return the list of ids used to generate the resulting rectangle
	 */
	public Set<Integer> rectangleIds();

	/**
	 * 
	 * @param ids list of ids
	 * @return a string compatible with expected display
	 */
	static String idsToString(Set<Integer> ids) {
		// FIXME: sort ids.
		String res = "";
		int i = 0;
		for (Integer id : ids) {
			if (i == 0) {
				// first element
				res += id;
			} else if (i == ids.size() - 1) {
				// last element
				res += " and " + id;
			} else {
				// all the others
				res += ", " + id;
			}
			i++;
		}
		return res;
	}
}
