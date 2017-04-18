package ru.rooh.bsgdx.game;

import ru.rooh.bsgdx.basics.InputHandler;
import ru.rooh.bsgdx.objects.Ship;

/**
 * Created by rooh on 4/18/17.
 */
public class GameInputHandler extends InputHandler {
    private Ship mShip;

    public GameInputHandler(Ship bird) {
        super();
        mShip = bird;
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        mShip.onClick();
        return false;
    }
}
