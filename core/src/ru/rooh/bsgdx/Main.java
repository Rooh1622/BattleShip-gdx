package ru.rooh.bsgdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Main extends Game {
	SpriteBatch batch;
	Texture img;
	private static Game game;
	//public static int scale;

	
	@Override
	public void create () {
		Gdx.app.log("Main", "created");
		ru.rooh.bsgdx.assets.AssetLoader.load();
		game = this;
		setScreen(new mScreen("menu"));
		//scale = (int)Gdx.graphics.getWidth()/136;

		//Gdx.app.log("Main", scale + "");
	}
	@Override
	public void dispose() {
		super.dispose();
		ru.rooh.bsgdx.assets.AssetLoader.dispose();
	}
	public static void changeScreen(String n){
		game.getScreen().dispose();
		game.setScreen(new mScreen(n));
	}
	public static String getScreenState(){
		mScreen s = (mScreen) game.getScreen();

		//Gdx.app.log("Name", s.getRendererName());
		return s.getRendererName();
	}
}
