package generators;

import java.io.IOException;


/**
 * Created by dplekhotkin on 1/14/21.
 */
public interface ImageGenerator
{
	void generate(int size, String filename) throws IOException;
}
