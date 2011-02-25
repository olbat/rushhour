package solver.heuristic;

import solver.State;

public class BlockedToGoalBoardsidesFreeFreeFromRedCarHeuristic implements Heuristic {
	private BlockedToGoalHeuristic blockedToGoal = new BlockedToGoalHeuristic();
	private BoardsidesFreeHeuristic boardsidesFree = new BoardsidesFreeHeuristic();
	private FreeFromRedCarHeuristic freeFromRedCar = new FreeFromRedCarHeuristic();
	
	public int performCost(State s) {
		int ret = 0;
		
		ret += blockedToGoal.performCost(s);
		ret += boardsidesFree.performCost(s);
		ret += freeFromRedCar.performCost(s);
	
		return ret;
	}
}
