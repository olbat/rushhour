package solver.heuristic;

import solver.State;

public class PriorityRedCarHeuristic implements Heuristic {

	public int performCost(State s) {
		int ret;
		if (s.getResAction().getTarget() == s.getBoard().getRedCar())
			ret = 0;
		else
			ret = 2;
		
		return ret;
	}

}
