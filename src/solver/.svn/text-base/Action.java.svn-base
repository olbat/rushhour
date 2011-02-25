package solver;

import rushhour.RushHourBoard;
import rushhour.Vehicle;
import rushhour.VehicleClashException;

public class Action {
	private Vehicle target;
	private int moveOffset; /* +/-, up/down, left/right */
	
	public Action(Vehicle t, int mo) {
		target = t;
		moveOffset = mo;
	}

	public Vehicle getTarget() {
		return target;
	}
	
	public boolean isOpposite(Action a) {
		return this.target == a.target && this.moveOffset == -1 * a.moveOffset;
	}

	public int getMoveOffset() {
		return moveOffset;
	}
	
	private void apply(RushHourBoard rhb, int off) throws VehicleClashException {
		if (target.getDirection() == Vehicle.Direction.HORIZONTAL)
			rhb.move(target, target.getPosition().getX() + off, target.getPosition().getY());
		else
			rhb.move(target, target.getPosition().getX(), target.getPosition().getY() + off);
	}
	
	public void apply(RushHourBoard rhb) throws VehicleClashException {
		this.apply(rhb,moveOffset);
	}
	
	public void revert(RushHourBoard rhb) throws VehicleClashException {
		this.apply(rhb,-(moveOffset));
	}
	
	public void setTarget(Vehicle v) {
		target = v;
	}
	
	public String toString() {
		return target.getName() + "," + moveOffset;
	}
}
