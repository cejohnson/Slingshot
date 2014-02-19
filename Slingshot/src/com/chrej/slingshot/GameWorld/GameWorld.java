package com.chrej.slingshot.GameWorld;

import com.chrej.slingshot.GameObjects.ProjectileLauncher;
import com.chrej.slingshot.GameObjects.Projectiles.*;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class GameWorld {
	private ProjectileLauncher launcher;
	private Projectile proj;
	private Rectangle ground;
	
	private int gameWidth;
	private int gameHeight;
	
	public GameWorld(int gameWidth, int gameHeight) {
		this.gameWidth = gameWidth;
		this.gameHeight = gameHeight;
		ground = new Rectangle(0, 0, gameWidth, 5);
		launcher = new ProjectileLauncher(gameWidth);
		launcher.addProjectile();
		proj = launcher.getProjectile();
		//scroller = new ScrollHandler(midPointY + 66);
	}

	public void update(float delta) {
		launcher.update(delta);
		if (Intersector.overlaps(proj.getBoundingCircle(), ground)) {
			proj.ground();
		}
	}
	
	public ProjectileLauncher getLauncher() {
		return launcher;
	}
	
	/*public ScrollHandler getScroller() {
		return scroller;
	}*/
	
}
