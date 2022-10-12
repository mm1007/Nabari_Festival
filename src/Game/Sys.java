package Game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.imageio.ImageIO;

import Game.Canvas.PaintListener;
import Game.Timer.TimerListener;
import Main.Array;
import Main.Boot;
import Reflection.Log;

public class Sys implements TimerListener, PaintListener
{

	public Player player;

	public Score score;

	public int Score = 0;

	public TimeLine timeline;

	public static Array<TexData> Tex;

	public Array<Enemy> enemy_list;

	public Array<Ammo> enemy_ammo_list;

	public Array<EnemyDataBase> EnemyDataBaseList;

	public Sys()
	{
		// TODO 自動生成されたコンストラクター・スタブ
		Boot.canvas.addPaintListener(
			this);
		Boot.timer.addTimerListener(
			this);
	}

	public void LoadTex(File DataFile) throws IOException
	{
		Tex = new Array<TexData>();
		for (File data : DataFile.listFiles()) {
			var name = data.getName();
			Tex.add(
				new TexData(
					name.substring(
						0,
						name.indexOf(
							".")),
					data,
					ImageIO.read(
						data)));
		}
	}

	public void setEnemyDataBase()
	{

	}

	public TexData TexIndexOf(Array<TexData> List, String name)
	{
		for (int k = 0, t = List.size(); k < t; k++) {
			TexData data = List.get(
				k);
			if (data.name.equals(
				name))
				return data;
		}
		return null;
	}

	public class EnemyDataBase
	{
		public Image Tex, AmmoTex;
		public int MovePattern, AmmoMovePattern;

		public EnemyDataBase(Image Tex, int MovePattern, Image AmmoTex, int AmmoMovePattern, int AmmoSpeed, int Health)
		{
			this.Tex = Tex;
			this.AmmoTex = AmmoTex;
			this.MovePattern = MovePattern;
			this.AmmoMovePattern = AmmoMovePattern;
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

	public void createGame()
		throws IllegalAccessException, IllegalArgumentException, InvocationTargetException,
		NoSuchMethodException,
		IOException
	{
		enemy_list = new Array<Enemy>();
		enemy_ammo_list = new Array<Ammo>();
		score = new Score(Boot.ScoreW, Boot.ScoreH);
		score.setPaintListener(
			Boot.elements.UI);

		BufferedImage PlayerTex = TexIndexOf(
			Tex,
			"自機_U").image;
		BufferedImage AmmoTex = TexIndexOf(
			Tex,
			"弾Blue_U").image;
		this.player = new Player(Boot.FPlayerX, Boot.FPlayerY, PlayerTex, AmmoTex);
		score.setPlayer(
			player);

		this.timeline = new TimeLine();

		/*var time = 0;
		for (int t = 0; t < 8; t++) {
			for (int i = 0; i < 3; i++) {
				if (time % 2 == 0)
					Log.CallMethod("add", enemy_list, new Enemy(80 * t + 60, 80 * i + 50));
				time++;
			}
		}*/
	}

	@Override
	public void TimerEvent()
	{
		try {
			var time = 0;
			for (Ammo move : enemy_ammo_list.List) {
				move.move();
				if (move.AmmoY > Boot.CanvasH) {
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
			time = 0;
			for (Enemy collision : enemy_list.List) {
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
					break;
				}
				time++;
			}
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
				}
			}
		} catch (Exception e) {
			//e.printStackTrace();
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

}
