package ru.rooh.bsgdx.menu;

import ru.rooh.bsgdx.basics.InputHandler;
import ru.rooh.bsgdx.ui.SimpleButton;

/**
 * Created by rooh on 4/18/17.
 */
public class MenuInputHandler extends InputHandler {
    private SimpleButton play;

    public MenuInputHandler(SimpleButton play) {
        super();
        this.play = play;
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        play.isTouchDown(screenX,screenY);

       // Gdx.app.log("Handler", screenX + " " + screenY);
        return false;
    }
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        play.isTouchUp(screenX,screenY);
        return false;
    }

}
