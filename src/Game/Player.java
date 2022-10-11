package Game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;

import Game.Canvas.PaintListener;
import Game.Timer.TimerListener;
import Main.Boot;
import Main.Key;
import Reflection.Log;

public class Player extends Entity implements TimerListener, PaintListener
{
	public final int INTERVAL = 5;
	public int Interval = 0;
	public final int AMMOSPEED = 20;
	public int Score = 0;

	public Player(int FPlayerX, int FPlayerY, Image Tex, Image AmmoTex)
	{
		X = FPlayerX;
		Y = FPlayerY;
		this.Tex = Tex;
		W = this.Tex.getWidth(
			null);
		H = this.Tex.getHeight(
			null);
		CollisionW = (int) (W * 0.4);
		CollisionH = (int) (H * 0.4);
		this.AmmoTex = AmmoTex;
		this.EntitySpeedO = 10;
		this.EntitySpeedS = 5;

		MaxHealth = 100;
		Health = 100;

		Boot.timer.addTimerListener(
			this);
		Boot.canvas.addPaintListener(
			this);
	}

	@Override
	public void addAmmo()
	{
		try {
			Ammo ammo = new Ammo(X, Y - 30, AMMOSPEED, AmmoTex);
			Log.CallMethod(
				"setCollision",
				ammo,
				Boot.sys.enemy_list.List);
			Log.CallMethod(
				"add",
				AmmoList,
				ammo);
		} catch (Exception e) {

		}
	}

	@Override
	public void TimerEvent()
	{
		try {
			// TODO 自動生成されたメソッド・スタブ
			if (Key.Key[KeyEvent.VK_LEFT] && X > W / 2)
				Log.CallMethod(
					"moveEntityX",
					this,
					-EntitySpeed);
			if (Key.Key[KeyEvent.VK_RIGHT] && X < Boot.CanvasW - W / 2)
				Log.CallMethod(
					"moveEntityX",
					this,
					EntitySpeed);
			if (Key.Key[KeyEvent.VK_UP] && Y > H / 2)
				Log.CallMethod(
					"moveEntityY",
					this,
					-EntitySpeed);
			if (Key.Key[KeyEvent.VK_DOWN] && Y < Boot.CanvasH - H / 2)
				Log.CallMethod(
					"moveEntityY",
					this,
					EntitySpeed);
			if (Key.Key[KeyEvent.VK_Z] && Interval == 0) {
				Interval = INTERVAL;
				addAmmo();
			}
			if (Key.Key[KeyEvent.VK_SHIFT])
				EntitySpeed = EntitySpeedS;
			else
				EntitySpeed = EntitySpeedO;
			if (Interval > 0)
				Interval--;
			var time = 0;
			for (Ammo move : AmmoList.List) {
				move.move();
				if (move.AmmoY < 0) {
					Log.CallMethod(
						"remove",
						move);
					Log.CallMethod(
						"remove",
						AmmoList,
						time);
				}
				time++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void Painted(Graphics2D g)
	{
		// TODO 自動生成されたメソッド・スタブ
		g.setFont(
			font);
		g.drawImage(
			Tex,
			X - W / 2,
			Y - H / 2,
			null);
		for (Ammo draw : AmmoList.List) {
			draw.draw(
				g);
		}
		g.setColor(
			Color.green);
		g.drawString(
			"HP",
			0,
			(int) (Boot.CanvasH * 0.9));
		g.drawRect(
			20,
			(int) (Boot.CanvasH * 0.9),
			100,
			20);
		g.fillRect(
			20,
			(int) (Boot.CanvasH * 0.9),
			100 * Health / MaxHealth,
			20);
	}

}
