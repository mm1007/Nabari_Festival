package Game;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player implements KeyListener
{

	public int PlayerX;
	public int PlayerY;

	public Player(int FPlayerX, int FPlayerY, Component KeyListener)
	{
		this.PlayerX = FPlayerX;
		this.PlayerY = FPlayerY;

		KeyListener.addKeyListener(this);
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		// TODO 自動生成されたメソッド・スタブ

	}

}
