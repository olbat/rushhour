package solver.heuristic;

import rushhour.Vehicle;
import solver.State;

public class FreeFromRedCarHeuristic implements Heuristic {

	public int performCost(State s) {
		Vehicle tmp, prec = null;
		int i, ret = 0;
		if (s.getBoard().getRedCar().getDirection() == Vehicle.Direction.HORIZONTAL) {
			int y = s.getBoard().getRedCar().getPosition().getY();
			if (s.getBoard().getGoal().getPosition().getX() == 0) {
				i = s.getBoard().getRedCar().getLeftPosition();
				while ((s.getBoard().isFree(i, y)) && (i-- >= 0))
					ret++;
			} else {
				i = s.getBoard().getRedCar().getRightPosition();
				while ((s.getBoard().isFree(i, y)) && (i++ < s.getBoard().getWidth() - 1))
					ret++;
			}
		} else {
			int x = s.getBoard().getRedCar().getPosition().getX();
			if (s.getBoard().getGoal().getPosition().getY() == 0) {
				i = s.getBoard().getRedCar().getUpPosition();
				while ((s.getBoard().isFree(x, i)) && (i-- >= 0))
					ret++;
			} else {
				i = s.getBoard().getRedCar().getDownPosition();
				while ((s.getBoard().isFree(x, i)) && (i++ < s.getBoard().getHeight() - 1))
					ret++;
			}
		}

		return ret * Heuristic.PRIORITY;
	}

}
