import java.io.IOException;

import processing.core.PApplet;

public class Main {

	public static void main(String[] args) {
    	PApplet.main("Screen");
    }

	static void setup() {
		try {
			LevelManager.load("test.grm");
		} catch (IOException e) {
			e.printStackTrace();
		}

		new PlayerCube();
		/*for (int i = 0; i < 10; i++) {

			for (int j = 1; j < 4; j++) {
				Wall wall = new Wall();
				wall.getPosition().setX(10000 + i * 2000);
				wall.getPosition().setY(Screen.height - Screen.FLOOR_HEIGHT - j * wall.getPosition().getHeight());
			}

			for (int j = 0; j < 10; j++) {
				Wall wall = new Wall();
				wall.getPosition().setX(10000 + j * 2000 + i * wall.getPosition().getWidth());
				wall.getPosition().setY(Screen.height - wall.getPosition().getHeight() - Screen.FLOOR_HEIGHT - 384);
			}

			Spike spike = new Spike();
			spike.getPosition().setX(10256 + i * 2000);
			spike.getPosition().setY(Screen.height - spike.getPosition().getHeight() - Screen.FLOOR_HEIGHT - 512);

			JumpPad jumpPad = new JumpPad();
			jumpPad.getPosition().setX(10000 + i * 2000);
			jumpPad.getPosition().setY(Screen.height - spike.getPosition().getHeight() - Screen.FLOOR_HEIGHT - 384);

			JumpOrb jumpOrb = new JumpOrb();
			jumpOrb.getPosition().setX(9744 + i * 2000);
			jumpOrb.getPosition().setY(Screen.height - spike.getPosition().getHeight() - Screen.FLOOR_HEIGHT - 640);
		}

		try {
			LevelManager.save("test.grm");
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}

}
