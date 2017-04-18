package ru.rooh.bsgdx.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.rooh.bsgdx.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Battle Ship";
		config.width = 720;
		config.height = 1280;
		new LwjglApplication(new Main(), config);
	}
}
