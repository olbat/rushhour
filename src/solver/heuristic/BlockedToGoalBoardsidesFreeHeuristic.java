package solver.heuristic;

import solver.State;

public class BlockedToGoalBoardsidesFreeHeuristic implements Heuristic {
	private BlockedToGoalHeuristic blockedToGoal = new BlockedToGoalHeuristic();
	private BoardsidesFreeHeuristic boardsidesFree = new BoardsidesFreeHeuristic();
	
	public int performCost(State s) {
		int ret = 0;
		ret += blockedToGoal.performCost(s);
		ret += boardsidesFree.performCost(s);
		return ret;
	}
}
