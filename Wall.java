import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class Wall extends Entity {

	private static ArrayList<Wall> wallList = new ArrayList<Wall>();

	private Position position = new Position(0, 0, 128, 128);

	private Sprite sprite;
	private Collidable collidable;

	public Wall() {
		sprite = new Sprite("wall");
		sprite.setPosition(position);

		collidable = new Collidable();
		collidable.setPosition(position);

		wallList.add(this);
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
		wallList.remove(this);
	}

	public static ArrayList<Wall> getWallList() {
		return wallList;
	}

	public static void setWallList(ArrayList<Wall> newWallList) {
		wallList = newWallList;
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

	public static void serializeWallList(OutputStream stream) throws IOException {
		DataOutputStream writer = new DataOutputStream(stream);
		writer.writeInt(wallList.size());
		for (Wall wall : wallList)
			wall.serialize(stream);
	}

	public static Wall fromStream(InputStream stream) throws IOException {
		DataInputStream reader = new DataInputStream(stream);
		Wall wall = new Wall();
		wall.getPosition().setX(reader.readDouble());
		wall.getPosition().setY(reader.readDouble());
		return wall;
	}

	public static void loadFromStream(InputStream stream) throws IOException {
		DataInputStream reader = new DataInputStream(stream);
		ArrayList<Wall> newWallList = new ArrayList<Wall>();
		int size = reader.readInt();
		for (int i = 0; i < size; i++) {
			newWallList.add(fromStream(stream));
		}
		wallList = newWallList;
	}

}
