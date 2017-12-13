package DungeonOfDoom.bot;
import java.util.Random;

/**
 *  Class for creating bot thread and handling its random movement.
 */
public class Bot extends Thread {
	int bx, by;
	Random rad = new Random();
	int nextdir;

	/**
	 * Constructor for setting bot's position.
	 * @param bx
	 * 			Bot's x-position
	 * @param by
	 * 			Bot's y-position
	 */
	public Bot(int bx, int by) {
		
		this.bx = bx;
		this.by = by;
		
	}
	
	/**
	 * Method for bot movement.
	 */
	public void moveBot() {
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				nextdir = rad.nextInt(4);
				switch (nextdir) {
					case 0:
						bx -= 1;
						break;
					case 1:
						by -= 1;
						break;
					case 2:
						bx += 1;
						break;
					case 3:
						by += 1;
						break;
				}

				try {
					Thread.sleep(1000);
				} 
				catch (Exception e) {
					System.out.println("Thread cannot sleep.");
				}

			}
		}).start();
	}

}
