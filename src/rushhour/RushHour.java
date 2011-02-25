package rushhour;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import GUI.RushHourFrame;
import solver.AStarSearch;
import solver.Action;
import solver.BFSearch;
import solver.DFSearch;
import solver.IDAStarSearch;
import solver.NodeClone;
import solver.NodeClone;
import solver.State;
import solver.Tree;
import solver.heuristic.*;

public class RushHour {
	public static void main(String[] args) throws VehicleClashException {
		if(args.length < 2) {
			System.out.println("usage: java RushHour [astar|df|[idastar maxCost]] file.rh");
			System.exit(0);
		}
		if( (args.length == 2 && args[0].toLowerCase().compareTo("idastar") == 0) || (args.length == 3 &&  !(args[0].toLowerCase().compareTo("idastar") == 0)) ) {
			System.out.println("usage: java RushHour [astar|df|[idastar maxCost]] file.rh");
			System.out.println("Help : idastar requires a cost parameter");
			System.exit(0);
		}
		//for the df algorithm
		int nb_operations_df = 0;
		
		System.out.println("Loading file :");
		RushHourBoard rhb = new RushHourBoard();
		try {
			rhb.constructFromFile(args[1]);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(rhb);
		System.out.print("Optimized calculations are being performed, please wait ... ");
		
		ArrayList<NodeClone> res = null;
		TestHeuristic h = new TestHeuristic();
		long execTime = System.currentTimeMillis();
		
		if(args[0].toLowerCase().compareTo("astar") == 0) {
			
			AStarSearch<NodeClone> s = new AStarSearch<NodeClone>();
			res = s.getSolution(new Tree<NodeClone>(rhb,
					new NodeClone(null, null, new State(new Action(rhb.getRedCar(), 0), rhb), 0)), h);
		
		}else if(args[0].toLowerCase().compareTo("df") == 0){
			
			DFSearch<NodeClone> s = new DFSearch<NodeClone>();
			res = s.getSolution(new Tree<NodeClone>(rhb,
					new NodeClone(null, null, new State(new Action(rhb.getRedCar(), 0), rhb), 0)), h);
			nb_operations_df = s.getNbOperations();
		}else if(args[0].toLowerCase().compareTo("idastar") == 0){
			
			IDAStarSearch<NodeClone> s = new IDAStarSearch<NodeClone>();
			s.setMaxCost(Integer.parseInt(args[2]));
			res = s.getSolution(new Tree<NodeClone>(rhb,
					new NodeClone(null, null, new State(new Action(rhb.getRedCar(), 0), rhb), 0)), h);
		}
		
		execTime = System.currentTimeMillis() - execTime; 
		System.out.println("OK\n");
		if (res == null)
			System.out.println("There is no solution, sorry.");
		else {
			System.out.print("A solution has been found in " + (res.size() - 1) + " steps and " + ((float)execTime/1000) + "s\nLaunching GUI ...");
			
			RushHourFrame<NodeClone> rof = new RushHourFrame<NodeClone>(res, execTime);
			
			if(args[0].toLowerCase().compareTo("df") == 0){
				System.out.println("Nb operations by the df algorithm : " + nb_operations_df);
			}
		}
	}
}
