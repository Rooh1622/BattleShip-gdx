package ru.rooh.bsgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
/**
 * Created by rooh on 4/18/17.
 */

public class GameScreen implements Screen{
    private GameWorld world;
    private GameRenderer renderer;
    private float runTime = 0;

    public GameScreen(){
        Gdx.app.log("GameScreen", "Attached");
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = 136;
        float gameHeight = screenHeight / (screenWidth / gameWidth);

        int midPointY = (int) (gameHeight / 2);

        world = new GameWorld(midPointY); // initialize world
        renderer = new GameRenderer(world, (int) gameHeight, midPointY);

        Gdx.input.setInputProcessor(new InputHandler(world.getShip()));
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        //Gdx.app.log("GameScreen FPS", (int)(1/delta) + ""); //fps log
        runTime += delta;
        world.update(delta);
        renderer.render(runTime);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
