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
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


/**
 * Class for setting up a game
 */
public class Game extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JButton start, exit;
	private JPanel panel;
	private JPanel panel2;
	public static JTextField name;
	private String title = "Dungeon of Doom";
	private JLabel background;
	private JTextArea text;
	public static String nameText;
	public File music;
	public static Clip clip;
	private ImageIcon img;
	public static DungeonOfDoom Dod;
	private boolean hasSet=false;
	
	/**
	 * Constructor. Sets up window.
	 */
	public Game() {
		// Initialisation
		frame = new JFrame(title);
		start = new JButton("Start");
		exit = new JButton("Exit");
		panel = new JPanel();
		panel2 = new JPanel();
		name = new JTextField("Please enter your name...");
		URL filepath = this.getClass().getResource("/Mystical_Music.wav");
		AudioInputStream music = null;
		try {
			music = AudioSystem.getAudioInputStream(filepath);
		} catch (UnsupportedAudioFileException e2) {
			e2.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		img = new ImageIcon(this.getClass().getResource("/old_paper_background.png"));
		background = new JLabel(img);
		text = new JTextArea(
				"You find yourself in a deep, dark and dingy dungeon. A goblin thief has "
				+ "stolen all your gold, but has dropped it throughout the different rooms. "
				+ "You must collect all gold in the dungeon to be able to escape. "
				+ "Good luck and watch out for the monsters lurking in the dark...");
		
		// Adding focus listener to JTextField
		name.addFocusListener(new FocusListener() {
	
			@Override
			public void focusLost(FocusEvent e) {
				if(hasSet==false) {
					name.setText("Please enter your name...");				
				}
				else {
					nameText=name.getText();
				}
			}	
			
			@Override
			public void focusGained(FocusEvent e) {				
				name.setText("");
				hasSet=true;
			}
		});
		
		// Setting layouts
		background.setLayout(new BorderLayout());
		frame.setLayout(new BorderLayout());
		panel.setLayout(new FlowLayout());
		
		// Setting text formatting
		text.setFont(new Font("Serif", Font.BOLD, 16));
		text.setLineWrap(true);
		text.setWrapStyleWord(true);
		text.setColumns(40);
		text.setForeground(Color.BLACK);
		text.setAlignmentX(CENTER_ALIGNMENT);
		text.setAlignmentY(CENTER_ALIGNMENT);
		text.setOpaque(false);
		
		// Adding components to JFrame
		frame.add(background);
		background.add(panel, BorderLayout.SOUTH);
		panel.add(start);
		panel.add(exit);
		panel.setOpaque(false);
		background.add(panel2, BorderLayout.CENTER);
		panel2.add(Box.createRigidArea(new Dimension(0,350)));
		panel2.add(text, BorderLayout.CENTER);
		panel2.add(name, BorderLayout.SOUTH);
		panel2.setOpaque(false);
		
		// Adding and setting action listeners
		start.addActionListener(this);
		start.setActionCommand("Start");
		exit.addActionListener(this); 
		exit.setActionCommand("Exit");
		
		// Set frame characteristics
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640, 480);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		// Play music
		PlaySound(music);
	}
	
	/**
	 * Main method. Instantiates a game object.
	 * @param args
	 */
	public static void main(String[] args) {
		Game game = new Game();
	}
	
	/**
	 * Accessor for the JFrame.
	 * @return JFrame
	 */
	public JFrame getFrame() {
		return frame;
	}
	
	/**
	 * Method for playing sound
	 * @param Sound (an inputstream of audio in .wav format)
	 */
	public static void PlaySound(AudioInputStream Sound) {
        while(true) {
		try {
            clip = AudioSystem.getClip();
            clip.open(Sound);
            clip.start();
        } catch(Exception e){}
		try {
			Thread.sleep(103000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        }

    }
	
	/**
	 * Method for handling button click actions
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String cmd = e.getActionCommand();
		
		if(cmd.equals("Start")) {
			if (hasSet==false||name.getText().length() == 0) {
				JOptionPane.showMessageDialog(frame, "Please enter your name in the text box provided.");
			}
			else if(name.getText().length() > 10) {
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
	
	/**
	 * Accessor. Gets the player's inputted name.
	 * @return String containing player's name
	 */
	public static String getNameText() {
		return nameText;
	}
	
	public JButton getStart() {
		return start;
	}

}
