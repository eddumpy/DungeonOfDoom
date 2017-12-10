package DungeonOfDoom.map;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import DungeonOfDoom.listener.MoveListener;

/** 
 * Class for handling map drawing.
 */
public class MapHandling implements Functional{
	
	private JFrame jFrame;
	//static int total_gold=0;
	private int total_gold=0;
	
	/**
	 * Constructor.
	 * @param jFrame
	 */
	public MapHandling(JFrame jFrame) {

		this.jFrame=jFrame;
		
	}

	/**
	 * Method for drawing map to screen from 2D array.
	 * 
	 * @param map
	 * 			2D array map (20x20)
	 */
	public void drawMap(char[][] map) {
		
		// Finds all JPanels and deletes all but the first (first JPanel is the side bar).
		// The reason for doing this to remove previous maps to avoid it being multiple times to the screen.
		Component[] comp = jFrame.getContentPane().getComponents();
		for (int i = 1; i < comp.length; i++) {
			if (comp[i] instanceof JPanel) {
				jFrame.remove(comp[i]);
			}
		}

		Item item=new Item("wall", "ground", "player", "gold", "door1",  "door2",  "door3",  "door4", "blue_potion","red_potion","vortex","bot");
		if(SideBar.gold_counter==SideBar.total_gold) {
			setVortex(map);
		}
		
		// Create 20x20 grid which the ImageIcons will then be added to
		JPanel grid = new JPanel();
		grid.setLayout(new GridLayout(20, 20));
		
		// For every square in grid, create new JLabel with ImageIcon for that tile/landform and add to square. 
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				switch (map[i][j]) {
				case '0':
					grid.add(new JLabel(item.getGround()));
					break;
				case '1':
					grid.add(new JLabel(item.getWall()));
					break;
				case '2':
					grid.add(new JLabel(item.getPlayer()));
					break;
				case '3':
					grid.add(new JLabel(item.getGold()));
					break;
				case '4':
					grid.add(new JLabel(item.getDoor1()));
					break;
				case '5':
					grid.add(new JLabel(item.getDoor2()));
					break;
				case '6':
					grid.add(new JLabel(item.getDoor3()));
					break;
				case '7':
					grid.add(new JLabel(item.getDoor4()));
					break;
				case '8':
					grid.add(new JLabel(item.getBlue_potion()));
					break;
				case '9':
					grid.add(new JLabel(item.getRed_potion()));
					break;
				case 'v':
					grid.add(new JLabel(item.getVortex()));
					break;
				case 'b':
					grid.add(new JLabel(item.getBot()));
					break;
				}

			}
		}
		
		// Adds the drawn grid to the frame
		jFrame.add(grid, BorderLayout.CENTER);
		jFrame.setVisible(true);
		jFrame.pack();
		
	}
	
	/** 
	 * Method for getting the total gold in all maps.
	 */
	@Override
	public int getGold(ArrayList<char[][]> mapList) {
		
		for(char[][] map : mapList) {
			for(int i=0;i<20;i++) {
				for(int j=0;j<20;j++) {
					if(map[i][j]=='3') {
						total_gold++;
					}					
				}							
			}
		}
		return total_gold;
		
	}
	
	/**
	 * Method for drawing the mini map
	 */
	@Override
	public void drawMiniMap(int room, SideBar side_bar) {
		
		Item item = new Item("room", "current_room");
		
		try {
			side_bar.remove(9);
		} catch (Exception e) {
			System.out.println("no mini map");
		}

		JPanel mini_grid = new JPanel();
		mini_grid.setLayout(new GridLayout(3, 3));
		
		int x = 1, y = 1;
		
		if (MoveListener.count % 2 == 0) {
			switch (room) {
				case 1:
					x = 1;
					y = 0;
					break;
				case 2:
					x = 0;
					y = 1;
					break;
				case 3:
					x = 1;
					y = 2;
					break;
				case 4:
					x = 2;
					y = 1;
					break;
			}

		}

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {

				if (i == x && j == y) {
					JLabel label1 = new JLabel(item.getCurrent_room());
					label1.setBorder(BorderFactory.createLineBorder(Color.black));
					mini_grid.add(label1);
				}

				else {
					JLabel label2 = new JLabel();
					if ((i == 0 && j == 0) || (i == 0 && j == 2) || (i == 2 && j == 0) || (i == 2 && j == 2)) {
						label2.setOpaque(true);
						label2.setBackground(Color.gray);
					} else {
						label2 = new JLabel(item.getMini_room());
						label2.setBorder(BorderFactory.createLineBorder(Color.black));
					}
					
					mini_grid.add(label2);

				}
			}
		}
		side_bar.add(mini_grid);
	}
	
	public void setVortex(char[][] map) {
		map[18][1]='v';
	}

}
