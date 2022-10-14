package Game;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import Game.Canvas.PaintListener;
import Game.Timer.TimerListener;
import Main.Array;
import Main.Boot;
import Main.Key;
import Main.Key.KeyListener;
import Reflection.Log;
import Window.Frame;
import Window.ScrollPane;

public class Sys implements TimerListener, PaintListener, KeyListener
{

	public Player player;

	public int Score = 0;

	public static HashMap<String, BufferedImage> Tex;

	public Array<Enemy> enemy_list;

	public Array<Ammo> enemy_ammo_list;

	public Array<EnemyDataBase> EnemyDataBaseList;

	private Frame InFrame;
	private Canvas GameCanvas;
	private Timer GameTimer;
	private Canvas ScoreCanvas;
	private ScrollPane DevPanel;
	private TimeLine EnemyTimeLine;
	private Score GameScore;

	public Sys(Frame InFrame, Canvas GameCanvas, Timer GameTimer, TimeLine EnemyTimeLine, Score GameScore,
		Canvas ScoreCanvas,
		ScrollPane DevPanel)
	{
		// TODO 自動生成されたコンストラクター・スタブ
		this.InFrame = InFrame;
		this.GameCanvas = GameCanvas;
		this.GameTimer = GameTimer;
		this.ScoreCanvas = ScoreCanvas;
		this.DevPanel = DevPanel;
		this.EnemyTimeLine = EnemyTimeLine;
		this.GameScore = GameScore;
	}

	public void destroy()
	{
		Key.removeKeyListener(this);
		GameCanvas.setVisible(false);
		GameCanvas.removePaintListener(this);
		GameTimer.removeTimerListener(this);
		GameScore.destroy();
		ScoreCanvas.setVisible(false);
		DevPanel.ScrollPane.setVisible(false);
		EnemyTimeLine.destroy();
	}

	public void createSys()
	{
		Key.addKeyListener(this);
		GameCanvas.setVisible(true);
		Boot.elements.DevLabelScroll.setVisible(true);
		if (GameScore != null)
			GameScore.create();
		ScoreCanvas.setVisible(true);
		GameCanvas.addPaintListener(this);
		GameTimer.addTimerListener(this);
		EnemyTimeLine.createTimeLine();
	}

