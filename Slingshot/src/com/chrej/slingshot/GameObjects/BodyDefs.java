package com.chrej.slingshot.GameObjects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public final class BodyDefs {
	public static BodyDef groundBodyDef;
	public static BodyDef launcherPocketBodyDef;
	public static BodyDef launcherUprightBodyDef;
	public static BodyDef projectileBodyDef;
	public static BodyDef flyingEnemyBodyDef;

	public BodyDefs() {
		groundBodyDef = new BodyDef();
		groundBodyDef.position.set(new Vector2(0, 5));
		
		launcherPocketBodyDef = new BodyDef();
		launcherPocketBodyDef.type = BodyType.DynamicBody;
		
		launcherUprightBodyDef = new BodyDef();
		
		projectileBodyDef = new BodyDef();
		projectileBodyDef.type = BodyType.DynamicBody;
		
		flyingEnemyBodyDef = new BodyDef();
		flyingEnemyBodyDef.type = BodyType.DynamicBody;
	}

}
