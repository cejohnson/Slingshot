package com.chrej.slingshot.GameObjects;

import java.util.ArrayList;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.chrej.slingshot.GameObjects.Projectiles.*;
import com.chrej.slingshot.GameWorld.GameWorld;

public class ProjectileLauncher {
	
	private int center;
	private ElasticBand leftBand;
	private ElasticBand rightBand;
	private ArrayList<Projectile> projectiles;
	private Projectile currentProj;
	
	private Vector2 projForce;
	private Vector2 acceleration;
	private Rectangle pocket;
	
	private static final float BAND_TOP = 20f;
	private static final float BAND_BOTTOM = 15f;
	private static final float Y_MIN = 6;
	private static final float X_RANGE = 10;
	protected static int numProjectiles = 5;
	

	public ProjectileLauncher(int gameWidth) {
		center = gameWidth / 2;
		projectiles = new ArrayList<Projectile>();
		leftBand = new ElasticBand((float) center - 3, BAND_TOP, (float) center - 1, BAND_BOTTOM);
		rightBand = new ElasticBand((float) center + 3, BAND_TOP, (float) center + 1, BAND_BOTTOM);
		pocket = new Rectangle(leftBand.bottom.x, leftBand.bottom.y, 2, 1);
	}
	
	public void addProjectile(float radius) {
		currentProj = new Rock((int) center, (int) BAND_BOTTOM, radius);
		projectiles.add(currentProj);
		System.out.println(numProjectiles);
		GameWorld.addProjectile(currentProj);
	}
	
	public static void returnProj() {
		numProjectiles++;
		System.out.println(numProjectiles);
	}
	
	public void update(float delta) {
		for (Projectile proj : projectiles) {
			proj.update(delta);
		}
	}
	
	public void onClick(float x, float y) {
		if (numProjectiles > 0){
			addProjectile(.75f);
		}
		onDrag(x, y);
	}
	
	public void onDrag(float x, float y) {
		if (y < Y_MIN)
			y = Y_MIN;
		if (x > center + X_RANGE)
			x = center + X_RANGE;
		else if (x < center - X_RANGE)
			x = center - X_RANGE;
		leftBand.bottom.x = x - 1;
		rightBand.bottom.x = x + 1;
		leftBand.bottom.y = y;
		rightBand.bottom.y = y;
		pocket.setX(leftBand.bottom.x);
		pocket.setY(leftBand.bottom.y);
		
		if (numProjectiles > 0) {
			currentProj.setX(x);
			currentProj.setY(y);
		}
	}
	
	public void offClick(float x, float y) {
		onDrag(x, y);
		if (numProjectiles > 0) {
			projForce = leftBand.getForce().add(rightBand.getForce());
			acceleration = projForce.div(currentProj.getMass());
			currentProj.launch(acceleration);
			numProjectiles--;
		}
		leftBand.bottom.x = (float) center - 1;
		leftBand.bottom.y = BAND_BOTTOM;
		rightBand.bottom.x = (float) center + 1;
		rightBand.bottom.y = BAND_BOTTOM;
	}
	
	public ArrayList<Projectile> getProjectiles() {
		return projectiles;
	}
	
	public int getCenter() {
		return center;
	}
	
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
		
		Vector2 top;
		Vector2 bottom;
		
		public ElasticBand(float x1, float y1, float x2, float y2) {
			top = new Vector2(x1, y1);
			bottom = new Vector2(x2, y2);
			L0 = top.dst2(bottom);
			deltaL = 0;
			force = 0;
		}
		
		public Vector2 getForce() {
			deltaL = top.dst2(bottom) - L0;
			force = E*A0*deltaL/L0;
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
