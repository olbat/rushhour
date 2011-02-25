package solver.heuristic;

import rushhour.RushHourBoard;
import solver.State;

public class SimpleHeuristic implements Heuristic {

	public int performCost(State s) {
		return 1;
	}

}
