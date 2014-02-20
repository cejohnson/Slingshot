package com.chrej.slingshot.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.chrej.slingshot.GameWorld.GameRenderer;
import com.chrej.slingshot.GameWorld.GameWorld;
import com.chrej.slingshot.Helpers.InputHandler;

public class GameScreen implements Screen {

	private GameWorld gameWorld;
	//World world;
	//World world = new World(new Vector2(0, -100), true); 
	//private GameRenderer renderer;
	Box2DDebugRenderer debugRenderer;
	private float runTime;
	OrthographicCamera camera;

	// This is the constructor, not the class declaration
	public GameScreen() {

		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();
		float gameWidth = 240;
		float gameHeight = screenHeight / (screenWidth / gameWidth);
		
		camera = new OrthographicCamera();  
        camera.viewportHeight = 135;  
        camera.viewportWidth = 240;  
        camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0f);  
        camera.update();

		

		gameWorld = new GameWorld((int) gameWidth, (int) gameHeight);
		//world = gameWorld.getWorld();
		//renderer = new GameRenderer(world, (int) gameHeight, (int) gameWidth);

		Gdx.input.setInputProcessor(new InputHandler(gameWorld.getLauncher(), gameWidth/screenWidth, gameHeight));
		debugRenderer = new Box2DDebugRenderer();

	}

	@Override
	public void render(float delta) {
		//runTime += delta;
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		//System.out.println("World: " + gameWorld.getWorld());
		//System.out.println("Camera: " + camera.combined);
		debugRenderer.render(GameWorld.world, camera.combined);
		gameWorld.update(delta);
		//renderer.render(runTime);
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
