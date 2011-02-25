package solver;

import rushhour.VehicleClashException;
import solver.heuristic.Heuristic;

public class NodeNoClone extends Node {
	public NodeNoClone(Node pa, Node pr, State s, int cost) {
		super(pa, pr, s, cost);
	}
	
	public NodeNoClone createChilds(Heuristic h) throws VehicleClashException {
		NodeNoClone tmp = null;
		String key;
		
		state.doAction();
		for (Action a : state.getPossibleActions()) {
			key = Node.getKey(this,a);

			/* Check if this state was already used */
			if (!NodeMap.contains(key)) {
				tmp = new NodeNoClone(this, tmp, new State(a,state.getBoard()), h.performCost(state));
				NodeMap.put(key, tmp);
			}
		}
		state.revertAction();
		
		this.firstChild = tmp;
		
		return tmp;
	}
	
	public boolean isSatisfied() throws VehicleClashException {
		boolean ret;
		doAction();
		ret = state.isSatisfiedState();
		revertAction();
		return ret;
	}

	public void doAction() throws VehicleClashException {
		state.doAction();
	}

	public void revertAction() throws VehicleClashException {
		state.revertAction();
	}
}
