package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Game.Canvas.PaintListener;
import Game.Title.Button.ButtonListener;
import Main.Array;
import Main.Boot;
import Main.Key;
import Window.Frame;

public class Title implements PaintListener, KeyListener
{
	Array<Button> ButtonList;

	Font TitleFont = new Font("ＭＳ ゴシック", Font.PLAIN, 50);

	BufferedImage Title;

	Canvas TitleCanvas;

	Frame InFrame;

	int Selecting = 0;

	public Title(Frame InFrame, Canvas TitleCanvas)
	{
		this.InFrame = InFrame;
		this.TitleCanvas = TitleCanvas;
	}

	public void createTitle() throws IOException
	{
		Title = ImageIO.read(new File(Boot.PATH + "\\data\\Title.png"));
		ButtonList = new Array<>();
		ButtonList.add(new Button("START", Started));
		ButtonList.add(new Button("EXIT", Exited));
		TitleCanvas.setVisible(true);
		InFrame.addKeyListener(this);
		TitleCanvas.addPaintListener(this);
	}

	public void destroy()
	{
		TitleCanvas.setVisible(false);
		InFrame.removeKeyListener(this);
		TitleCanvas.removePaintListener(this);
	}

	private ButtonListener Started = () ->
	{
		destroy();
		Boot.sys.createGame(Boot.elements.Game,
			Boot.canvas,
			Boot.timer);
	};

	private ButtonListener Exited = () ->
	{

	};

	public class Button
	{
		String name;
		ButtonListener Listener;

		public Button(String name, ButtonListener Listener)
		{
			this.name = name;
			this.Listener = Listener;
		}

		public void callPushed()
		{
			Listener.Pushed();
		}

		interface ButtonListener
		{
			void Pushed();
		}
	}

	@Override
	public void Painted(Graphics2D g)
	{
		try {
			g.setFont(TitleFont);
			g.setColor(Color.green);
			g.drawImage(Title,
				TitleCanvas.getWidth() / 2 - Title.getWidth() / 2,
				0,
				null);
			var time = 0;
			for (Button draw : ButtonList.List) {
				Canvas.drawCenter(g,
					draw.name,
					TitleCanvas.getWidth() / 2,
					time * 100 + TitleCanvas.getHeight() / 2);
				time++;
			}
			g.fillOval(TitleCanvas.getWidth() / 2 - 100,
				Selecting * 100 + TitleCanvas.getHeight() / 2 - 10,
				20,
				20);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		System.out.println("push");
		if (Key.Key[KeyEvent.VK_UP] && Selecting - 1 >= 0) {
			Selecting -= 1;
		}
		if (Key.Key[KeyEvent.VK_DOWN] && Selecting + 1 < ButtonList.size()) {
			Selecting += 1;
		}
		if (Key.Key[KeyEvent.VK_Z]) {
			ButtonList.get(Selecting).callPushed();
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		// TODO 自動生成されたメソッド・スタブ

	}

}
