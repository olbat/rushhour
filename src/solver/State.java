package solver;

import java.util.ArrayList;

import rushhour.RushHourBoard;
import rushhour.Vehicle;
import rushhour.VehicleClashException;

public class State {
	private Action resAction; /* The action we have to do to come to this State */
	private RushHourBoard board; /* Too big, to be removed */
	private ArrayList<Action> possibleActions;
	
	public State(Action a, RushHourBoard r) {
		resAction = a;
		board = r;
		possibleActions = new ArrayList<Action>();
	}
	
	public ArrayList<Action> getPossibleActions() {
		this.updatePossibleActions();
		return possibleActions;
	}

	public Action getResAction() {
		return resAction;
	}

	public RushHourBoard getBoard() {
		return board;
	}

	
	public void setResAction(Action resAction) {
		this.resAction = resAction;
	}

	public void setBoard(RushHourBoard board) {
		this.board = board;
	}

	private void updatePossibleActions() {
		this.possibleActions.clear();
		
		for(Vehicle v : this.board.getVehicles()) {
			this.updatePossibleActions(v);
		}
	}
	
	private void updatePossibleActions(Vehicle v) {
		if (v.getDirection() == Vehicle.Direction.HORIZONTAL) {
			if(this.board.isValidPosition(v.getRightPosition(), v.getPosition().getY()) && this.board.isFreePosition(v.getRightPosition(), v.getPosition().getY())) {
				Action a = new Action(v, 1);
				
				if(this.resAction == null || !this.resAction.isOpposite(a))
					this.possibleActions.add(a);
			}
			
			if(this.board.isValidPosition(v.getLeftPosition(), v.getPosition().getY()) && this.board.isFreePosition(v.getLeftPosition(), v.getPosition().getY())) {
				Action a = new Action(v, -1);
				
				if(!this.resAction.isOpposite(a))
					this.possibleActions.add(a);
			}
		} else {
			if(this.board.isValidPosition(v.getPosition().getX(), v.getDownPosition()) && this.board.isFreePosition(v.getPosition().getX(), v.getDownPosition())) {
				Action a = new Action(v, 1);

				if(this.resAction == null || !this.resAction.isOpposite(a))
					this.possibleActions.add(a);
			}
			
			if(this.board.isValidPosition(v.getPosition().getX(), v.getUpPosition()) && this.board.isFreePosition(v.getPosition().getX(), v.getUpPosition())) {
				Action a = new Action(v, -1);
				
				if(this.resAction == null || !this.resAction.isOpposite(a))
					this.possibleActions.add(a);
			}
		}
	}
	
	public boolean isSatisfiedState() throws VehicleClashException {
		return board.isWon();
	}
	
	public void doAction() throws VehicleClashException {
		if(this.resAction != null)
			resAction.apply(board);
	}
	
	public void revertAction() throws VehicleClashException {
		if(this.resAction != null)
			resAction.revert(board);
	}
	
	public boolean isPossibleAction() {
		return possibleActions.isEmpty();
	}
	
	public String toString() {
		return board + "(" + resAction + ")";
	}
}
