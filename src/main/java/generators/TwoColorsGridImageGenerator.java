package generators;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;


/**
 * Created by dplekhotkin on 1/14/21.
 */
public class TwoColorsGridImageGenerator implements ImageGenerator
{

	// generateImage(size, color, type, path);

	private static final String FOLDER = "random/twoColorsGrid/";
	private static final String FILE_EXTENSION = ".jpg";
	private static final int DIVISOR = 10;

	public static void main(String[] args) throws IOException
	{
		ImageGenerator generator = new TwoColorsGridImageGenerator();
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


	/**
	 *
	 * @param size
	 * @return unique identifier of an image
	 */
	public void generate(final int size, String filename) throws IOException
	{
		BufferedImage img = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);

		Graphics g = img.createGraphics();
		final int step = size/DIVISOR;

		Color color1 = getRandomColor();
		Color color2 = getRandomColor();
		Color[] colors = {color1, color2};


		for (int i = 0; i < size; i+=step)
		{
			for (int j = 0; j < size; j+=step)
			{
				Color color = colors[chooseColor(i, j, step)];
				g.setColor(color);
				g.fillRect(i, j, step, step);
			}
		}

		ImageIO.write(img, "jpg", new File(FOLDER + filename + FILE_EXTENSION));
	}

	private int chooseColor(int i, int j, int step) {
		int a = i/step%2 == 0?0:1;
		int b = j/step%2 == 0?0:1;
		return a == b? 0:1;
	}

}
