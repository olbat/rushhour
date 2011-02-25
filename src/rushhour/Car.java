package rushhour;

public class Car extends Vehicle {
	public static final int LENGTH = 2;
	
	public Car(int x, int y, Vehicle.Direction dir) {
		super(x,y,LENGTH,dir);
	}
	
	public Car(int x, int y, Vehicle.Direction dir, char c) {
		super(x,y,LENGTH,dir, c);
	}
	
	public Object clone() {
		Car c = new Car(this.getPosition().getX(), this.getPosition().getY(), this.getDirection(), this.getName());
		
		return c;
	}
}
