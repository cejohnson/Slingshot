package com.chrej.slingshot.GameObjects;

import java.util.ArrayList;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.chrej.slingshot.GameObjects.Projectiles.*;
import com.chrej.slingshot.GameWorld.GameWorld;

public class ProjectileLauncher {
	
	private int center;
	private ElasticBand leftBand;
	private ElasticBand rightBand;
	//private ArrayList<Body> projectiles;
	private Body currentProj;
	private Vector2 acceleration;
	//private Rectangle pocket;
	private Body leftUpright;
	private Body rightUpright;
	private Body pocket;
	
	private static final float BAND_TOP = 20f;
	private static final float BAND_BOTTOM = 15f;
	private static final float Y_MIN = 6;
	private static final float X_RANGE = 10;
	private static final int NUM_PROJECTILES = 5;
	protected static int numProjectiles = NUM_PROJECTILES;
	protected static Vector2 force;
	
	private boolean applyForce = true;
	

	public ProjectileLauncher(int gameWidth) {
		center = gameWidth / 2;
		//System.out.println(center);
		leftUpright = GameWorld.world.createBody(BodyDefs.launcherUprightBodyDef);
		leftUpright.createFixture(FixtureDefs.launcherUprightFixtureDef);
		leftUpright.setTransform(center-5, 5, leftUpright.getAngle());
		rightUpright = GameWorld.world.createBody(BodyDefs.launcherUprightBodyDef);
		rightUpright.createFixture(FixtureDefs.launcherUprightFixtureDef);
		rightUpright.setTransform(center+5, 5, rightUpright.getAngle());
		
		pocket = GameWorld.world.createBody(BodyDefs.launcherPocketBodyDef);
		pocket.createFixture(FixtureDefs.launcherPocketFixtureDef);
		//pocket.setTransform(center, 15, pocket.getAngle());
		
		
		//projectiles = new ArrayList<Body>();
		leftBand = new ElasticBand((float) center - 3, BAND_TOP, (float) center - 1, BAND_BOTTOM);
		rightBand = new ElasticBand((float) center + 3, BAND_TOP, (float) center + 1, BAND_BOTTOM);
		//pocket = new Rectangle(leftBand.bottom.x, leftBand.bottom.y, 2, 1);
		force = new Vector2();
		
	}
	
	public void addProjectile(float radius) {
		currentProj = GameWorld.world.createBody(BodyDefs.projectileBodyDef);
		currentProj.createFixture(FixtureDefs.projectileFixtureDef);
		//currentProj.setTransform(new Vector2(center, BAND_BOTTOM), currentProj.getAngle());
		//currentProj = proj;
		//projectiles.add(currentProj);
		//if (projectiles.size() > NUM_PROJECTILES)
			//projectiles.remove(0);
		//GameWorld.addProjectile(currentProj);
	}
	
	public static void returnProj() {
		numProjectiles++;
		System.out.println(numProjectiles);
	}
	
	public void update(float delta) {
		if (applyForce) {
			if (currentProj != null && currentProj.getPosition().y < BAND_BOTTOM) {
				leftBand.bottom.x = currentProj.getPosition().x - 1;
				rightBand.bottom.x = currentProj.getPosition().x + 1;
				leftBand.bottom.y = currentProj.getPosition().y - currentProj.getFixtureList().get(0).getShape().getRadius();
				rightBand.bottom.y = currentProj.getPosition().y - currentProj.getFixtureList().get(0).getShape().getRadius();
				
				System.out.println("Left: (" + leftBand.top.x + ", " + leftBand.top.y + "), (" + leftBand.bottom.x + ", " + leftBand.bottom.y + ")");
				System.out.println("Right: (" + rightBand.top.x + ", " + rightBand.top.y + "), (" + rightBand.bottom.x + ", " + rightBand.bottom.y + ")");
				
				force = leftBand.getForce().add(rightBand.getForce());
				//force.y -= currentProj.getMass()*GameWorld.GRAVITY;
				//acceleration = force.div(currentProj.getMass());
				System.out.println(force);
				currentProj.applyForceToCenter(force, true);
			} else {
				leftBand.bottom.x = center - 1;
				rightBand.bottom.x = center + 1;
				leftBand.bottom.y = BAND_BOTTOM;
				rightBand.bottom.y = BAND_BOTTOM;
			}
			//pocket.setX(leftBand.bottom.x);
			//pocket.setY(leftBand.bottom.y);
		} else {
			currentProj.applyForceToCenter(0, currentProj.getMass()*GameWorld.GRAVITY, true);
		}
		/*for (Projectile proj : projectiles) {
			proj.update(delta);
		}*/
	}
	
