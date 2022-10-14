package Game;

import java.awt.image.BufferedImage;
import java.util.Random;

import Game.Sys.EnemyDataBase;
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
		createRandomTimeLine(100,
			Boot.sys.EnemyDataBaseList);
		Boot.timer.addTimerListener(this);
	}

	public void destroy()
	{
		Boot.timer.removeTimerListener(this);
	}

	public void createRandomTimeLine(int amount, Array<EnemyDataBase> DataBase)
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
			int DataIndex = RD.nextInt(0,
				DataBase.size());
			EnemyDataBase Data = DataBase.get(DataIndex);
			int X = 0;
			int Y = 0;
			int Speed = Data.Speed;
			int AmmoSpeed = Data.AmmoSpeed;
			int Health = Data.Health;
			int MovePattern = Data.MovePattern[RD.nextInt(0,
				Data.MovePattern.length)];
			int AmmoMovePattern = Data.AmmoMovePattern[0];
			int AmmoInterval = Data.AmmoInterval;
			BufferedImage Tex = Data.Tex;
			BufferedImage AmmoTex = Data.AmmoTex;
			switch (MovePattern) {
			case Enemy.ONLYMOVEX:
				X = 0;
				Y = RD.nextInt(Data.Tex.getHeight(),
					H[1] / 2);
				break;
			case Enemy.R_ONLYMOVEX:
				X = W[1];
				Y = RD.nextInt(Data.Tex.getHeight(),
					H[1] / 2);
				break;
			case Enemy.ONLYMOVEY:
				X = RD.nextInt(0,
					W[1]);
				Y = 0;
				if (X > W[1] / 2)
					AmmoMovePattern = Ammo.ONLYMOVEX;
				else
					AmmoMovePattern = Ammo.R_ONLYMOVEX;
				break;
			case Enemy.R_ONLYMOVEY:
				X = RD.nextInt(0,
					W[1]);
				Y = H[1];
				if (X > W[1] / 2)
					AmmoMovePattern = Ammo.ONLYMOVEX;
				else
					AmmoMovePattern = Ammo.R_ONLYMOVEX;
				break;
			}
			TimeLineList.add(
				new TimeLineData(
					Frame,
					X,
					Y,
					MovePattern,
					AmmoMovePattern,
					Health,
					Speed,
					AmmoSpeed,
					AmmoInterval,
					Tex,
					AmmoTex));
		}
	}

	public class TimeLineData
	{

		public int Frame, X, Y, MovePattern, Health, Speed, AmmoSpeed, AmmoMovePattern, AmmoInterval;
		BufferedImage Tex, AmmoTex;

		public TimeLineData(int Frame, int X, int Y, int MovePattern, int AmmoMovePattern, int Health, int Speed,
			int AmmoSpeed, int AmmoInterval,
			BufferedImage Tex,
			BufferedImage AmmoTex)
		{
			this.Frame = Frame;
			this.X = X;
			this.Y = Y;
			this.MovePattern = MovePattern;
			this.AmmoMovePattern = AmmoMovePattern;
			this.Health = Health;
			this.Speed = Speed;
			this.AmmoSpeed = AmmoSpeed;
			this.AmmoInterval = AmmoInterval;
			this.Tex = Tex;
			this.AmmoTex = AmmoTex;
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
					addEntity.AmmoMovePattern,
					addEntity.Health,
					addEntity.Speed,
					addEntity.AmmoSpeed,
					addEntity.AmmoInterval,
					addEntity.Tex,
					addEntity.AmmoTex);
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
