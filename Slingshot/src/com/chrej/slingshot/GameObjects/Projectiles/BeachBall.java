package com.chrej.slingshot.GameObjects.Projectiles;

public class BeachBall extends Projectile {
	
	private static final float density = 1.184f; // kg/m^3

	public BeachBall(int x, int y, float radius) {
		super(x, y, radius, density);
	}

}
