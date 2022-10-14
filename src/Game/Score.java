package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import Game.Canvas.PaintListener;

public class Score implements PaintListener
{
	protected Font font = new Font("ＭＳ ゴシック", Font.PLAIN, 42);

	int W, H;

	Player player;

	Canvas ScoreCanvas;

	public Score(int W, int H, Canvas ScoreCanvas)
	{
		this.W = W;
		this.H = H;
		this.ScoreCanvas = ScoreCanvas;
		this.ScoreCanvas.setVisible(true);
	}

	public void destroy()
	{
		ScoreCanvas.removePaintListener(this);
	}

	public void setPlayer(Player set)
	{
		this.player = set;
	}

	public void setPaintListener(Canvas set)
	{
		set.addPaintListener(
			this);
	}

	public void removePaintListener(Canvas remove)
	{
		remove.removePaintListener(
			this);
	}

	@Override
	public void Painted(Graphics2D g)
	{
		// TODO 自動生成されたメソッド・スタブ
		g.setFont(
			font);
		g.setColor(
			Color.black);
		g.fillRect(
			0,
			0,
			W,
			H);
		g.setColor(
			Color.green);
		if (player != null)
			g.drawString(
				"Score:" + player.Score,
				10,
				100);
	}

}
