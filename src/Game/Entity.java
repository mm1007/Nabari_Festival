package Game;

import java.awt.Font;
import java.util.ArrayList;

import Main.Array;

public class Entity
{
	public Array<Ammo> AmmoList = new Array<Ammo>();

	public Font font = new Font("ＭＳ ゴシック", Font.PLAIN, 20);

	public final int INTERVAL = 30;
	public int Interval = 0;
	public final int AMMOSPEED = 10;

	public int MaxHealth;
	public int Health;

	public int X;
	public int Y;

	public int W = 50;
	public int H = 40;

	public void moveEntityX(int move)
	{
		X += move;
	}

	public void moveEntityY(int move)
	{
		Y += move;
	}

	public int[] collision(ArrayList<Ammo> Ammo)
	{
		var time = 0;
		int[] ret = { 0, 0 };
		for (Ammo collision : Ammo) {
			var DistanceX = Math.abs(collision.AmmoX - X);
			var DistanceY = Math.abs(collision.AmmoY - Y);
			var minDistanceX = W / 2 + collision.AmmoW / 2;
			var minDistanceY = H / 2 + collision.AmmoH / 2;

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
}
