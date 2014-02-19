package com.chrej.slingshot.GameObjects.Projectiles;

public class Rock extends Projectile {

	private static final float density = 2800; // kg/m^3
	
	public Rock(int x, int y, float radius) {
		super(x, y, radius, density);
	}

}
