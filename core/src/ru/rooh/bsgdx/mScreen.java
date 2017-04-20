package ru.rooh.bsgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import ru.rooh.bsgdx.basics.World;
import ru.rooh.bsgdx.game.GameInputHandler;
import ru.rooh.bsgdx.game.GameRenderer;
import ru.rooh.bsgdx.game.GameWorld;
import ru.rooh.bsgdx.menu.MainMenuRenderer;
import ru.rooh.bsgdx.menu.MenuInputHandler;
import ru.rooh.bsgdx.menu.MenuWorld;
import ru.rooh.bsgdx.ui.SimpleButton;

/**
 * Created by rooh on 4/18/17.
 */

public class mScreen implements Screen{
    private World world;


    private ru.rooh.bsgdx.basics.Renderer renderer;
    private String name;
    private float runTime = 0;

    public mScreen(String r){
        Gdx.app.log("mScreen", "attached");

        this.name = r;
        if (r.equals("game")) {

            world = new GameWorld((int) (Gdx.graphics.getHeight() / (Gdx.graphics.getWidth() / 136) / 2)); // initialize world
            renderer = new GameRenderer(world);
            Gdx.input.setInputProcessor(new GameInputHandler(((GameWorld) world).getShip()));
        } else if(r.equals("menu")){
            world = new MenuWorld((int) (Gdx.graphics.getHeight() / (Gdx.graphics.getWidth() / 136) / 2)); // initialize world
            renderer = new MainMenuRenderer(world);

            SimpleButton s = ((MainMenuRenderer) renderer).getPlay();
            Gdx.input.setInputProcessor(new MenuInputHandler(s));
        }

    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        //Gdx.app.log("mScreen FPS", (int)(1/delta) + ""); //fps log
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
        Gdx.app.log("mScreen", Main.getScreenState() + " disposed");
    }

    public String getRendererName() {
        return name;
    }

}
