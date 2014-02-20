package com.chrej.slingshot.GameWorld;

import java.util.ArrayList;
import java.util.Random;

import com.chrej.slingshot.GameObjects.BodyDefs;
import com.chrej.slingshot.GameObjects.FixtureDefs;
import com.chrej.slingshot.GameObjects.ProjectileLauncher;
import com.chrej.slingshot.GameObjects.Enemies.Enemy;
import com.chrej.slingshot.GameObjects.Enemies.Flying.FlyingEnemy;
import com.chrej.slingshot.GameObjects.Projectiles.*;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class GameWorld {	
	public static final float BOX_STEP = 1 / 60f;
	public static final int BOX_VELOCITY_ITERATIONS = 6;
	public static final int BOX_POSITION_ITERATIONS = 2;
	public static final float WORLD_TO_BOX = 0.01f;
	public static final float BOX_WORLD_TO = 100f;

	public static final float GRAVITY = 9.8f; // m/s^2
	public static final float AIR_DENSITY = 1.275f; // kg/m^3
	public static final float DRAG_COEFFICIENT = 0.47f; // No units
	public static final int GROUND = 5;
	public static final int NUM_ENEMIES = 20;
	
	public static World world = new World(new Vector2(0, -GRAVITY), true);
	public static BodyDefs bodyDefs;
	public static FixtureDefs fixtureDefs;

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
		//world = new World(new Vector2(0, -9.8f), true);
		GameWorld.gameWidth = gameWidth;
		GameWorld.gameHeight = gameHeight;
		bodyDefs = new BodyDefs();
		fixtureDefs = new FixtureDefs(gameWidth);
		/*shots = 0;
		kills = 0;
		accuracy = 0;
		score = 0;

		rand = new Random();
		// Ground body
		Body groundBody = world.createBody(BodyDefs.groundBodyDef);
		projectiles = new ArrayList<Projectile>();
		enemies = new ArrayList<Enemy>();
		for (int i = 0; i < NUM_ENEMIES; i++) {
			enemies.add(new FlyingEnemy(gameWidth, gameHeight,
					rand.nextInt(6) + 4, 2));
		}*/
		Body groundBody = world.createBody(BodyDefs.groundBodyDef);
		groundBody.createFixture(FixtureDefs.groundBoxFixtureDef);
		
		/*Body proj = world.createBody(BodyDefs.projectileBodyDef);
		proj.createFixture(FixtureDefs.projectileFixtureDef);
		proj.setTransform(new Vector2(gameWidth/2, gameHeight), proj.getAngle());*/
		launcher = new ProjectileLauncher(gameWidth);
	}

	public void update(float delta) {
		//launcher.update(delta);
		world.step(BOX_STEP, BOX_VELOCITY_ITERATIONS, BOX_POSITION_ITERATIONS);
		/*launcher.update(delta);
		// proj.update(delta);
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
		}*/
	}

	public World getWorld() {
		return world;
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
		score += 100 * accuracy * streak;
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

	/*
	 * public ScrollHandler getScroller() { return scroller; }
	 */

}
