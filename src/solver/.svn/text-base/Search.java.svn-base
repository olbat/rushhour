package solver;

import java.util.ArrayList;

import rushhour.RushHourBoard;
import rushhour.VehicleClashException;
import solver.heuristic.Heuristic;

public interface Search<T extends Node> {
	public ArrayList<T> getSolution(Tree<T> t, Heuristic h) throws VehicleClashException;
}
