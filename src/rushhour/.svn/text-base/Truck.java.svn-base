package rushhour;

public class Truck extends Vehicle {
	public static final int LENGTH = 3;
	
	public Truck(int x, int y, Vehicle.Direction dir) {
		super(x,y,LENGTH,dir);
	}
	
	public Truck(int x, int y, Vehicle.Direction dir, char c) {
		super(x,y,LENGTH,dir, c);
	}
	
	public Object clone() {
		Truck t = new Truck(this.getPosition().getX(), this.getPosition().getY(), this.getDirection(), this.getName());
		
		return t;
	}
}
