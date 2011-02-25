package solver.heuristic;

import rushhour.RushHourBoard;
import solver.State;

public interface Heuristic {
	public static final int PRIORITY = 1;
	
	public abstract int performCost(State s);
}
