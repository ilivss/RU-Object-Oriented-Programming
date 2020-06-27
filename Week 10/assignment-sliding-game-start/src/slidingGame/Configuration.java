package slidingGame;
/**
 * An interface for representing nodes in a state space.
 */

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public interface Configuration extends Comparable<Configuration> {
	/**
	 * To obtain the parent of the current configuration, i.e. the configuration
	 * which had this as one of its successors
	 *
	 * @return a reference to the parent
	 */
	public abstract Configuration getParent();

	/**
	 * To obtain the successors for a specific configuration
	 *
	 * @return a collection of configurations containing the successors
	 */
	public abstract Collection<Configuration> successors();

	/**
	 * For marking final / solution configurations.
	 * 
	 * @return true if a this is a solution, false otherwise
	 */
	public abstract boolean isSolution();

	/**
	 * To build a path from the root configuration to the current one.
	 *
	 * @return a list of successive configurations from the root to 'this'
	 */
	public default List<Configuration> pathFromRoot() {
		List<Configuration> out = new LinkedList<>();

		Configuration current = this;
		out.add(current);

		while (current.getParent() != null){
			current = current.getParent();
			out.add(current);
		}

		Collections.reverse(out);
		return out;
	}
}
