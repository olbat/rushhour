package solver.heuristic;

import rushhour.Vehicle;
import solver.State;

public class BlockedToGoalHeuristic implements Heuristic {

	public int performCost(State s) {
		Vehicle tmp, prec = null;
		int i, ret = 0;
		if (s.getBoard().getRedCar().getDirection() == Vehicle.Direction.HORIZONTAL) {
			int y = s.getBoard().getRedCar().getPosition().getY();
			if (s.getBoard().getGoal().getPosition().getX() == 0) {
				i = s.getBoard().getRedCar().getLeftPosition();
				do {
					tmp = s.getBoard().get(i,y);
					if ((tmp != null) && (tmp != prec)) {
						ret++;
						prec = tmp;
					}
				} while (i-- >= 0);
			} else {
				i = s.getBoard().getRedCar().getRightPosition();
				do {
					tmp = s.getBoard().get(i,y);
					if ((tmp != null) && (tmp != prec)) {
						ret++;
						prec = tmp;
					}
				} while (i++ < s.getBoard().getWidth() - 1);
			}
		} else {
			int x = s.getBoard().getRedCar().getPosition().getX();
			if (s.getBoard().getGoal().getPosition().getY() == 0) {
				i = s.getBoard().getRedCar().getUpPosition();
				do {
					tmp = s.getBoard().get(x,i);
					if ((tmp != null) && (tmp != prec)) {
						ret++;
						prec = tmp;
					}
				} while (i-- >= 0);
			} else {
				i = s.getBoard().getRedCar().getDownPosition();
				do {
					tmp = s.getBoard().get(x,i);
					if ((tmp != null) && (tmp != prec)) {
						ret++;
						prec = tmp;
					}
				} while (i++ < s.getBoard().getHeight() - 1);
			}
		}

		return ret * Heuristic.PRIORITY;
	}

}
