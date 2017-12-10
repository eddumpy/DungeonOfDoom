package DungeonOfDoom.score;

import java.io.File;
import java.sql.*;
import java.util.HashMap;

public class DBManager {
	private static String dbaddr;
	private static Connection conn;
	private static Statement stat;

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
			System.out.println("database£ºOK");
		}
	}

	public static void insertData(String name, String score) {

		try {
			/*
			 * Class.forName("org.sqlite.JDBC"); conn =
			 * DriverManager.getConnection("jdbc:sqlite:./" + dbaddr); stat =
			 * conn.createStatement();
			 */
			String insert = "INSERT OR REPLACE INTO SCORES (ID,PLAYER,SCORE) VALUES (NULL,'" + name + "','" + score + "');";
			System.out.println(name+":"+score);
			stat.executeUpdate(insert);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String[][] localData() {
		String[][] toreturn = null;
		try {
			ResultSet rscount = stat.executeQuery("SELECT COUNT (*) FROM SCORES");
			rscount.next();
			int rowcount = rscount.getInt(1);
			/*if(rowcount>10) {
				stat.executeQuery("DELETE SELECT MAX (SCORE) FROM SCORES;");
			}*/
			System.out.println("row count:"+rowcount);
			toreturn = new String[rowcount][2];
			String read = "SELECT * FROM SCORES ORDER BY SCORE ASC;";
			stat.executeQuery(read);
			ResultSet rs = stat.executeQuery(read);
			int i = 0;
			while (rs.next()) {
				for (int j = 0; j < 2; j++) {
					toreturn[i][j] = rs.getString(j+2);
					System.out.println(toreturn[i][j]);
				}
				i++;
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return toreturn;
	}
/*	public static void remainList() {
		try {
			ResultSet rscount = stat.executeQuery("");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	public static void createlocaldatabase(String addr) throws SQLException {
		// stat.execute("create table users(name char(30) primary key, password char(20)
		// not null)");
		stat.execute(
				"CREATE TABLE SCORES (ID INT PRIMARY KEY, PLAYER CHAR(20) UNIQUE, SCORE CHAR(20));");
		// stat.executeUpdate("insert into users (name,password) values
		// ('admin','12345')");
	}

}
