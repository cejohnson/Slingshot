package com.chrej.slingshot.GameObjects.Projectiles;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.MathUtils;

public class Projectile {
	
	private static final int GROUND = 5;
	private static int launchHeight;

	private Vector2 position; // m
	private Vector2 velocity; // m/s
	private Vector2 acceleration; // m/s^2
	
	private Circle boundingCircle;

	private static final float gravity = 9.8f; // m/s^2
	private static final float airDensity = 1.275f; // kg/m^3
	private static final float dragCoefficient = 0.47f; // No units

	// private float rotation;
	private float radius; // m
	private float mass; // kg
	private float area; // m^2
	private float drag; // kg/m

	private boolean launched;
	private boolean traveling;
	private boolean inAir;

	public Projectile(int x, int y, float radius, float density) {
		this.radius = radius;
		launchHeight = y;
		area = MathUtils.PI * radius * radius;
		mass = density * (4 / 3 * MathUtils.PI * radius * radius * radius);
		drag = (airDensity * dragCoefficient * area) / 2;

		position = new Vector2(x, y + radius);
		velocity = new Vector2(0, 0);
		acceleration = new Vector2(0, -gravity);
		
		boundingCircle = new Circle(x, y + radius, radius);

		launched = false;
		traveling = true;
		inAir = true;
	}

	public void update(float delta) {

		if (launched && traveling) {
			velocity.add(acceleration.cpy().scl(delta));

			//System.out.println("X: " + acceleration.x + ", Y: " + acceleration.y);

			position.add(velocity.cpy().scl(delta));
			
			boundingCircle.set(position.x, position.y, radius);

			if ((velocity.len() > 0 && position.y > launchHeight) || velocity.y < 0) {
				acceleration.x = -(drag / mass) * velocity.len() * velocity.x;
				acceleration.y = -gravity - (drag / mass) * velocity.len()
						* velocity.y;
			}
			if (!inAir) {
				if (velocity.len() > 0) {
					acceleration.x = -(drag) * velocity.x; // Making it up
				} else
					traveling = false;
			}
		}
	}
	
	public void reset() {
		setAcceleration(new Vector2(0, 0));
		launched = false;
		traveling = true;
		inAir = true;
	}
	
	public void ground() {
		position.y = (GROUND + radius);
		velocity.y = 0;
		inAir = false;
	}

	public void setAcceleration(Vector2 a) {
		acceleration.set(a);
	}
	
	public float getSpeed() {
		return velocity.len();
	}

	public void launch(Vector2 a) {
		acceleration.add(a);
		launched = true;
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
