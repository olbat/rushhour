package solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import rushhour.VehicleClashException;
import solver.heuristic.*;

public class IDAStarSearch<T extends Node> implements Search<T> {

	private int maxCost = 23;
	
	public void setMaxCost(int cost){
		this.maxCost = cost;
	}
	
	public ArrayList<T> getSolution(Tree<T> t, Heuristic h) throws VehicleClashException {
		int operation_count = 0;
		ArrayList<T> close = new ArrayList<T>(t.getRoot().getState().getBoard().getLength() * 1000), 
			open = new ArrayList<T>(t.getRoot().getState().getBoard().getLength() * 1000);
		HashMap<String,Integer> costMap = new HashMap<String,Integer>(),
			totalCost = new HashMap<String,Integer>();
		T currentNode = t.getRoot(), child;
		int gCurrent;
		
		open.add(currentNode);
		costMap.put(currentNode.getKey(), 0);
		while ((!open.isEmpty()) && (!open.get(0).isSatisfied())) {
			operation_count++;
			open.remove(currentNode);
			close.add(currentNode);
			
			//currentNode.doAllActions(currentNode);
			child = (T) currentNode.createChilds(h);
			gCurrent = costMap.get(currentNode.getKey());
			//currentNode.revertAllActions(currentNode);
			
			//currentNode.getState().doAction();
			do {
				if (child == null)
					break;
				
				operation_count++;
				
				//child.getParent().getState().doAction();
				if ((((!open.contains(child)) && (!close.contains(child))) 
						|| ((costMap.containsKey(child.getKey())) && (costMap.get(child.getKey()) > gCurrent + child.getActionCost()))
					) && (
							(!totalCost.containsKey(child.getKey()))
							|| ((totalCost.containsKey(child.getKey())) && (totalCost.get(child.getKey()) < this.maxCost))
					)
				) {
					child.setActionCost(gCurrent + child.getActionCost());
					costMap.put(child.getKey(),child.getActionCost());
					
					child.setTotalCost(child.getActionCost() + child.getHeuristicCost());
					totalCost.put(child.getKey(), child.getTotalCost());

					child.setParent(currentNode);
					if (open.contains(child))
						open.remove(child);
					
					open.add(child);
					Collections.sort(open);
					
					if (close.contains(child))
						close.remove(child);
				}
				//child.getParent().getState().revertAction();
			} while ((child = (T) child.getPrec()) != null);
			//currentNode.getState().revertAction();
			
			if (!open.isEmpty())
				currentNode = (T) open.get(0);
		}
		
		System.out.print("IDAStarSearch did it in " + operation_count + " operations and expanded " + close.size() + " nodes ...");
		
		if (open.isEmpty())
			return null;
		else {
			ArrayList<T> ret = new ArrayList<T>();
			
			do {
				ret.add(currentNode);
			} while ((currentNode = (T) currentNode.getParent()) != null);
			
			Collections.reverse(ret);
			
			return ret;
		}
	}
}
