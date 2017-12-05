package DungeonOfDoom.score;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Score {
	
	Charset utf8 = StandardCharsets.UTF_8;
	List<String> lines; // = Arrays.asList("Player name", "00:01");
	Path file = Paths.get("scores.txt");
	Scanner input;
	String[] data;
	DefaultTableModel model = new DefaultTableModel(0, 0);
    static JTable table = new JTable();
	

	public Score() {
		this.readScoreFromFile();
	}
	
	public void writeScoreToFile(String playerName, int gold_counter, int mins, int secs) {
		lines = Arrays.asList(playerName + ", " + gold_counter + ", " + mins + ", " + secs);
		
		try {
			Files.write(file, lines, utf8, StandardOpenOption.APPEND);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void readScoreFromFile() {
		String[] columns= {"Name", "Gold", "Minutes", "Seconds"};
		int i = 0;
	    
	    model.setColumnIdentifiers(columns);
        
		
		try {
			input = new Scanner(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

	    while (input.hasNextLine()) {
	    		data = input.nextLine().split(",");
	    		System.out.println("Line = " + Arrays.toString(data));
	    		model.insertRow(i, data);
	    		i++;	    		
	    }
	    table.setModel(model); 
	    System.out.println(table);
		
	}

	public JTable getTable() {
		return table;
	}
	
}
