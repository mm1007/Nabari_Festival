package Game;

import Main.Boot;

public class Sys
{

	public Player player;

	public Sys()
	{
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public void createGame()
	{
		this.player = new Player(Boot.FPlayerX, Boot.FPlayerY);
	}

}
