package DungeonOfDoom.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;

import java.net.URL;

import DungeonOfDoom.game.Game;
import DungeonOfDoom.game.SideBar;
import DungeonOfDoom.map.Map;
import DungeonOfDoom.map.MapHandling;

/**
 * Class for handling arrow key presses for movement
 */
public class MoveListener implements KeyListener, Runnable {

	private char[][] map1;
	private int x, y, bx, by, thread;
	private int door;
	private boolean toSave;
	private boolean toSwitch;
	public static int count = 1; // records whether in outer or centre room (odd or even, respectively). Used in mini-map.
	private ArrayList<char[][]> mapList = new ArrayList<char[][]>();
	public static int time_penalty;
	private MapHandling mapHandling;
	private Map newMap;
	private SideBar side_bar;
	private JFrame frame;
	private int mute_count = 0;
	URL filepath = this.getClass().getResource("/collision.wav");
	AudioInputStream collision = null;
	
	/**
	 * Constructor
	 * @param map
	 * 			20x20 2D array
	 * @param x
	 * 			x position of player
	 * @param y
	 * 			y position of player
	 * @param mapHandling
	 * 			Object for handling map drawing
	 * @param sideBar
	 * 			Menu object for showing information at side of game
	 * @param frame
	 * 			JFrame that contains viewable elements of game
	 */
	public MoveListener(char[][] map, int x, int y, int bx, int by, MapHandling mapHandling, SideBar sideBar,
			JFrame frame, int thread) {

		this.map1 = map;
		this.x = x;
		this.y = y;
		this.bx = bx;
		this.by = by;
		this.thread = thread;
		this.mapHandling = mapHandling;
		this.side_bar = sideBar;
		this.frame = frame;
		for (int i = 0; i < 5; i++)
			mapList.add(null);

	}

	
	/**
	 * Method for checking the next tile in the player's path. Used for collision detection.
	 * @param x
	 * 			x position of player
	 * @param y
	 * 			y position of player
	 * @return String of what the next tile/land-form is
	 */
	public String nextLandForm(int x, int y) {
		if (map1[x][y] == '1')
			return "wall";
		else if (map1[x][y] == '2')
			return "player";
		else if (map1[x][y] == '4' || map1[x][y] == '5' || map1[x][y] == '6' || map1[x][y] == '7')
			return "door";
		else if (map1[x][y] == '8')
			return "blue_potion";
		else if (map1[x][y] == '9')
			return "red_potion";
		else if (map1[x][y] == '3')
			return "gold";
		else if (map1[x][y] == 'b')
			return "bot";
		else if (map1[x][y] == 'v')
			return "vortex";
		return "land";
		
	}

	
	/**
	 * Auto-generated method stub from KeyListener interface.
	 */
	public void tryToMove() {
		
		map1[bx][by] = '0';
		int temptbx, temptby;
		temptbx = bx;
		temptby = by;
		Random random = new Random();
		int rand = random.nextInt(4);
		
		if (toSave == false) {
			switch (rand) {
			case 0:
				bx -= 1;
				break;
			case 1:
				bx += 1;
				break;
			case 2:
				by -= 1;
				break;
			case 3:
				by += 1;
				break;
			}
		}
		
		if(nextLandForm(bx, by) == "player") {
			time_penalty += 10;
			try {
				collision = AudioSystem.getAudioInputStream(filepath);
				Clip clip=AudioSystem.getClip();
				clip.open(collision);
				clip.start();
			} catch (Exception e2) {
				System.out.println("Collision not played");
			}
		}
		
		if (nextLandForm(bx, by) != "land") {
			bx = temptbx;
			by = temptby;
		}		
		map1[bx][by] = 'b';
		mapHandling.drawMap(map1);
	}
	
