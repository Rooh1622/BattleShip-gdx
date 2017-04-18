package ru.rooh.bsgdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Main extends Game {
	SpriteBatch batch;
	Texture img;
	private static Game game;
	
	@Override
	public void create () {
		Gdx.app.log("Main", "created");
		AssetLoader.load();
		game = this;
		setScreen(new GameScreen("menu"));
	}
	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}
	public static void changeScreen(String s){
		game.getScreen().dispose();
		game.setScreen(new GameScreen(s));
	}
	public static String getScreenState(){
		GameScreen s = (GameScreen) game.getScreen();

		Gdx.app.log("Name", s.getRendererName());
		return s.getRendererName();
	}
}
