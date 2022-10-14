package Game;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Game.Canvas.PaintListener;
import Game.Timer.TimerListener;
import Main.Array;
import Main.Boot;
import Reflection.Log;

public abstract class Entity implements PaintListener, TimerListener
{
	protected Array<Ammo> AmmoList = new Array<Ammo>();
	protected boolean AmmoListC = false;

	protected Font font = new Font("ＭＳ ゴシック", Font.PLAIN, 20);

	protected BufferedImage Tex = null;
	protected BufferedImage AmmoTex = null;;

	protected int INTERVAL = 30;
	protected int Interval = 0;
	protected int AMMOSPEED = 5;

	protected int EntitySpeedO = 5;
	protected int EntitySpeed = EntitySpeedO;
	protected int EntitySpeedS = 3;

	protected int MaxHealth = 100;
	protected int Health = 100;

	protected int X = 0;
	protected int Y = 0;

	protected int W = 0;
	protected int H = 0;
	protected int CollisionW = 0;
	protected int CollisionH = 0;

	public void moveEntityX(int move)
	{
		X += move;
	}

	public void moveEntityY(int move)
	{
		Y += move;
	}

	public void changeHealth(int amount)
	{
		Health += amount;
	}

	public void setHealth(int health)
	{
		Health = health;
	}

	public void destroy()
	{
		Boot.timer.removeTimerListener(this);
		Boot.canvas.removePaintListener(this);
	}

	public void create()
	{
		Boot.timer.addTimerListener(this);
		Boot.canvas.addPaintListener(this);
	}

	public void remove()
	{
		Boot.timer.removeTimerListener(this);
		Boot.canvas.removePaintListener(this);
	}

	public void setAmmoList(Array<Ammo> List)
	{
		AmmoListC = true;
		AmmoList = List;
	}

	public void addAmmo()
	{
		try {
			Ammo ammo = new Ammo(X, Y - 30, AMMOSPEED, AmmoTex);
			//Log.CallMethod("setCollision", ammo, Boot.sys.enemy_list.List);
			Log.CallMethod("add",
				AmmoList,
				ammo);
		} catch (Exception e) {

		}
	}

	public void removeAmmo(int index)
	{
		AmmoList.remove(index);
	}

	/**
	 *
	 * @param Ammo
	 * @return {判定(0:false,1:true),Index)
	 */
	public int[] collision(ArrayList<Ammo> Ammo)
	{
		try {
			var time = 0;
			int[] ret =
			{
				0, 0
			};
			for (Ammo collision : Ammo) {
				var DistanceX = Math.abs(collision.AmmoX - X);
				var DistanceY = Math.abs(collision.AmmoY - Y);
				var minDistanceX = CollisionW / 2 + collision.AmmoW / 2;
				var minDistanceY = CollisionH / 2 + collision.AmmoH / 2;

				if (minDistanceX < DistanceX) {
					time++;
					continue;
				}
				if (minDistanceY < DistanceY) {
					time++;
					continue;
				}
				ret[0] = 1;
				ret[1] = time;
				return ret;
			}
			return ret;
		} catch (Exception e) {
			return new int[]
			{
				0, 0
			};
		}
	}

	@Override
	public void Painted(Graphics2D g)
	{
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void TimerEvent()
	{
		// TODO 自動生成されたメソッド・スタブ

	}
}
