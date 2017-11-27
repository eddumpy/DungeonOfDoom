import java.util.Random;

public class Bot extends Thread {
	int bx, by;
	Random rad = new Random();
	int nextdir;

	public Bot(int bx, int by) {
		// TODO Auto-generated constructor stub
		this.bx = bx;
		this.by = by;
	}

	public void moveBot() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
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
					// TODO: handle exception
				}

			}
		}).start();
	}

}
