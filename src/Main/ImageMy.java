package Main;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

public class ImageMy
{

	public static Image getRotatedInstance(Image image, int arc)
	{
		Image RotateImage = image;
		Graphics2D Graphics = (Graphics2D) RotateImage.getGraphics();
		AffineTransform Rotate = Graphics.getTransform();
		Rotate.setToRotation(Math.toRadians(arc));
		Graphics.setTransform(Rotate);
		return RotateImage;
	}

}
