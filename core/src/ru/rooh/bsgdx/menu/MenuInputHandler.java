package ru.rooh.bsgdx.menu;

import com.badlogic.gdx.Gdx;
import ru.rooh.bsgdx.Main;
import ru.rooh.bsgdx.basics.InputHandler;
import ru.rooh.bsgdx.ui.SimpleButton;
import ru.rooh.bsgdx.ui.StatusBar;
import ru.rooh.bsgdx.utils.AssetLoader;

/**
 * Created by rooh on 4/18/17.
 */
public class MenuInputHandler extends InputHandler {
    private SimpleButton play;
    private SimpleButton setup;
    private SimpleButton random;

    public MenuInputHandler(SimpleButton play, SimpleButton setup, SimpleButton random, StatusBar statusBar) {
        super();
        this.play = play;
        this.setup = setup;
        this.random = random;
        this.statusBar = statusBar;
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        play.isTouchDown(screenX,screenY);
        if (random.onTouch(screenX, screenY)) {
            Main.usePreset = !Main.usePreset;
            if (Main.usePreset) random.setButtonUp(AssetLoader.randomBtnRed);
            else random.setButtonUp(AssetLoader.randomBtnBlue);
        }
        setup.setup(screenX, screenY);
        statusBar.onClick(screenX, screenY);

        Gdx.app.log("Handler", screenX + " " + screenY);

        //Gdx.app.log("Server", Main.server.getReadyState().toString());
        //Main.server.send(Main.json.toJSONString());
        return false;
    }
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        play.isTouchUp(screenX,screenY);
        return false;
    }

}
