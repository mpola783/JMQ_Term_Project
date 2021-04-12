import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
public class LevelManager {

	private static final String PATH_NAME = "assets\\levels\\";

	public static void save(String name) throws IOException {
		File path = new File(PATH_NAME);
		if (!path.exists())
			path.mkdir();

		File file = new File(PATH_NAME + name);
		FileOutputStream fileOut = new FileOutputStream(file, false);

		Wall.serializeWallList(fileOut);
		Spike.serializeSpikeList(fileOut);
		JumpOrb.serializeJumpOrbList(fileOut);
		JumpPad.serializeJumpPadList(fileOut);

		fileOut.close();
	}

	public static void load(String name) throws IOException {
		File file = new File(PATH_NAME + name);
		FileInputStream fileIn = new FileInputStream(file);

		Wall.loadFromStream(fileIn);
		Spike.loadFromStream(fileIn);
		JumpOrb.loadFromStream(fileIn);
		JumpPad.loadFromStream(fileIn);

		fileIn.close();
	}

}
