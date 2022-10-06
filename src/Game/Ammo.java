package Game;

import java.awt.Color;
import java.awt.Graphics;

import Game.Canvas.PaintListener;
import Main.Boot;

public class Ammo implements PaintListener
{

	int AmmoX, AmmoY, AmmoSpeed;

	public Ammo(int AmmoX, int AmmoY, int AmmoSpeed)
	{
		this.AmmoX = AmmoX;
		this.AmmoY = AmmoY;
		this.AmmoSpeed = AmmoSpeed;

		Boot.canvas.addPaintListener(this);
	}

	public void move()
	{
		AmmoY -= AmmoSpeed;
	}

	@Override
	public void Painted(Graphics g)
	{
		g.setColor(Color.blue);
		g.fillRect(AmmoX, AmmoY, 10, 10);
	}

}
