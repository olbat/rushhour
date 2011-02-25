package solver.heuristic;

import solver.State;

public class TestHeuristic implements Heuristic {
	private BlockedToGoalHeuristic blockedToGoal = new BlockedToGoalHeuristic();
	private BoardsidesFreeHeuristic boardsidesFree = new BoardsidesFreeHeuristic();
	private FreeFromRedCarHeuristic freeFromRedCar = new FreeFromRedCarHeuristic();
	private FreeNeighbourHeuristic freeNeighbour = new FreeNeighbourHeuristic();
	private RepulsiveHeuristic repulsive = new RepulsiveHeuristic();
	private AttractiveHeuristic attractive = new AttractiveHeuristic();
	private PriorityRedCarHeuristic priorityRedCar = new PriorityRedCarHeuristic();
	private SimpleHeuristic simple = new SimpleHeuristic();
	
	public int performCost(State s) {
		int ret = 0;
		
		//ret += simple.performCost(s);
		ret += blockedToGoal.performCost(s);
		ret += boardsidesFree.performCost(s);
		ret += freeFromRedCar.performCost(s);
		ret += priorityRedCar.performCost(s);
		ret += attractive.performCost(s);
		ret += freeNeighbour.performCost(s);
		
	
		return ret;
	}
}
