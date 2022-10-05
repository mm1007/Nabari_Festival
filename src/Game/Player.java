package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import Game.Canvas.PaintListener;
import Game.Timer.TimerListener;
import Main.Boot;
import Main.Key;
import Reflection.Log;

public class Player implements TimerListener, PaintListener
{

	private final int PlayerSpeed = 15;

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
		} catch (Exception e) {

		}
	}

	@Override
	public void Painted(Graphics g)
	{
		// TODO 自動生成されたメソッド・スタブ
		g.setColor(Color.green);
		g.fillRect(PlayerX - 30, PlayerY - 30, 60, 30);
		g.fillRect(PlayerX - 15, PlayerY - 50, 30, 30);
	}

}
