package Window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import javax.swing.JFrame;

import Game.Canvas;
import Game.GameOver;
import Game.Pause;
import Game.Ranking;
import Main.Array;
import Main.Boot;
import Reflection.Log;
import Reflection.Log.LogListener;

public class Elements implements LogListener
{
	private final Font DevFont = new Font(
		"ＭＳ ゴシック",
		Font.PLAIN,
		20);

	private Array<String> OutputLog = new Array<String>();

	public Frame Game;
	//public Frame DevScreen;

	public Canvas Title;
	public Canvas UI;
	public Label DevLabel;
	public ScrollPane DevLabelScroll;

	public Elements()
		throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException
	{
		Log.addLogListener(
			this);
	}

	public void createElement()
		throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException,
		ClassNotFoundException, SQLException
	{
		this.Game = new Frame();
		Log.CallMethodNoThread("setLayoutNull",
			Game);
		Log.CallMethodNoThread(
			"setSize",
			Game,
			Boot.FrameW,
			Boot.FrameH);
		Log.CallMethodNoThread(
			"setWindowName",
			Game,
			"ゲーム画面");
		Log.CallMethodNoThread(
			"setCloseOp",
			Game,
			JFrame.EXIT_ON_CLOSE);
		Log.CallMethodNoThread(
			"setResizable",
			Game,
			false);
		Log.CallMethodNoThread(
			"add",
			Game,
			Boot.canvas,
			BorderLayout.CENTER);
		Log.CallMethodNoThread(
			"addKeyListener",
			Game,
			Boot.key);
		Log.CallMethodNoThread(
			"setVisible",
			Game,
			true);

		UI = new Canvas(0, 0, Boot.FrameW - Boot.CanvasW - 1, Boot.FrameH - Boot.DEV_HEIGHT - 40);
		Log.CallMethodNoThread("setVisible",
			UI,
			false);
		Log.CallMethodNoThread(
			"setBounds",
			UI,
			0,
			0,
			Boot.ScoreW - 1,
			Boot.ScoreH);
		Log.CallMethodNoThread(
			"setBackground",
			UI,
			Color.blue);
		Log.CallMethodNoThread(
			"add",
			Game,
			UI,
			BorderLayout.CENTER);

		/*this.DevScreen = new Frame();
		Log.CallMethodNoThread("setBounds", DevScreen, 0, 0, Boot.DEV_WIDTH, Boot.DEV_HEIGHT);
		Log.CallMethodNoThread("setWindowName", DevScreen, "メソッド履歴");
		Log.CallMethodNoThread("setCloseOp", DevScreen, JFrame.DO_NOTHING_ON_CLOSE);
		Log.CallMethodNoThread("setResizable", DevScreen, false);
		// Log.CallMethodNoThread("setLayoutNull", DevScreen);
		Log.CallMethodNoThread("setVisible", DevScreen, true);*/

		DevLabel = new Label();
		DevLabelScroll = new ScrollPane();
		Log.CallMethodNoThread(
			"setBounds",
			DevLabel,
			0,
			0,
			Boot.DEV_WIDTH,
			Boot.DEV_HEIGHT);
		Log.CallMethodNoThread(
			"setBounds",
			DevLabelScroll,
			-1,
			Boot.FrameH - Boot.DEV_HEIGHT - 39,
			Boot.DEV_WIDTH,
			Boot.DEV_HEIGHT);
		Log.CallMethodNoThread("setVisible",
			DevLabelScroll,
			false);
		Log.CallMethodNoThread(
			"setFont",
			DevLabel,
			DevFont);
		Log.CallMethodNoThread(
			"setForeground",
			DevLabel,
			Color.green);
		Log.CallMethodNoThread(
			"setViewport",
			DevLabelScroll,
			DevLabel.label);
		Log.CallMethodNoThread(
			"setBackground",
			DevLabelScroll,
			Color.black);
		/*Log.CallMethodNoThread(
			"add",
			DevPanel,
			DevLabelScroll.ScrollPane,
			BorderLayout.CENTER);*/
		//Log.CallMethodNoThread("add", DevScreen, DevPanel.Panel, BorderLayout.CENTER);
		Log.CallMethodNoThread(
			"add",
			Game,
			DevLabelScroll.ScrollPane,
			BorderLayout.CENTER);

		Title = new Canvas(0, 0, Boot.FrameW, Boot.FrameH);
		Log.CallMethodNoThread(
			"setBounds",
			Title,
			0,
			0,
			Boot.FrameW,
			Boot.FrameH);
		Log.CallMethodNoThread(
			"setVisible",
			Title,
			false);
		Log.CallMethod("add",
			Game,
			Title,
			BorderLayout.CENTER);

		Boot.title = new Game.Title(Game, Title);
		Boot.pause = new Pause(Game, Title);
		Boot.gameover = new GameOver(Game, Title);
		Boot.ranking = new Ranking(Game, Title);
	}

	@Override
	public void ChangedLog(String log)
	{
		OutputLog.add(
			log);

		var text = "<html>";
		for (String Log : OutputLog.List) {
			text += Log + "<br/><br/>";
		}
		text += "</html>";

		while (OutputLog.size() > 50) {
			OutputLog.remove(
				0);
		}

		if (DevLabel != null) {
			DevLabel.setText(
				text);
		}
		if (DevLabelScroll != null) {
			DevLabelScroll.ScrollPane.getVerticalScrollBar().setValue(
				DevLabelScroll.ScrollPane.getVerticalScrollBar().getMaximum());
		}
	}

}
