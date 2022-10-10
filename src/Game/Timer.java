package Game;

import java.awt.event.ActionListener;
import java.util.EventListener;

import javax.swing.event.EventListenerList;

public class Timer
{
	public int Frame = 0;

	protected EventListenerList ELL = new EventListenerList();

	private javax.swing.Timer timer;

	public Timer()
	{
		this.timer = new javax.swing.Timer(10, event);
		//this.timer.start();
	}

	public void addTimerListener(TimerListener add)
	{
		this.ELL.add(TimerListener.class, add);
	}

	public void removeTimerListener(TimerListener remove)
	{
		this.ELL.remove(TimerListener.class, remove);
	}

	public void start()
	{
		timer.start();
	}

	ActionListener event = e ->
	{
		for (TimerListener listener : this.ELL.getListeners(TimerListener.class)) {
			try {
				listener.TimerEvent();
				//Log.CallMethod("TimerEvent", listener);
			} catch (Exception e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}
			//listener.TimerEvent();
		}
		Frame++;
	};

	public interface TimerListener extends EventListener
	{
		public void TimerEvent();
	}

}
