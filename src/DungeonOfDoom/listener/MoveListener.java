package DungeonOfDoom.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.nio.file.Files;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;

import java.io.File;
import com.sun.org.apache.bcel.internal.classfile.Field;

import DungeonOfDoom.map.Map;
import DungeonOfDoom.map.MapHandling;
import DungeonOfDoom.map.SideBar;

/**
 * Class for handling arrow key presses for movement
 */
public class MoveListener implements KeyListener, Runnable {

	private char[][] map1;
	private int x, y, bx, by, thread;
	private int door;
	private boolean ToSave;
	private boolean ToSwitch;
	public static int count = 1;
	private ArrayList<char[][]> mapList = new ArrayList<char[][]>();
	public static int time_penalty;
	private MapHandling mapHandling;
	private Map newMap;
	private SideBar side_bar;
	private JFrame frame;
	File collision=new File("music/collision.wav");
	
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
		// TODO Auto-generated constructor stub
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
	public String nextlandform(int x, int y) {
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
		System.out.println("Rand" + rand);
		if (ToSave == false) {
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
		if(nextlandform(bx, by)=="player") {
			time_penalty+=10;
			try {
				Clip clip=AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(collision));
				clip.start();
			} catch (Exception e2) {
				// TODO: handle exception
				System.out.println("not played");
			}
		}
		if (nextlandform(bx, by) != "land") {
			bx = temptbx;
			by = temptby;
			// tryToMove();
		}		
		map1[bx][by] = 'b';
		mapHandling.drawMap(map1);
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
				x -= 1;
				break;
			}
			case (KeyEvent.VK_DOWN): {
				x += 1;
				break;
	
			}
			case (KeyEvent.VK_LEFT): {
				y -= 1;
				break;
			}
			case (KeyEvent.VK_RIGHT): {
				y += 1;
				break;
			}

		}
		
		// If next landform is a wall, set player's position back to what it was previously.
		if (nextlandform(x, y) == "wall") {
			x = tempx;
			y = tempy;
		}
		// If next landform is gold, increment gold counter
		if (nextlandform(x, y) == "gold") {
			SideBar.gold_counter++;
			SideBar.gold_count.setText("Gold count = " + SideBar.gold_counter + "/" + SideBar.total_gold);
		}
		// If next landform is potion, incur time penalty
		if (nextlandform(x, y) == "blue_potion") {
			time_penalty += 10;
		}
		if (nextlandform(x, y) == "red_potion") {
			if(SideBar.countTime<10)
				time_penalty-=SideBar.countTime;
			else 
				time_penalty -= 10;
		}
		if( nextlandform(x, y) == "bot") {
			x = tempx;
			y = tempy;
			time_penalty += 10;
			try {
				Clip clip=AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(collision));
				clip.start();
			} catch (Exception e2) {
				// TODO: handle exception
				System.out.println("not played");
			}
		}
		// If next landform is a door, check which door and handle maps.
		if (nextlandform(x, y) == "door") {
			// System.out.println(mapList.size());

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
			ToSave = true;
			ToSwitch = true;

			if (ToSave == true) {
				map1[x][y] = '2';
				if (count % 2 == 1)
					mapList.set(0, map1);
				else {
					mapList.set(door, map1);
				}
				count++;
				setCount(count);
				// savemap = map1;
				System.out.println("save");

				ToSave = false;

			}
			
			if (ToSwitch == true) {
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
				ToSwitch = false;
			}
		}

		// If next landform is a vortex, finish game.
		if(nextlandform(x, y) == "vortex") {
			SideBar.isFinish=true;
		}
		
		map1[x][y] = '2';
		
		mapHandling.drawMap(map1);
		
		System.out.println(door);
		mapHandling.drawMiniMap(door, side_bar);
	}
	
	/**
	 * Auto-generated method stub from KeyListener interface.
	 */
	@Override
	public void keyReleased(KeyEvent e) {
	}
	
	/**
	 * Accessor. Gets count for...
	 * @return
	 */
	public int getCount() {
		return count;
	}
	
	/**
	 * Mutator. Sets count of ... to given value
	 * @param count
	 */
	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (thread == 1) {
			while (true) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				tryToMove();
			}
		}
	}
}
