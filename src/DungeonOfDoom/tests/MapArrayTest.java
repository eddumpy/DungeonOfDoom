package DungeonOfDoom.tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

import DungeonOfDoom.map.Map;

public class MapArrayTest {

	Map map = new Map();
	
	@Test
	public void testMapInt() {
		
		for (int i = 0; i < 5; i++) {
			Map mapi = new Map(i);
			assertNotNull(mapi);
		}
		
	}
	
	@Test
	public void testCharacterPosition() {
		Map map0 = new Map(0);
		int x = map0.getX();
		int y = map0.getY();
		
		assertEquals(9, x);
		assertEquals(10, y);
	}
	
	@Test
	public void testCharacterPresent() {
						
		for (int i = 0; i < 5; i++) {
			Map mapi = new Map(i);
		}
		
		int x = map.getX();
		int y = map.getY();
		
		assertNotNull(x);
		assertNotNull(y);
		
	}

	@Test
	public void testGetRows() {
		//Test default constructor
		assertEquals(20, map.getRows());
		
		//Test other maps
		for (int i = 0; i < 5; i++) {
			Map mapi = new Map(i);
			assertEquals(20, mapi.getRows());
		}
	}

	@Test
	public void testGetCols() {
		//Test default constructor
		assertEquals(20, map.getCols());
		
		//Test other maps
		for (int i = 0; i < 5; i++) {
			Map mapi = new Map(i);
			assertEquals(20, mapi.getCols());
		}
	}

	/**
	 * Want to test whether all maps have gold.
	 */
	@Test
	public void testGetGold() {
		
		for (int i = 0; i < 5; i++) {
			Map mapi = new Map(i);
			assertTrue(map.getGold(mapi.getMap()) > 0);
		}
		
	}

	@Test
	public void testGetX() {
		Map map0 = new Map(0);
		assertEquals(9, map0.getX());
	}

	@Test
	public void testGetY() {
		Map map0 = new Map(0);
		assertEquals(10, map0.getY());
	}


}
