package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Game.Button.ButtonListener;
import Game.Canvas.PaintListener;
import Main.Array;
import Main.Boot;
import Main.Key;
import Window.Frame;

public class GameOver implements KeyListener, PaintListener
{
	Font PauseFont = new Font("ＭＳ ゴシック", Font.PLAIN, 50);

	Frame InFrame;
	Canvas PauseCanvas;

	int Selecting;
	String name;

	Array<Button> ButtonList;

	public GameOver(Frame InFrame, Canvas PauseCanvas)
	{
		this.InFrame = InFrame;
		this.PauseCanvas = PauseCanvas;
	}

	public void createGameOver()
	{
		name = "";
		ButtonList = new Array<>();
		ButtonList.List.clear();
		ButtonList.add(new Button("Restart", Restart));
		ButtonList.add(new Button("Back To Menu", Back_To_Menu));
		PauseCanvas.setVisible(true);
		InFrame.addKeyListener(this);
		PauseCanvas.addPaintListener(this);
	}

	public void destroy()
	{
		PauseCanvas.setVisible(false);
		InFrame.removeKeyListener(this);
		PauseCanvas.removePaintListener(this);
	}

	ButtonListener Restart = () ->
	{
		destroy();
		Boot.sys.createGame();
		Boot.sys.createSys();
	};

	ButtonListener Back_To_Menu = () ->
	{
		destroy();
		Boot.title.createTitle();
	};

	@Override
	public void Painted(Graphics2D g)
	{
		// TODO 自動生成されたメソッド・スタブ
		try {
			g.setFont(PauseFont);
			g.setColor(Color.green);
			var time = 0;
			for (Button draw : ButtonList.List) {
				g.drawString(draw.name,
					50,
					time * 100 + PauseCanvas.getHeight() / 2);
				time++;
			}
			g.drawString("Score:" + Boot.sys.player.Score,
				100,
				100);
			g.drawString("Name:" + name,
				100,
				200);
			g.fillOval(20,
				Selecting * 100 + PauseCanvas.getHeight() / 2 - 30,
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
		try {
			// TODO 自動生成されたメソッド・スタブ
			if (Key.Key[KeyEvent.VK_UP] && Selecting - 1 >= 0) {
				Selecting -= 1;
			}
			if (Key.Key[KeyEvent.VK_DOWN] && Selecting + 1 < ButtonList.size()) {
				Selecting += 1;
			}
			if (Key.Key[KeyEvent.VK_ENTER]) {
				if (!name.equals("")) {
					Boot.ranking.insertData(name,
						Boot.sys.player.Score);
				} else {
					Boot.ranking.insertData("No Name",
						Boot.sys.player.Score);
				}
				ButtonList.get(Selecting).callPushed();
			}
			if (Character.isLetterOrDigit(e.getKeyChar()) && name.length() < 15)
				name += e.getKeyChar();
			if (Key.Key[KeyEvent.VK_BACK_SPACE] && name.length() > 0) {
				name = name.substring(0,
					name.length() - 1);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		// TODO 自動生成されたメソッド・スタブ

	}
}
