package Game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Random;

import Game.Canvas.PaintListener;
import Game.Timer.TimerListener;
import Main.Boot;
import Reflection.Log;

public class Enemy extends Entity implements PaintListener, TimerListener
{
	public final static int ONLYMOVEY = 0;
	public final static int R_ONLYMOVEY = 1;
	public final static int ONLYMOVEX = 2;
	public final static int R_ONLYMOVEX = 3;

	public int MovePattern;

	public final int INTERVAL = new Random().nextInt(10, 20);
	public final int AMMOSPEED = 10;
	public int Interval = 0;

	public Enemy(int EnemyX, int EnemyY, int MovePattern, Image Tex, Image AmmoTex)
	{
		X = EnemyX;
		Y = EnemyY;
		this.Tex = Tex;
		this.AmmoTex = AmmoTex;
		W = this.Tex.getWidth(null);
		H = this.Tex.getHeight(null);
		CollisionW = W;
		CollisionH = H;
		this.MovePattern = MovePattern;

		Boot.timer.addTimerListener(this);
		Boot.canvas.addPaintListener(this);
	}

	@Override
	public void Painted(Graphics2D g)
	{
		// TODO 自動生成されたメソッド・スタブ
		Graphics2D g1 = (Graphics2D) Tex.getGraphics();
		g.setColor(Color.red);
		g.drawImage(Tex, X - W / 2, Y - H / 2, null);
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
				var ammo = new Ammo(X, Y, -AMMOSPEED, AmmoTex);
				Log.CallMethod("add", AmmoList, ammo);
			}
			if (Interval > 0) {
				Interval--;
			}
			var time = 0;
			if (AmmoListC == false) {
				for (Ammo move : AmmoList.List) {
					move.move();
					if (move.AmmoY > Boot.HEIGHT) {
						Log.CallMethod("remove", move);
						Log.CallMethod("remove", AmmoList, time);
					}
					time++;
				}
			}
			switch (MovePattern) {
			case ONLYMOVEX:
				moveEntityX(EntitySpeed);
				break;
			case R_ONLYMOVEX:
				moveEntityX(-EntitySpeed);
				break;
			case ONLYMOVEY:
				moveEntityY(EntitySpeed);
				break;
			case R_ONLYMOVEY:
				moveEntityX(-EntitySpeed);
				break;
			}
		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}

}
