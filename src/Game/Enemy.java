package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import Game.Canvas.PaintListener;
import Game.Timer.TimerListener;
import Main.Array;
import Main.Boot;
import Reflection.Log;

public class Enemy extends Entity implements PaintListener, TimerListener
{

	public final int INTERVAL = new Random().nextInt(30, 60);
	public final int AMMOSPEED = 10;
	public int Interval = 0;

	public Enemy(int EnemyX, int EnemyY)
	{
		X = EnemyX;
		Y = EnemyY;

		Boot.timer.addTimerListener(this);
		Boot.canvas.addPaintListener(this);
	}

	public void remove()
	{
		Boot.canvas.removePaintListener(this);
	}

	@Override
	public void Painted(Graphics g)
	{
		// TODO 自動生成されたメソッド・スタブ
		g.setColor(Color.red);
		g.fillRect(X - 25, Y - 20, 50, 20);
		g.fillRect(X - 10, Y, 20, 20);
		for (Ammo draw : AmmoList.List) {
			draw.draw(g);
		}
	}

	@Override
	public void TimerEvent()
	{
		// TODO 自動生成されたメソッド・スタブ
		try {
			if (Interval == 0) {
				Interval = INTERVAL;
				var ammo = new Ammo(X, Y, -AMMOSPEED, Color.red);
				Log.CallMethod("add", AmmoList, ammo);
			}
			if (Interval > 0) {
				Interval--;
			}
			var time = 0;
			for (Ammo move : AmmoList.List) {
				move.move();
				if (move.AmmoY > Boot.HEIGHT) {
					Log.CallMethod("remove", move);
					Log.CallMethod("remove", AmmoList, time);
				}
				time++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
