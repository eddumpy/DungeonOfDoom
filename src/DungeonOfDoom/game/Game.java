package DungeonOfDoom.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.Box;
import javax.swing.ImageIcon;
// Why use Swing over AWT: https://stackoverflow.com/questions/408820/what-is-the-difference-between-swing-and-awt
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	public static DungeonOfDoom Dod;
	private boolean hasSet=false;
	public Game() {
		
		frame = new JFrame(title);
		start = new JButton("Start");
		exit = new JButton("Exit");
		panel = new JPanel();
		panel2 = new JPanel();
		name = new JTextField("Please enter your name...");
		name.addFocusListener(new FocusListener() {
			
			

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
/*				if(nameText.equals("")) {
					//System.out.println(nameText);
					hasSet=false;
				}*/
				
			
				if(hasSet==false) {
					name.setText("Please enter your name...");				
				}
				else {
					nameText=name.getText();
					System.out.println(nameText);
				}
			}	
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub				
				name.setText("");
				System.out.println(nameText);
				//nameText=name.getText();
				hasSet=true;
			}
		});
		File Music = new File("music/Mystical_Music.wav");
		
		
		frame.setLayout(new FlowLayout());
		
		ImageIcon img = new ImageIcon("images/DungeonBackground2.png");
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
        while(true) {
		try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(Sound));
            clip.start();
            
        } catch(Exception e){}
		try {
			Thread.sleep(103000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        }
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String cmd = e.getActionCommand();
		
		if(cmd.equals("Start")) {
			//System.out.println(name.getText());
			if (hasSet==false||name.getText().length()==0) {
				JOptionPane.showMessageDialog(frame, "Please enter your name in the text box provided.");
			}

			else if(name.getText().length()>10) {
				JOptionPane.showMessageDialog(frame, "Name is too long! Please enter your name again.");
				hasSet=false;
			}
			else {
				nameText = name.getText();
				frame.dispose();
				Dod=new DungeonOfDoom();
			}
            
        }
		if(cmd.equals("Exit")) {
			System.exit(0);
        }
		   
	}

	public static String getNameText() {
		return nameText;
	}



}
