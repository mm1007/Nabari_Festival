package Game;

import java.awt.Graphics;
import java.awt.Image;

import Main.Array;

public class Ammo
{

	Array<Entity> CollisionList = new Array<Entity>();

	int AmmoX, AmmoY, AmmoSpeed;
	int AmmoW;
	int AmmoH;
	Image Tex;

	public Ammo(int AmmoX, int AmmoY, int AmmoSpeed, Image Tex)
	{
		this.AmmoX = AmmoX;
		this.AmmoY = AmmoY;
		this.AmmoSpeed = AmmoSpeed;
		this.Tex = Tex;
		this.AmmoW = Tex.getWidth(null);
		this.AmmoH = Tex.getHeight(null);
		// Boot.canvas.addPaintListener(this);
	}

	public void move()
	{
		AmmoY -= AmmoSpeed;
	}

	public void draw(Graphics g)
	{
		g.drawImage(Tex, AmmoX - AmmoW / 2, AmmoY - AmmoH / 2, null);
	}

}
