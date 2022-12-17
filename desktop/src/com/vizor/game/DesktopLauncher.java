package com.vizor.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.vizor.game.Asteroids;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

		config.setForegroundFPS(60);
//		config.setWindowedMode(800, 400);
		config.useVsync(true);
		config.setMaximized(true);
		config.setTitle("VizorAsteroids");
		new Lwjgl3Application(new Asteroids(), config);
	}
}
