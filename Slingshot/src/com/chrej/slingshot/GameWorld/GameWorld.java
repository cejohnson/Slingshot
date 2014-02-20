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
	public static final float GRAVITY = 9.8f; // m/s^2
	public static final float AIR_DENSITY = 1.275f; // kg/m^3
	public static final float DRAG_COEFFICIENT = 0.47f; // No units
	public static final int GROUND = 5;
	public static final int NUM_ENEMIES = 20;
	
	public static int gameWidth;
	public static int gameHeight;
	
	private static ProjectileLauncher launcher;
	private static ArrayList<Projectile> projectiles;
	private static Rectangle ground;
	private static ArrayList<Enemy> enemies;
	
	private static Random rand;
	
	private static int shots;
	private static int kills;
	private static float accuracy;
	private static int score;
	private static int streak = 0;
	private static boolean miss = false;
	
	public GameWorld(int gameWidth, int gameHeight) {
		GameWorld.gameWidth = gameWidth;
		GameWorld.gameHeight = gameHeight;
		shots = 0;
		kills = 0;
		accuracy = 0;
		score = 0;
		rand = new Random();
		ground = new Rectangle(0, 0, gameWidth, 5);
		projectiles = new ArrayList<Projectile>();
		enemies = new ArrayList<Enemy>();
		for (int i = 0; i < NUM_ENEMIES; i++) {
			enemies.add(new FlyingEnemy(gameWidth, gameHeight, rand.nextInt(6) + 4, 2));
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
	
	public static void addShot() {
		shots++;
		if (miss) {
			streak = 0;
		} else {
			miss = true;
		}
			
	}
	
	public static void addKill() {
		kills++;
		streak += 1;
		miss = false;
		accuracy = (float) kills / shots;
		score += 100*accuracy*streak;
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
	
	public int getScore() {
		return score;
	}
	
	/*public ScrollHandler getScroller() {
		return scroller;
	}*/
	
}
