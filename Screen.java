import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.time.Duration;
import java.time.Instant;

import processing.core.PApplet;
import processing.core.PGraphics;

public class Screen extends PApplet {

	final static int UPDATES_PER_FRAME = 4;
	final static double TIME_SCALE = 1;
	final static int FLOOR_IMAGE_WIDTH = 384;
	public final static int FLOOR_MOVE_SPEED = 1500;
	public final static int FLOOR_HEIGHT = 256;

	final static int BG_COLOR = 0xFF000000;
	final static int GRAD_COLOR = 0xFFFA3939;

	PGraphics alphaGradient;

	static int width, height;

	Instant previousFrame;
	double floorPosition = 0;
	public static double xPan = 0;

    @Override
    public void settings() {
    	//fullScreen(P2D);
    	size(2560, 1440, P2D);
    	smooth(8);
    	Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    	width = (int) size.getWidth();
    	height = (int) size.getHeight();
    }

    @Override
    public void setup() {
    	frameRate(60);
    	noLoop();

    	final File folder = new File("assets\\images");
    	for (final File fileEntry : folder.listFiles()) {
    		if (!fileEntry.isDirectory()) {
    			String fullName = fileEntry.getName();
    			String shortName = fullName.substring(0, fullName.lastIndexOf('.'));
    			ImageList.images.put(shortName, loadImage("assets\\images\\" + fullName));
    		}
    	}

    	alphaGradient = createGraphics(width, height);
    	alphaGradient.beginDraw();
    	alphaGradient.background(0xFF000000);
    	alphaGradient.blendMode(BLEND);
    	alphaGradient.noStroke();
    	float radius = width * 1.5f;
    	for (float r = radius; r > 0; r--) {
    		int color = lerpColor(0xFFFFFFFF, 0xFF000000, r / radius);
    		alphaGradient.fill(color);
    		alphaGradient.circle(width / 2, 0, r);
    	}
    	alphaGradient.endDraw();

    	Main.setup();

    	loop();
    }

    @Override
    public void draw() {

    	// Calculate frame time
    	if (previousFrame == null) {
    		previousFrame = Instant.now();
    	}
    	Instant currentFrame = Instant.now();
    	Duration duration = Duration.between(previousFrame, currentFrame);
    	double deltaTime = ((double) duration.toNanos()) / 1_000_000_000 * TIME_SCALE;

    	// Draw background
    	background(GRAD_COLOR);
    	blendMode(MULTIPLY);
    	image(alphaGradient, 0, 0, width, height);
    	blendMode(BLEND);

    	// Draw ground
		floorPosition += FLOOR_MOVE_SPEED * deltaTime;
		if (floorPosition >= FLOOR_IMAGE_WIDTH)
			floorPosition -= FLOOR_IMAGE_WIDTH;
		for (int i = 0; i < width + FLOOR_IMAGE_WIDTH; i += FLOOR_IMAGE_WIDTH) {
			image(ImageList.images.get("floor"), (float) (-floorPosition + i), height - FLOOR_HEIGHT);
		}

		// Draw sprites
		for (Sprite sprite : Sprite.getSpriteList()) {
		    if (sprite.getVisible()) {
	    		pushMatrix();

	    		translate((float) sprite.getPosition().getX() - (float) xPan, (float) sprite.getPosition().getY());
	    		rotate((float) Math.toRadians(sprite.getAngle()));

		    	image(sprite.getImage(), (float) (sprite.getPosition().getWidth() * -sprite.getAnchorPoint().x), (float) (sprite.getPosition().getHeight() * -sprite.getAnchorPoint().y));

		    	popMatrix();
		    }
		}

		// Update entities
    	for (int i = 0; i < UPDATES_PER_FRAME; i++) {
    		xPan += FLOOR_MOVE_SPEED * (deltaTime / UPDATES_PER_FRAME);
    		for (int j = 0; j < Entity.entityList.size(); j++) {
				Entity entity = Entity.entityList.get(j);
				if (entity.enabled) {
					entity.update(deltaTime / UPDATES_PER_FRAME);
				}
			}
    	}

		previousFrame = currentFrame;
    }

    @Override
	public void keyPressed() {
    	InputReceiver.callKeyPressed(key);
    }

    @Override
	public void keyReleased() {
    	InputReceiver.callKeyReleased(key);
    }

}
