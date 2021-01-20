import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.arcticicestudio.lumio.Lumio;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;


/**
 * Created by dplekhotkin on 12/17/20.
 */
public class Main
{
	private static final Map<String, String> colors = new LinkedHashMap<String, String>();

	private static final Map<String, String> lumioColors = new LinkedHashMap<String, String>();

	private static final java.util.List<Integer> sizes = Arrays.asList(400, 300, 200, 100, 50);

	static
	{
		colors.put("000000", "Black");
		colors.put("000080", "Navy");
		colors.put("00008B", "DarkBlue");
		colors.put("0000CD", "MediumBlue");
		colors.put("0000FF", "Blue");
		colors.put("006400", "DarkGreen");
		colors.put("008000", "Green");
		colors.put("008080", "Teal");
		colors.put("008B8B", "DarkCyan");
		colors.put("00BFFF", "DeepSkyBlue");
		colors.put("00CED1", "DarkTurquoise");
		colors.put("00FA9A", "MediumSpringGreen");
		colors.put("00FF00", "Lime");
		colors.put("00FF7F", "SpringGreen");
		colors.put("00FFFF", "Aqua");
		colors.put("00FFFF", "Cyan");
		colors.put("191970", "MidnightBlue");
		colors.put("1E90FF", "DodgerBlue");
		colors.put("20B2AA", "LightSeaGreen");
		colors.put("228B22", "ForestGreen");
		colors.put("2E8B57", "SeaGreen");
		colors.put("2F4F4F", "DarkSlateGray");
		colors.put("2F4F4F", "DarkSlateGrey");
		colors.put("32CD32", "LimeGreen");
		colors.put("3CB371", "MediumSeaGreen");
		colors.put("40E0D0", "Turquoise");
		colors.put("4169E1", "RoyalBlue");
		colors.put("4682B4", "SteelBlue");
		colors.put("483D8B", "DarkSlateBlue");
		colors.put("48D1CC", "MediumTurquoise");
		colors.put("4B0082", "Indigo");
		colors.put("556B2F", "DarkOliveGreen");
		colors.put("5F9EA0", "CadetBlue");
		colors.put("6495ED", "CornflowerBlue");
		colors.put("663399", "RebeccaPurple");
		colors.put("66CDAA", "MediumAquaMarine");
		colors.put("696969", "DimGray");
		colors.put("696969", "DimGrey");
		colors.put("6A5ACD", "SlateBlue");
		colors.put("6B8E23", "OliveDrab");
		colors.put("708090", "SlateGray");
		colors.put("708090", "SlateGrey");
		colors.put("778899", "LightSlateGray");
		colors.put("778899", "LightSlateGrey");
		colors.put("7B68EE", "MediumSlateBlue");
		colors.put("7CFC00", "LawnGreen");
		colors.put("7FFF00", "Chartreuse");
		colors.put("7FFFD4", "Aquamarine");
		colors.put("800000", "Maroon");
		colors.put("800080", "Purple");
		colors.put("808000", "Olive");
		colors.put("808080", "Gray");
		colors.put("808080", "Grey");
		colors.put("87CEEB", "SkyBlue");
		colors.put("87CEFA", "LightSkyBlue");
		colors.put("8A2BE2", "BlueViolet");
		colors.put("8B0000", "DarkRed");
		colors.put("8B008B", "DarkMagenta");
		colors.put("8B4513", "SaddleBrown");
		colors.put("8FBC8F", "DarkSeaGreen");
		colors.put("90EE90", "LightGreen");
		colors.put("9370DB", "MediumPurple");
		colors.put("9400D3", "DarkViolet");
		colors.put("98FB98", "PaleGreen");
		colors.put("9932CC", "DarkOrchid");
		colors.put("9ACD32", "YellowGreen");
		colors.put("A0522D", "Sienna");
		colors.put("A52A2A", "Brown");
		colors.put("A9A9A9", "DarkGray");
		colors.put("A9A9A9", "DarkGrey");
		colors.put("ADD8E6", "LightBlue");
		colors.put("ADFF2F", "GreenYellow");
		colors.put("AFEEEE", "PaleTurquoise");
		colors.put("B0C4DE", "LightSteelBlue");
		colors.put("B0E0E6", "PowderBlue");
		colors.put("B22222", "FireBrick");
		colors.put("B8860B", "DarkGoldenRod");
		colors.put("BA55D3", "MediumOrchid");
		colors.put("BC8F8F", "RosyBrown");
		colors.put("BDB76B", "DarkKhaki");
		colors.put("C0C0C0", "Silver");
		colors.put("C71585", "MediumVioletRed");
		colors.put("CD5C5C", "IndianRed");
		colors.put("CD853F", "Peru");
		colors.put("D2691E", "Chocolate");
		colors.put("D2B48C", "Tan");
		colors.put("D3D3D3", "LightGray");
		colors.put("D3D3D3", "LightGrey");
		colors.put("D8BFD8", "Thistle");
		colors.put("DA70D6", "Orchid");
		colors.put("DAA520", "GoldenRod");
		colors.put("DB7093", "PaleVioletRed");
		colors.put("DC143C", "Crimson");
		colors.put("DCDCDC", "Gainsboro");
		colors.put("DDA0DD", "Plum");
		colors.put("DEB887", "BurlyWood");
		colors.put("E0FFFF", "LightCyan");
		colors.put("E6E6FA", "Lavender");
		colors.put("E9967A", "DarkSalmon");
		colors.put("EE82EE", "Violet");
		colors.put("EEE8AA", "PaleGoldenRod");
		colors.put("F08080", "LightCoral");
		colors.put("F0E68C", "Khaki");
		colors.put("F0F8FF", "AliceBlue");
		colors.put("F0FFF0", "HoneyDew");
		colors.put("F0FFFF", "Azure");
		colors.put("F4A460", "SandyBrown");
		colors.put("F5DEB3", "Wheat");
		colors.put("F5F5DC", "Beige");
		colors.put("F5F5F5", "WhiteSmoke");
		colors.put("F5FFFA", "MintCream");
		colors.put("F8F8FF", "GhostWhite");
		colors.put("FA8072", "Salmon");
		colors.put("FAEBD7", "AntiqueWhite");
		colors.put("FAF0E6", "Linen");
		colors.put("FAFAD2", "LightGoldenRodYellow");
		colors.put("FDF5E6", "OldLace");
		colors.put("FF0000", "Red");
		colors.put("FF00FF", "Fuchsia");
		colors.put("FF00FF", "Magenta");
		colors.put("FF1493", "DeepPink");
		colors.put("FF4500", "OrangeRed");
		colors.put("FF6347", "Tomato");
		colors.put("FF69B4", "HotPink");
		colors.put("FF7F50", "Coral");
		colors.put("FF8C00", "DarkOrange");
		colors.put("FFA07A", "LightSalmon");
		colors.put("FFA500", "Orange");
		colors.put("FFB6C1", "LightPink");
		colors.put("FFC0CB", "Pink");
		colors.put("FFD700", "Gold");
		colors.put("FFDAB9", "PeachPuff");
		colors.put("FFDEAD", "NavajoWhite");
		colors.put("FFE4B5", "Moccasin");
		colors.put("FFE4C4", "Bisque");
		colors.put("FFE4E1", "MistyRose");
		colors.put("FFEBCD", "BlanchedAlmond");
		colors.put("FFEFD5", "PapayaWhip");
		colors.put("FFF0F5", "LavenderBlush");
		colors.put("FFF5EE", "SeaShell");
		colors.put("FFF8DC", "Cornsilk");
		colors.put("FFFACD", "LemonChiffon");
		colors.put("FFFAF0", "FloralWhite");
		colors.put("FFFAFA", "Snow");
		colors.put("FFFF00", "Yellow");
		colors.put("FFFFE0", "LightYellow");
		colors.put("FFFFF0", "Ivory");
		colors.put("FFFFFF", "White");
	}

