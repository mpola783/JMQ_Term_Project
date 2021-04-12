
public class PlayerCube extends Entity implements InputReceiver {

	final static double GRAVITY = 25000;
	final static double MAX_VELOCITY = 8000;
	final static double SPIN_SPEED = 540;
	final static double JUMP_VELOCITY = -6000;

	Position position = new Position(300, 100, 128, 128);
	double velocity = 0;

	boolean touchingGround = false;
	boolean touchingOrb = false;
	boolean jumping = false;

	Sprite sprite;
	Collidable collidable;

	public PlayerCube() {
		sprite = new Sprite("cube");
		sprite.setPosition(position);
		sprite.getAnchorPoint().set(0.5, 0.5);

		collidable = new Collidable();
		collidable.setPosition(position);
		collidable.getAnchorPoint().set(0.5, 0.5);

		InputReceiver.registerReceiver(this);
	}

	@Override
	void update(double deltaTime) {

		touchingGround = false;
		touchingOrb = false;

		// Save pre-physics position for wall collisions
		Position previousPosition = position.copy();

		// Update position
		velocity += GRAVITY * deltaTime;
		if (velocity > MAX_VELOCITY)
			velocity = MAX_VELOCITY;
		position.setY(position.getY() + velocity * deltaTime);
		position.setX(Screen.xPan + 300);

		// Check ground collision
		if (position.getY() > Screen.height - Screen.FLOOR_HEIGHT - sprite.getAnchorPoint().y * sprite.getPosition().getHeight()) {
			position.setY(Screen.height - Screen.FLOOR_HEIGHT - sprite.getAnchorPoint().y * sprite.getPosition().getHeight());
			velocity = 0;
			touchingGround = true;
		}

		// Check wall collision
		for (Wall wall : Wall.getWallList()) {
			if (collidable.collidesWith(wall.getCollidable())) {
				if (previousPosition.getY() + (1 - sprite.getAnchorPoint().y) * sprite.getPosition().getHeight() > wall.getPosition().getY() &&
						previousPosition.getY() - sprite.getAnchorPoint().y * sprite.getPosition().getHeight() < wall.getPosition().getY() + wall.getPosition().getHeight())
					destroy();

				if (velocity > 0) {
					position.setY(wall.getPosition().getY() - sprite.getAnchorPoint().y * position.getHeight());
					velocity = 0;
					touchingGround = true;
				} else if (velocity < 0){
					position.setY(wall.getPosition().getY() + wall.getPosition().getHeight() + (1 - sprite.getAnchorPoint().y) * position.getHeight());
					velocity = 0;
				}
			}
		}

		// Check spike collision
		for (Spike spike : Spike.getSpikeList()) {
			if (collidable.collidesWith(spike.getCollidable()))
				destroy();
		}

		// Check jump pad collision
		for (JumpPad jumpPad : JumpPad.getJumpPadList()) {
			if (collidable.collidesWith(jumpPad.getCollidable())) {
				velocity = JUMP_VELOCITY;
				touchingGround = false;
			}
		}

		// Check jump orb collision
		for (JumpOrb jumpOrb : JumpOrb.getJumpOrbList()) {
			if (collidable.collidesWith(jumpOrb.getCollidable())) {
				touchingOrb = true;
			}
		}

		// Handle jumping
		if (jumping && touchingGround)
			jump();

		// Update rotation
		if (!touchingGround)
			sprite.setAngle(sprite.getAngle() + SPIN_SPEED * deltaTime);
		else
			sprite.setAngle(Math.floor((sprite.getAngle() + 45) / 90) * 90);

	}

	@Override
	public void keyPressed(char key) {
		if (key == ' ') {
			jumping = true;
			if (touchingOrb)
				jump();
		}
	}

	@Override
	public void keyReleased(char key) {
		if (key == ' ') {
			jumping = false;
		}
	}

	@Override
	public void destroy() {
		super.destroy();
		sprite.destroy();
		InputReceiver.deregisterReceiver(this);
	}

	private void jump() {
		velocity = JUMP_VELOCITY;
		touchingGround = false;
	}

}
