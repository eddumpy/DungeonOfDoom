package DungeonOfDoom.map;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SideBar extends JPanel{
	public static int gold_counter = 0;
	public static int total_gold;
	JPanel side_bar;
	int time_penalty = 0;
	JLabel timer = new JLabel("Timer", JLabel.CENTER);
	public static JLabel gold_count = new JLabel("Gold Count", JLabel.CENTER);
	public int getTotalGold() {
		return MapHandling.total_gold;		
	}
	/*public void setTotalGold(int total_gold) {
		this.total_gold=total_gold;
	}*/
	public SideBar() {
		ArrayList<int[][]> map;
		// TODO Auto-generated constructor stub
		total_gold=getTotalGold();
		this.setLayout(new FlowLayout()); 
		this.setPreferredSize(new Dimension(200, getHeight()));
		this.setOpaque(true); 
		this.setBackground(Color.GRAY);


		// Addition of Dungeon of Doom graphic
		this.add(new JLabel(new ImageIcon("images1/dod.png"), JLabel.CENTER));

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
		new Timer(100, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Long countTime = (System.currentTimeMillis() - startTime) / 1000;
				int secs = (int) ((countTime % 60) + time_penalty);
				if (secs < 0) {
					secs = 0;
				}
				int mins = (int) (System.currentTimeMillis() - startTime) / 60000;

				timer.setText("Time: " + String.format("%02d", mins) + ":" + String.format("%02d", secs));
			}
		}).start();

		// Addition of gold counter
		gold_count.setFont(new Font("Monospaced", Font.PLAIN, 18));
		this.add(Box.createRigidArea(new Dimension(100, 25)));
		this.add(gold_count);
		gold_count.setText("Gold count = " + gold_counter + "/" + total_gold);
		this.add(Box.createRigidArea(new Dimension(100, 25)));

		// Addition of mini-map

		this.add(Box.createRigidArea(new Dimension(100, 15)));

		// Addition of exit button
		JButton exit = new JButton("Exit");
		exit.setOpaque(true);
		exit.setBackground(Color.GRAY);
		exit.setFont(new Font("Monospaced", Font.PLAIN, 16));
		exit.setPreferredSize(new Dimension(150, 30));
		this.add(exit);
		//exit.addActionListener(this);
		exit.setActionCommand("Exit");
	}
	public void setGoldCount(JLabel gold_count) {
		this.gold_count=gold_count;
	}
	public JLabel getGoldCount() {
		return this.gold_count;
	}
	public SideBar getSideBar() {
		return this;		
	}
	public void setSideBar(SideBar sideBar) {
		this.side_bar=sideBar;
	}
}
