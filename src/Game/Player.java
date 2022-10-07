package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.print.attribute.HashPrintJobAttributeSet;

import Game.Canvas.PaintListener;
import Game.Timer.TimerListener;
import Main.Array;
import Main.Boot;
import Main.Key;
import Reflection.Log;

public class Player extends Entity implements TimerListener, PaintListener
{

	private final int PlayerSpeed = 10;

	public final int INTERVAL = 30;
	public int Interval = 0;
	public final int AMMOSPEED = 10;

	public Player(int FPlayerX, int FPlayerY)
	{
		X = FPlayerX;
		Y = FPlayerY;

		MaxHealth = 100;
		Health = 100;

		Boot.timer.addTimerListener(this);
		Boot.canvas.addPaintListener(this);
	}

	public void remove()
	{
		Boot.canvas.removePaintListener(this);
	}

	public void addAmmo()
	{
		try {
			Ammo ammo = new Ammo(X, Y - 30, AMMOSPEED, Color.blue);
			Log.CallMethod("setCollision", ammo, Boot.sys.enemy_list.List);
			Log.CallMethod("add", AmmoList, ammo);
		} catch (Exception e) {

		}
	}

	public void removeAmmo(int index)
	{
		AmmoList.remove(index);
	}

	@Override
	public void TimerEvent()
	{
		try {
			// TODO 自動生成されたメソッド・スタブ
			if (Key.Key[KeyEvent.VK_LEFT])
				Log.CallMethod("moveEntityX", this, -PlayerSpeed);
			if (Key.Key[KeyEvent.VK_RIGHT])
				Log.CallMethod("moveEntityX", this, PlayerSpeed);
			if (Key.Key[KeyEvent.VK_UP]) {
				Log.CallMethod("moveEntityY", this, -PlayerSpeed);
			}
			if (Key.Key[KeyEvent.VK_DOWN]) {
				Log.CallMethod("moveEntityY", this, PlayerSpeed);
			}
			if (Key.Key[KeyEvent.VK_SPACE] && Interval == 0) {
				Interval = INTERVAL;
				addAmmo();
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
			e.printStackTrace();
		}
	}

	@Override
	public void Painted(Graphics g)
	{
		// TODO 自動生成されたメソッド・スタブ
		g.setFont(font);
		g.setColor(Color.green);
		g.fillRect(X - 25, Y - 25, 50, 20);
		g.fillRect(X - 10, Y - 45, 20, 20);
		for (Ammo draw : AmmoList.List) {
			draw.draw(g);
		}
		g.setColor(Color.green);
		g.drawString("HP", 0, (int) (Boot.HEIGHT * 0.9));
		g.drawRect(0, (int) (Boot.HEIGHT * 0.9), 100, 20);
		g.fillRect(0, (int) (Boot.HEIGHT * 0.9), 100 * Health / MaxHealth, 20);
	}

}
