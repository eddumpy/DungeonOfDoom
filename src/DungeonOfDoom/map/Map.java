package DungeonOfDoom.map;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Map {

	public char[][] map = new char[20][20];
	public int rows = map.length;
	public int cols = map[0].length; // Since square map, just need length of
										// one row to get number of columns.
	public int x_pos;
	public int y_pos;	
	public int bx_pos;
	public int by_pos;
	public ArrayList<char[][]> mapList=new ArrayList<>();
	public Map() {
		
	}
	public Map(char[][] newmap) {
		this.map=newmap;
		for(int i=0;i<20;i++) {
			for(int j=0;j<20;j++) {
				if(map[i][j]==2) {
					x_pos = i;
					y_pos = j;
				}
				
			}
		}
	}
	public Map(int num) {
		
		String filepath="mapc/map"+num+".txt";   
		File file = new File(filepath);   
		FileReader fr = null;//use FileReader流来读取一个文件中的数据   
		BufferedReader br = null;//字符读到缓存里      
		try {    
			fr = new FileReader(file);    
			br = new BufferedReader(fr);    
			for (int i = 0; i < 20; i++){      
				String line = br.readLine();//以行为单位，一次读一行利用BufferedReader的readLine，读取分行文本   
				//System.out.println(line);
				byte[] bytes=new byte[20];
				bytes = line.getBytes();//将字符串转换为字节数组     
				for (int j = 0; j < bytes.length; j++) {
					map[i][j] = (char) (bytes[j] - 48);// 根据ASCall码表要减掉30H（十进制的48）         
					if (map[i][j] == 2) {
						x_pos = i;
						y_pos = j;
					}
					if (map[i][j] == 9) {
						bx_pos = i;
						by_pos = j;
					}
				}
			}   
			//mapList.add(map);
		}     
		catch (FileNotFoundException e){    
			e.printStackTrace();//深层次的输出异常调用的流程  
		}     
		catch (IOException e){        
			e.printStackTrace();//深层次的输出异常调用的流程   
		}    
		catch(NullPointerException e){     
			e.printStackTrace();//深层次的输出异常调用的流程  
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
			if (fr == null){       
				try { 
					fr.close();      
				} 
				catch (IOException e){      
					e.printStackTrace();     
				}      
				fr = null;     
			}    
		}    
		
		/*int[][] map1 = {
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 1 },
				{ 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1 },
				{ 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1 },
				{ 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1 },
				{ 1, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1 },
				{ 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1 },
				{ 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1 },
				{ 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 1 },
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }

		};

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				map[i][j] = map1[i][j];
				if (map[i][j] == 2) {
					x_pos = i;
					y_pos = j;
				}
			}
		}*/

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
	public int getGold(int[][] map) {
		int total_gold = 0;
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (map[i][j] == 3) {
					total_gold++;
				}
			}
		}
		return total_gold;
	}
	public synchronized char[][] getMap() {
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
}
