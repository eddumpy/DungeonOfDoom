package DungeonOfDoom.game;
import java.awt.BorderLayout;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import DungeonOfDoom.listener.MoveListener;
import DungeonOfDoom.map.Map;
import DungeonOfDoom.map.MapHandling;
import DungeonOfDoom.map.SideBar;

public class DungeonOfDoom extends JFrame /*implements KeyListener, ActionListener*/ {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	int room_num = 0; // Main room is in the middle of a 3x3 array
	private String title = "Dungeon of Doom";
	private ArrayList<char[][]> mapList = new ArrayList<char[][]>();
	public static int index = 0;
	static Map map_init;
	MapHandling mapHandling;

	char[][] map1;

	int rows;
	int cols;
	int x, y, bx, by;

	private SideBar side_bar;


	public void Init(int index) {
		map_init = new Map(index);
		map1 = map_init.getMap();
		rows = map1.length;
		cols = map1[0].length;
		x = map_init.x_pos;
		y = map_init.y_pos;
		bx = map_init.bx_pos;
		by = map_init.by_pos;
		/*Bot bot = new Bot(bx, by);
		bot.moveBot();*/
	}

	public DungeonOfDoom() {
		// Creation of JFrame and setting layout
//		Map map;
		try {
			Map map;
			for(int i=0;i<5;i++) {
				map=new Map(i);
				char[][] maps=map.getMap();
				mapList.add(maps);
			}
			
			//mapList=map.getMapList();
		} catch (NullPointerException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		frame = new JFrame(title); 
		frame.getContentPane(); 
		frame.setLayout(new BorderLayout());
		
		mapHandling = new MapHandling(frame);
		System.out.println(mapList.size());
		int tg=mapHandling.getGold(mapList);
		System.out.println(tg);
		side_bar=new SideBar(frame,tg);
		frame.add(side_bar, BorderLayout.WEST);
		frame.setSize(640, 1100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		//frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.pack();
		mapHandling = new MapHandling(frame);
		
		Init(index);
		MoveListener moveListener1=new MoveListener(map1, x, y, bx, by,mapHandling, side_bar, frame,1);		
		MoveListener moveListener2=new MoveListener(map1, x, y, bx, by, mapHandling, side_bar, frame,2);
		
		frame.addKeyListener(moveListener1);
		Thread t1=new Thread(moveListener1);
		Thread t2=new Thread(moveListener2);
		t1.start();
		t2.start();
		mapHandling.drawMap(map1);
		mapHandling.drawMiniMap(room_num,side_bar);
		frame.setFocusable(true);
		//frame.addKeyListener(this);
	}
		
}

