package com.chrej.slingshot;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.utils.GdxNativesLoader;
import com.chrej.slingshot.SlingshotGame.SlingshotGame;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Slingshot";
		cfg.useGL20 = false;
		cfg.width = 1280;
		cfg.height = 720;
		
		GdxNativesLoader.load();
		
		new LwjglApplication(new SlingshotGame(), cfg);
	}
}
