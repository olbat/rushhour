package solver;

import java.util.ArrayList;
import java.util.Collections;

import rushhour.VehicleClashException;
import solver.heuristic.Heuristic;
import solver.heuristic.SimpleHeuristic;

public class DFSearch<T extends Node> implements Search<T> {
	
	private int nb_operations;
	
	public ArrayList<T> getSolution(Tree<T> t, Heuristic h) throws VehicleClashException {
		ArrayList<T> ret = getSolution(t.getRoot(), h);
		if (ret != null)
			Collections.reverse(ret);
		return ret;
	}

	public ArrayList<T> getSolution(T current, Heuristic h) throws VehicleClashException {
		nb_operations ++;
		ArrayList<T> ret;
		if (current == null)
			return null;
		else if (current.isSatisfied()) {
			ret = new ArrayList<T>();
			ret.add(current);
		} else {
			//System.out.println(current.getState() + "/" + current.getState().getPossibleActions());
			
			current.createChilds(h);
			current.doAction();
			h= new SimpleHeuristic();
			ret = getSolution((T)current.getFirstChild(), h);
			current.revertAction();
			
			if (ret == null) {
				ret = getSolution((T)current.getPrec(), h);
				
				(Runtime.getRuntime()).gc();
			} else {
				ret.add(current);
			}
		}
		return ret;
	}
	
	public int getNbOperations() {
		return this.nb_operations;
	}

}
