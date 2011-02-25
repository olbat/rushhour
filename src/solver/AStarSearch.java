package solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import rushhour.VehicleClashException;
import solver.heuristic.*;

public class AStarSearch<T extends Node> implements Search<T> {

	public ArrayList<T> getSolution(Tree<T> t, Heuristic h) throws VehicleClashException {
		int operation_count = 0;
		ArrayList<T> close = new ArrayList<T>(t.getRoot().getState().getBoard().getLength() * 1000), 
			open = new ArrayList<T>(t.getRoot().getState().getBoard().getLength() * 1000);
		HashMap<String,Integer> costMap = new HashMap<String,Integer>();
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
				if (((!open.contains(child)) && (!close.contains(child))) 
						|| ((costMap.containsKey(child.getKey())) && (costMap.get(child.getKey()) > gCurrent + child.getActionCost()))
				) {
					child.setActionCost(gCurrent + child.getActionCost());
					costMap.put(child.getKey(),child.getActionCost());
					
					child.setTotalCost(child.getActionCost() + child.getHeuristicCost());

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
		
		float average = getAverage(close);
		
		System.out.print("AStarSearch did it in " + operation_count
				+ " operations and expanded " + close.size() + " nodes "
				+ "TotalCosts: {average=" + average + ", standard_deviation=" + getStandardDeviation(close,average) + "} ...");
		
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
	
	private float getAverage(ArrayList<T> list) {
		float sum = 0;
		for (T n : list)
			sum += n.getTotalCost();
		return sum/list.size();
	}
	
	private float getStandardDeviation(ArrayList<T> list, float average) {
		float sum = 0;
		for (T n : list)
			sum += Math.pow((n.getTotalCost() - average),2);
		sum = (float) Math.sqrt(sum/list.size());
	
		return sum;
	}
}
