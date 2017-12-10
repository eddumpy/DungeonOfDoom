/**
 * 
 */
package DungeonOfDoom.tests;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Robot;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;

import org.junit.Test;

import DungeonOfDoom.game.DungeonOfDoom;
import DungeonOfDoom.game.Game;

/**
 * @author edjeffery
 *
 */
public class GameTest {

	Game game = new Game();
	

	/**
	 * Test method for {@link DungeonOfDoom.game.Game#getFrame()}.
	 */
	@Test
	public void testGetFrame() {
		
		assertNotNull(game.getFrame());		
		
	}
	
	/**
	 * Test method for window dimensions.
	 */
	@Test
	public void testWindowDimensions() {
		int height = game.getFrame().getHeight();
		int width = game.getFrame().getWidth();
		
		
		assertEquals(640, width);
		assertEquals(480+22, height); //+22 for the Mac window border
		
	}

	/**
	 * Test method for {@link DungeonOfDoom.game.Game#actionPerformed(java.awt.event.ActionEvent)}.
	 */
	@Test
	public void testActionPerformed() {
		
		JButton start = game.getStart();
		game.name.setText("Test");
		start.doClick();
		assertNotNull(DungeonOfDoom.getFrames());
		
	}

	/**
	 * Test method for {@link DungeonOfDoom.game.Game#getNameText()}.
	 */
	@Test
	public void testGetNameText() {
		game.name.setText("Test");
		assertEquals("Test", game.getNameText());
	}

}
