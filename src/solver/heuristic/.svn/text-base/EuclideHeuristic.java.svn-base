package solver.heuristic;

import rushhour.Vehicle;
import solver.State;

public class EuclideHeuristic implements Heuristic {

	public int performCost(State s) {
		return (int) (Heuristic.PRIORITY * 
			Math.sqrt(
				Math.abs(
						Math.pow((s.getBoard().getRedCar().getPosition().getX() 
								- s.getBoard().getRedCar().getPosition().getY()), 2)
				)
				+ Math.abs(
						Math.pow((s.getBoard().getGoal().getPosition().getX() 
								- s.getBoard().getGoal().getPosition().getY()), 2)
				)
			));
		
	}

}
