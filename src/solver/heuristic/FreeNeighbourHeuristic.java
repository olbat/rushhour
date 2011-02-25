package solver.heuristic;

import rushhour.Vehicle;
import solver.State;

public class FreeNeighbourHeuristic implements Heuristic {

	public int performCost(State s) {
		int ret = 0;
		if (s.getResAction().getTarget().getDirection() == Vehicle.Direction.HORIZONTAL) {
			int y = s.getResAction().getTarget().getPosition().getY();
			if ((s.getBoard().isValidPosition(s.getResAction().getTarget().getLeftPosition(), y))
				&& (s.getBoard().isFree(
					s.getResAction().getTarget().getLeftPosition(),
					y
				)
			)) {
				ret++;
			}
			if ((s.getBoard().isValidPosition(s.getResAction().getTarget().getRightPosition(), y))
					&& s.getBoard().isFree(
					s.getResAction().getTarget().getRightPosition(),
					y
				)
			) {
				ret++;
			}
		} else {
			int x = s.getResAction().getTarget().getPosition().getX();
			if ((s.getBoard().isValidPosition(x,s.getResAction().getTarget().getUpPosition()))
				&& (s.getBoard().isFree(
					x,
					s.getResAction().getTarget().getUpPosition()
				)
			)) {
				ret++;
			}
			if ((s.getBoard().isValidPosition(x,s.getResAction().getTarget().getDownPosition()))
					&& s.getBoard().isFree(
					x,
					s.getResAction().getTarget().getDownPosition()
				)
			) {
				ret++;
			}
		}
		return (3 - ret) * Heuristic.PRIORITY;
	}

}
