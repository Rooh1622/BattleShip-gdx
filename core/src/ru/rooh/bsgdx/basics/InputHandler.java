package ru.rooh.bsgdx.basics;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import ru.rooh.bsgdx.Main;
import ru.rooh.bsgdx.ui.StatusBar;

/**
 * Created by rooh on 4/18/17.
 */
public class InputHandler implements InputProcessor {

    protected StatusBar statusBar;
    public InputHandler() {
    }
    @Override
    public boolean keyDown(int keycode) {
        switch (keycode){
            case Input.Keys.ENTER:
                Main.changeScreen("game");
                break;
            case Input.Keys.ESCAPE:
                Main.changeScreen("menu");
                break;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        statusBar.onClick(screenX, screenY);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
