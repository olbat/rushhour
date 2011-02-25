package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import rushhour.Car;
import rushhour.RedCar;
import rushhour.RushHourBoard;
import rushhour.Truck;
import rushhour.Vehicle;
import rushhour.VehicleClashException;
import solver.Node;
import solver.State;

public class RushHourFrame<T extends Node> extends JFrame{

	private JPanel panel;
	private ArrayList<Character> vehicle_colors;
	
	public RushHourFrame(ArrayList<T> res, long execTime) throws VehicleClashException {
		super();
		
		this.vehicle_colors = new ArrayList<Character>();
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);
		
		JTabbedPane tabbedPane = new JTabbedPane();
		mainPanel.add(tabbedPane, BorderLayout.CENTER);
		
		JLabel label = new JLabel("Resolved in " + (res.size() - 1) + " steps, duration : " + execTime + "ms");
		label.setBorder(BorderFactory.createTitledBorder(""));
		mainPanel.add(label, BorderLayout.SOUTH);
		
		int rows = res.size() / 5 + (res.size() % 5 == 0 ? 0 : 1);
		JPanel panel = new JPanel(new GridLayout(rows, 1));
		JPanel currentPanel = new JPanel(new GridLayout(1, 5));
		tabbedPane.add(new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), "Graphic");
		
		JPanel panelConsole = new JPanel(new GridLayout());
		JTextArea console = new JTextArea("");
		console.setEditable(false);
		console.setFont(new Font("Monospaced", Font.PLAIN, 14));
		panelConsole.add(new JScrollPane(console));
		tabbedPane.add(panelConsole, "Terminal");
		
		int i = 0;
		
		for(T s : res) {
			s.doAction();
			JPanel newPanel = this.buildRushHourBoardPanel(s.getState().getBoard());
			newPanel.setBorder(BorderFactory.createTitledBorder(" Step " + i + " "));
			currentPanel.add(newPanel);
			
			console.append("Step " + i + " \n");
			console.append(s.getState().getBoard().toString());
			console.append("\n\n");
			
			i++;
			
			if(i % 5 == 0) {
				panel.add(currentPanel);
				currentPanel = new JPanel(new GridLayout(1, 4));
			}
		}
		
		if(i % 5 != 0) {
			while(i % 5 != 0) {
				currentPanel.add(new JPanel());
				i++;
			}
			
			panel.add(currentPanel);
		}
		
		this.setResizable(false);
		this.setPreferredSize(new Dimension(780, rows > 4 ? 700 : rows * 200 + 50));
		this.pack();
		this.setVisible(true);
		this.setTitle("RushHourSolver v1.0");
		this.setEnabled(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		System.out.println(" OK");
	}
	
	private JPanel buildRushHourBoardPanel(RushHourBoard rhb) {
		JPanel panel = new JPanel(new GridLayout(rhb.getWidth() + 2, rhb.getHeight() + 2, 1, 1));
		panel.setPreferredSize(new Dimension(150, 150));
		
		Color tmpColor;
		
		
		for(int i = 0; i < rhb.getWidth() + 2; i++) {
			for(int j = 0; j < rhb.getHeight() + 2; j++) {
				
				if(j > 0 && j < rhb.getWidth() + 1 && i > 0 && i < rhb.getHeight() + 1 && rhb.get(j - 1,i - 1) != null) {
					String n = "";
					
					if (rhb.get(j - 1,i - 1)  instanceof RedCar) {
						tmpColor = Color.RED;
						n = "x";
					}
					else if (rhb.get(j - 1,i - 1) instanceof Car) {
						tmpColor = this.getCarColor(rhb.get(j - 1,i - 1).getName(), rhb.getLength());
						n = rhb.get(j - 1,i - 1).getName() + "";
					}
					else if (rhb.get(j - 1,i - 1) instanceof Truck) {
						tmpColor = this.getTruckColor(rhb.get(j - 1,i - 1).getName(), rhb.getLength());
						n = rhb.get(j - 1,i - 1).getName() + "";
					}
					else
						tmpColor = Color.GRAY;
					
					JLabel jl = new JLabel(n);
					jl.setAlignmentX(JLabel.CENTER_ALIGNMENT);
					jl.setBackground(tmpColor);
					jl.setOpaque(true);
					panel.add(jl);
				
				}else{
					if(rhb.getRedCar().getDirection() == Vehicle.Direction.HORIZONTAL && rhb.getGoal().getPosition().getX() == rhb.getWidth() - 1 && j == rhb.getWidth() + 1 && rhb.getGoal().getPosition().getY() + 1 == i) {
						JLabel jl = new JLabel(" ");
						jl.setOpaque(true);
						jl.setBackground(Color.WHITE);
						panel.add(jl);
					} else if(rhb.getRedCar().getDirection() == Vehicle.Direction.HORIZONTAL && rhb.getGoal().getPosition().getX() == 0 && j == 0 && rhb.getGoal().getPosition().getY() + 1 == i) {
						JLabel jl = new JLabel(" ");
						jl.setOpaque(true);
						jl.setBackground(Color.WHITE);
						panel.add(jl);
					} else if(rhb.getRedCar().getDirection() == Vehicle.Direction.VERTICAL && rhb.getGoal().getPosition().getY() == 0 && i == 0 && rhb.getGoal().getPosition().getX() + 1 == j) {
						JLabel jl = new JLabel(" ");
						jl.setOpaque(true);
						jl.setBackground(Color.WHITE);
						panel.add(jl);
					} else if(rhb.getRedCar().getDirection() == Vehicle.Direction.VERTICAL && rhb.getGoal().getPosition().getY() == rhb.getHeight() - 1 && i == rhb.getHeight() + 1 && rhb.getGoal().getPosition().getX() + 1 == j) {
						JLabel jl = new JLabel(" ");
						jl.setOpaque(true);
						jl.setBackground(Color.WHITE);
						panel.add(jl);
					} else if(i == 0 || j == 0 || i == rhb.getWidth() + 1 || j == rhb.getHeight() + 1) {
						JLabel jl = new JLabel(" ");
						jl.setOpaque(true);
						jl.setBackground(Color.GRAY);
						panel.add(jl);
					} else {
						JLabel jl = new JLabel(" ");
						jl.setOpaque(true);
						jl.setBackground(Color.WHITE);
						panel.add(jl);
					}
				}
			}
		}
		
		return panel;
	}
	
