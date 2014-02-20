package com.chrej.slingshot.GameObjects;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.chrej.slingshot.GameWorld.GameWorld;

public final class FixtureDefs {
	public static final float PROJECTILE_RADIUS = .75f;
	public static final float ENEMY_HEIGHT = 2f;
	public static final float ENEMY_WALL_WIDTH = .03f;
	public static final float PROJECTILE_DENSITY = 2800;
	public static final float ALUMINUM_DENSITY = 2700;

	public static PolygonShape groundBox;
	public static EdgeShape launcherPocket;
	public static PolygonShape launcherUpright;
	
	public static CircleShape projectile;
	public static PolygonShape smallFlyer;
	public static PolygonShape mediumFlyer;
	public static PolygonShape largeFlyer;

	public static FixtureDef groundBoxFixtureDef;
	public static FixtureDef launcherPocketFixtureDef;
	public static FixtureDef launcherUprightFixtureDef;
	
	public static FixtureDef projectileFixtureDef;
	public static FixtureDef smallFlyerFixtureDef;
	public static FixtureDef mediumFlyerFixtureDef;
	public static FixtureDef largeFlyerFixtureDef;

	public FixtureDefs(int gameWidth) {
		groundBox = new PolygonShape();
		groundBox.setAsBox(gameWidth * 2, GameWorld.GROUND);
		launcherPocket = new EdgeShape();
		launcherPocket.set(gameWidth/2 - 1, 15, gameWidth/2 + 1, 15);
		launcherUpright = new PolygonShape();
		launcherUpright.setAsBox(2, 30);
		
		projectile = new CircleShape();
		projectile.setRadius(PROJECTILE_RADIUS);
		smallFlyer = new PolygonShape();
		smallFlyer.setAsBox(4f, ENEMY_HEIGHT);
		mediumFlyer = new PolygonShape();
		mediumFlyer.setAsBox(7f, ENEMY_HEIGHT);
		largeFlyer = new PolygonShape();
		largeFlyer.setAsBox(10f, ENEMY_HEIGHT);
		

		groundBoxFixtureDef = new FixtureDef();
		groundBoxFixtureDef.shape = groundBox;
		groundBoxFixtureDef.density = 0;
		
		launcherPocketFixtureDef = new FixtureDef();
		launcherPocketFixtureDef.shape = launcherPocket;
		
		launcherUprightFixtureDef = new FixtureDef();
		launcherUprightFixtureDef.shape = launcherUpright;
		launcherUprightFixtureDef.filter.maskBits = 0;

		projectileFixtureDef = new FixtureDef();
		projectileFixtureDef.shape = projectile;
		projectileFixtureDef.density = PROJECTILE_DENSITY;
		projectileFixtureDef.friction = 0.3f;
		projectileFixtureDef.restitution = 0.8f;

		smallFlyerFixtureDef = new FixtureDef();
		float smallFlyerRadius = smallFlyer.getRadius();
		float smallFlyerArea = MathUtils.PI * smallFlyerRadius
				* smallFlyerRadius;
		smallFlyerFixtureDef.shape = projectile;
		smallFlyerFixtureDef.density = ALUMINUM_DENSITY
				* ((MathUtils.PI
						* (-2 * ENEMY_WALL_WIDTH * smallFlyerRadius + ENEMY_WALL_WIDTH
								* ENEMY_WALL_WIDTH)
						* (ENEMY_HEIGHT - 2 * ENEMY_WALL_WIDTH) + (smallFlyerArea * 2 * ENEMY_WALL_WIDTH)) / (smallFlyerArea * ENEMY_HEIGHT));
		smallFlyerFixtureDef.friction = 0.3f;
		smallFlyerFixtureDef.restitution = 0.8f;
		
		mediumFlyerFixtureDef = new FixtureDef();
		float mediumFlyerRadius = mediumFlyer.getRadius();
		float mediumFlyerArea = MathUtils.PI * mediumFlyerRadius
				* mediumFlyerRadius;
		mediumFlyerFixtureDef.shape = projectile;
		mediumFlyerFixtureDef.density = ALUMINUM_DENSITY
				* ((MathUtils.PI
						* (-2 * ENEMY_WALL_WIDTH * mediumFlyerRadius + ENEMY_WALL_WIDTH
								* ENEMY_WALL_WIDTH)
						* (ENEMY_HEIGHT - 2 * ENEMY_WALL_WIDTH) + (mediumFlyerArea * 2 * ENEMY_WALL_WIDTH)) / (mediumFlyerArea * ENEMY_HEIGHT));
		mediumFlyerFixtureDef.friction = 0.3f;
		mediumFlyerFixtureDef.restitution = 0.8f;
		
		largeFlyerFixtureDef = new FixtureDef();
		float largeFlyerRadius = largeFlyer.getRadius();
		float largeFlyerArea = MathUtils.PI * largeFlyerRadius
				* largeFlyerRadius;
		largeFlyerFixtureDef.shape = projectile;
		largeFlyerFixtureDef.density = ALUMINUM_DENSITY
				* ((MathUtils.PI
						* (-2 * ENEMY_WALL_WIDTH * largeFlyerRadius + ENEMY_WALL_WIDTH
								* ENEMY_WALL_WIDTH)
						* (ENEMY_HEIGHT - 2 * ENEMY_WALL_WIDTH) + (largeFlyerArea * 2 * ENEMY_WALL_WIDTH)) / (largeFlyerArea * ENEMY_HEIGHT));
		largeFlyerFixtureDef.friction = 0.3f;
		largeFlyerFixtureDef.restitution = 0.8f;
	}

}
