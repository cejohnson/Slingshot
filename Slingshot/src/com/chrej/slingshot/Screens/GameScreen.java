package com.chrej.slingshot.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.chrej.slingshot.GameWorld.GameRenderer;
import com.chrej.slingshot.GameWorld.GameWorld;
import com.chrej.slingshot.Helpers.InputHandler;

public class GameScreen implements Screen {

	private GameWorld world;
	private GameRenderer renderer;
	private float runTime;

	// This is the constructor, not the class declaration
	public GameScreen() {

		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();
		float gameWidth = 300;
		float gameHeight = screenHeight / (screenWidth / gameWidth);

		

		world = new GameWorld((int) gameWidth, (int) gameHeight);
		renderer = new GameRenderer(world, (int) gameHeight, (int) gameWidth);

		Gdx.input.setInputProcessor(new InputHandler(world.getLauncher(), gameWidth/screenWidth, gameHeight));

	}

	@Override
	public void render(float delta) {
		runTime += delta;
		world.update(delta);
		renderer.render(runTime);
	}

	@Override
	public void resize(int width, int height) {
		System.out.println("GameScreen - resizing");
	}

	@Override
	public void show() {
		System.out.println("GameScreen - show called");
	}

	@Override
	public void hide() {
		System.out.println("GameScreen - hide called");
	}

	@Override
	public void pause() {
		System.out.println("GameScreen - pause called");
	}

	@Override
	public void resume() {
		System.out.println("GameScreen - resume called");
	}

	@Override
	public void dispose() {
		// Leave blank
	}

}
