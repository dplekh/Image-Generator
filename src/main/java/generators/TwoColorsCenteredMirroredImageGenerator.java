package generators;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import wordgenerators.EpicGenerator;


/**
 * Created by dplekhotkin on 1/14/21.
 */
public class TwoColorsCenteredMirroredImageGenerator implements ImageGenerator
{
	private static final int SECONDARY_COLOR_MAX = 255;
	private static final int SECONDARY_COLOR_MIN = SECONDARY_COLOR_MAX/2;

	private static final int BASE_COLOR_MAX = SECONDARY_COLOR_MIN;
	private static final int BASE_COLOR_MIN = 0;

	private static final int COLOR_MAX = 255;
	private static final int COLOR_MIN = 0;
	private static final int MIN_COLOR_SUM_DIFF = (COLOR_MAX-COLOR_MIN);
	private static final int MIN_COLOR_INDIVIDUAL_DIFF = (COLOR_MAX-COLOR_MIN)/4;

	private static final String FILE_EXTENSION = ".jpg";
	private static final int DIVISOR = 6;

	private String folder;
	private int divisor;
	private boolean isTiled;
	public TwoColorsCenteredMirroredImageGenerator(String folder) {
		this.folder = folder;
		this.divisor = DIVISOR;
		this.isTiled = false;
	}

	public TwoColorsCenteredMirroredImageGenerator(String folder, int divisor) {
		this(folder);
		this.divisor = divisor;
	}

	public TwoColorsCenteredMirroredImageGenerator(String folder, boolean isTiled) {
		this(folder);
		this.isTiled = isTiled;
	}


	public TwoColorsCenteredMirroredImageGenerator(String folder, int divisor, boolean isTiled) {
		this(folder);
		this.divisor = divisor;
		this.isTiled = isTiled;
	}



	private Color getBaseRandomColor()
	{
		Random random = new Random();
		int[] rgb = random.ints(3, BASE_COLOR_MIN, BASE_COLOR_MAX).toArray();
		return new Color(rgb[0], rgb[1], rgb[2]);
	}

	private Color getSecondaryRandomColor()
	{
		Random random = new Random();
		int[] rgb = random.ints(3, SECONDARY_COLOR_MIN, SECONDARY_COLOR_MAX).toArray();
		return new Color(rgb[0], rgb[1], rgb[2]);
	}





	@Override
	public void generate(final int size, final String filename) throws IOException
	{
		BufferedImage img = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
		BufferedImage img1 = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
		BufferedImage img2 = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
		BufferedImage img3 = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);

		int thumbnailSize = size/5;
		BufferedImage imgThumbnail = new BufferedImage(thumbnailSize, thumbnailSize, BufferedImage.TYPE_INT_RGB);
		BufferedImage img1Thumbnail = new BufferedImage(thumbnailSize, thumbnailSize, BufferedImage.TYPE_INT_RGB);
		BufferedImage img2Thumbnail = new BufferedImage(thumbnailSize, thumbnailSize, BufferedImage.TYPE_INT_RGB);
		BufferedImage img3Thumbnail = new BufferedImage(thumbnailSize, thumbnailSize, BufferedImage.TYPE_INT_RGB);


		Map<BufferedImage, Color[]> images = new HashMap<>();

		Color[] colors = getTwoColors();
		Color[] invColors = {colors[1], colors[0]};
		Color[] whiteBlack = {Color.WHITE, Color.BLACK};
		Color[] blackWhite = {Color.BLACK, Color.WHITE};
		images.put(img, colors);
		images.put(img1, blackWhite);
		images.put(img2, whiteBlack);
		images.put(img3, invColors);

		images.put(imgThumbnail, colors);
		images.put(img1Thumbnail, blackWhite);
		images.put(img2Thumbnail, whiteBlack);
		images.put(img3Thumbnail, invColors);

		int [][]colorMap = getColorMap();

		for (Map.Entry<BufferedImage, Color[]> image : images.entrySet())
		{
			paint(image.getKey(), colorMap, image.getValue());
		}

