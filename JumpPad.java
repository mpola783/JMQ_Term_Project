import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class JumpPad extends Entity {

	private static ArrayList<JumpPad> jumpPadList = new ArrayList<JumpPad>();

	private Position position = new Position(0, 0, 128, 16);

	private Sprite sprite;
	private Collidable collidable;

	public JumpPad() {
		sprite = new Sprite("jumpPad");
		sprite.setPosition(position);
		sprite.getAnchorPoint().set(0, 1);

		collidable = new Collidable();
		collidable.setPosition(position);
		collidable.getAnchorPoint().set(0, 1);

		jumpPadList.add(this);
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
		jumpPadList.remove(this);
	}

	public static ArrayList<JumpPad> getJumpPadList() {
		return jumpPadList;
	}

	public static void setJumpPadList(ArrayList<JumpPad> newJumpPadList) {
		jumpPadList = newJumpPadList;
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

	public static void serializeJumpPadList(OutputStream stream) throws IOException {
		DataOutputStream writer = new DataOutputStream(stream);
		writer.writeInt(jumpPadList.size());
		for (JumpPad jumpPad : jumpPadList)
			jumpPad.serialize(stream);
	}

	public static JumpPad fromStream(InputStream stream) throws IOException {
		DataInputStream reader = new DataInputStream(stream);
		JumpPad jumpPad = new JumpPad();
		jumpPad.getPosition().setX(reader.readDouble());
		jumpPad.getPosition().setY(reader.readDouble());
		return jumpPad;
	}

	public static void loadFromStream(InputStream stream) throws IOException {
		DataInputStream reader = new DataInputStream(stream);
		ArrayList<JumpPad> newJumpPadList = new ArrayList<JumpPad>();
		int size = reader.readInt();
		for (int i = 0; i < size; i++) {
			newJumpPadList.add(fromStream(stream));
		}
		jumpPadList = newJumpPadList;
	}

}
