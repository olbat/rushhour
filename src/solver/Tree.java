package solver;

import java.util.HashMap;

import rushhour.RushHourBoard;

public class Tree<T extends Node> {
	private T root;
	private int depth;
	private RushHourBoard initialBoard;
	
	public Tree(T n, int d) {
		root = n;
		depth = d;
	}
	
	public Tree(RushHourBoard initialBoard, T n) {
		this(n, 0);
		this.initialBoard = initialBoard;
	}

	public T getRoot() {
		return root;
	}

	public void setRoot(T root) {
		this.root = root;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}
}