		ImageIO.write(img, "jpg", new File(folder + filename + FILE_EXTENSION));
		ImageIO.write(img, "jpg", new File(folder + filename + "-original"+ FILE_EXTENSION));
		ImageIO.write(img1, "jpg", new File(folder + filename + "-bw" + FILE_EXTENSION));
		ImageIO.write(img2, "jpg", new File(folder + filename + "-wb" + FILE_EXTENSION));
		ImageIO.write(img3, "jpg", new File(folder + filename + "-inv" + FILE_EXTENSION));

		ImageIO.write(imgThumbnail, "jpg", new File(folder + filename + "-thumbnail"+ FILE_EXTENSION));
		ImageIO.write(imgThumbnail, "jpg", new File(folder + filename + "-original" + "-thumbnail"+ FILE_EXTENSION));
		ImageIO.write(img1Thumbnail, "jpg", new File(folder + filename + "-bw" + "-thumbnail"+ FILE_EXTENSION));
		ImageIO.write(img2Thumbnail, "jpg", new File(folder + filename + "-wb" + "-thumbnail"+ FILE_EXTENSION));
		ImageIO.write(img3Thumbnail, "jpg", new File(folder + filename + "-inv" + "-thumbnail"+ FILE_EXTENSION));
	}

	private void paint(BufferedImage img, int[][] colorMap, Color[] colors) {
		final int step = img.getHeight()/divisor;

		Graphics2D g = img.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

		for (int i = 0; i < divisor/2; i++)
		{
			for (int j = 0; j < divisor; j++)
			{

				int colorIndex = colorMap[i][j];
				Color chosenColor = colors[colorIndex];
				g.setColor(chosenColor);

				if(isTiled) {
					g.fillRect(i*step-1, j*step-1, step, step);
				}
				else {
					g.fillRect(i*step, j*step, step, step);
				}
			}
		}

		for (int i = divisor/2; i < divisor; i++)
		{
			for (int j = 0; j < divisor; j++)
			{
				int colorIndex = colorMap[(divisor -1 - i)][j];

				Color chosenColor = colors[colorIndex];
				g.setColor(chosenColor);

				if(isTiled) {
					g.fillRect(i*step-1, j*step-1, step, step);
				}
				else {
					g.fillRect(i*step, j*step, step, step);
				}
			}
		}
	}

	private int[][] getColorMap() {

		int[][] colorMap = new int[divisor][divisor];

		Random colorChooser = new Random();
		for (int i = 0; i < divisor/2; i++)
		{
			for (int j = 0; j < divisor; j++)
			{
				int colorIndex = colorChooser.nextInt(2);
				if(j == 0 || i == 0 || j == divisor -1 || i == divisor -1) {
					colorIndex = 0;
				}
				colorMap[i][j] = colorIndex;
			}
		}

		return colorMap;
	}

	private Color[] getTwoColors() {
		Color color1, color2;
		int totalDiff, color1Sum, color2Sum, redDiff, greenDiff, blueDiff;
		do
		{
			Color[] colors = {getBaseRandomColor(), getSecondaryRandomColor()};
			Random baseOrSecondaryChooser = new Random();
			int baseOrSecondary = baseOrSecondaryChooser.nextInt(2);
			if(baseOrSecondary == 0) {
				color1 = colors[0];
				color2 = colors[1];
			} else{
				color1 = colors[1];
				color2 = colors[0];
			}

			color1Sum = color1.getRed() + color1.getGreen() + color1.getBlue();
			color2Sum = color2.getRed() + color2.getGreen() + color2.getBlue();
			redDiff = Math.abs(color1.getRed() - color2.getRed());
			greenDiff = Math.abs(color1.getGreen() - color2.getGreen());
			blueDiff = Math.abs(color1.getBlue() - color2.getBlue());

			totalDiff = Math.abs(color1Sum - color2Sum);
		}
		while (totalDiff < MIN_COLOR_SUM_DIFF || redDiff < MIN_COLOR_INDIVIDUAL_DIFF || greenDiff < MIN_COLOR_INDIVIDUAL_DIFF || blueDiff < MIN_COLOR_INDIVIDUAL_DIFF);

		Color[] colors = {color1, color2};
		return colors;
	}
}