	static
	{

		for (int i = 0; i < Lumio.values().length; i++)
		{

			String name = "";

			if (i <= 4)
			{
				name = "Rough Coal";
			}
			else if (i <= 13)
			{
				name = "Soft Earth";
			}
			else if (i <= 17)
			{
				name = "Hard Metal";
			}
			else if (i <= 21)
			{
				name = "Piquant Olive";
			}
			else if (i <= 25)
			{
				name = "Cold Snow";
			}
			else if (i <= 34)
			{
				name = "Clear Water";
			}
			else if (i <= 40)
			{
				name = "Hot Flame";
			}
			else if (i <= 46)
			{
				name = "Roasted Cocoa Bean";
			}
			else if (i <= 51)
			{
				name = "Tropical Wood";
			}
			else if (i <= 56)
			{
				name = "Fine Sand";
			}
			else if (i <= 60)
			{
				name = "Sweet Banana";
			}
			else if (i <= 65)
			{
				name = "Calm Sunset";
			}
			else if (i <= 70)
			{
				name = "Living Forest";
			}
			else if (i <= 75)
			{
				name = "Deep Sea";
			}
			else if (i <= 80)
			{
				name = "Fresh Wild Berries";
			}

			name += " " + i;
			String hex = Lumio.hex(Lumio.valueOf("LUMIO" + i).get());
			if(i == 58) {
				hex = "#B28143";
			}

			hex=hex.replace("#", "");

			lumioColors.put(hex, name);
		}
	}


