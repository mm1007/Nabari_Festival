package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;

import Game.Canvas.PaintListener;
import Main.Array;

public class Ranking implements PaintListener
{
	Canvas Ranking;

	Font RankingFont = new Font("ＭＳ ゴシック", Font.PLAIN, 50);

	private static final String URL = "jdbc:mysql://localhost:7782/gamedb";
	private static final String USER = "root";
	private static final String PASSWORD = "mm1007";
	private static final String GETDATA = "select * from score;";
	private static final String INSERTDATA = "insert into gamedb.score(name,score) value(?,?)";

	Connection connection;

	private Array<RankingData> RankingData;

	public Ranking(Canvas Ranking) throws SQLException, ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection(URL,
			USER,
			PASSWORD);
		this.Ranking = Ranking;
	}

	public void create()
	{
		try {
			RankingData = getData();
			Ranking.setVisible(true);
			Ranking.addPaintListener(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void destroy()
	{
		Ranking.removePaintListener(this);
	}

	public void insertData(String UserName, int Score) throws SQLException
	{
		PreparedStatement ps = connection.prepareStatement(INSERTDATA);
		ps.setString(1,
			UserName);
		ps.setInt(2,
			Score);
		ps.executeUpdate();
	}

	public Array<RankingData> getData() throws SQLException
	{
		Array<RankingData> Data = new Array<>();
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery(GETDATA);
		while (result.next()) {
			String name = result.getString("name");
			int score = result.getInt("score");
			Data.add(new RankingData(name, score));
			Collections.sort(Data.List,
				(o1, o2) -> o2.score - o1.score);
		}
		return Data;
	}

	public class RankingData
	{
		String name;
		int score;

		public RankingData(String name, int score)
		{
			this.name = name;
			this.score = score;
		}
	}

	@Override
	public void Painted(Graphics2D g)
	{
		// TODO 自動生成されたメソッド・スタブ
		g.setColor(Color.green);
		g.setFont(RankingFont);
		var time = 0;
		for (RankingData data : RankingData.List) {
			g.drawString((time + 1) + ":" + data.name,
				100,
				time * 50 + 100);
			g.drawString(data.score + " 点",
				500,
				time * 50 + 100);

			time++;
		}
	}
}
