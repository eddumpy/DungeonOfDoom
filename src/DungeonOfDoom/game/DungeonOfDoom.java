package DungeonOfDoom.game;
import java.awt.BorderLayout;

import java.util.ArrayList;
import javax.swing.JFrame;
import DungeonOfDoom.listener.MoveListener;
import DungeonOfDoom.map.Map;
import DungeonOfDoom.map.MapHandling;
import DungeonOfDoom.map.SideBar;

/**
 * Class for setting up the Dungeon of Doom game
 */
public class DungeonOfDoom extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private SideBar side_bar;
	int room_num = 0;
	private String title = "Dungeon of Doom";
	private ArrayList<char[][]> mapList = new ArrayList<char[][]>();
	public static int index = 0;
	static Map map_init;
	MapHandling mapHandling;
	MoveListener moveListener;
	char[][] map1;
	int rows;
	int cols;
	int x, y, bx, by;
	public Thread t1;
	public Thread t2;

	/**
	 * Method to initialise the maps
	 * @param index
	 * 				Reference number of map (value between 0-4)
	 */
	public void Init(int index) {
		map_init = new Map(index);
		map1 = map_init.getMap();
		rows = map1.length;
		cols = map1[0].length;
		x = map_init.x_pos;
		y = map_init.y_pos;
		bx = map_init.bx_pos;
		by = map_init.by_pos;
	}
	
	/**
	 * Constructor
	 */
	public DungeonOfDoom() {

		try {
			Map map;
			for(int i=0;i<5;i++) {
				map=new Map(i);
				char[][] maps=map.getMap();
				mapList.add(maps);
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		frame = new JFrame(title); 

		
		mapHandling = new MapHandling(frame);

		
		frame.getContentPane(); 
		frame.setLayout(new BorderLayout());

		System.out.println(mapList.size());
		int tg=mapHandling.getGold(mapList);
		System.out.println(tg);
		side_bar=new SideBar(frame,tg);

		frame.add(side_bar, BorderLayout.WEST);
		frame.setSize(640, 1100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.pack();
		
		mapHandling = new MapHandling(frame);
		Init(index);

		MoveListener moveListener1=new MoveListener(map1, x, y, bx, by,mapHandling, side_bar, frame,1);		
		MoveListener moveListener2=new MoveListener(map1, x, y, bx, by, mapHandling, side_bar, frame,2);
		
		frame.addKeyListener(moveListener1);
		t1=new Thread(moveListener1);
		t2=new Thread(moveListener2);
		t1.start();
		t2.start();
		if(SideBar.isFinish==true) {
			t1.interrupt();;
			t2.interrupt();;
		}
		mapHandling.drawMap(map1);
		mapHandling.drawMiniMap(room_num,side_bar);
		frame.setFocusable(true);
	}
		
}