//	private int getScale(int size) {
//		return (size > 0 ? Color.WHITE.getRed() / size : Color.WHITE.getRed());
//	}
//	
//	private Color getCarColor(char name, int size) {
//		return (new Color(Color.BLUE.getRed(),Color.BLUE.getGreen(),((name - 'a' + 1) * getScale(size)))).brighter().brighter();
//	}
//	
//	private Color getTruckColor(char name, int size) {
//		return new Color(Color.GREEN.getRed(),((name - 'a') * getScale(size)),Color.GREEN.getBlue());
//	}
	
	private Color getCarColor(char name, int size) {
		Color blue1 = new Color(80, 188, 255);
		Color blue2 = new Color(13, 15, 110);
		
		int i = 0;
		
		if(this.vehicle_colors.contains(name)) {
			i = this.vehicle_colors.indexOf(name);
		} else {
			i = this.vehicle_colors.size();
			this.vehicle_colors.add(name);
		}
		
		Color c = new Color((blue1.getRed()*(size-i)+blue2.getRed()*i)/size,(blue1.getGreen()*(size-i)+blue2.getGreen()*i)/size,(blue1.getBlue()*(size-i)+blue2.getBlue()*i)/size);
		return c;
	}
	
	private Color getTruckColor(char name, int size) {
		Color blue1 = new Color(88, 255, 80);
		Color blue2 = new Color(54, 118, 53);
		
		int i = 0;
		
		if(this.vehicle_colors.contains(name)) {
			i = this.vehicle_colors.indexOf(name);
		} else {
			i = this.vehicle_colors.size();
			this.vehicle_colors.add(name);
		}
		
		Color c = new Color((blue1.getRed()*(size-i)+blue2.getRed()*i)/size,(blue1.getGreen()*(size-i)+blue2.getGreen()*i)/size,(blue1.getBlue()*(size-i)+blue2.getBlue()*i)/size);
		return c;
	}
}
