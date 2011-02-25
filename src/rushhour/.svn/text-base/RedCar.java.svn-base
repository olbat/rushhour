package rushhour;


public class RedCar extends Car {

	public RedCar(int x, int y, Direction dir) {
		super(x, y, dir, 'x');
	}

	public RedCar(int x, int y, Direction dir, char c) {
		super(x, y, dir, c);
	}

	public Object clone() {
		RedCar rc = new RedCar(this.getPosition().getX(), this.getPosition().getY(), this.getDirection(), this.getName());
		
		return rc;
	}
}
