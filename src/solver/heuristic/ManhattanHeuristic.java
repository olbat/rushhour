package solver.heuristic;

import solver.State;

public class ManhattanHeuristic implements Heuristic {

	public int performCost(State s) {
		return Heuristic.PRIORITY * (
				Math.abs(s.getBoard().getRedCar().getPosition().getX() - s.getBoard().getRedCar().getPosition().getY())
				+ Math.abs(s.getBoard().getGoal().getPosition().getX() - s.getBoard().getGoal().getPosition().getY())			
		);
	}

}
