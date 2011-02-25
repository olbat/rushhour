package rushhour;

public class Position {
	private int x;
	private int y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public String toString() {
		return "(" + x + "," + y + ")";
	}
	
	public boolean equals(Object p) {
		return this == p
			|| (p instanceof Position
				&& ((Position)p).getX() == this.getX()
				&& ((Position)p).getY() == this.getY()
				);
	}
}
