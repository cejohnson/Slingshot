package com.chrej.slingshot.GameWorld;

import com.chrej.slingshot.GameObjects.ProjectileLauncher;
import com.chrej.slingshot.GameObjects.Projectiles.*;

public class GameWorld {
	private ProjectileLauncher launcher;
	
	private int gameWidth;
	private int gameHeight;
	
	public GameWorld(int gameWidth, int gameHeight) {
		this.gameWidth = gameWidth;
		this.gameHeight = gameHeight;
		launcher = new ProjectileLauncher(gameWidth);
		launcher.addProjectile();
		//scroller = new ScrollHandler(midPointY + 66);
	}

	public void update(float delta) {
		launcher.update(delta);
		//scroller.update(delta);
	}
	
	public ProjectileLauncher getLauncher() {
		return launcher;
	}
	
	/*public ScrollHandler getScroller() {
		return scroller;
	}*/
	
}
