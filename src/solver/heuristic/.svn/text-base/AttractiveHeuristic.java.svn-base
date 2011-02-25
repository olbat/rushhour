package solver.heuristic;

import solver.State;

public class AttractiveHeuristic implements Heuristic {
	public int performCost(State s) {
		/* Set Manhattan distance */
		int ret = Math.abs(s.getBoard().getRedCar().getPosition().getX() - s.getBoard().getRedCar().getPosition().getY())
			+ Math.abs(s.getResAction().getTarget().getPosition().getX() - s.getResAction().getTarget().getPosition().getY());
		
		if (!((s.getResAction().getTarget() != s.getBoard().getRedCar()) && (ret <= 3)))
			ret = 1;
		
		return ret * Heuristic.PRIORITY;
	}
}
