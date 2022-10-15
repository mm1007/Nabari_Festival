package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;

import Game.Canvas.PaintListener;
import Main.Array;
import Main.Boot;
import Main.Key;
import Window.Frame;

public class Ranking implements PaintListener, KeyListener
{
	Canvas Ranking;
	Frame InFrame;

	Font RankingFont = new Font("ＭＳ ゴシック", Font.PLAIN, 50);

	private static final String URL = "jdbc:mysql://172.23.101.51:7782/gamedb";
	private static final String USER = "root";
	private static final String PASSWORD = "mm1007";
	private static final String GETDATA = "select * from score;";
	private static final String INSERTDATA = "insert into gamedb.score(name,score) value(?,?)";

	Connection connection;

	private Array<RankingData> RankingData;

	public Ranking(Frame InFrame, Canvas Ranking)
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL,
				USER,
				PASSWORD);
			this.Ranking = Ranking;
			this.InFrame = InFrame;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void create()
	{
		try {
			RankingData = getData();
			InFrame.addKeyListener(this);
			Ranking.setVisible(true);
			Ranking.addPaintListener(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void destroy()
	{
		InFrame.removeKeyListener(this);
		Ranking.setVisible(false);
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
			if (time >= 10) {
				break;
			}
		}
		g.drawString("ESC:もどる",
			20,
			Boot.FrameH - 100);
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		// TODO 自動生成されたメソッド・スタブ
		if (Key.Key[KeyEvent.VK_ESCAPE]) {
			destroy();
			Boot.title.createTitle();
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		// TODO 自動生成されたメソッド・スタブ

	}
}
