package wordgenerators;

import java.util.Arrays;
import java.util.List;
import java.util.Random;


/**
 * Created by dplekhotkin on 1/14/21.
 */
public class EpicGenerator
{
	private static final String[] EPIC_WORDS = { "heroic", "grand", "monumental", "vast", "Homeric", "Miltonian", "lofty",
			"grandiloquent", "extravagant", "bombastic", "ambitious", "arduous", "extraordinary", "Herculean", "huge" };

	private static final String[] SOME_NOUNS = { "Midnight", "Gangster", "Birthday", "Cave Man", "Nail Gun", "Railroad",
			"Dwelling", "Sea Lion", "Bathroom", "Hooligan", "Director", "Evidence", "Driveway", "Keepsake", "Mushroom", "Ear Ring",
			"Preacher", "Washroom", "Restroom", "Criminal", "Dog Bowl", "Airplane", "Soda Pop", "Rat Tail" };


	private String categoryId;
	public EpicGenerator(String categoryId) {
		this.categoryId = categoryId;
	}
	public String generate(String prefix, String suffix, String divider)
	{
		StringBuilder builder = new StringBuilder();
		if (divider == null)
		{
			divider = " ";
		}

		builder.append(categoryId).append(divider);

		if (prefix != null)
		{

			builder.append(prefix.toUpperCase()).append(divider);
		}

		final Random random = new Random();
		int wordIndex = random.nextInt(EPIC_WORDS.length);
		builder.append(capitalize(EPIC_WORDS[wordIndex]));
		builder.append(divider);
		wordIndex = random.nextInt(SOME_NOUNS.length);
		builder.append(capitalize(fixDivider(SOME_NOUNS[wordIndex], divider)));

		if (suffix != null)
		{
			builder.append(divider).append(suffix);
		}

		return builder.toString();
	}

	public static String capitalize(String str)
	{
		if (str == null)
			return str;
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	private static String fixDivider(String str, String divider) {
		return str.replaceAll(" ", divider);
	}
}
