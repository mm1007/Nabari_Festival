package Game;

import java.awt.Color;
import java.awt.Graphics;

import Game.Canvas.PaintListener;
import Main.Boot;

public class Ammo implements PaintListener
{

	int AmmoX, AmmoY, AmmoSpeed;
	Color color;

	public Ammo(int AmmoX, int AmmoY, int AmmoSpeed, Color color)
	{
		this.AmmoX = AmmoX;
		this.AmmoY = AmmoY;
		this.AmmoSpeed = AmmoSpeed;
		this.color = color;

		//Boot.canvas.addPaintListener(this);
	}

	public void move()
	{
		AmmoY -= AmmoSpeed;
	}

	public void remove()
	{
		Boot.canvas.removePaintListener(this);
	}

	public void draw(Graphics g)
	{
		g.setColor(this.color);
		g.fillRect(AmmoX - 5, AmmoY - 5, 10, 10);
	}

	public void setCollision()
	{

	}

	public void collision()
	{

	}

	@Override
	public void Painted(Graphics g)
	{
		g.setColor(this.color);
		g.fillRect(AmmoX - 5, AmmoY - 5, 10, 10);
	}

}
