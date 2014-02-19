package com.chrej.slingshot.GameWorld;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.chrej.slingshot.GameObjects.*;
import com.chrej.slingshot.GameObjects.Enemies.Enemy;
import com.chrej.slingshot.GameObjects.Projectiles.*;
import com.chrej.slingshot.Helpers.AssetLoader;

public class GameRenderer {

	private GameWorld myWorld;
	private OrthographicCamera cam;
	private ShapeRenderer shapeRenderer;

	private SpriteBatch batcher;
	private BitmapFont font;

	private int gameWidth;
	private int gameHeight;

	// Game Objects
	private ArrayList<Projectile> projectiles;
	private ProjectileLauncher launcher;
	private ArrayList<Enemy> enemies;
	/*private ScrollHandler scroller;
	private Grass frontGrass, backGrass;
	private Pipe pipe1, pipe2, pipe3;*/

	// Game Assets
	/*private TextureRegion bg, grass;
	private Animation birdAnimation;
	private TextureRegion birdMid, birdDown, birdUp;
	private TextureRegion skullUp, skullDown, bar;*/

	public GameRenderer(GameWorld world, int gameHeight, int gameWidth) {
		myWorld = world;

		this.gameHeight = gameHeight;
		this.gameWidth = gameWidth;

		cam = new OrthographicCamera();
		cam.setToOrtho(false, gameWidth, gameHeight);

		batcher = new SpriteBatch();
		batcher.setProjectionMatrix(cam.combined);
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);
		
		font = new BitmapFont();
		font.setColor(0,0,0,1);

