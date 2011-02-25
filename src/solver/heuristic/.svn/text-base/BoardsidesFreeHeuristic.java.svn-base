package solver.heuristic;

import rushhour.Vehicle;
import solver.State;

public class BoardsidesFreeHeuristic implements Heuristic {
	/* WARNING: only usable if the goal is not on corners of the board */
	public int performCost(State s) {
		int ret = 0;
		if (s.getBoard().getRedCar().getDirection() == Vehicle.Direction.HORIZONTAL) {
			int y;
			//if ((s.getResAction().getTarget().getDirection() == Vehicle.Direction.VERTICAL)
			//	&& (s.getBoard().getRedCar().getPosition().getX() > s.getResAction().getTarget().getPosition().getX()))
			y = 0;
			for (int x = 0; x < s.getBoard().getWidth(); x++) {
				if (s.getBoard().isFree(x, y))
					ret++;
			}
			y = s.getBoard().getHeight() - 1;
			for (int x = 0; x < s.getBoard().getWidth(); x++) {
				if (s.getBoard().isFree(x, y))
					ret++;
			}
		} else {
			int x = 0;
			for (int y = 0; x < s.getBoard().getHeight(); y++) {
				if (s.getBoard().isFree(x, y))
					ret++;
			}
			x = s.getBoard().getWidth() - 1;
			for (int y = 0; x < s.getBoard().getHeight(); y++) {
				if (s.getBoard().isFree(x, y))
					ret++;
			}
		}
		return ret * Heuristic.PRIORITY;
	}

}
