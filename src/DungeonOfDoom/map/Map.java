package DungeonOfDoom.map;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Class for instantiating and dealing with map arrays.
 */
public class Map {

	public char[][] map = new char[20][20];
	public int rows = map.length;
	public int cols = map[0].length; // Since square map, just need length of one row to get number of columns.
	public int x_pos;
	public int y_pos;	
	public int bx_pos;
	public int by_pos;
	public ArrayList<char[][]> mapList=new ArrayList<>();

	
	/**
	 * Default constructor
	 */
	public Map() {
		
	}
	
	/** 
	 * Constructor. Sets map array and finds and stories player's position in the map.
	 * @param newmap
	 */
	public Map(char[][] newmap) {
		this.map=newmap;
		for(int i=0;i<20;i++) {
			for(int j=0;j<20;j++) {
				if(map[i][j]=='2') {
					x_pos = i;
					y_pos = j;
				}
				if (map[i][j] == 'b') {
					bx_pos = i;
					by_pos = j;
				}
			}
		}
	}

	
	/**
	 * Constructor. Reads map from file and stores map in 2D array.
	 * @param num
	 * 			Indicates which map to read (0-4)
	 */
	public Map(int num) {
		
		URL filepath = this.getClass().getResource("/"+num+".txt");
		BufferedReader br = null; //read chars into buffer   

		try {    
			br = new BufferedReader(new InputStreamReader(filepath.openStream()));    
			for (int i = 0; i < 20; i++){      
				String line = br.readLine();//read lines based on lines
				for (int j = 0; j <20; j++) {
					map[i][j] =  line.charAt(j); // cut each character    
					if (map[i][j] == '2') {
						x_pos = i;
						y_pos = j;
					}
					if (map[i][j] == 'b') {
						bx_pos = i;
						by_pos = j;
					}
				}
			} 
		}     
		catch (FileNotFoundException e){  
			e.printStackTrace();
		}     
		catch (IOException e){        
			e.printStackTrace();
		}    
		catch(NullPointerException e){     
			e.printStackTrace();
		}    
		finally {    
			if (br == null){     
				try{      
					br.close();     
					}      
				catch (IOException e){      
					e.printStackTrace();     
					}     br = null;    
					}     
		}    


	}
	
	public ArrayList<char[][]> getMapList() {
		return mapList;
	}
	
	public ArrayList<char[][]> setMapList() {
		for(int i=0;i<5;i++)
			mapList.add(new Map(i).getMap());
		return mapList;
	}
	
	public int getRows() {
		return rows;
	}
	
	public void setRows(int rows) {
		this.rows = rows;
	}
	
	public int getCols() {
		return cols;
	}
	
	public void setCols(int cols) {
		this.cols = cols;
	}
	
	public int getGold(char[][] map) {
		int total_gold = 0;
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (map[i][j] == '3') {
					total_gold++;
				}
			}
		}
		return total_gold;
	}

	public char[][] getMap() {
		return map;
	}

	public void setMap(char[][] map) {
		this.map = map;
	}
	
	public int getX() {
		return x_pos;
	}
	
	public int getY() {
		return y_pos;
	}
	
	public void setX(int x) {
		this.x_pos=x;
	}
	
	public void setY(int y) {
		this.y_pos=y;
	}

	public int getBx_pos() {
		return bx_pos;
	}
	public int getBy_pos() {
		return by_pos;
	}

}
