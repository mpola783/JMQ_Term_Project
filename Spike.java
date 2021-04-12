import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class Spike extends Entity {

	private static ArrayList<Spike> spikeList = new ArrayList<Spike>();

	private Position position = new Position(0, 0, 128, 128);

	private Sprite sprite;
	private Collidable collidable;

	public Spike() {
		sprite = new Sprite("spike");
		sprite.setPosition(position);

		collidable = new Collidable();
		collidable.setPosition(position);

		spikeList.add(this);
	}

	@Override
	void update(double deltaTime) {

		if (position.getX() + position.getWidth() < Screen.xPan)
			destroy();

	}

	@Override
	public void destroy() {
		super.destroy();
		sprite.destroy();
		spikeList.remove(this);
	}

	public static ArrayList<Spike> getSpikeList() {
		return spikeList;
	}

	public static void setSpikeList(ArrayList<Spike> newSpikeList) {
		spikeList = newSpikeList;
	}

	public Position getPosition() {
		return position;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public Collidable getCollidable() {
		return collidable;
	}

	public void serialize(OutputStream stream) throws IOException {
		DataOutputStream writer = new DataOutputStream(stream);
		writer.writeDouble(position.getX());
		writer.writeDouble(position.getY());
	}

	public static void serializeSpikeList(OutputStream stream) throws IOException {
		DataOutputStream writer = new DataOutputStream(stream);
		writer.writeInt(spikeList.size());
		for (Spike spike : spikeList)
			spike.serialize(stream);
	}

	public static Spike fromStream(InputStream stream) throws IOException {
		DataInputStream reader = new DataInputStream(stream);
		Spike spike = new Spike();
		spike.getPosition().setX(reader.readDouble());
		spike.getPosition().setY(reader.readDouble());
		return spike;
	}

	public static void loadFromStream(InputStream stream) throws IOException {
		DataInputStream reader = new DataInputStream(stream);
		ArrayList<Spike> newSpikeList = new ArrayList<Spike>();
		int size = reader.readInt();
		for (int i = 0; i < size; i++) {
			newSpikeList.add(fromStream(stream));
		}
		spikeList = newSpikeList;
	}

}
