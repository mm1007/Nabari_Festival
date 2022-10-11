package Main;

import java.io.File;

import javax.swing.UIManager;

import Game.Canvas;
import Game.Sys;
import Game.Timer;
import Reflection.Log;
import Window.Elements;

public class Boot
{
	public final static File PATH = new File("").getAbsoluteFile();

	public final static int FrameW = 1080;
	public final static int FrameH = 980;

	public final static int CanvasW = 720;
	public final static int CanvasH = FrameH - 30;

	public final static int DEV_WIDTH = FrameW - CanvasW;
	public final static int DEV_HEIGHT = 400;

	public final static int ScoreW = FrameW - CanvasW;
	public final static int ScoreH = FrameH - DEV_HEIGHT - 30;

	public final static int FPlayerX = CanvasW / 2;
	public final static int FPlayerY = (int) (CanvasH * 0.8);

	private final static String SYSTEM_DEF = UIManager.getSystemLookAndFeelClassName();

	public static Timer timer;
	public static Key key;
	public static Sys sys;
	public static Canvas canvas;
	public static Elements elements;

	public static void main(String[] args) throws Exception
	{
		//Set LookAndFeel
		UIManager.setLookAndFeel(
			SYSTEM_DEF);

		//Create Instance
		timer = new Timer();
		key = new Key();
		canvas = new Canvas(FrameW - CanvasW, 0, CanvasW, CanvasH);
		sys = new Sys();
		elements = new Elements();

		//
		Log.CallMethodNoThread(
			"createElement",
			elements);
		Log.CallMethodNoThread(
			"LoadTex",
			sys,
			new File(
				PATH + "\\data"));
		Log.CallMethod(
			"createGame",
			sys);
		Log.CallMethodNoThread(
			"start",
			timer);
	}

}
