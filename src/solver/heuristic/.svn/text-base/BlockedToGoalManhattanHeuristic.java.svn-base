package solver.heuristic;

import solver.State;

public class BlockedToGoalManhattanHeuristic implements Heuristic {
	private BlockedToGoalHeuristic blockedToGoal = new BlockedToGoalHeuristic();
	private ManhattanHeuristic manhattan = new ManhattanHeuristic();
	
	public int performCost(State s) {
		int ret = 0;
		ret += blockedToGoal.performCost(s);
		ret += manhattan.performCost(s);
		return ret;
	}
}
