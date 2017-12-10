package DungeonOfDoom.tests;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import org.junit.Test;

import DungeonOfDoom.listener.MoveListener;
import DungeonOfDoom.map.Map;
import DungeonOfDoom.map.MapHandling;
import DungeonOfDoom.map.SideBar;

/**
 * @author edjeffery
 *
 */
public class MovementTest {
	
	private MapHandling mapHandling;
	private char[][] map = {{'0','1','2','3','4','5','6','7','8'}};
	private SideBar side_bar;
	private JFrame frame;
	int x = 10;
	int y = 10;
	int bx;
	int by;
	int thread;
	
	MoveListener movelistener = new MoveListener(map, x, y, bx, by, mapHandling, side_bar, frame, thread);
	

	/**
	 * Test method for {@link DungeonOfDoom.listener.MoveListener#nextlandform(int, int)}.
	 */
	@Test
	public void testNextLandForm() {
		String[] landforms = {"land", "wall", "character", "gold", "door", "door", "door", "door", "blue_potion"};
		
		for (int i = 0; i < 9; i++) {
			assertEquals(landforms[i] , movelistener.nextLandForm(0, i));
		}
			
	}
	
	@Test
	public void testMovement() {
		movelistener.movePlayer("Up");
		assertEquals(9, movelistener.getX());
		movelistener.movePlayer("Down");
		assertEquals(10, movelistener.getX());
		movelistener.movePlayer("Left");
		assertEquals(9, movelistener.getY());
		movelistener.movePlayer("Right");
		assertEquals(10, movelistener.getY());
	}

//	/**
//	 * Test method for {@link DungeonOfDoom.listener.MoveListener#keyPressed(java.awt.event.KeyEvent)}.
//	 */
//	@Test
//	public void testKeyPressed() {
//		//movelistener.keyPressed(KeyEvent.VK_UP);
//		Robot r;
//		x = 10;
//		try {
//			r = new Robot();
//			r.keyPress(KeyEvent.VK_UP);
//			assertEquals(11, x);
//		} catch (AWTException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	    
//	}

}
