import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

public class TimeManager {
	JLabel timer;
	int time_penalty;
	public TimeManager(JLabel timer, int time_penalty) {
		// TODO Auto-generated constructor stub
		this.timer=timer;
		this.time_penalty=time_penalty;
		long startTime = System.currentTimeMillis();
		new Timer(100, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Long countTime = (System.currentTimeMillis() - startTime) / 1000;
				int secs = (int) ((countTime % 60) + time_penalty);
				if (secs < 0) {
					secs = 0;
				}
				int mins = (int) (System.currentTimeMillis() - startTime) / 60000;

				timer.setText("Time: " + String.format("%02d", mins) + ":" + String.format("%02d", secs));
			}
		}).start();
	}
	
}
