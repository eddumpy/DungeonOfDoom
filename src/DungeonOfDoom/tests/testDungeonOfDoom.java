package DungeonOfDoom.tests;

import static org.junit.Assert.*;

import java.awt.BorderLayout;

import javax.swing.JFrame;

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
		assertEquals(640, dod.getSize());
	}
	
	@Test
	public void testSideBarPosition() {
		
		fail("Not yet implemented.");
		
	}

}
