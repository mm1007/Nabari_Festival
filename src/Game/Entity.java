package Game;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import Game.Canvas.PaintListener;
import Game.Timer.TimerListener;
import Main.Array;
import Main.Boot;
import Reflection.Log;

public class Entity implements PaintListener, TimerListener
{
	public Array<Ammo> AmmoList = new Array<Ammo>();
	public boolean AmmoListC = false;

	public Font font = new Font("ＭＳ ゴシック", Font.PLAIN, 20);

	public Image Tex;
	public Image AmmoTex;

	public final int INTERVAL = 30;
	public int Interval = 0;
	public final int AMMOSPEED = 10;

	public int EntitySpeedO = 5;
	public int EntitySpeed = EntitySpeedO;
	public int EntitySpeedS = 3;

	public int MaxHealth;
	public int Health;

	public int X;
	public int Y;

	public int W;
	public int H;
	public int CollisionW;
	public int CollisionH;

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
			Log.CallMethod("add", AmmoList, ammo);
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