	public void onClick(float x, float y) {
		//if (numProjectiles > 0){
			addProjectile(.75f);
		//}
		applyForce = false;
		onDrag(x, y);
	}
	
	public void onDrag(float x, float y) {
		System.out.println("X: " + x + ", Y: " + y);
		if (y < Y_MIN)
			y = Y_MIN;
		if (x > center + X_RANGE)
			x = center + X_RANGE;
		else if (x < center - X_RANGE)
			x = center - X_RANGE;
		leftBand.bottom.x = x - 1;
		rightBand.bottom.x = x + 1;
		leftBand.bottom.y = y - currentProj.getFixtureList().get(0).getShape().getRadius();
		rightBand.bottom.y = y - currentProj.getFixtureList().get(0).getShape().getRadius();
		//pocket.setX(leftBand.bottom.x);
		//pocket.setY(leftBand.bottom.y);
		
		System.out.println("Left: (" + leftBand.top.x + ", " + leftBand.top.y + "), (" + leftBand.bottom.x + ", " + leftBand.bottom.y + ")");
		System.out.println("Right: (" + rightBand.top.x + ", " + rightBand.top.y + "), (" + rightBand.bottom.x + ", " + rightBand.bottom.y + ")");
		
		//if (numProjectiles > 0) {
			currentProj.setTransform(new Vector2(x, y), currentProj.getAngle());
			force = leftBand.getForce().add(rightBand.getForce());
			//force.y -= currentProj.getMass()*GameWorld.GRAVITY;
			System.out.println(force);
		//}
	}
	
	public void offClick(float x, float y) {
		onDrag(x, y);
		//if (numProjectiles > 0) {
			//numProjectiles--;
			//GameWorld.addShot();
		//}
		System.out.println("\n\n\nLAUNCH\n\n\n");
		applyForce = true;
	}
	
	public int getNumProjectiles() {
		return numProjectiles;
	}
	
	/*public ArrayList<Projectile> getProjectiles() {
		return projectiles;
	}*/
	
	public int getCenter() {
		return center;
	}
	
	public Vector2 getForce() {
		return force;
	}
	
	/*public Vector2 getCurrentProjLoc() {
		if (currentProj != null)
			return new Vector2(currentProj.getX(), currentProj.getY());
		return null;
	}
	
	public Vector2 getEndOfForceLine() {
		if (currentProj != null)
			return getCurrentProjLoc().add(force.cpy().scl(.00001f));
		return null;
	}*/
	
	public ElasticBand getLeftBand() {
		return leftBand;
	}
	
	public ElasticBand getRightBand() {
		return rightBand;
	}
	
	public class ElasticBand {
		static final double E = 100000000; // Young's Modulus for rubber in Pa
		static final double A0 = .001963495; // Cross-sectional area of .1m diameter cord in m^2
		
		double force; // N (kg*m/s^2)
		float L0; // Initial length in m
		double deltaL; // Change in length in m
		double k;
		
		Vector2 top;
		Vector2 bottom;
		
		public ElasticBand(float x1, float y1, float x2, float y2) {
			top = new Vector2(x1, y1);
			bottom = new Vector2(x2, y2);
			L0 = top.dst2(bottom);
			deltaL = 0;
			force = 0;
			k = E*A0/L0;
		}
		
		public Vector2 getForce() {
			deltaL = top.dst2(bottom) - L0;
			force = k*deltaL; // Hooke's Law
			return top.cpy().sub(bottom.cpy()).nor().scl((float) force);
		}
		
		public Vector2 getTop() {
			return top;
		}
		
		public Vector2 getBottom() {
			return bottom;
		}
		
	}

}