	public static void main(String[] args) throws IOException
	{
		generateColorFiles();
		generateProductFiles();
		generateCategoryToCategoryFiles();
		generateCategoryFiles();
		generateProductToCategoryFiles();
		generateInventory();

		//
		generateSizeVariantColorFiles();
		generateProductFilesForVariants();
		//generateProductVariantsToCategoryFiles();
		generateProductVariantsInventory();
	}

	private static void generateSizeVariantColorFiles() throws IOException
	{
		for (Integer size : sizes)
		{
			for (String color : colors.keySet())
			{
				Color rgb = hex2Rgb(color);
				BufferedImage img = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);

				Graphics g = img.createGraphics();
				g.setColor(rgb);
				g.fillRect(0, 0, size, size); //add some nice border


				ImageIO.write(img, "jpg", new File("data/colors/"+color+ size +".jpg"));
			}
		}

	}

	private static void generateProductFilesForVariants() throws IOException
	{
		final String outputFile = "data/colorVariants.csv";
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader("id", "sku", "name", "sellingPrice", "fullSize", "thumbnail", "active", "published", "type", "parentId", "variantAttributes");
		FileWriter fileWriter = new FileWriter(outputFile);
		CSVPrinter csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);

		Set<Map.Entry<String, String>> entries = new LinkedHashSet<Map.Entry<String, String>>();
		entries.addAll(colors.entrySet());
		entries.addAll(lumioColors.entrySet());

		for (Integer size : sizes)
		{

			for (Map.Entry<String, String> color : colors.entrySet())
			{
				String id = color.getKey() + size;
				String parentId = color.getKey();
				String sku = id + "0000";
				String name = color.getValue() + " " + size;
				int price = randomPrice();
				String fullSize = "https://color-project.surge.sh/colors/" + id + ".jpg";
				String thumbnail = fullSize;
				boolean active = true;
				boolean published = true;
				String type = "VARIANT";
				String variantAttributes = size + "|" + fullSize + "|" + id + "|" + size;
				csvFilePrinter
						.printRecord(id, sku, name, price, fullSize, thumbnail, active, published, type, parentId, variantAttributes);
			}

			for (Map.Entry<String, String> color : lumioColors.entrySet())
			{
				String id = color.getKey() + size;
				String parentId = "";
				String sku = id + "0000";
				String name = color.getValue() + " " + size;
				int price = randomPrice();
				String fullSize = "";
				String thumbnail = fullSize;
				boolean active = false;
				boolean published = false;
				String type = "";
				String variantAttributes = "";
				csvFilePrinter
						.printRecord(id, sku, name, price, fullSize, thumbnail, active, published, type, parentId, variantAttributes);
			}

		}

		csvFilePrinter.flush();


		fileWriter.flush();
		fileWriter.close();
		csvFilePrinter.close();

	}

	private static void generateProductFiles() throws IOException
	{
		final String outputFile = "data/colors.csv";
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader("id", "sku", "name", "sellingPrice", "fullSize", "thumbnail", "active", "published", "type");
		FileWriter fileWriter = new FileWriter(outputFile);
		CSVPrinter csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);

		for (Map.Entry<String, String> color : colors.entrySet())
		{
			String id = color.getKey();
			String sku = id + "0000";
			String name = color.getValue();
			int price = randomPrice();
			String fullSize = "https://color-project.surge.sh/colors/"+id + ".jpg";
			String thumbnail = fullSize;
			boolean active = true;
			boolean published = true;
			String type = "PARENT";
			csvFilePrinter.printRecord(id, sku, name, price, fullSize, thumbnail, active, published, type);
		}

		for (Map.Entry<String, String> color : lumioColors.entrySet())
		{
			String id = color.getKey();
			String sku = id + "0000";
			String name = color.getValue();
			int price = randomPrice();
			String fullSize = "https://color-project.surge.sh/colors/"+id + ".jpg";
			String thumbnail = fullSize;
			boolean active = true;
			boolean published = true;
			String type = "REGULAR";
			csvFilePrinter.printRecord(id, sku, name, price, fullSize, thumbnail, active, published, type);
		}


		csvFilePrinter.flush();


		fileWriter.flush();
		fileWriter.close();
		csvFilePrinter.close();
	}

	private static void generateInventory() throws IOException
	{
		final String outputFile = "data/inventory.csv";
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader("fulfillmentLocationId", "onHand", "productId");
		FileWriter fileWriter = new FileWriter(outputFile);
		CSVPrinter csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);



		for (String color : colors.keySet())
		{

			String productId = color;
			String fulfillmentLocationId = "color_warehouse_1";
			int onHand = onHand();

			csvFilePrinter.printRecord(fulfillmentLocationId, onHand, productId);

		}

		for (String color : lumioColors.keySet())
		{

			String productId = color;
			String fulfillmentLocationId = "color_warehouse_1";
			int onHand = onHand();

			csvFilePrinter.printRecord(fulfillmentLocationId, onHand, productId);

		}

		csvFilePrinter.flush();


		fileWriter.flush();
		fileWriter.close();
		csvFilePrinter.close();
	}

	private static void generateProductVariantsInventory() throws IOException
	{
		final String outputFile = "data/variantsInventory.csv";
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader("fulfillmentLocationId", "onHand", "productId");
		FileWriter fileWriter = new FileWriter(outputFile);
		CSVPrinter csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);


		for (Integer size : sizes)
		{


			for (String color : colors.keySet())
			{

				String productId = color+size;
				String fulfillmentLocationId = "color_warehouse_1";
				int onHand = onHand();

				csvFilePrinter.printRecord(fulfillmentLocationId, onHand, productId);

			}
		}

		csvFilePrinter.flush();


		fileWriter.flush();
		fileWriter.close();
		csvFilePrinter.close();
	}

	private static void generateCategoryFiles() throws IOException
	{
		final String outputFile = "data/categories.csv";
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader("id", "name", "active", "published");
		FileWriter fileWriter = new FileWriter(outputFile);
		CSVPrinter csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);

		boolean active = true;
		boolean published = true;
		csvFilePrinter.printRecord("html", "HTML", active, published);
		csvFilePrinter.printRecord("htmlWarm", "Warm", active, published);
		csvFilePrinter.printRecord("htmlCool", "Cool", active, published);
		csvFilePrinter.printRecord("htmlDark", "Dark", active, published);
		csvFilePrinter.printRecord("htmlLight", "Light", active, published);
		csvFilePrinter.printRecord("lumio", "Lumio", active, published);
		csvFilePrinter.printRecord("roughCoal", "Rough Coal", active, published);
		csvFilePrinter.printRecord("softEarth", "Soft Earth", active, published);
		csvFilePrinter.printRecord("hardMetal", "Hard Metal", active, published);
		csvFilePrinter.printRecord("piquantOlive", "Piquant Olive", active, published);
		csvFilePrinter.printRecord("coldSnow", "Cold Snow", active, published);
		csvFilePrinter.printRecord("clearWater", "Clear Water", active, published);
		csvFilePrinter.printRecord("hotFlame", "Hot Flame", active, published);
		csvFilePrinter.printRecord("roastedCocoaBean", "Roasted Cocoa Bean", active, published);
		csvFilePrinter.printRecord("tropicalWood", "Tropical Wood", active, published);
		csvFilePrinter.printRecord("fineSand", "Fine Sand", active, published);
		csvFilePrinter.printRecord("sweetBanana", "Sweet Banana", active, published);
		csvFilePrinter.printRecord("calmSunset", "Calm Sunset", active, published);
		csvFilePrinter.printRecord("livingForest", "Living Forest", active, published);
		csvFilePrinter.printRecord("deepSea", "Deep Sea", active, published);
		csvFilePrinter.printRecord("freshWildBerries", "Fresh Wild Berries", active, published);

		csvFilePrinter.flush();

		fileWriter.flush();
		fileWriter.close();
		csvFilePrinter.close();
	}

	private static void generateCategoryToCategoryFiles() throws IOException
	{
		final String outputFile = "data/cat2cat.csv";
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader("categoryId", "parentId");
		FileWriter fileWriter = new FileWriter(outputFile);
		CSVPrinter csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);


		csvFilePrinter.printRecord("htmlWarm", "html");
		csvFilePrinter.printRecord("htmlCool", "html");
		csvFilePrinter.printRecord("htmlDark", "html");
		csvFilePrinter.printRecord("htmlLight", "html");
		csvFilePrinter.printRecord("roughCoal", "lumio");
		csvFilePrinter.printRecord("softEarth", "lumio");
		csvFilePrinter.printRecord("hardMetal", "lumio");
		csvFilePrinter.printRecord("piquantOlive", "lumio");
		csvFilePrinter.printRecord("coldSnow", "lumio");
		csvFilePrinter.printRecord("clearWater", "lumio");
		csvFilePrinter.printRecord("hotFlame", "lumio");
		csvFilePrinter.printRecord("roastedCocoaBean", "lumio");
		csvFilePrinter.printRecord("tropicalWood", "lumio");
		csvFilePrinter.printRecord("fineSand", "lumio");
		csvFilePrinter.printRecord("sweetBanana", "lumio");
		csvFilePrinter.printRecord("calmSunset", "lumio");
		csvFilePrinter.printRecord("livingForest", "lumio");
		csvFilePrinter.printRecord("deepSea", "lumio");
		csvFilePrinter.printRecord("freshWildBerries", "lumio");

		csvFilePrinter.flush();

		fileWriter.flush();
		fileWriter.close();
		csvFilePrinter.close();
	}

	private static void generateProductToCategoryFiles() throws IOException
	{
		final String outputFile = "data/prod2cat.csv";
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader("categoryId", "productId");
		FileWriter fileWriter = new FileWriter(outputFile);
		CSVPrinter csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);


		for (String color : colors.keySet())
		{
			Color rgb = hex2Rgb(color);
			csvFilePrinter.printRecord("html", color);
			if (isWarm(rgb))
			{
				csvFilePrinter.printRecord("htmlWarm", color);
			}
			else if (isCool(rgb))
			{
				csvFilePrinter.printRecord("htmlCool", color);
			}

			if (isDark(rgb))
			{
				csvFilePrinter.printRecord("htmlDark", color);
			}
			else if (isLight(rgb))
			{
				csvFilePrinter.printRecord("htmlLight", color);
			}
		}

		int lumioIndex = 0;
		for (String color : lumioColors.keySet())
		{
			if (lumioIndex <= 4)
			{
				csvFilePrinter.printRecord("roughCoal", color);
			}
			else if (lumioIndex <= 13)
			{
				csvFilePrinter.printRecord("softEarth", color);
			}
			else if (lumioIndex <= 17)
			{
				csvFilePrinter.printRecord("hardMetal", color);
			}
			else if (lumioIndex <= 21)
			{
				csvFilePrinter.printRecord("piquantOlive", color);
			}
			else if (lumioIndex <= 25)
			{
				csvFilePrinter.printRecord("coldSnow", color);
			}
			else if (lumioIndex <= 34)
			{
				csvFilePrinter.printRecord("clearWater", color);
			}
			else if (lumioIndex <= 40)
			{
				csvFilePrinter.printRecord("hotFlame", color);
			}
			else if (lumioIndex <= 46)
			{
				csvFilePrinter.printRecord("roastedCocoaBean", color);
			}
			else if (lumioIndex <= 51)
			{
				csvFilePrinter.printRecord("tropicalWood", color);
			}
			else if (lumioIndex <= 56)
			{
				csvFilePrinter.printRecord("fineSand", color);
			}
			else if (lumioIndex <= 60)
			{
				csvFilePrinter.printRecord("sweetBanana", color);
			}
			else if (lumioIndex <= 65)
			{
				csvFilePrinter.printRecord("calmSunset", color);
			}
			else if (lumioIndex <= 70)
			{
				csvFilePrinter.printRecord("livingForest", color);
			}
			else if (lumioIndex <= 75)
			{
				csvFilePrinter.printRecord("deepSea", color);
			}
			else if (lumioIndex <= 80)
			{
				csvFilePrinter.printRecord("freshWildBerries", color);
			}
			lumioIndex++;
		}

		csvFilePrinter.flush();

		fileWriter.flush();
		fileWriter.close();
		csvFilePrinter.close();
	}


	private static int randomPrice()
	{
		Random r = new Random();
		int low = 50;
		int high = 100;
		int result = r.nextInt(high - low) + low;
		return result;
	}

	private static int onHand()
	{
		Random r = new Random();
		int low = 0;
		int high = 1000;
		int result = r.nextInt(high - low) + low;
		return result;
	}

	private static void generateColorFiles() throws IOException
	{
		final int size = 500;
		for (String color : colors.keySet())
		{
			Color rgb = hex2Rgb(color);
			BufferedImage img = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);

			Graphics g = img.createGraphics();
			g.setColor(rgb);
			g.fillRect(0, 0, size, size); //add some nice border


			ImageIO.write(img, "jpg", new File("data/colors/"+color + ".jpg"));
		}

		for (String color : lumioColors.keySet())
		{
			Color rgb = hex2Rgb(color);
			BufferedImage img = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);

			Graphics g = img.createGraphics();
			g.setColor(rgb);
			g.fillRect(0, 0, size, size); //add some nice border


			ImageIO.write(img, "jpg", new File("data/colors/"+color+ ".jpg"));
		}
	}

	private static boolean isWarm(Color color)
	{
		return color.getBlue() < color.getRed();
	}

	private static boolean isCool(Color color)
	{
		return color.getBlue() > color.getRed();
	}

	private static boolean isDark(Color color)
	{
		return color.getRed() + color.getGreen() + color.getBlue() < 255;
	}

	private static boolean isLight(Color color)
	{
		return color.getRed() + color.getGreen() + color.getBlue() > 255 * 2;
	}


	/**
	 * @param colorStr
	 * 		e.g. "#FFFFFF"
	 * @return
	 */
	public static Color hex2Rgb(String colorStr)
	{
		if (!colorStr.startsWith("#"))
		{
			colorStr = "#" + colorStr;
		}
		return new Color(Integer.valueOf(colorStr.substring(1, 3), 16), Integer.valueOf(colorStr.substring(3, 5), 16),
				Integer.valueOf(colorStr.substring(5, 7), 16));
	}
}
