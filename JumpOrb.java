import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class JumpOrb extends Entity {

	private static ArrayList<JumpOrb> jumpOrbList = new ArrayList<JumpOrb>();

	private Position position = new Position(0, 0, 64, 64);

	private Sprite sprite;
	private Collidable collidable;

	public JumpOrb() {
		sprite = new Sprite("jumpOrb");
		sprite.setPosition(position);

		collidable = new Collidable();
		collidable.setPosition(position);

		jumpOrbList.add(this);
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
		jumpOrbList.remove(this);
	}

	public static ArrayList<JumpOrb> getJumpOrbList() {
		return jumpOrbList;
	}

	public static void setJumpOrbList(ArrayList<JumpOrb> newJumpOrbList) {
		jumpOrbList = newJumpOrbList;
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

	public static void serializeJumpOrbList(OutputStream stream) throws IOException {
		DataOutputStream writer = new DataOutputStream(stream);
		writer.writeInt(jumpOrbList.size());
		for (JumpOrb jumpOrb : jumpOrbList)
			jumpOrb.serialize(stream);
	}

	public static JumpOrb fromStream(InputStream stream) throws IOException {
		DataInputStream reader = new DataInputStream(stream);
		JumpOrb jumpOrb = new JumpOrb();
		jumpOrb.getPosition().setX(reader.readDouble());
		jumpOrb.getPosition().setY(reader.readDouble());
		return jumpOrb;
	}

	public static void loadFromStream(InputStream stream) throws IOException {
		DataInputStream reader = new DataInputStream(stream);
		ArrayList<JumpOrb> newJumpOrbList = new ArrayList<JumpOrb>();
		int size = reader.readInt();
		for (int i = 0; i < size; i++) {
			newJumpOrbList.add(fromStream(stream));
		}
		jumpOrbList = newJumpOrbList;
	}

}
