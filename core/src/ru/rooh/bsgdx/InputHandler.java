package ru.rooh.bsgdx;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

/**
 * Created by rooh on 4/18/17.
 */
public class InputHandler implements InputProcessor {
    private Ship mShip;

    public InputHandler(Ship bird) {
        mShip = bird;
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
        if(Main.getScreenState().equals("game"))
            mShip.onClick();
        else
            Main.changeScreen("game");
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
