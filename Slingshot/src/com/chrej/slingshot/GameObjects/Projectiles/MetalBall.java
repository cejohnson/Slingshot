package com.chrej.slingshot.GameObjects.Projectiles;

public class MetalBall extends Projectile {

	private static final float density = 7900; // kg/m^3
	
	public MetalBall(int x, int y, float radius) {
		super(x, y, radius, density);
	}

}
