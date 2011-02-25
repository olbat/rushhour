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
import solver.NodeMap;
import solver.State;
import solver.Tree;
import solver.heuristic.*;

public class RushHourTest {
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
		
		RushHourBoard rhb = new RushHourBoard();
		try {
			rhb.constructFromFile(args[1]);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(rhb);
		System.out.println("Optimized calculations are being performed, please wait ... ");
		
		ArrayList<Heuristic> ah = new ArrayList<Heuristic>();
	
		ah.add(new SimpleHeuristic());
		ah.add(new AttractiveHeuristic());
		ah.add(new BlockedToGoalBoardsidesFreeFreeFromRedCarHeuristic());
		ah.add(new BlockedToGoalBoardsidesFreeHeuristic());
		ah.add(new BlockedToGoalBoardsidesFreeManhattanHeuristic());
		ah.add(new BlockedToGoalHeuristic());
		ah.add(new BlockedToGoalManhattanHeuristic());
		ah.add(new BoardsidesFreeHeuristic());
		ah.add(new EuclideHeuristic());
		ah.add(new FreeFromRedCarHeuristic());
		ah.add(new FreeNeighbourHeuristic());
		ah.add(new ManhattanHeuristic());
		ah.add(new PriorityRedCarHeuristic());
		ah.add(new RandomHeuristic());
		ah.add(new RepulsiveHeuristic());
		ah.add(new TestHeuristic());
		
		for(Heuristic h : ah){
			//reconstruct board
			rhb = new RushHourBoard();
			try {
				rhb.constructFromFile(args[1]);
			} catch (IOException e) {
				e.printStackTrace();
			}
			//reinit res
			ArrayList<NodeClone> res = null;
			long execTime = System.currentTimeMillis();
			
			//empty the static NodeMap
			NodeMap.clean();
			
			if(args[0].toLowerCase().compareTo("astar") == 0) {
				
				AStarSearch<NodeClone> s = new AStarSearch<NodeClone>();
				res = s.getSolution(new Tree<NodeClone>(rhb,
						new NodeClone(null, null, new State(new Action(rhb.getRedCar(), 0), rhb), 0)), h);
		
			}else if(args[0].toLowerCase().compareTo("df") == 0){
				
				DFSearch<NodeClone> s = new DFSearch<NodeClone>();
				res = s.getSolution(new Tree<NodeClone>(rhb,
						new NodeClone(null, null, new State(new Action(rhb.getRedCar(), 0), rhb), 0)), h);
				
			}else if(args[0].toLowerCase().compareTo("idastar") == 0){
				
				IDAStarSearch<NodeClone> s = new IDAStarSearch<NodeClone>();
				s.setMaxCost(Integer.parseInt(args[2]));
				res = s.getSolution(new Tree<NodeClone>(rhb,
						new NodeClone(null, null, new State(new Action(rhb.getRedCar(), 0), rhb), 0)), h);
			}
			
			execTime = System.currentTimeMillis() - execTime; 
			System.out.print(h+"\t");
			if (res == null)
				System.out.println("No solution"+res);
			else {
				System.out.println((res.size() - 1) + "steps\t" + ((float)execTime/1000)+"seconds");
				
				//we don't display in test mode
				//RushHourFrame<NodeClone> rof = new RushHourFrame<NodeClone>(res, execTime);
		
			}
		}
		
	}
}
