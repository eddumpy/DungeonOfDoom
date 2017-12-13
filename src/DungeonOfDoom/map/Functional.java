package DungeonOfDoom.map;
import java.util.ArrayList;

import DungeonOfDoom.game.SideBar;

public interface Functional {
	public void drawMap(char[][] index);
	public int getGold(ArrayList<char[][]> mapList);
	public void drawMiniMap(int room,SideBar side_bar);
}
