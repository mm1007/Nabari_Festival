package Game;

import java.lang.reflect.InvocationTargetException;

import Main.Array;
import Main.Boot;
import Reflection.Log;

public class Sys
{

	public Player player;

	public Array<Enemy> enemy_list = new Array<Enemy>();

	public Sys()
	{
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public void createGame()
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException
	{
		this.player = new Player(Boot.FPlayerX, Boot.FPlayerY);

		var time = 0;
		for (int t = 0; t < 8; t++) {
			for (int i = 0; i < 3; i++) {
				if (time % 2 == 0)
					Log.CallMethod("add", enemy_list, new Enemy(80 * t + 60, 80 * i + 50));
				time++;
			}
		}
	}

}
