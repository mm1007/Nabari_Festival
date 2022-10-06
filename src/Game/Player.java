package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import Game.Canvas.PaintListener;
import Game.Timer.TimerListener;
import Main.Array;
import Main.Boot;
import Main.Key;
import Reflection.Log;

public class Player implements TimerListener, PaintListener
{

	private final int PlayerSpeed = 10;

	public Array<Ammo> AmmoList = new Array<Ammo>();

	public final int INTERVAL = 30;
	public int Interval = 0;
	public final int AMMOSPEED = 10;

	public int PlayerX;
	public int PlayerY;

	public Player(int FPlayerX, int FPlayerY)
	{
		this.PlayerX = FPlayerX;
		this.PlayerY = FPlayerY;

		Boot.timer.addTimerListener(this);
		Boot.canvas.addPaintListener(this);
	}

	public void movePlayer(int move)
	{
		PlayerX += move;
	}

	@Override
	public void TimerEvent()
	{
		try {
			// TODO 自動生成されたメソッド・スタブ
			if (Key.Key[KeyEvent.VK_LEFT])
				Log.CallMethod("movePlayer", Boot.sys.player, -PlayerSpeed);
			if (Key.Key[KeyEvent.VK_RIGHT])
				Log.CallMethod("movePlayer", Boot.sys.player, PlayerSpeed);
			if (Key.Key[KeyEvent.VK_SPACE] && Interval == 0) {
				Interval = INTERVAL;
				Log.CallMethod("add", AmmoList, new Ammo(PlayerX, PlayerY - 30, AMMOSPEED, Color.blue));
			}
			if (Interval > 0)
				Interval--;
			var time = 0;
			for (Ammo move : AmmoList.List) {
				move.move();
				if (move.AmmoY < 0) {
					Log.CallMethod("remove", move);
					Log.CallMethod("remove", AmmoList, time);
				}
				time++;
			}
		} catch (Exception e) {

		}
	}

	@Override
	public void Painted(Graphics g)
	{
		// TODO 自動生成されたメソッド・スタブ
		g.setColor(Color.green);
		g.fillRect(PlayerX - 25, PlayerY - 25, 50, 20);
		g.fillRect(PlayerX - 10, PlayerY - 45, 20, 20);
		for (Ammo draw : AmmoList.List) {
			draw.draw(g);
		}
	}

}
