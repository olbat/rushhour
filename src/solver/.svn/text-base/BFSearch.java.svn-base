package solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

import rushhour.RushHourBoard;
import rushhour.VehicleClashException;
import solver.heuristic.Heuristic;
import solver.heuristic.SimpleHeuristic;

public class BFSearch<T extends Node> implements Search<T> {
	
	public ArrayList<T> getSolution(Tree<T> t, Heuristic h) throws VehicleClashException {
		return getSolution(t.getRoot(), 0, new HashMap<Integer,Boolean>(), h);
	}

	public ArrayList<T> getSolution(T current, int depth, HashMap<Integer,Boolean> added, Heuristic h) throws VehicleClashException {
		ArrayList<T> ret = null;
		if (current == null)
			ret = null;
		else if (current.getState().isSatisfiedState()) {
			ret = new ArrayList<T>();
			ret.add(current);
		} else {
			//System.out.println("\n" + depth + "/ " + current.getState() + "/" + current.getState().getPossibleActions());
			
			if (current.getPrec() != null) {
				ret = getSolution((T) current.getPrec(),depth,added, h);
			}
			
			if (ret == null) {
				current.createChilds(h);
				current.getState().doAction();
				ret = getSolution((T) current.getFirstChild(), depth + 1, added, h);
				current.getState().revertAction();
			}

			if ((ret != null) && (!added.containsKey(depth))) {
				added.put(depth, true);
				ret.add(current);
			}
		}
		return ret;
	}
}
