package ru.rooh.bsgdx.menu;

import com.badlogic.gdx.Gdx;
import ru.rooh.bsgdx.basics.InputHandler;
import ru.rooh.bsgdx.ui.SimpleButton;
import ru.rooh.bsgdx.ui.StatusBar;

/**
 * Created by rooh on 4/18/17.
 */
public class MenuInputHandler extends InputHandler {
    private SimpleButton play;

    public MenuInputHandler(SimpleButton play, StatusBar statusBar) {
        super();
        this.play = play;
        this.statusBar = statusBar;
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        play.isTouchDown(screenX,screenY);
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
