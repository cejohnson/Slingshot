package com.chrej.slingshot.GameObjects.Projectiles;

import java.util.ArrayList;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.MathUtils;
import com.chrej.slingshot.GameObjects.ProjectileLauncher;
import com.chrej.slingshot.GameObjects.Enemies.Enemy;
import com.chrej.slingshot.GameWorld.GameWorld;

public class Projectile {
	private static int launchHeight;

	private Vector2 position; // m
	private Vector2 velocity; // m/s
	private Vector2 acceleration; // m/s^2
	
	private Circle boundingCircle;

	private float radius; // m
	private float mass; // kg
	private float area; // m^2
	private float drag; // kg/m

	private boolean traveling;
	private boolean inAir;
	private boolean postLaunch;
	private boolean projectileReturned;

	public Projectile(int x, int y, float radius, float density) {
		this.radius = radius;
		launchHeight = y;
		area = MathUtils.PI * radius * radius;
		mass = density * (4 / 3 * MathUtils.PI * radius * radius * radius);
		drag = (GameWorld.AIR_DENSITY * GameWorld.DRAG_COEFFICIENT * area) / 2;

		position = new Vector2(x, y + radius);
		velocity = new Vector2(0, 0);
		acceleration = new Vector2(0, -GameWorld.GRAVITY);
		
		boundingCircle = new Circle(x, y + radius, radius);

		traveling = false;
		inAir = false;
		postLaunch = false;
		projectileReturned = false;
	}

	public void update(float delta) {

		if (traveling) {
			velocity.add(acceleration.cpy().scl(delta));

			position.add(velocity.cpy().scl(delta));
			
			if (position.x > GameWorld.gameWidth)
				position.x = 0;
			if (position.x < 0)
				position.x = GameWorld.gameWidth;
			
			boundingCircle.set(position.x, position.y, radius);

			// Air resistance
			if (inAir && postLaunch) {
				acceleration.x = -(drag / mass) * velocity.len() * velocity.x;
				acceleration.y = -GameWorld.GRAVITY - (drag / mass) * velocity.len()
						* velocity.y;
			}
			if (!inAir && postLaunch) {
				if (velocity.len() > 0) {
					acceleration.x = -(drag) * velocity.x; // Making it up
				} else
					traveling = false;
			}
			if (position.y > launchHeight) {
				postLaunch = true;
			}
		}
	}
	
	public void reset() {
		acceleration.set(0, 0);
		velocity.set(0, 0);
		traveling = false;
		inAir = false;
		postLaunch = false;
		projectileReturned = false;
	}
	
	public void ground() {
		position.y = (GameWorld.GROUND + radius);
		velocity.y = 0;
		inAir = false;
		if (!projectileReturned) {
			ProjectileLauncher.returnProj();
			projectileReturned = true;
		}		
	}
	
	public boolean isInAir() {
		return (position.y <= GameWorld.gameHeight && inAir);
	}
	
	public boolean isPostLaunch() {
		return postLaunch;
	}
	
	public void checkCollisions(ArrayList<Enemy> enemies) {
		for (Enemy enemy : enemies) {
			if (Intersector.overlaps(boundingCircle, enemy.getBoundingRectangle())) {
				enemy.hit();
			}
		}
	}
	
	public float getSpeed() {
		return velocity.len();
	}

	public void launch(Vector2 a) {
		acceleration.add(a);
		inAir = true;
		traveling = true;
		postLaunch = false;
	}

	public void onClick() {
		System.out.println("Mass: " + mass + "kg");
		velocity.x = 40;
		velocity.y = 40;
	}

	public float getX() {
		return position.x;
	}

	public float getY() {
		return position.y;
	}

	public void setX(float x) {
		position.x = x;
	}

	public void setY(float y) {
		position.y = y;
	}

	public float getRadius() {
		return radius;
	}

	public float getMass() {
		return mass;
	}
	
	public Circle getBoundingCircle() {
		return boundingCircle;
	}

	/*
	 * public float getRotation() { return rotation; }
	 */

}
