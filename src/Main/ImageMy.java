package Main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class ImageMy
{

	public static BufferedImage getRotatedInstance(BufferedImage image, double theta)
	{
		int W = image.getWidth();
		int H = image.getHeight();
		BufferedImage RotateImage = new BufferedImage(
			W,
			H,
			BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D Graphics = (Graphics2D) RotateImage.getGraphics();
		Graphics.rotate(Math.toDegrees(theta),
			W / 2,
			H / 2);
		Graphics.drawImage(image,
			0,
			0,
			null);
		return RotateImage;
	}

}
