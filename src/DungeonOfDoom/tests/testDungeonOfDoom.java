package DungeonOfDoom.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import DungeonOfDoom.game.DungeonOfDoom;

public class testDungeonOfDoom {

	DungeonOfDoom dod = new DungeonOfDoom();
	
	@Test
	public void testFrameLayout() {
		
		assertEquals("java.awt.BorderLayout", dod.getLayout().getClass().getName());
		
	}
	
	@Test
	public void testFrameSize() {
		assertEquals(640 + 200, dod.getFrame().getWidth()); // 640 + side bar width
		assertEquals(662, dod.getFrame().getHeight());
	}
	

}
