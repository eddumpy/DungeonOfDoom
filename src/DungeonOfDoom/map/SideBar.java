package DungeonOfDoom.map;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;

import DungeonOfDoom.game.Game;
import DungeonOfDoom.listener.MoveListener;
import DungeonOfDoom.score.DBManager;
import DungeonOfDoom.score.Score;
import sun.swing.table.DefaultTableCellHeaderRenderer;

/** 
 * Class that sets up the side bar/menu with all necessary information objects
 */
public class SideBar extends JPanel implements ActionListener {
	
	public static int gold_counter = 0;
	public static int total_gold;
	public static boolean isFinish = false;
	File file;
	JPanel side_bar;
	int time_penalty = 0;
	int secs;
	int mins;
	String addr="score.db";
	JLabel timer = new JLabel("Timer", JLabel.CENTER);
	JLabel playerName = new JLabel("Player: " + Game.getNameText(), JLabel.CENTER);
	public static JLabel gold_count = new JLabel("Gold Count", JLabel.CENTER);
	JButton exit = new JButton("Exit");
	Timer time = null;
	private Object[][] data;
	private String[] columnNames= {"Player","Score"};
	
	public SideBar(JFrame frame) {

		total_gold = getTotalGold();
		this.setLayout(new FlowLayout());
		this.setPreferredSize(new Dimension(200, getHeight()));
		this.setOpaque(true);
		this.setBackground(Color.GRAY);

		// Addition of Dungeon of Doom graphic
		this.add(new JLabel(new ImageIcon("images/dod.png"), JLabel.CENTER));

		// Addition of timer
		timer.setFont(new Font("Monospaced", Font.BOLD, 20));
		timer.setForeground(Color.WHITE);
		timer.setBackground(Color.BLACK);
		timer.setOpaque(true);
		timer.setPreferredSize(new Dimension(150, 50));
		timer.setBorder(BorderFactory.createLineBorder(Color.black, 5));
		this.add(Box.createRigidArea(new Dimension(100, 10)));
		this.add(timer);
		long startTime = System.currentTimeMillis();
		ActionListener actionListener;
		actionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Long countTime = (System.currentTimeMillis() - startTime) / 1000;
				int secs = (int) ((countTime % 60) + MoveListener.time_penalty);
				if (secs < 0) {
					secs = 0;
				}
				int mins = (int) (System.currentTimeMillis() - startTime) / 60000;
				String minutes=String.format("%02d", mins);
				String seconds=String.format("%02d", secs);
				timer.setText("Time: " +minutes  + ":" + seconds);
				
				
				if (SideBar.isFinish == true) {
					DBManager dbManger = new DBManager("score.db");
					String score = minutes + ":" + seconds;
					DBManager.insertData( Game.getNameText(), score);
					String[] options = {"OK"};
					JPanel panel = new JPanel();
					String[][] content=DBManager.localData();
					data=content;
					JTable table = new JTable(data,columnNames);
					DefaultTableCellHeaderRenderer renderer= new DefaultTableCellHeaderRenderer();
					renderer.setHorizontalAlignment(JLabel.CENTER);
					renderer.setBackground(Color.white);
					table.setDefaultRenderer(Object.class, renderer);
					table.setEnabled(false);
					JScrollPane jsPane = new JScrollPane();
					jsPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
					jsPane.setViewportView(table);
					
					panel.add(jsPane);
					
					int response=JOptionPane.showOptionDialog(frame, panel, "Score", JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
					if(response==0) {
						System.exit(0);
					}
					time.stop();

				}
			}
		};
		
		time = new Timer(100, actionListener);
		time.start();

		// Addition of player's name
		this.add(Box.createRigidArea(new Dimension(100, 15)));
		this.add(playerName);
		playerName.setPreferredSize(new Dimension(100, 15));
		playerName.setFont(new Font("Monospaced", Font.PLAIN, 18));

		// Addition of gold counter
		gold_count.setFont(new Font("Monospaced", Font.PLAIN, 18));
		this.add(Box.createRigidArea(new Dimension(100, 15)));
		this.add(gold_count);
		gold_count.setText("Gold count = " + gold_counter + "/" + total_gold);

		this.add(Box.createRigidArea(new Dimension(100, 100)));
		this.add(Box.createRigidArea(new Dimension(100, 100)));

		// Addition of exit button

		exit.setOpaque(true);
		exit.setBackground(Color.GRAY);
		exit.setFont(new Font("Monospaced", Font.PLAIN, 16));
		exit.setPreferredSize(new Dimension(150, 30));
		this.add(exit);
		exit.addActionListener(this);
		exit.setActionCommand("Exit");

	}

	public int getTotalGold() {
		return MapHandling.total_gold;
	}

	public void setGoldCount(JLabel gold_count) {
		this.gold_count = gold_count;
	}

	public JLabel getGoldCount() {
		return this.gold_count;
	}

	public SideBar getSideBar() {
		return this;
	}

	public void setSideBar(SideBar sideBar) {
		this.side_bar = sideBar;
	}

	/**
	 * Method for handling exit button press.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		System.exit(0);
		
	}
	
}
