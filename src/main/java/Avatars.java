import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import generators.ImageGenerator;
import generators.TwoColorsCenteredMirroredImageGenerator;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import wordgenerators.EpicGenerator;



/**
 * Created by dplekhotkin on 12/17/20.
 */
public class Avatars
{

	private static final String FOLDER = "avatars/";
	private static final String IMAGES = "images/";

	private static final String IMAGE_FOLDER = FOLDER + IMAGES;
	private static final String DATA_FOLDER = FOLDER + "data/";
	private static final String CNAME = "https://dplekh-avatars-project.surge.sh/";
	private static final String IMAGES_ENDPOINT = CNAME + IMAGES;

	private static final java.util.List<String> colorVariants = Arrays.asList("original", "inv", "wb", "bw");
	private static final java.util.List<String> sizeVariants = Arrays.asList("medium", "small", "large");

	private static final Map<String, String> colorVariantsLabelMap = new HashMap<>();
	private static final Map<String, String> sizeVariantsLabelMap = new HashMap<>();
	private static final Map<String, String> sizeVariantsSurchargeMap = new HashMap<>();
	static {
		colorVariantsLabelMap.put("original", "Original");
		colorVariantsLabelMap.put("inv", "Inverse");
		colorVariantsLabelMap.put("wb", "White and black");
		colorVariantsLabelMap.put("bw", "Black and white");
	}

	static {
		sizeVariantsLabelMap.put("medium", "Medium");
		sizeVariantsLabelMap.put("small", "Small");
		sizeVariantsLabelMap.put("large", "Large");

		sizeVariantsSurchargeMap.put("medium", "10");
		sizeVariantsSurchargeMap.put("small", "0");
		sizeVariantsSurchargeMap.put("large", "20");
	}

