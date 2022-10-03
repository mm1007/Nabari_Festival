package Main;

import javax.swing.UIManager;

import Game.Canvas;
import Reflection.Log;
import Window.Elements;

public class Boot
{

	public final static int WIDTH = 720;
	public final static int HEIGHT = 720;

	public final static int DEV_WIDTH = 500;
	public final static int DEV_HEIGHT = 500;

	private final static String SYSTEM_DEF = UIManager.getSystemLookAndFeelClassName();

	public static Canvas canvas;
	public static Elements elements;

	public static void main(String[] args) throws Exception
	{
		//Set LookAndFeel
		UIManager.setLookAndFeel(SYSTEM_DEF);

		//Create Instance
		canvas = new Canvas(WIDTH, HEIGHT);
		elements = new Elements();

		//
		Log.CallMethod("createElement", elements);
	}

}
