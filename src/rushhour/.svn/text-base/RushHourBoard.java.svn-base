package rushhour;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import rushhour.Vehicle.Direction;

public class RushHourBoard {
	public static final int SIZE = 6;
	public static int caca = 0;
	private int width;
	private int height;
	private Vehicle[][] board;
	private ArrayList<Vehicle> vehicles;
	private Goal goal;
	private String boardString;
	
	public RushHourBoard() {
		width = SIZE;
		height = SIZE;
		
		board = new Vehicle[width][];
		
		for(int i = 0; i < width; i++)
			board[i] = new Vehicle[height];
		
		this.setGoal(new Goal(new Position(width-1, (height / 2) -1)));

		this.vehicles = new ArrayList<Vehicle>();
	}
	
	public Vehicle[][] getBoard() {
		return board;
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Goal getGoal() {
		return goal;
	}
	
	public RedCar getRedCar() {
		for(Vehicle v : this.vehicles) {
			if(v instanceof RedCar)
				return (RedCar)v;
		}
		
		return null;
	}

	public void setGoal(Goal goal) {
		this.goal = goal;
	}
	
	public boolean isWon() {
		return this.board[this.goal.getPosition().getX()][goal.getPosition().getY()] instanceof RedCar;
	}
	
	public boolean isValidPosition(int x, int y) {
		return x >= 0 && x < this.width && y >= 0 && y < this.height;
	}
	
	public boolean isFreePosition(int x, int y) {
		return this.get(x, y) == null;
	}
	
	public void add(Vehicle v) throws VehicleClashException {
		this.vehicles.add(v);
		
		if (v.getDirection() == Vehicle.Direction.HORIZONTAL) {
			for (int i = v.getPosition().getX(); i < (v.getPosition().getX() + v.getLength()); i++) {
				if (board[i][v.getPosition().getY()] == null) {
					board[i][v.getPosition().getY()] = v;
				}
				else
					throw new VehicleClashException();
			}
		}
		else if (v.getDirection() == Vehicle.Direction.VERTICAL) {
			for (int i = v.getPosition().getY(); i < (v.getPosition().getY() + v.getLength()); i++) {
				if (board[v.getPosition().getX()][i] == null) {
					board[v.getPosition().getX()][i] = v;
				}
				else
					throw new VehicleClashException();
			}
		}
		generateBoardString();
	}
	
	public void remove(Vehicle v) {
		this.vehicles.remove(v);
		
		if (v.getDirection() == Vehicle.Direction.HORIZONTAL) {
			for (int i = v.getPosition().getX(); i < (v.getPosition().getX() + v.getLength()); i++) {
				board[i][v.getPosition().getY()] = null;
			}
		}
		else if (v.getDirection() == Vehicle.Direction.VERTICAL) {
			for (int i = v.getPosition().getY(); i < (v.getPosition().getY() + v.getLength()); i++) {
				board[v.getPosition().getX()][i] = null;
			}
		}
		generateBoardString();
	}
	
	public ArrayList<Vehicle> getVehicles() {
		return this.vehicles;
	}
	
	public Vehicle get(int x, int y) {
		return board[x][y];
	}
	
	public void move(Vehicle v, int x, int y) throws VehicleClashException {
		this.remove(v);
		
		v.getPosition().setX(x);
		v.getPosition().setY(y);
		
		this.add(v);
	}
	
	public int getLength() {
		return vehicles.size();
	}
	
	public Object clone() {
		RushHourBoard r = new RushHourBoard();
		r.width = this.width;
		r.height = this.height;
		
		r.board = new Vehicle[board.length][];
		for (int i = 0; i < board.length; i++)
			r.board[i] = new Vehicle[board[i].length];
		
		r.vehicles = (ArrayList<Vehicle>) new ArrayList<Vehicle>();
		
		for (Vehicle v: vehicles) {
			try {
				r.add((Vehicle)(v.clone()));
			} catch (VehicleClashException e) { e.printStackTrace(); }
		}
		
		r.goal = this.goal;
		
		return r;
	}
	
	public boolean isFree(int x, int y) {
		return get(x,y) == null;
	}
	
	public String toString() {
		return boardString;
	}
	
	public void generateBoardString() {
		StringBuffer sb = new StringBuffer();
		if (this.getRedCar() == null)
			return;
		
		for (int y = 0; y < this.width + 2; y++) {
			if(this.getRedCar().getDirection() == Vehicle.Direction.VERTICAL && this.getGoal().getPosition().getY() == 0 && this.getGoal().getPosition().getX() == y - 1) {
				sb.append(".");
			} else {
				sb.append("#");
			}
		}
		
		sb.append("\n");
		
		for (int y = 0; y < this.width; y++) {
			
			if(this.getRedCar().getDirection() == Vehicle.Direction.HORIZONTAL && this.getGoal().getPosition().getX() == 0 && this.getGoal().getPosition().getY() == y) {
				sb.append(".");
			} else {
				sb.append("#");
			}
			
			for (int x = 0; x < this.height; x++) {
				if (board[x][y] != null)
					sb.append(board[x][y].getName());
				else
					sb.append('.');
			}
			
			if(this.getRedCar().getDirection() == Vehicle.Direction.HORIZONTAL && this.getGoal().getPosition().getX() == this.getWidth() - 1 && this.getGoal().getPosition().getY() == y) {
				sb.append(".");
			} else {
				sb.append("#");
			}
			
			sb.append('\n');
		}
		
		for (int y = 0; y < this.width + 2; y++) {
			if(this.getRedCar().getDirection() == Vehicle.Direction.VERTICAL && this.getGoal().getPosition().getY() == this.getHeight() - 1 && this.getGoal().getPosition().getX() == y - 1) {
				sb.append(".");
			} else {
				sb.append("#");
			}
		}
		
		boardString = sb.toString();
	}
	
	public void flushBoard() {
		this.vehicles.clear();
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				board[i][j] = null;
			}
		}
	}
	
	public void constructFromFile(String path) throws VehicleClashException, NumberFormatException, IOException {
		BufferedReader file = null;
		try {
			file = new BufferedReader(new FileReader(path));
		
		int width = Integer.parseInt(file.readLine());
		int height = Integer.parseInt(file.readLine());
		} catch (FileNotFoundException e1) {
			System.out.println("File not found");
			//e1.printStackTrace();
			System.exit(0);
		}
		Goal goal = null;
		HashMap<Character,int[]> hm = new HashMap<Character,int[]>();
		
		this.flushBoard();
		ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
		this.vehicles = vehicles;
		Vehicle[][] board = new Vehicle[width][];
		this.board = board;
		
		for(int i = 0; i < width; i++)
			board[i] = new Vehicle[height];
		// +2 to consider the border
		for(int i = 0; i < height + 2; i++){
			String str = file.readLine();
			for(int j = 0; j < width + 2; j++){
				char c = str.charAt(j);
				switch(c) {
					case '.' :
						break;
					case '#' :
						break;
					case '@' :
						if(i == 0)
							goal = new Goal(new Position(j-1,i));
						if(i == height + 1)
							goal = new Goal(new Position(j-1,i-2));
						if(j == 0)
							goal = new Goal(new Position(j,i-1));
						if(j == width + 1)
							goal = new Goal(new Position(j-2,i-1));
						break;
					default : 
						if(!hm.containsKey(Character.valueOf(c))) {
							int tab[] = {i-1,j-1,1,0};
							hm.put(Character.valueOf(c), tab);
						}else{
							int tab[] = hm.get(Character.valueOf(c));
							tab[2]++;
							if(j >1 && str.charAt(j-1) == c) {
								tab[3] = 0;
							}else{
								tab[3] = 1;
							}
							
						}
						;break;
				}
			}
		}
		Set lesEntrees = hm.entrySet();
		Iterator it = lesEntrees.iterator();
		while (it.hasNext()){
			Map.Entry e = (Map.Entry)it.next();
			char name = ((Character)e.getKey()).charValue();
			int value[] = (int[])e.getValue();
			Direction d = value[3] == 0  ? Vehicle.Direction.HORIZONTAL : Vehicle.Direction.VERTICAL;
			if(((Character)e.getKey()).compareTo(Character.valueOf('x')) == 0) {
				RedCar r = new RedCar(value[1], value[0], d);
				this.add(r);
			}else if(value[2] == 2){
				Car c = new Car(value[1], value[0], d, name);
				this.add(c);
			}else{
				Truck t = new Truck(value[1], value[0], d, name);
				this.add(t);
			}
		}
		
		this.setGoal(goal);
		this.width = width;
		this.height = height;
		
	}

}
