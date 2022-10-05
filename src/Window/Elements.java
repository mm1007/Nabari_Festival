package Window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;

import Main.Boot;
import Reflection.Log;
import Reflection.Log.LogListener;

public class Elements implements LogListener
{
	private final Font DevFont = new Font("ＭＳ ゴシック", Font.PLAIN, 15);

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
		Log.CallMethod("setBounds", Game, Boot.DEV_WIDTH, 0, Boot.WIDTH, Boot.HEIGHT);
		Log.CallMethod("setWindowName", Game, "プログラミング体験");
		Log.CallMethod("setCloseOp", Game, JFrame.EXIT_ON_CLOSE);
		Log.CallMethod("setResizable", Game, false);
		Log.CallMethod("setLayoutNull", Game);
		Log.CallMethod("add", Game, Boot.canvas, BorderLayout.CENTER);
		Log.CallMethod("addKeyListener", Game, Boot.key);
		Log.CallMethod("setVisible", Game, true);

		this.DevScreen = new Frame();
		Log.CallMethod("setBounds", DevScreen, 0, 0, Boot.DEV_WIDTH, Boot.DEV_HEIGHT);
		Log.CallMethod("setWindowName", DevScreen, "Developer");
		Log.CallMethod("setCloseOp", DevScreen, JFrame.DO_NOTHING_ON_CLOSE);
		Log.CallMethod("setResizable", DevScreen, false);
		//Log.CallMethod("setLayoutNull", DevScreen);
		Log.CallMethod("setVisible", DevScreen, true);

		DevPanel = new Panel();
		DevLabel = new Label();
		DevLabelScroll = new ScrollPane();
		Log.CallMethod("setBounds", DevPanel, -3, 0, Boot.DEV_WIDTH, Boot.DEV_HEIGHT);
		Log.CallMethod("setBounds", DevLabel, 0, 0, Boot.DEV_WIDTH, Boot.DEV_HEIGHT);
		Log.CallMethod("setBounds", DevLabelScroll, 0, 0, Boot.DEV_WIDTH - 10, Boot.DEV_HEIGHT - 20);
		Log.CallMethod("setFont", DevLabel, DevFont);
		Log.CallMethod("setForeground", DevLabel, Color.green);
		Log.CallMethod("setViewport", DevLabelScroll, DevLabel.label);
		Log.CallMethod("setBackground", DevLabelScroll, Color.black);
		Log.CallMethod("add", DevPanel, DevLabelScroll.ScrollPane, BorderLayout.CENTER);
		DevLabelScroll.ScrollPane.getVerticalScrollBar().addAdjustmentListener(e ->
		{
			e.getAdjustable().setValue(e.getAdjustable().getMaximum());
		});
		Log.CallMethod("add", DevScreen, DevPanel.Panel, BorderLayout.CENTER);
	}

	@Override
	public void ChangedLog(String log)
	{
		String text = "<html>" + log + "</html>";

		if (DevLabel != null)
			DevLabel.setText(text);
	}

}
