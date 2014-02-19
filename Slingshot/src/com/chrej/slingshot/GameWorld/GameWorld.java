package com.chrej.slingshot.GameWorld;

import java.util.ArrayList;
import java.util.Random;

import com.chrej.slingshot.GameObjects.ProjectileLauncher;
import com.chrej.slingshot.GameObjects.Enemies.Enemy;
import com.chrej.slingshot.GameObjects.Enemies.Flying.FlyingEnemy;
import com.chrej.slingshot.GameObjects.Projectiles.*;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class GameWorld {
	public static final float GRAVITY = 17.6f; // m/s^2
	public static final float AIR_DENSITY = 1.275f; // kg/m^3
	public static final float DRAG_COEFFICIENT = 0.47f; // No units
	public static final int GROUND = 5;
	public static final int NUM_ENEMIES = 12;
	
	public static int gameWidth;
	public static int gameHeight;
	
	private static ProjectileLauncher launcher;
	private static ArrayList<Projectile> projectiles;
	private static Rectangle ground;
	private static ArrayList<Enemy> enemies;
	
	private static Random rand;
	
	public GameWorld(int gameWidth, int gameHeight) {
		GameWorld.gameWidth = gameWidth;
		GameWorld.gameHeight = gameHeight;
		rand = new Random();
		ground = new Rectangle(0, 0, gameWidth, 5);
		projectiles = new ArrayList<Projectile>();
		enemies = new ArrayList<Enemy>();
		for (int i = 0; i < NUM_ENEMIES; i++) {
			enemies.add(new FlyingEnemy(gameWidth, gameHeight, rand.nextInt(6) + 3, 2));
		}
		launcher = new ProjectileLauncher(gameWidth);
	}

	public void update(float delta) {
		launcher.update(delta);
		//proj.update(delta);
		for (Enemy enemy : enemies) {
			enemy.update(delta);
		}
		
		for (Projectile proj : projectiles) {
			if (proj.isInAir()) {
				proj.checkCollisions(enemies);
			}
			if (Intersector.overlaps(proj.getBoundingCircle(), ground)) {
				proj.ground();
			}
		}
		for (Enemy enemy : enemies) {
			if (enemy.isHit() && enemy.isAlive()) {
				enemy.checkCollisions(enemies);
			}
			if (Intersector.overlaps(enemy.getBoundingRectangle(), ground)) {
				enemy.ground();
			}
		}
	}
	
	public static void addProjectile(Projectile proj) {
		projectiles.add(proj);
	}
	
	public static void destroy(Object o) {
		
		o = null;
	}
	
	public ProjectileLauncher getLauncher() {
		return launcher;
	}
	
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}
	
	/*public ScrollHandler getScroller() {
		return scroller;
	}*/
	
}
