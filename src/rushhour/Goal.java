package rushhour;

import rushhour.Vehicle.Direction;

public class Goal {
	
	private Position position;
	
	public Goal(Position p){
		this.position = p;
	}
	
	public Position getPosition() {
		return position;
	}
	
	public void setPosition(Position position) {
		this.position = position;
	}
	
	public boolean equals(Object o) {
		return this == o
			|| (o instanceof Goal
				&& (((Goal)o).getPosition()).equals(this.getPosition()));
	}
	
}
