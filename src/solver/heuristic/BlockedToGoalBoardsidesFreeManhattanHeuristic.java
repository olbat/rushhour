package solver.heuristic;

import solver.State;

public class BlockedToGoalBoardsidesFreeManhattanHeuristic implements Heuristic {
	private BlockedToGoalHeuristic blockedToGoal = new BlockedToGoalHeuristic();
	private BoardsidesFreeHeuristic boardsidesFree = new BoardsidesFreeHeuristic();
	private ManhattanHeuristic manhattan = new ManhattanHeuristic();
	
	public int performCost(State s) {
		int ret = 0;
		ret += blockedToGoal.performCost(s);
		ret += boardsidesFree.performCost(s);
		ret += manhattan.performCost(s);
		return ret;
	}
}
