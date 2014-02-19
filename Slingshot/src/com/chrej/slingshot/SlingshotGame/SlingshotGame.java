package com.chrej.slingshot.SlingshotGame;

import com.badlogic.gdx.Game;
import com.chrej.slingshot.Helpers.AssetLoader;
import com.chrej.slingshot.Screens.GameScreen;

public class SlingshotGame extends Game {

	@Override
	public void create() {
		System.out.println("Catapult Game Created!");
		AssetLoader.load();
		setScreen(new GameScreen());
	}
	
	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}
	
}
