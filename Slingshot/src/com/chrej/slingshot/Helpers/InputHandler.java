package com.chrej.slingshot.Helpers;

import com.badlogic.gdx.InputProcessor;
import com.chrej.slingshot.GameObjects.ProjectileLauncher;
import com.chrej.slingshot.GameObjects.Projectiles.Projectile;

public class InputHandler implements InputProcessor {
	private Projectile proj;
	private ProjectileLauncher launcher;
	private float proportion;
	private float gameHeight;

	// Ask for a reference to the Bird when InputHandler is created.
	public InputHandler(ProjectileLauncher launcher, float proportion, float gameHeight) {
		this.launcher = launcher;
		this.proportion = proportion;
		this.gameHeight = gameHeight;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		//proj.onClick();
		launcher.onClick(screenX*proportion, (gameHeight - screenY*proportion));
		//System.out.println("TouchDown: (" + screenX*proportion + ", " + (gameHeight - screenY*proportion) + ")");
		return true; // Return true to say we handled the touch.
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		launcher.offClick(screenX*proportion, (gameHeight - screenY*proportion));
		//System.out.println("TouchUp: (" + screenX*proportion + ", " + (gameHeight - screenY*proportion) + ")");
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		launcher.onDrag(screenX*proportion, (gameHeight - screenY*proportion));
		//System.out.println("TouchDrag: (" + screenX*proportion + ", " + (gameHeight - screenY*proportion) + ")");
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
