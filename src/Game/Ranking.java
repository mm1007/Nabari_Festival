package Game;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Ranking
{

	private static final String URL = "jdbc:mysql://localhost:7782/gamedb";
	private static final String USER = "root";
	private static final String PASSWORD = "mm1007";
	private static final String SQL = "select * from score;";

	Connection connection;
	Statement statement;

	Ranking() throws SQLException
	{
		connection = DriverManager.getConnection(URL,
			USER,
			PASSWORD);
	}

	public void insertData(String UserName, int Score)
	{

	}

	public void getData() throws SQLException
	{
		statement = connection.createStatement();

	}
}
