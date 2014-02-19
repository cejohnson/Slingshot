package com.chrej.slingshot.GameObjects.Enemies;

import java.util.ArrayList;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.chrej.slingshot.GameWorld.GameWorld;

public class Enemy {
	
	protected static final float GRAVITY = 9.8f;
	private static final int GROUND = 5;

	protected Vector2 position; // m
	protected Vector2 velocity; // m/s
	protected Vector2 acceleration; // m/s^2
	protected boolean traveling;
	protected boolean hit;
	protected boolean alive;
	protected boolean scoreAdded = false;
	
	protected Rectangle boundingRectangle;
	
	protected int gameWidth;
	protected int gameHeight;
	protected int width;
	protected int height;

	public Enemy(int gameWidth, int gameHeight, int x, int y, int width, int height, int speed) {
		this.gameWidth = gameWidth;
		this.gameHeight = gameHeight;
		this.width = width;
		this.height = height;
		position = new Vector2(x, y);
		velocity = new Vector2(speed, 0);
		acceleration = new Vector2(0, 0);
		boundingRectangle = new Rectangle(x, y, width, height);
		traveling = true;
		hit = false;
		alive = true;
	}
	
	public void update(float delta) {
		if (traveling) {
			velocity.add(acceleration.cpy().scl(delta));
	
			position.add(velocity.cpy().scl(delta));
			
			if (position.x > gameWidth) {
				position.x = -width;
			}
			if (position.x < -width) {
				position.x = gameWidth;
			}
			
			boundingRectangle.set(position.x, position.y, width, height);
		} else {
			die();
		}
	}
	
	public void checkCollisions(ArrayList<Enemy> enemies) {
		for (Enemy enemy : enemies) {
			if (enemy != this) {
				if (Intersector.overlaps(boundingRectangle, enemy.getBoundingRectangle())) {
					enemy.hit();
				}
			}
		}
	}
	
	public void hit() {
		hit = true;
		if(!scoreAdded) {
			GameWorld.addKill();
			scoreAdded = true;
		}
	}
	
	public boolean isHit() {
		return hit;
	}
	
	public boolean isAlive() {
		return alive;
	}
	
	public Rectangle getBoundingRectangle() {
		return boundingRectangle;
	}
	
	public void ground() {
		position.y = GROUND;
		velocity.y = 0;
		acceleration.y = 0;
	}
	
	public void die() {
		alive = false;		
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
