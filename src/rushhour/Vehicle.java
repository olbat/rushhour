package rushhour;

import java.awt.Color;
import java.util.Random;

public abstract class Vehicle {
	public static char vehicle_name = 'a';
	
	public enum Direction {
		HORIZONTAL,
		VERTICAL
	}
	
	/* Position of the point at the top-left */
	protected char name;
	private Position position;
	private int length;
	private Direction direction;
	
	public Vehicle(int x, int y, int len, Direction dir) {
		position = new Position(x,y);
		length = len;
		direction = dir;
		name = vehicle_name++;
	}

	public Vehicle(int x, int y, int len, Direction dir, char c) {
		position = new Position(x,y);
		length = len;
		direction = dir;
		name = c;
	}
	
	public void move(int len) {
		if (direction == Direction.HORIZONTAL)
			this.position.setX(len);
		else
			this.position.setY(len);
	}
	
	public void translate(int dlen) {
		this.move(dlen);
	}
	
	public char getName() {
		return name;
	}
	
	public int getLength() {
		return length;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public Position getPosition() {
		return position;
	}
	
	public int getUpPosition() {
		return position.getY() - 1;
	}
	
	public int getDownPosition() {
		int ret = position.getY();
		
		if (direction == Direction.VERTICAL)
			ret += length;
		else
			ret++;
		
		return ret;
	}
	
	public int getLeftPosition() {
		return position.getX() - 1;
	}
	
	public int getRightPosition() {
		int ret = position.getX();
		
		if (direction == Direction.HORIZONTAL)
			ret += length;
		else
			ret++;
		
		return ret;
	}
	
	public String toString() {
		return name + "";
	}
	
	public abstract Object clone();
}
