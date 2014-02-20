package com.chrej.slingshot.GameObjects.Enemies.Flying;

import java.util.Random;
import com.chrej.slingshot.GameObjects.Enemies.Enemy;
import com.chrej.slingshot.GameWorld.GameWorld;

public class FlyingEnemy extends Enemy {

	private boolean grounded;

	// This is God awful
	public FlyingEnemy(int gameWidth, int gameHeight, int width, int height) {
		super(gameWidth, gameHeight, new Random().nextInt(gameWidth),
				new Random().nextInt(gameHeight / 2) + gameHeight / 2 - 2, width,
				height, (new Random().nextInt(30) + 30)
						* (new Random().nextInt(2) * 2 - 1));
		grounded = false;
	}

	public void update(float delta) {
		super.update(delta);
		if (grounded) {
			if (velocity.len() > 0.001) {
				acceleration.x = -.5f * velocity.x; // Making it up
			} else {
				traveling = false;
			}
		}
	}

	public void hit() {
		super.hit();
		acceleration.set(0, -GameWorld.GRAVITY);
	}

	public void ground() {
		super.ground();
		grounded = true;
	}

}
