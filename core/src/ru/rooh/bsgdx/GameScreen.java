package ru.rooh.bsgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

/**
 * Created by rooh on 4/18/17.
 */

public class GameScreen implements Screen{
    private GameWorld world;


    private Renderer renderer;
    private String name;
    private float runTime = 0;

    public GameScreen(String r){
        Gdx.app.log("GameScreen", "Attached");

        this.name = r;
        if (r.equals("game")) {

            world = new GameWorld((int) (Gdx.graphics.getHeight() / (Gdx.graphics.getWidth() / 136) / 2)); // initialize world
            renderer = new GameRenderer(world);
        } else if(r.equals("menu")){
            world = new GameWorld((int) (Gdx.graphics.getHeight() / (Gdx.graphics.getWidth() / 136) / 2)); // initialize world
            renderer = new MainMenuRenderer(world);
        }

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
        Gdx.app.log("GameScreen", "Disposed");
    }

    public String getRendererName() {
        return name;
    }

}
