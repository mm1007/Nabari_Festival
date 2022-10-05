package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Key extends Boot implements KeyListener
{

	public static boolean[] Key = new boolean[KeyEvent.KEY_LAST];

	@Override
	public void keyTyped(KeyEvent e)
	{
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		// TODO 自動生成されたメソッド・スタブ
		Key[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		// TODO 自動生成されたメソッド・スタブ
		Key[e.getKeyCode()] = false;
	}

}
