package DungeonOfDoom.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.Box;
import javax.swing.ImageIcon;
// Why use Swing over AWT: https://stackoverflow.com/questions/408820/what-is-the-difference-between-swing-and-awt
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Game extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JButton start, exit;
	private JPanel panel;
	private JPanel panel2;
	private JTextField name;
	private String title = "Dungeon of Doom";
	public static String nameText;
	
	public Game() {
		
		frame = new JFrame(title);
		start = new JButton("Start");
		exit = new JButton("Exit");
		panel = new JPanel();
		panel2 = new JPanel();
		name = new JTextField("Please enter your name...");
		
		File Music = new File("music/music.wav");
		
		frame.setLayout(new FlowLayout());
		
		ImageIcon img = new ImageIcon("images1/DungeonBackground2.png");
		JLabel background = new JLabel(img);
		background.setLayout(new BorderLayout());
		frame.setLayout(new BorderLayout());
		frame.add(background);
		//panel.setPreferredSize(new Dimension(640, 480));
		panel.setLayout(new FlowLayout());
		panel.add(start);
		panel.add(exit);
		panel.setOpaque(false);
		background.add(panel, BorderLayout.SOUTH);
		start.addActionListener(this);
		start.setActionCommand("Start");
		exit.addActionListener(this); 
		exit.setActionCommand("Exit");
		
		JTextArea text = new JTextArea(
				"You find yourself in a deep, dark and dingy dungeon. A goblin thief has "
				+ "stolen all your gold, but has dropped in throughout the different rooms. "
				+ "You must collect all gold in the dungeon to be able to escape. "
				+ "Good luck and watch out for the monsters lurking in the dark...");
		text.setFont(new Font("Serif", Font.BOLD, 16));
		text.setLineWrap(true);
		text.setWrapStyleWord(true);
		text.setColumns(40);
		text.setForeground(Color.WHITE);
		text.setAlignmentX(CENTER_ALIGNMENT);
		text.setAlignmentY(CENTER_ALIGNMENT);
		text.setOpaque(false);
		panel2.add(Box.createRigidArea(new Dimension(0,350)));
		panel2.add(text, BorderLayout.CENTER);
		panel2.setOpaque(false);
		background.add(panel2, BorderLayout.CENTER);
		
		panel2.add(name, BorderLayout.SOUTH);
		
		frame.pack(); // Packs content within frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640, 480);
		
		//frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		PlaySound(Music);
		
	}
	
	
	
	public static void main(String[] args) {
		Game game = new Game();

	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public static void PlaySound(File Sound) {
        
		try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(Sound));
            clip.start();
            
        } catch(Exception e){}
        
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String cmd = e.getActionCommand();
		
		if(cmd.equals("Start")) {
            frame.dispose();
            new DungeonOfDoom();
        }
		if(cmd.equals("Exit")) {
			System.exit(0);
        }
		
		     
	}

}
