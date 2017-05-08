package ru.rooh.bsgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import ru.rooh.bsgdx.basics.World;
import ru.rooh.bsgdx.game.GameInputHandler;
import ru.rooh.bsgdx.game.GameRenderer;
import ru.rooh.bsgdx.game.GameWorld;
import ru.rooh.bsgdx.login.LoginInputHandler;
import ru.rooh.bsgdx.login.LoginRenderer;
import ru.rooh.bsgdx.login.LoginWorld;
import ru.rooh.bsgdx.menu.MainMenuRenderer;
import ru.rooh.bsgdx.menu.MenuInputHandler;
import ru.rooh.bsgdx.menu.MenuWorld;
import ru.rooh.bsgdx.objects.Map;
import ru.rooh.bsgdx.ui.InputBox;
import ru.rooh.bsgdx.ui.SimpleButton;
import ru.rooh.bsgdx.ui.StatusBar;

/**
 * Created by rooh on 4/18/17.
 */

public class mScreen implements Screen{
    public World world;


    private ru.rooh.bsgdx.basics.Renderer renderer;
    private String name;
    private float runTime = 0;

    public mScreen(String r){
        Gdx.app.log("mScreen", "attached");

        this.name = r;
        if (r.equals("game")) {

            world = new GameWorld((int) (Gdx.graphics.getHeight() / (Gdx.graphics.getWidth() / 136) / 2)); // initialize world
            renderer = new GameRenderer(world);

            StatusBar bar = (renderer).getStatusBar();
            Map map = ((GameWorld) world).getMap();
            Gdx.input.setInputProcessor(new GameInputHandler(((GameWorld) world).getDecorativeShip(), bar, map));
        } else if(r.equals("menu")){
            world = new MenuWorld((int) (Gdx.graphics.getHeight() / (Gdx.graphics.getWidth() / 136) / 2)); // initialize world
            renderer = new MainMenuRenderer(world);

            SimpleButton s = ((MainMenuRenderer) renderer).getPlay();
            StatusBar bar = ((MainMenuRenderer) renderer).getStatusBar();
            Gdx.input.setInputProcessor(new MenuInputHandler(s, bar));
            //Gdx.input.getTextInput(listener, "Dialog Title", "Initial Textfield Value", "Hint Value");
        } else if (r.equals("login")) {
            world = new LoginWorld((int) (Gdx.graphics.getHeight() / (Gdx.graphics.getWidth() / 136) / 2)); // initialize world
            renderer = new LoginRenderer(world);

            InputBox login = ((LoginRenderer) renderer).lb;
            InputBox passwd = ((LoginRenderer) renderer).pas;

            SimpleButton go = ((LoginRenderer) renderer).go;
            StatusBar bar = ((LoginRenderer) renderer).getStatusBar();
            Gdx.input.setInputProcessor(new LoginInputHandler(login, passwd, go, bar));

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
