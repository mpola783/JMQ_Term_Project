import java.util.ArrayList;

public abstract class Entity {

	static ArrayList<Entity> entityList = new ArrayList<Entity>();

	boolean enabled = true;

	public Entity() {
		entityList.add(this);
	}

	abstract void update(double deltaTime);

	public void destroy() {
		entityList.remove(this);
	}

}
