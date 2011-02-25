package solver;

import rushhour.RushHourBoard;
import rushhour.VehicleClashException;
import solver.heuristic.Heuristic;

public class NodeClone extends Node {
	public NodeClone(Node pa, Node pr, State s, int cost) {
		super(pa, pr, s, cost);
	}

	public NodeClone createChilds(Heuristic h) throws VehicleClashException {
        NodeClone ret = null, tmp = null;
        RushHourBoard rhb;
        String key;
        
        for (Action a : state.getPossibleActions()) {
                key = Node.getKey(this,a);
                /* Check if this state was already used */
               if (!NodeMap.contains(key)) {
                        rhb = (RushHourBoard) state.getBoard().clone();
                        a.setTarget(rhb.get(a.getTarget().getPosition().getX(), a.getTarget().getPosition().getY()));
                        a.apply(rhb);
                        tmp = new NodeClone(this, tmp, new State(a,rhb), h.performCost(state));
                        tmp.setActionCost((Math.abs(a.getMoveOffset())));
                        //tmp.setActionCost(Integer.MAX_VALUE);
                        
                        NodeMap.put(key, tmp);
               }
        }
        this.firstChild = tmp;
        
        return tmp;
	}
	
	public boolean isSatisfied() throws VehicleClashException {
		return state.isSatisfiedState();
	}

	public void doAction() throws VehicleClashException {}

	public void revertAction() throws VehicleClashException {}

}