	public void createGame()
	{
		try {
			Boot.timer.setFrame(0);
			if (player != null)
				player.destroy();
			if (enemy_ammo_list != null)
				for (Enemy destroy : enemy_list.List) {
					destroy.destroy();
				}
			enemy_list = new Array<Enemy>();
			enemy_ammo_list = new Array<Ammo>();
			GameScore.setPaintListener(
				Boot.elements.UI);
			BufferedImage PlayerTex = TexIndexOf(
				"自機_U");
			BufferedImage AmmoTex = TexIndexOf(
				"弾Blue_D");
			this.player = new Player(Boot.FPlayerX, Boot.FPlayerY, PlayerTex, AmmoTex);
			GameScore.setPlayer(player);
			setEnemyDataBase();
			EnemyTimeLine.create();
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*var time = 0;
		for (int t = 0; t < 8; t++) {
			for (int i = 0; i < 3; i++) {
				if (time % 2 == 0)
					Log.CallMethod("add", enemy_list, new Enemy(80 * t + 60, 80 * i + 50));
				time++;
			}
		}*/
	}

	public void LoadTex(File DataFile) throws IOException
	{
		Tex = new HashMap<>();
		for (File data : DataFile.listFiles()) {
			var name = data.getName();
			Tex.put(name.substring(0,
				name.indexOf(".")),
				ImageIO.read(data));
		}
	}

	public void setEnemyDataBase()
	{
		EnemyDataBaseList = new Array<>();
		EnemyDataBaseList.List.clear();
		EnemyDataBaseList.add(new EnemyDataBase(
			TexIndexOf("敵_D"),
			new int[]
			{
				Enemy.ONLYMOVEX, Enemy.R_ONLYMOVEX
			},
			TexIndexOf("弾Red_D"),
			new int[]
			{
				Ammo.ONLYMOVEY
			},
			3,
			60,
			5,
			20));
		EnemyDataBaseList.add(new EnemyDataBase(
			TexIndexOf("敵2_D"),
			new int[]
			{
				Enemy.ONLYMOVEX, Enemy.R_ONLYMOVEX
			},
			TexIndexOf("弾Red_D"),
			new int[]
			{
				Ammo.ONLYMOVEY
			},
			3,
			60,
			5,
			30));
		EnemyDataBaseList.add(new EnemyDataBase(
			TexIndexOf("敵3_D"),
			new int[]
			{
				Enemy.ONLYMOVEY, Enemy.R_ONLYMOVEY
			},
			TexIndexOf("弾Red_D"),
			new int[]
			{
				Ammo.ONLYMOVEY
			},
			3,
			60,
			5,
			30));
		EnemyDataBaseList.add(new EnemyDataBase(
			TexIndexOf("敵4_D"),
			new int[]
			{
				Enemy.ONLYMOVEX, Enemy.R_ONLYMOVEX
			},
			TexIndexOf("弾Red_D"),
			new int[]
			{
				Ammo.TRACKING
			},
			3,
			100,
			5,
			30));
	}

	public BufferedImage TexIndexOf(String name)
	{
		return Tex.get(name);
	}

	public class EnemyDataBase
	{
		public BufferedImage Tex, AmmoTex;
		public int[] MovePattern, AmmoMovePattern;
		public int AmmoSpeed, Speed, Health, AmmoInterval;

		public EnemyDataBase(BufferedImage Tex, int[] MovePattern, BufferedImage AmmoTex, int[] AmmoMovePattern,
			int AmmoSpeed, int AmmoInterval,
			int Speed,
			int Health)
		{
			this.Tex = Tex;
			this.AmmoTex = AmmoTex;
			this.MovePattern = MovePattern;
			this.AmmoMovePattern = AmmoMovePattern;
			this.AmmoSpeed = AmmoSpeed;
			this.AmmoInterval = AmmoInterval;
			this.Speed = Speed;
			this.Health = Health;
		}
	}

	class TexData
	{
		String name;
		File file;
		BufferedImage image;

		public TexData(String name, File file, BufferedImage image)
		{
			try {
				this.name = name;
				this.file = file;
				this.image = image;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void moveAmmo()
	{
		try {
			var time = 0;
			for (Ammo move : enemy_ammo_list.List) {
				if (move == null)
					continue;
				move.move();
				if (move.AmmoY > Boot.CanvasH) {
					Log.CallMethod(
						"remove",
						move);
					Log.CallMethod(
						"remove",
						enemy_ammo_list,
						time);
				} else if (move.AmmoX > Boot.CanvasW || move.AmmoX < 0) {
					Log.CallMethod(
						"remove",
						move);
					Log.CallMethod(
						"remove",
						enemy_ammo_list,
						time);
				} else
					time++;
			}
		} catch (Exception e) {

		}
	}

	public void chkCollisionE()
	{
		try {
			var time = 0;
			for (Enemy collision : enemy_list.List) {
				collision.move();
				int[] result = collision.collision(
					player.AmmoList.List);
				if (result[0] == 1) {
					Log.CallMethod(
						"changeHealth",
						collision,
						-10);
					Log.CallMethod(
						"removeAmmo",
						player,
						result[1]);
					if (collision.Health <= 0) {
						Log.CallMethod(
							"destroy",
							enemy_list,
							time);
						player.Score += 100;
					}
				} else if (collision.X < 0 || collision.X > Boot.CanvasW || collision.Y < 0
					|| collision.Y > Boot.CanvasH) {
					Log.CallMethod("destroy",
						enemy_list,
						time);
				} else
					time++;
			}
		} catch (Exception e) {

		}
	}

	public void chkCollisionP()
	{
		try {
			int[] result = player.collision(
				enemy_ammo_list.List);
			if (result[0] == 1) {
				Log.CallMethod(
					"remove",
					enemy_ammo_list,
					result[1]);
				Log.CallMethod(
					"changeHealth",
					player,
					-10);
				if (player.Health <= 0) {
					player.remove();
					destroy();
					Boot.gameover.createGameOver();
				}
			}
		} catch (

		Exception e) {

		}
	}

	@Override
	public void TimerEvent()
	{
		try {
			moveAmmo();
			chkCollisionE();
			chkCollisionP();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void Painted(Graphics2D g)
	{
		// TODO 自動生成されたメソッド・スタブ
		for (Ammo draw : enemy_ammo_list.List) {
			if (draw != null)
				draw.draw(
					g);
		}
	}

	@Override
	public void KeyPressed()
	{
		// TODO 自動生成されたメソッド・スタブ
		if (Key.Key[KeyEvent.VK_ESCAPE]) {
			destroy();
			Boot.pause.createPause();
		}
	}

	@Override
	public void KeyReleased()
	{
		// TODO 自動生成されたメソッド・スタブ

	}

}
