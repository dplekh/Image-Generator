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
public class TwoColorsImageGenerator implements ImageGenerator
{

	// generateImage(size, color, type, path);

	private static final String FOLDER = "random/twoColors/";
	private static final String FILE_EXTENSION = ".jpg";
	private static final int DIVISOR = 10;

	public static void main(String[] args) throws IOException
	{
		ImageGenerator generator = new TwoColorsImageGenerator();
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
	public String generate(final int size) throws IOException
	{
		Color color1 = getRandomColor();
		Color color2 = getRandomColor();
		Color[] colors = {color1, color2};
		Random colorChooser = new Random();

		String uuidString = UUID.randomUUID().toString();
		BufferedImage img = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);

		Graphics g = img.createGraphics();
		final int step = size/DIVISOR;

		for (int i = 0; i < size; i+=step)
		{
			for (int j = 0; j < size; j+=step)
			{
				g.setColor(colors[colorChooser.nextInt(2)]);
				g.fillRect(i, j, step, step);
			}
		}

		ImageIO.write(img, "jpg", new File(FOLDER + uuidString + FILE_EXTENSION));
		return uuidString;
	}

	public void generate(final int size, final String filename) throws IOException
	{
		Color color1 = getRandomColor();
		Color color2 = getRandomColor();
		Color[] colors = {color1, color2};
		Random colorChooser = new Random();

		BufferedImage img = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);

		Graphics g = img.createGraphics();
		final int step = size/DIVISOR;

		for (int i = 0; i < size; i+=step)
		{
			for (int j = 0; j < size; j+=step)
			{
				g.setColor(colors[colorChooser.nextInt(2)]);
				g.fillRect(i, j, step, step);
			}
		}

		ImageIO.write(img, "jpg", new File(FOLDER + filename + FILE_EXTENSION));
	}
}
