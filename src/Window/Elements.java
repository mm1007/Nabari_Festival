package Window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;

import Main.Array;
import Main.Boot;
import Reflection.Log;
import Reflection.Log.LogListener;

public class Elements implements LogListener
{
	private final Font DevFont = new Font("ＭＳ ゴシック", Font.PLAIN, 20);
	private int BeforeReload = 0;

	private Array<String> OutputLog = new Array<String>();

	public Frame Game;
	public Frame DevScreen;

	public Panel DevPanel;
	public Label DevLabel;
	public ScrollPane DevLabelScroll;

	public Elements()
		throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException
	{
		Log.addLogListener(this);
	}

	public void createElement()
		throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException
	{
		this.Game = new Frame();
		Log.CallMethodNoThread("setBounds", Game, Boot.DEV_WIDTH, 0, Boot.FrameW, Boot.FrameH);
		Log.CallMethodNoThread("setWindowName", Game, "ゲーム画面");
		Log.CallMethodNoThread("setCloseOp", Game, JFrame.EXIT_ON_CLOSE);
		Log.CallMethodNoThread("setResizable", Game, false);
		Log.CallMethodNoThread("setLayoutNull", Game);
		Log.CallMethodNoThread("add", Game, Boot.canvas, BorderLayout.CENTER);
		Log.CallMethodNoThread("addKeyListener", Game, Boot.key);
		Log.CallMethodNoThread("setVisible", Game, true);

		this.DevScreen = new Frame();
		Log.CallMethodNoThread("setBounds", DevScreen, 0, 0, Boot.DEV_WIDTH, Boot.DEV_HEIGHT);
		Log.CallMethodNoThread("setWindowName", DevScreen, "メソッド履歴");
		Log.CallMethodNoThread("setCloseOp", DevScreen, JFrame.DO_NOTHING_ON_CLOSE);
		Log.CallMethodNoThread("setResizable", DevScreen, false);
		// Log.CallMethodNoThread("setLayoutNull", DevScreen);
		Log.CallMethodNoThread("setVisible", DevScreen, true);

		DevPanel = new Panel();
		DevLabel = new Label();
		DevLabelScroll = new ScrollPane();
		Log.CallMethodNoThread("setBounds", DevPanel, -1, 0, Boot.DEV_WIDTH, Boot.DEV_HEIGHT);
		Log.CallMethodNoThread("setBounds", DevLabel, 0, 0, Boot.DEV_WIDTH, Boot.DEV_HEIGHT);
		Log.CallMethodNoThread("setBounds", DevLabelScroll, 0, 0, Boot.DEV_WIDTH - 10, Boot.DEV_HEIGHT - 20);
		Log.CallMethodNoThread("setFont", DevLabel, DevFont);
		Log.CallMethodNoThread("setForeground", DevLabel, Color.green);
		Log.CallMethodNoThread("setViewport", DevLabelScroll, DevLabel.label);
		Log.CallMethodNoThread("setBackground", DevLabelScroll, Color.black);
		Log.CallMethodNoThread("add", DevPanel, DevLabelScroll.ScrollPane, BorderLayout.CENTER);
		Log.CallMethodNoThread("add", DevScreen, DevPanel.Panel, BorderLayout.CENTER);
		Log.CallMethodNoThread("add", Game, DevPanel.Panel, BorderLayout.CENTER);
	}

	@Override
	public void ChangedLog(String log)
	{
		OutputLog.add(log);

		var text = "<html>";
		for (String Log : OutputLog.List) {
			text += Log + "<br/><br/>";
		}
		text += "</html>";

		while (OutputLog.size() > 50) {
			OutputLog.remove(0);
		}

		if (DevLabel != null) {
			DevLabel.setText(text);
			BeforeReload = Boot.timer.Frame;
		}
		if (DevLabelScroll != null) {
			DevLabelScroll.ScrollPane.getVerticalScrollBar().setValue(
				DevLabelScroll.ScrollPane.getVerticalScrollBar().getMaximum());
		}
	}

}
