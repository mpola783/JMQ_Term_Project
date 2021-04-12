import java.util.ArrayList;

import processing.core.PImage;

public class Sprite {

	private static ArrayList<Sprite> spriteList = new ArrayList<Sprite>();

    private PImage image;
    private boolean visible = true;

    private Position position = new Position(0, 0, 0, 0);

    private Vector2 anchorPoint = new Vector2();

    private double angle = 0;

    public Sprite(String imageName) {
        this.image = ImageList.images.get(imageName);

        position.setWidth(image.width);
        position.setHeight(image.height);

        spriteList.add(this);
    }

    public static ArrayList<Sprite> getSpriteList() {
    	return spriteList;
    }

    public PImage getImage() {
    	return image;
    }

    public boolean getVisible() {
    	return visible;
    }

    public Position getPosition() {
    	return position;
    }

    public Vector2 getAnchorPoint() {
    	return anchorPoint;
    }

    public double getAngle() {
    	return angle;
    }

    public void setImage(String imageName) {
    	this.image = ImageList.images.get(imageName);
    }

    public void setVisible(boolean visible) {
    	this.visible = visible;
    }

    public void setPosition(Position position) {
    	this.position = position;
    }

    public void setAnchorPoint(Vector2 anchorPoint) {
    	this.anchorPoint = anchorPoint;
    }

    public void setAngle(double angle) {
    	this.angle = angle;
    }

    public void destroy() {
    	spriteList.remove(this);
    }

}