	public void movePlayer(String dir) {
		
		switch (dir) {
			case "Up":
				x -= 1;
				break;
			case "Down":
				x += 1;
				break;
			case "Left":
				y -= 1;
				break;
			case "Right":
				y += 1;
				break;
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	/**
	 * Method for handling key press actions.
	 */
	@Override
	public void keyPressed(KeyEvent e) {

		map1[x][y] = '0';

		int tempx, tempy;
		tempx = x;
		tempy = y;
		
		switch (e.getKeyCode()) {
			case (KeyEvent.VK_UP): {
				movePlayer("Up");
				break;
			}
			case (KeyEvent.VK_DOWN): {
				movePlayer("Down");
				break;
	
			}
			case (KeyEvent.VK_LEFT): {
				movePlayer("Left");
				break;
			}
			case (KeyEvent.VK_RIGHT): {
				movePlayer("Right");
				break;
			}
			case (KeyEvent.VK_M): {
				if (mute_count % 2 == 0)
					Game.clip.stop();
				else {
					Game.clip.start();
				}
				mute_count++;
				break;
			}
		}
		
		// If next landform is a wall, set player's position back to what it was previously.
		if (nextLandForm(x, y) == "wall") {
			x = tempx;
			y = tempy;
		}
		// If next landform is gold, increment gold counter
		if (nextLandForm(x, y) == "gold") {
			SideBar.gold_counter++;
			SideBar.gold_count.setText("Gold count = " + SideBar.gold_counter + "/" + SideBar.total_gold);
		}
		// If next landform is potion, incur time penalty
		if (nextLandForm(x, y) == "blue_potion") {
			time_penalty += 10;
		}
		if (nextLandForm(x, y) == "red_potion") {
			if(SideBar.countTime < 10)
				time_penalty -= SideBar.countTime;
			else 
				time_penalty -= 10;
		}
		if( nextLandForm(x, y) == "bot") {
			x = tempx;
			y = tempy;
			time_penalty += 10;
			try {
				collision = AudioSystem.getAudioInputStream(filepath);
				Clip clip=AudioSystem.getClip();
				clip.open(collision);
				clip.start();
			} catch (Exception e2) {
				System.out.println("not played");
			}
		}
		// If next landform is a door, check which door and handle maps.
		if (nextLandForm(x, y) == "door") {

			switch (map1[x][y]) {
				case '4':
					door = 1;
					break;
				case '5':
					door = 2;
					break;
				case '6':
					door = 3;
					break;
				case '7':
					door = 4;
					break;
			}

			x = tempx;
			y = tempy;
			toSave = true;
			toSwitch = true;

			if (toSave == true) {
				map1[x][y] = '2';
				if (count % 2 == 1)
					mapList.set(0, map1);
				else {
					mapList.set(door, map1);
				}
				count++;
				setCount(count);
				toSave = false;

			}
			
			if (toSwitch == true) {
				System.out.println(door);

				if (mapList.get(door) != null) {
					for (int i = 0; i < 20; i++) {
						for (int j = 0; j < 20; j++)
							System.out.print(mapList.get(door)[i][j] + " ");
						System.out.println("");
					}
					if (count % 2 == 1) {
						System.out.println(mapList.get(0));
						newMap = new Map(mapList.get(0));
					} else {
						newMap = new Map(mapList.get(door));
					}
				} else {
					newMap = new Map(door);
				}
				x = newMap.getX();
				y = newMap.getY();
				bx = newMap.getBx_pos();
				by = newMap.getBy_pos();
				map1 = newMap.getMap();
				toSwitch = false;
			}
		}

		// If next landform is a vortex, finish game.
		if(nextLandForm(x, y) == "vortex") {
			SideBar.isFinish = true;
		}
		
		map1[x][y] = '2';
		
		mapHandling.drawMap(map1);
		mapHandling.drawMiniMap(door, side_bar);
		
	}
	
	/**
	 * Auto-generated method stub from KeyListener interface.
	 */
	@Override
	public void keyReleased(KeyEvent e) {
	}
	
	/**
	 * Accessor. Gets count for whether in centre or outside room.
	 * @return
	 */
	public int getCount() {
		return count;
	}
	
	/**
	 * Mutator. Sets whether player is in centre or outside room.
	 * @param count
	 */
	public void setCount(int count) {
		this.count = count;
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

	@Override
	public void run() {
		if (thread == 1) {
			while (!SideBar.isFinish) {
				try {
					Thread.sleep(140);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				tryToMove();
			}
		}
	}
}
