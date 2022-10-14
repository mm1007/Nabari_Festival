package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.EventListener;

import javax.swing.event.EventListenerList;

public class Key extends Boot implements KeyListener
{

	private static EventListenerList ELL = new EventListenerList();

	public static boolean[] Key = new boolean[KeyEvent.KEY_LAST];

	public static void addKeyListener(KeyListener add)
	{
		ELL.add(KeyListener.class,
			add);
	}

	public static void removeKeyListener(KeyListener remove)
	{
		ELL.remove(KeyListener.class,
			remove);
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
		Key[e.getKeyCode()] = true;
		for (KeyListener pressed : ELL.getListeners(KeyListener.class)) {
			pressed.KeyPressed();
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		// TODO 自動生成されたメソッド・スタブ
		Key[e.getKeyCode()] = false;
		for (KeyListener released : ELL.getListeners(KeyListener.class)) {
			released.KeyReleased();
		}
	}

	public interface KeyListener extends EventListener
	{
		public void KeyPressed();

		public void KeyReleased();
	}

}
