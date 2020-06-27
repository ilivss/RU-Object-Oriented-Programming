package slidingGame;

import java.util.*;

/**
 * A class that implements a breadth-first search algorithm for finding the
 * Configurations for which the isSolution predicate holds
 */
public class Solver {
	private Queue<Configuration> toExamine;				// A queue for maintaining states that are not visited yet.
	private Collection<Configuration> encountered;		// A collection of states that have been visited
	private HashSet<Configuration> hashsetje;

	public Solver(Configuration g) {
//		toExamine = new LinkedList<>();
		toExamine = new PriorityQueue<>();
		toExamine.add(g);

		encountered = new LinkedList<>();
		hashsetje = new HashSet<>();
	}

	/**
	 * A skeleton implementation of the solver
	 *
	 * @return a string representation of the solution
	 */
	public String solve() {
		while (!toExamine.isEmpty()) {
			Configuration current = toExamine.remove();
			if (current.isSolution()) {
				return solutionToString(current);
			} else {
				// If next is not a solution, add it to encountered list.
//				encountered.add(next);
				hashsetje.add(current);
				for (Configuration succ : current.successors()) {
					// If successor is not in encountered: add to toExamine.
					if (!hashsetje.contains(succ)) {
						toExamine.add(succ);
					}
				}
			}
		}
		return "Failure! Not solvable!\n";
	}

	private String solutionToString (Configuration solution){
		StringBuilder out = new StringBuilder();
		int steps = 0;

		out.append("SOLUTION FOUND!\n");

		for (Configuration conf : solution.pathFromRoot()){
			out.append(String.format("Step %d\n", steps++));
			out.append(conf.toString());
			out.append("\n");
		}

		return out.toString();
	}
}
