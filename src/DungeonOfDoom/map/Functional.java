package DungeonOfDoom.map;
import java.util.ArrayList;

public interface Functional {
	public void drawMap(int[][] index);
	public int getGold(ArrayList<int[][]> mapList);
	public void drawMiniMap(int room,SideBar side_bar);
}
