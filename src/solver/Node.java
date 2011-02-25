package solver;

import java.util.Collections;
import java.util.Stack;

import rushhour.RushHourBoard;
import rushhour.VehicleClashException;
import solver.heuristic.Heuristic;

public abstract class Node implements Comparable<Node> {
	protected Node parent;
	protected Node firstChild;
	protected Node prec = null;
	protected State state;
	protected int heuristicCost;
	protected int actionCost;
	protected int totalCost;

	public Node(Node pa, Node pr, State s, int cost) {
		this.parent = pa;
		this.prec = pr;
		this.state = s;
		this.heuristicCost = cost;
	}
	
	public void setPrec(Node n) {
		n.prec = this.prec;
		this.prec = n;
	}
	
	public void setTotalCost(int t) {
		this.totalCost = t;
	}	
	
	public Node getPrec() {
		return prec;
	}
	
	public int getActionCost() {
		return actionCost;
	}

	public void setActionCost(int actionsCost) {
		this.actionCost = actionsCost;
	}

	public Node getParent() {
		return parent;
	}
	
	public Node getFirstChild() {
		return firstChild;
	}
	
	public String getKey() {
		return (state.getBoard().toString());
	}
	
	public static String getKey(Node n, Action a) {
		return (n.state.getBoard().toString() + "\n" + a.toString()).hashCode() + "";
	}
	
	public static String getKey(RushHourBoard rhb, State s) {
		return (rhb.toString() + "\n" + s.getResAction().toString()).hashCode() + "";
	}
	
	public State getState() {
		return state;
	}
	
	public int getHeuristicCost() {
		return heuristicCost;
	}

	public void setHeuristicCost(int cost) {
		this.heuristicCost = cost;
	}
	
	public void setParent(Node n) {
		this.parent = n;
	}
	
	public int getTotalCost() {
		return totalCost;
	}

	public int compareTo(Node n) {
		if (n.getTotalCost() > totalCost)
			return -1;
		else if (n.getTotalCost() < totalCost)
			return 1;
		else
			return 0;
	}
	
	public void doAllActions(Node n) throws VehicleClashException {
		Node tmp = n;
		Stack<State> stack = new Stack<State>();
		do {
			stack.add(tmp.getState());
		} while ((tmp = tmp.getParent()) != null);
			
		Collections.reverse(stack);
		
		while (!stack.isEmpty())
			stack.pop().doAction();
	}
	
	public void revertAllActions(Node n) throws VehicleClashException {
		Node tmp = n;
		Stack<State> stack = new Stack<State>();
		do {
			stack.add(tmp.getState());
		} while ((tmp = tmp.getParent()) != null);
			
		Collections.reverse(stack);
		
		while (!stack.isEmpty()) 
			stack.pop().revertAction();
	}

	public boolean equals(Object o) {
		if (o instanceof Node) {
			return getKey().compareTo(((Node)o).getKey()) == 0;
		}
		return false;
	}
	
	public abstract Node createChilds(Heuristic h) throws VehicleClashException;
	public abstract void doAction() throws VehicleClashException;
	public abstract void revertAction() throws VehicleClashException;
	public abstract boolean isSatisfied() throws VehicleClashException;
}
