package ru.rooh.bsgdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Main extends Game {
    public static float scale;
    public static float midPointY, midPointX, gameWidth, gameHeight;
    private static Game game;
    SpriteBatch batch;
	Texture img;

    public static void changeScreen(String n) {
        game.getScreen().dispose();

        scale = Gdx.graphics.getWidth() / 136;
        game.setScreen(new mScreen(n));
    }

    public static String getScreenState() {
        mScreen s = (mScreen) game.getScreen();

        //Gdx.app.log("Name", s.getRendererName());
        return s.getRendererName();
    }

	@Override
	public void create () {
		Gdx.app.log("Main", "created");
		ru.rooh.bsgdx.assets.AssetLoader.load();
        scale = Gdx.graphics.getWidth() / 136;
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        this.gameWidth = 136;
        this.gameHeight = screenHeight / (screenWidth / gameWidth);

        this.midPointY = (int) (gameHeight / 2);
        this.midPointX = (int) (gameWidth / 2);
        game = this;
		setScreen(new mScreen("menu"));

		//Gdx.app.log("Main", scale + "");
	}

    @Override
	public void dispose() {
		super.dispose();
		ru.rooh.bsgdx.assets.AssetLoader.dispose();
	}
}
