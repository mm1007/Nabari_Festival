package Game;

import java.lang.reflect.InvocationTargetException;

import Game.Timer.TimerListener;
import Main.Array;
import Main.Boot;
import Reflection.Log;

public class Sys implements TimerListener
{

	public Player player;

	public Array<Enemy> enemy_list = new Array<Enemy>();

	public Sys()
	{
		// TODO 自動生成されたコンストラクター・スタブ
		Boot.timer.addTimerListener(this);
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

	@Override
	public void TimerEvent()
	{
		var time = 0;
		for (Enemy collision : enemy_list.List) {
			int[] result = collision.collision(player.AmmoList.List);
			if (result[0] == 1) {
				player.removeAmmo(result[1]);
				collision.remove();
				enemy_list.remove(time);
				break;
			}
			time++;
		}
	}

}
