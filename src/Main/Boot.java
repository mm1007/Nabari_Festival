package Main;

import javax.swing.UIManager;

import Game.Canvas;
import Game.Sys;
import Game.Timer;
import Reflection.Log;
import Window.Elements;

public class Boot
{

	public final static int WIDTH = 720;
	public final static int HEIGHT = 720;

	public final static int DEV_WIDTH = 500;
	public final static int DEV_HEIGHT = 500;

	public final static int FPlayerX = WIDTH / 2;
	public final static int FPlayerY = (int) (HEIGHT * 0.8);

	private final static String SYSTEM_DEF = UIManager.getSystemLookAndFeelClassName();

	public static Timer timer;
	public static Key key;
	public static Sys sys;
	public static Canvas canvas;
	public static Elements elements;

	public static void main(String[] args) throws Exception
	{
		//Set LookAndFeel
		UIManager.setLookAndFeel(SYSTEM_DEF);

		//Create Instance
		timer = new Timer();
		sys = new Sys();
		key = new Key();
		canvas = new Canvas(WIDTH, HEIGHT);
		elements = new Elements();

		//
		Log.CallMethod("createElement", elements);
		Log.CallMethod("createGame", sys);
	}

}
