package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.EventListener;

import javax.swing.JPanel;
import javax.swing.event.EventListenerList;

import Game.Timer.TimerListener;
import Main.Boot;

public class Canvas extends JPanel implements TimerListener
{

	protected EventListenerList ELL = new EventListenerList();

	private Graphics2D offGraphics;
	private Image offImage;

	private int WIDTH;
	private int HEIGHT;

	public Canvas(int x, int y, int Width, int Height)
	{
		this.WIDTH = Width;
		this.HEIGHT = Height;

		setBounds(
			x,
			y,
			Width,
			Height);

		Boot.timer.addTimerListener(
			this);
	}

	public void addPaintListener(PaintListener add)
	{
		ELL.add(
			PaintListener.class,
			add);
	}

	public void removePaintListener(PaintListener remove)
	{
		ELL.remove(
			PaintListener.class,
			remove);
	}

	@Override
	public void paintComponent(Graphics g)
	{
		this.offImage = createImage(
			WIDTH,
			HEIGHT);
		this.offGraphics = (Graphics2D) offImage.getGraphics();

		offGraphics.setColor(
			Color.black);
		offGraphics.fillRect(
			0,
			0,
			WIDTH,
			HEIGHT);

		for (PaintListener paintListener : this.ELL.getListeners(
			PaintListener.class)) {
			paintListener.Painted(
				offGraphics);
		}

		g.drawImage(
			offImage,
			0,
			0,
			null);
	}

	@Override
	public void update(Graphics g)
	{
		paintComponent(
			g);
	}

	interface PaintListener extends EventListener
	{
		public void Painted(Graphics2D g);
	}

	@Override
	public void TimerEvent()
	{
		// TODO 自動生成されたメソッド・スタブ
		repaint();
	}

}