	static
	{
		deleteDir(new File(FOLDER));
		new File(IMAGE_FOLDER).mkdirs();
		new File(DATA_FOLDER).mkdirs();

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FOLDER + "CNAME")))
		{
			writer.write(CNAME);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

	}

	private static void deleteDir(File file)
	{
		File[] contents = file.listFiles();
		if (contents != null)
		{
			for (File f : contents)
			{
				deleteDir(f);
			}
		}
		file.delete();
	}

	public static void main(String[] args) throws IOException
	{

		java.util.List<String> imageNames = generateImages();
		generateProductsCSV(imageNames);
		generateInventory(imageNames);
		generateCategoryFiles();
		generateProductToCategoryFilesForSimpleCategory(imageNames);
	}



	private static String generatePrefix()
	{
		final char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		final Random random = new Random();

		StringBuilder prefixBuilder = new StringBuilder();
		for (int j = 0; j < 3; j++)
		{
			prefixBuilder.append(alphabet[random.nextInt(alphabet.length)]);
		}
		for (int j = 0; j < 2; j++)
		{
			prefixBuilder.append(random.nextInt(10));
		}

		return prefixBuilder.toString();
	}

	private static java.util.List<String> generateImages() throws IOException
	{

		ImageGenerator imageGenerator = new TwoColorsCenteredMirroredImageGenerator(IMAGE_FOLDER, 6, false);
		EpicGenerator nameGenerator = new EpicGenerator("AAA111");
		String wordDivider = "-";


		java.util.List<String> imageNames = new ArrayList<>();
		for (int i = 0; i < 100; i++)
		{
			int size = 600;
			String suffix = String.valueOf(i);
			String fileName = nameGenerator.generate(generatePrefix(), suffix, wordDivider);
			imageGenerator.generate(size, fileName);
			imageNames.add(fileName);
		}

		return imageNames;
	}

	private static void generateProductsCSV(java.util.List<String> products) throws IOException
	{
		final String outputFile = DATA_FOLDER + "products.csv";
		CSVFormat csvFileFormat = CSVFormat.DEFAULT
				.withHeader("id", "sku", "name", "sellingPrice", "fullSize", "thumbnail", "active", "published", "type","parentId",
						"colorVariantAttributes", "sizeVariantAttributes", "surchargeType", "surchargeValue");
		FileWriter fileWriter = new FileWriter(outputFile);
		CSVPrinter csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);

		for (String product : products)
		{

			printRecord(product, csvFilePrinter);

			for (String colorVariant : colorVariants)
			{
				for (String sizeVariant : sizeVariants)
				{
					printVariant(colorVariant, sizeVariant, product,  csvFilePrinter);
				}

			}
		}

		csvFilePrinter.flush();

		fileWriter.flush();
		fileWriter.close();
		csvFilePrinter.close();
	}



	private static void printRecord(String imageName, CSVPrinter csvFilePrinter) throws IOException
	{
		String id = imageName.replaceAll("-", "").toLowerCase();
		String sku = id + "0000";
		String name = imageName.replaceAll("-", " ");
		int price = randomPrice();

		String fullSize = IMAGES_ENDPOINT + imageName + ".jpg";
		String thumbnail = IMAGES_ENDPOINT + imageName + "-thumbnail.jpg";
		boolean active = true;
		boolean published = true;
		String type = "PARENT";

		String parentId = "";
		String variantAttributes = "";
		String surchargeType = "";
		String surchargeValue = "";

		csvFilePrinter.printRecord(id, sku, name, price, fullSize, thumbnail, active, published, type, parentId, variantAttributes, variantAttributes, surchargeType, surchargeValue);
	}

	private static void printVariant(final String colorVariant, final String sizeVariant, final String product,
			final CSVPrinter csvFilePrinter) throws IOException
	{
		String parentId = product.replaceAll("-", "").toLowerCase();
		String id = parentId + colorVariant + sizeVariant;
		String sku = id + "0000";
		String name = product.replaceAll("-", " ");


		String price = "";
		String fullSize = IMAGES_ENDPOINT + product + "-" + colorVariant + ".jpg";
		String thumbnail = IMAGES_ENDPOINT + product + "-" + colorVariant + "-thumbnail.jpg";
		boolean active = true;
		boolean published = true;
		String type = "VARIANT";
		String surchargeType = "ABSOLUTE";
		String surchargeValue = sizeVariantsSurchargeMap.get(sizeVariant);

		String colorVariantLabel = colorVariantsLabelMap.get(colorVariant);
		String colorVariantAttributes = colorVariantLabel + "|" + thumbnail + "||" + colorVariantLabel;


		String sizeVariantLabel = sizeVariantsLabelMap.get(sizeVariant);
		String sizeVariantAttributes = sizeVariantLabel + "|||" + sizeVariantLabel;


		csvFilePrinter.printRecord(id, sku, name, price, fullSize, thumbnail, active, published, type, parentId, colorVariantAttributes, sizeVariantAttributes, surchargeType, surchargeValue);

	}

	private static void generateInventory(java.util.List<String> products) throws IOException
	{
		final String outputFile = DATA_FOLDER + "inventory.csv";
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader("fulfillmentLocationId", "onHand", "productId");
		FileWriter fileWriter = new FileWriter(outputFile);
		CSVPrinter csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);
		String fulfillmentLocationId = "color_warehouse_1";
		for (String product : products)
		{
			for (String colorVariant : colorVariants)
			{
				for (String sizeVariant : sizeVariants)
				{
					int onHand = onHand();
					String productId = product.replaceAll("-", "").toLowerCase() + colorVariant + sizeVariant;
					csvFilePrinter.printRecord(fulfillmentLocationId, onHand, productId);
				}
			}
		}

		csvFilePrinter.flush();

		fileWriter.flush();
		fileWriter.close();
		csvFilePrinter.close();
	}

	private static void generateCategoryFiles() throws IOException
	{
		final String outputFile = DATA_FOLDER + "categories.csv";
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader("id", "name", "active", "published");
		FileWriter fileWriter = new FileWriter(outputFile);
		CSVPrinter csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);

		boolean active = true;
		boolean published = true;
		csvFilePrinter.printRecord("simple", "Simple", active, published);
		csvFilePrinter.printRecord("mosaic", "Mosaic", active, published);
		csvFilePrinter.printRecord("abstract", "Abstract", active, published);
		csvFilePrinter.printRecord("math", "Math", active, published);

		csvFilePrinter.flush();

		fileWriter.flush();
		fileWriter.close();
		csvFilePrinter.close();
	}

	private static void generateProductToCategoryFilesForSimpleCategory(java.util.List<String> products) throws IOException
	{
		final String outputFile = DATA_FOLDER + "prod2cat.csv";
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader("categoryId", "productId");
		FileWriter fileWriter = new FileWriter(outputFile);
		CSVPrinter csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);

		for (String product : products)
		{
			String productId = product.replaceAll("-", "").toLowerCase();
			csvFilePrinter.printRecord("simple", productId);


			for (String colorVariant : colorVariants)
			{
				for (String sizeVariant : sizeVariants)
				{
					csvFilePrinter.printRecord("simple", productId+colorVariant+sizeVariant);
				}
			}
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


}
