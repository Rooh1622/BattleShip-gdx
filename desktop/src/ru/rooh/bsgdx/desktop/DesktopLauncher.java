package ru.rooh.bsgdx.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.rooh.bsgdx.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Battle DecorativeShip";
        config.resizable = false;
		config.width = 720 / 2;
		config.height = 1280 / 2;
		//config.width = 144;
		//config.height = 256;
		new LwjglApplication(new Main(), config);
	}
}
