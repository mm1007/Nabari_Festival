package Game;

import java.awt.Graphics;
import java.awt.Image;
import java.util.EventListener;

import javax.swing.JPanel;
import javax.swing.event.EventListenerList;

public class Canvas extends JPanel
{

	protected EventListenerList ELL = new EventListenerList();

	private Graphics offGraphics;
	private Image offImage;

	private int WIDTH;
	private int HEIGHT;

	public Canvas(int Width, int Height)
	{
		this.WIDTH = Width;
		this.HEIGHT = Height;
	}

	public void addPaintListener(PaintListener add)
	{
		ELL.add(PaintListener.class, add);
	}

	public void removePaintListener(PaintListener remove)
	{
		ELL.remove(PaintListener.class, remove);
	}

	@Override
	public void paintComponent(Graphics g)
	{
		this.offImage = createImage(WIDTH, HEIGHT);
		this.offGraphics = offImage.getGraphics();

		for (PaintListener paintListener : ELL.getListeners(PaintListener.class)) {
			paintListener.Painted(offGraphics);
		}

		g.drawImage(offImage, 0, 0, null);
	}

	interface PaintListener extends EventListener
	{

		public void Painted(Graphics g);

	}

}
