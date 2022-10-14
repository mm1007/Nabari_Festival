package Game;

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
