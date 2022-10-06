package Reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.EventListener;

import javax.swing.event.EventListenerList;

public class Log
{

	private static EventListenerList ELL = new EventListenerList();

	public static String Log = "";

	public static void CallMethod(String MethodName, Object Instance, Object... args)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException
	{
		Thread thread = new Thread(() ->
		{
			try {
				if (Instance == null)
					return;
				for (Method var : Instance.getClass().getMethods()) {
					if (!(var.getName().equals(MethodName)))
						continue;
					if (args != null)
						var.invoke(Instance, args);
					else
						var.invoke(Instance);
					Log += "メソッドを呼び出しました -> " + var.getDeclaringClass().getName() + "." + var.getName() + "()"
							+ "<br/>";
					for (LogListener Listener : ELL.getListeners(LogListener.class))
						Listener.ChangedLog(Log);
					return;
				}
			} catch (Exception e) {

			}
		});
		thread.start();
	}

	public static void CallMethodNoThread(String MethodName, Object Instance, Object... args)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException
	{
		try {
			if (Instance == null)
				return;
			for (Method var : Instance.getClass().getMethods()) {
				if (!(var.getName().equals(MethodName)))
					continue;
				if (args != null)
					var.invoke(Instance, args);
				else
					var.invoke(Instance);
				Log += "メソッドを呼び出しました -> " + var.getDeclaringClass().getName() + "." + var.getName() + "()"
						+ "<br/>";
				for (LogListener Listener : ELL.getListeners(LogListener.class))
					Listener.ChangedLog(Log);
				return;
			}
		} catch (Exception e) {
		}
	}

	public static void addLogListener(LogListener add)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException
	{
		CallMethod("add", ELL, LogListener.class, add);
		//ELL.add(LogListener.class, add);
	}

	public static void removeLogListener(LogListener remove)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException
	{
		CallMethod("remove", ELL, LogListener.class, remove);
		//ELL.remove(LogListener.class, remove);
	}

	public interface LogListener extends EventListener
	{
		public void ChangedLog(String log);
	}

}