package generators;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;


/**
 * Created by dplekhotkin on 1/14/21.
 */
public class GridImageGenerator implements ImageGenerator
{

	// generateImage(size, color, type, path);

	private static final String FOLDER = "random/grid/";
	private static final String FILE_EXTENSION = ".jpg";
	private static final int DIVISOR = 10;

	public static void main(String[] args) throws IOException
	{
		ImageGenerator generator = new GridImageGenerator();
		int size = 500;
		for (int i = 0; i < 100 ; i++)
		{
			generator.generate(size, String.valueOf(i));
		}
	}



	private Color getRandomColor() {
		Random random = new Random();
		return new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256));
	}

	public void generate(final int size, String filename) throws IOException
	{
		BufferedImage img = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);

		Graphics g = img.createGraphics();
		final int step = size/DIVISOR;

		for (int i = 0; i < size; i+=step)
		{
			for (int j = 0; j < size; j+=step)
			{
				Color color = getRandomColor();
				g.setColor(color);
				g.fillRect(i, j, step, step);
			}
		}

		ImageIO.write(img, "jpg", new File(FOLDER + filename + FILE_EXTENSION));
	}
}
