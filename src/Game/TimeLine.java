package Game;

import java.util.Random;

import Game.Timer.TimerListener;
import Main.Array;
import Main.Boot;
import Reflection.Log;

public class TimeLine implements TimerListener
{

	public final int[][] TimeLineDataBase =
	{
		{
			50, 0, 100, Enemy.ONLYMOVEX
		},
		{
			100, 0, 100, Enemy.ONLYMOVEX
		},
		{
			150, 0, 100, Enemy.ONLYMOVEX
		},
		{
			50, Boot.CanvasW, 100, Enemy.R_ONLYMOVEX
		},
		{
			100, Boot.CanvasW, 100, Enemy.R_ONLYMOVEX
		},
		{
			150, Boot.CanvasW, 100, Enemy.R_ONLYMOVEX
		}
	};

	Array<TimeLineData> TimeLineList = new Array<TimeLineData>();

	public TimeLine()
	{
		/*for (int[] TimeLine : TimeLineDataBase) {
			TimeLineList.add(new TimeLineData(TimeLine[0], TimeLine[1], TimeLine[2], TimeLine[3]));
		}*/
		createRandomTimeLine(100);
		Boot.timer.addTimerListener(this);
	}

	public void createRandomTimeLine(int amount)
	{
		int[] W =
		{
			0, Boot.CanvasW
		};
		int[] H =
		{
			0, Boot.CanvasH
		};
		Random RD = new Random();
		for (int k = 0; k < amount; k++) {
			int Frame = RD.nextInt(5000);
			int X = W[RD.nextInt(2)];
			int Y = RD.nextInt(100, H[1] / 2);
			int MovePattern = (X == W[0]) ? Enemy.ONLYMOVEX : Enemy.R_ONLYMOVEX;
			int Health = Enemy.HealthList[0];
			TimeLineList.add(new TimeLineData(Frame, X, Y, MovePattern, Health));
		}
	}

	public class TimeLineData
	{

		public int Frame, X, Y, MovePattern, Health;

		public TimeLineData(int Frame, int X, int Y, int MovePattern, int Health)
		{
			this.Frame = Frame;
			this.X = X;
			this.Y = Y;
			this.MovePattern = MovePattern;
			this.Health = Health;
		}

	}

	@Override
	public void TimerEvent()
	{
		// TODO 自動生成されたメソッド・スタブ
		try {
			for (TimeLineData addEntity : TimeLineList.List) {
				if (addEntity.Frame != Boot.timer.Frame) {
					continue;
				}
				Enemy add = new Enemy(
					addEntity.X,
					addEntity.Y,
					addEntity.MovePattern,
					addEntity.Health,
					Boot.sys.TexIndexOf(Sys.Tex, "敵_D").image,
					Boot.sys.TexIndexOf(Sys.Tex, "弾Red_D").image);
				add.setAmmoList(Boot.sys.enemy_ammo_list);
				Log.CallMethodNoThread(
					"add",
					Boot.sys.enemy_list,
					add);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
