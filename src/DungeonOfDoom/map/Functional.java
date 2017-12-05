package DungeonOfDoom.map;
import java.util.ArrayList;

public interface Functional {
	public void drawMap(char[][] index);
	public int getGold(ArrayList<char[][]> mapList);
	public void drawMiniMap(int room,SideBar side_bar);
}
