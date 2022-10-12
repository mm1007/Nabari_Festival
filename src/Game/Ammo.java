package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import Main.Array;
import Main.Boot;

public class Ammo
{
	public final static int ONLYMOVEY = 0;
	public final static int R_ONLYMOVEY = 1;
	public final static int ONLYMOVEX = 2;
	public final static int R_ONLYMOVEX = 3;
	public final static int TRACKING = 4;

	Array<Entity> CollisionList = new Array<Entity>();

	double AmmoX, AmmoY, AmmoSpeed;
	int AmmoW;
	int AmmoH;
	int MovePattern = ONLYMOVEY;
	Image Tex;

	Entity TrackingEntity = Boot.sys.player;
	double Radian = Math.toRadians(
		0);
	double RealRadian = 0;

	public Ammo(int AmmoX, int AmmoY, int AmmoSpeed, Image Tex)
	{
		this.AmmoX = AmmoX;
		this.AmmoY = AmmoY;
		this.AmmoSpeed = AmmoSpeed;
		this.Tex = Tex;
		this.AmmoW = Tex.getWidth(
			null);
		this.AmmoH = Tex.getHeight(
			null);
		// Boot.canvas.addPaintListener(this);
	}

	public void setMovePatturn(int MovePatturn)
	{
		this.MovePattern = MovePatturn;
	}

	public void setTrackingEntity(Entity set)
	{
		this.TrackingEntity = set;
	}

	public void move()
	{
		switch (MovePattern) {
		case ONLYMOVEY:
			AmmoY -= AmmoSpeed;
			break;
		case R_ONLYMOVEY:
			AmmoY += AmmoSpeed;
			break;
		case ONLYMOVEX:
			AmmoX += AmmoSpeed;
			break;
		case R_ONLYMOVEX:
			AmmoX -= AmmoSpeed;
			break;
		case TRACKING:
			if (TrackingEntity == null)
				break;
			double DistanceX = TrackingEntity.X - AmmoX;
			double DistanceY = TrackingEntity.Y - AmmoY;
			RealRadian = Math.atan2(DistanceY,
				DistanceX);

			if (Radian > RealRadian)
				Radian -= Math.toRadians(1);
			else if (Radian < RealRadian)
				Radian += Math.toRadians(1);
			else {
			}
			AmmoX -= Math.cos(
				Radian) * AmmoSpeed;
			AmmoY -= Math.sin(
				Radian) * AmmoSpeed;
			break;
		}
	}

	public void draw(Graphics g)
	{
		g.drawImage(
			Tex,
			(int) AmmoX - AmmoW / 2,
			(int) AmmoY - AmmoH / 2,
			null);
		g.setColor(Color.green);
		g.drawLine(
			(int) (Math.cos(Radian) * 50 + AmmoX),
			(int) (Math.sin(Radian) * 50 + AmmoY),
			(int) AmmoX,
			(int) AmmoY);
		g.setColor(Color.yellow);
		g.drawLine(
			(int) (Math.cos(RealRadian) * 50 + AmmoX),
			(int) (Math.sin(RealRadian) * 50 + AmmoY),
			(int) AmmoX,
			(int) AmmoY);
		g.setColor(new Color(0, 255, 0, 60));
		g.drawString("Angle:" + (int) Math.toDegrees(RealRadian) + "°",
			(int) AmmoX + 20,
			(int) AmmoY + 20);
		g.drawString("TargetAngle:" + (int) Math.toDegrees(RealRadian) + "°",
			(int) AmmoX + 20,
			(int) AmmoY + 40);
	}

}
