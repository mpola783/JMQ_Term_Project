import java.io.Serializable;

public class Collidable implements Serializable {

	private static final long serialVersionUID = -1892530559371908253L;
	private Position position;
	private Vector2 anchorPoint = new Vector2(0, 0);

	public boolean collidesWith(Collidable other) {
		if (position == null)
			return false;

		Vector2 otherMin = new Vector2(other.getPosition().getX(), other.getPosition().getY());
		otherMin.sub(other.getPosition().getWidth() * other.getAnchorPoint().x, other.getPosition().getHeight() * other.getAnchorPoint().y);
		Vector2 otherMax = otherMin.copy();
		otherMax.add(other.getPosition().getWidth(), other.getPosition().getHeight());

		Vector2 thisMin = new Vector2(position.getX(), position.getY());
		thisMin.sub(position.getWidth() * anchorPoint.x, position.getHeight() * anchorPoint.y);
		Vector2 thisMax = thisMin.copy();
		thisMax.add(position.getWidth(), position.getHeight());

		double d1x = otherMin.x - thisMax.x;
		double d1y = otherMin.y - thisMax.y;
		double d2x = thisMin.x - otherMax.x;
		double d2y = thisMin.y - otherMax.y;

		return !(d1x > 0 || d1y > 0 || d2x > 0 || d2y > 0);
	}

	public Position getPosition() {
		return position;
	}

	public Vector2 getAnchorPoint() {
		return anchorPoint;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

}
