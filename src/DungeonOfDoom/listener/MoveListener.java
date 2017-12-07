package DungeonOfDoom.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import DungeonOfDoom.map.Map;
import DungeonOfDoom.map.MapHandling;
import DungeonOfDoom.map.SideBar;

public class MoveListener implements KeyListener {
	private char[][] map1;
	private int x, y, bx, by;
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

	public MoveListener(char[][] map, int x, int y, int bx, int by, MapHandling mapHandling, SideBar sideBar,
			JFrame frame) {
		// TODO Auto-generated constructor stub
		this.map1 = map;
		this.x = x;
		this.y = y;
		this.bx = bx;
		this.by = by;
		this.mapHandling = mapHandling;
		this.side_bar = sideBar;
		this.frame = frame;
		for (int i = 0; i < 5; i++)
			mapList.add(null);

	}

	public String nextlandform(int x, int y) {
		if (map1[x][y] == '1')
			return "wall";
		else if (map1[x][y] == '2')
			return "player";
		else if (map1[x][y] == '4' || map1[x][y] == '5' || map1[x][y] == '6' || map1[x][y] == '7')
			return "door";
		else if (map1[x][y] == '8')
			return "blue_potion";
		else if (map1[x][y] == '3')
			return "gold";
		else if (map1[x][y] == 'v')
			return "vortex";

		return "land";
	}
	public void tryToMove(int temptbx,int temptby) {
		SecureRandom random = new SecureRandom();
		int rand = random.nextInt(3);
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
		if (nextlandform(bx, by) == "wall" || nextlandform(bx, by) == "gold" || nextlandform(bx, by) == "door"
				|| nextlandform(bx, by) == "blue_potion"||nextlandform(bx, by) == "red_potion"||nextlandform(bx, by) == "vortex") {
			bx = temptbx;
			by = temptby;
			tryToMove(temptbx, temptby);
		}
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		map1[x][y] = '0';
		map1[bx][by] = '0';
		int tempx, tempy, temptbx, temptby;
		
		tempx = x;
		tempy = y;
		temptbx = bx;
		temptby = by;
		
		tryToMove(temptbx, temptby);
		map1[bx][by] = 'b';
		switch (e.getKeyCode()) {

		case (KeyEvent.VK_UP): {

			System.out.printf("x = %d, y = %d\n", x, y);
			// map1[x][y] = 0;
			x -= 1;
			// map1[x][y] = 2;
			System.out.printf("x = %d, y = %d\n", x, y);
			// drawMap(map1);
			break;
		}
		case (KeyEvent.VK_DOWN): {

			System.out.printf("x = %d, y = %d\n", x, y);

			// if (x + 1 == 19) {
			// Do nothing
			// } else {
			// map1[x][y] = 0;
			x += 1;
			// map1[x][y] = 2;
			// }
			System.out.printf("x = %d, y = %d\n", x, y);
			// drawMap(map1);
			break;

		}
		case (KeyEvent.VK_LEFT): {

			System.out.printf("x = %d, y = %d\n", x, y);
			// map1[x][y] = 0;
			y -= 1;
			// map1[x][y] = 2;
			System.out.printf("x = %d, y = %d\n", x, y);
			// drawMap(map1);
			break;
		}
		case (KeyEvent.VK_RIGHT): {

			System.out.printf("x = %d, y = %d\n", x, y);
			// map1[x][y] = 0;
			y += 1;
			// map1[x][y] = 2;
			System.out.printf("x = %d, y = %d\n", x, y);
			// drawMap(map1);
			break;
		}
		}
		if (nextlandform(x, y) == "wall") {
			x = tempx;
			y = tempy;
		}
		if (nextlandform(x, y) == "gold") {
			SideBar.gold_counter++;
			SideBar.gold_count.setText("Gold count = " + SideBar.gold_counter + "/" + SideBar.total_gold);
		}
		if (nextlandform(x, y) == "blue_potion") {
			time_penalty += 10;
		}
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
			// doorindex.add(door);

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
			/*
			 * if (doorindex.getFirst() != door) {
			 * 
			 * ToSwitch = true; doorindex.removeFirst(); }
			 */
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
				bx=newMap.getBx_pos();
				by=newMap.getBy_pos();
				map1 = newMap.getMap();
				ToSwitch = false;
			}
		}
		if (nextlandform(x, y) == "vortex") {
			SideBar.isFinish = true;
		}
		map1[x][y] = '2';

		mapHandling.drawMap(map1);

		// Leaderboard pop-up
		if (SideBar.gold_counter == 1) {
			// JOptionPane.showMessageDialog(frame, table);
		}

		System.out.println(door);
		mapHandling.drawMiniMap(door, side_bar);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		/*
		 * System.out.println("you pressed a key"); counter++; // Step counter
		 * System.out.println("The step counter = " + counter);
		 * System.out.println("The switch statement is next");
		 */

	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