		initGameObjects();
		//initAssets();
	}

	public void render(float runTime) {
		// Fill the entire screen with black, to prevent potential flickering.
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		// Begin ShapeRenderer
		shapeRenderer.begin(ShapeType.Filled);

		// Draw Background color
		shapeRenderer.setColor(0 / 255.0f, 255 / 255.0f, 255 / 255.0f, 1);
		shapeRenderer.rect(0, 0, gameWidth, gameHeight);

		// Draw Grass
		shapeRenderer.setColor(111 / 255.0f, 186 / 255.0f, 45 / 255.0f, 1);
		shapeRenderer.rect(0, 0, gameWidth, 5);
		
		shapeRenderer.setColor(1, 0, 0, 1);
		for (int i = 1; i < (gameWidth / 50); i += 2) {
			shapeRenderer.rect(50*i, 0, 50, 5);
		}

		// Draw Dirt
		shapeRenderer.setColor(147 / 255.0f, 80 / 255.0f, 27 / 255.0f, 1);
		shapeRenderer.rect(0, 0, gameWidth, 3);
		
		// Draw Launcher Uprights
		shapeRenderer.setColor(139 / 255.0f, 69 / 255.0f, 19 / 255.0f, 1);
		shapeRenderer.rect(launcher.getCenter() - 6, 5, 3, 16);
		shapeRenderer.rect(launcher.getCenter() + 3, 5, 3, 16);
		
		
		//Draw Projectile
		shapeRenderer.setColor(96 / 255.0f, 96 / 255.0f, 96 / 255.0f, 1);
		for (Projectile proj : projectiles) {
			shapeRenderer.circle(proj.getX(), proj.getY(), proj.getRadius());
		}
		
		//Draw Enemies
		shapeRenderer.setColor(0, 0, 0, 1);
		for (Enemy enemy : enemies) {
			shapeRenderer.rect(enemy.getPosition().x, enemy.getPosition().y, enemy.getWidth(), enemy.getHeight());
		}

		// End ShapeRenderer
		shapeRenderer.end();
		
		// Draw elastic bands
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(0, 0, 0, 1);
		shapeRenderer.line(launcher.getLeftBand().getTop(), launcher.getLeftBand().getBottom());
		shapeRenderer.line(launcher.getRightBand().getTop(), launcher.getRightBand().getBottom());
		shapeRenderer.line(launcher.getLeftBand().getBottom(), launcher.getRightBand().getBottom());
		shapeRenderer.end();
		
		/*batcher.begin();
		
		font.draw(batcher,  "X: " + (proj.getX() - launcher.getCenter()) + "m", gameWidth - 100, gameHeight - 5);
		font.draw(batcher,  "Y: " + (proj.getY()) + "m", gameWidth - 100, gameHeight - 20);
		font.draw(batcher,  "Speed: " + (proj.getSpeed()) + "m/s", gameWidth - 100, gameHeight - 35);
		
		batcher.end();*/
		
		

	}

	private void initGameObjects() {
		launcher = myWorld.getLauncher();
		projectiles = launcher.getProjectiles();
		enemies = myWorld.getEnemies();
		
		/*scroller = myWorld.getScroller();
		frontGrass = scroller.getFrontGrass();
		backGrass = scroller.getBackGrass();
		pipe1 = scroller.getPipe1();
		pipe2 = scroller.getPipe2();
		pipe3 = scroller.getPipe3();*/
	}

	private void initAssets() {
		
		/*bg = AssetLoader.bg;
		grass = AssetLoader.grass;
		birdAnimation = AssetLoader.birdAnimation;
		birdMid = AssetLoader.bird;
		birdDown = AssetLoader.birdDown;
		birdUp = AssetLoader.birdUp;
		skullUp = AssetLoader.skullUp;
		skullDown = AssetLoader.skullDown;
		bar = AssetLoader.bar;*/
	}

	/*private void drawGrass() {
		// Draw the grass
		batcher.draw(grass, frontGrass.getX(), frontGrass.getY(),
				frontGrass.getWidth(), frontGrass.getHeight());
		batcher.draw(grass, backGrass.getX(), backGrass.getY(),
				backGrass.getWidth(), backGrass.getHeight());
	}

	private void drawSkulls() {
		// Temporary code! Sorry about the mess :)
		// We will fix this when we finish the Pipe class.

		batcher.draw(skullUp, pipe1.getX() - 1,
				pipe1.getY() + pipe1.getHeight() - 14, 24, 14);
		batcher.draw(skullDown, pipe1.getX() - 1,
				pipe1.getY() + pipe1.getHeight() + 45, 24, 14);

		batcher.draw(skullUp, pipe2.getX() - 1,
				pipe2.getY() + pipe2.getHeight() - 14, 24, 14);
		batcher.draw(skullDown, pipe2.getX() - 1,
				pipe2.getY() + pipe2.getHeight() + 45, 24, 14);

		batcher.draw(skullUp, pipe3.getX() - 1,
				pipe3.getY() + pipe3.getHeight() - 14, 24, 14);
		batcher.draw(skullDown, pipe3.getX() - 1,
				pipe3.getY() + pipe3.getHeight() + 45, 24, 14);
	}

	private void drawPipes() {
		// Temporary code! Sorry about the mess :)
		// We will fix this when we finish the Pipe class.
		batcher.draw(bar, pipe1.getX(), pipe1.getY(), pipe1.getWidth(),
				pipe1.getHeight());
		batcher.draw(bar, pipe1.getX(), pipe1.getY() + pipe1.getHeight() + 45,
				pipe1.getWidth(), midPointY + 66 - (pipe1.getHeight() + 45));

		batcher.draw(bar, pipe2.getX(), pipe2.getY(), pipe2.getWidth(),
				pipe2.getHeight());
		batcher.draw(bar, pipe2.getX(), pipe2.getY() + pipe2.getHeight() + 45,
				pipe2.getWidth(), midPointY + 66 - (pipe2.getHeight() + 45));

		batcher.draw(bar, pipe3.getX(), pipe3.getY(), pipe3.getWidth(),
				pipe3.getHeight());
		batcher.draw(bar, pipe3.getX(), pipe3.getY() + pipe3.getHeight() + 45,
				pipe3.getWidth(), midPointY + 66 - (pipe3.getHeight() + 45));
	}*/

}
