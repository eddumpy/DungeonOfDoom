package DungeonOfDoom.database;

import java.io.File;
import java.sql.*;

/**
 * Class for handling the SQLite database. Database used for storing name and score.
 */
public class DBManager {
	private static String dbaddr;
	private static Connection conn;
	private static Statement stat;
	
	/**
	 * Constructor. Creates database.
	 * @param addr
	 * 			Address of database file (score.db) in game's directory.
	 */
	public DBManager(String addr) {
		DBManager.dbaddr = addr;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:./" + dbaddr);
			stat = conn.createStatement();

		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		File file = new File(addr);

		if (file.length() == 0) {
			System.out.println("No local database at " + addr + "creating..");
			try {
				createlocaldatabase(addr);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("finish");
		} else {
			System.out.println("database OK");
		}
	}
	
	/**
	 * Method for inserting data into database.
	 * @param name
	 * 			Player's name
	 * @param score
	 * 			Player's score (time)
	 */
	public static void insertData(String name, String score) {

		try {
			String insert = "INSERT OR REPLACE INTO SCORES (ID,PLAYER,SCORE) VALUES (NULL,'" + name + "','" + score + "');";
			System.out.println(name+":"+score);
			stat.executeUpdate(insert);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Method for reading data from database.
	 * @return 2D string array containing names and scores.
	 */
	public static String[][] localData() {
		String[][] toreturn = null;
		
		try {
			ResultSet rscount = stat.executeQuery("SELECT COUNT (*) FROM SCORES");
			rscount.next();
			int rowcount = rscount.getInt(1);
			System.out.println("row count:" + rowcount);
			toreturn = new String[rowcount][2];
			String read = "SELECT * FROM SCORES ORDER BY SCORE ASC;";
			stat.executeQuery(read);
			ResultSet rs = stat.executeQuery(read);
			int i = 0;
			while (rs.next()) {
				for (int j = 0; j < 2; j++) {
					toreturn[i][j] = rs.getString(j+2);
				}
				i++;
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return toreturn;
		
	}
	
	/**
	 * Method for creating local database.
	 * @param addr
	 * 			File address where database will be created
	 * @throws SQLException
	 */
	public static void createlocaldatabase(String addr) throws SQLException {
		
		stat.execute("CREATE TABLE SCORES (ID INT PRIMARY KEY, PLAYER CHAR(20) UNIQUE, SCORE CHAR(20));");
		
	}

}
