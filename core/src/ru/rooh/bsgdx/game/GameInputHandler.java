package ru.rooh.bsgdx.game;

import ru.rooh.bsgdx.basics.InputHandler;
import ru.rooh.bsgdx.objects.Map;
import ru.rooh.bsgdx.objects.Ship;
import ru.rooh.bsgdx.ui.StatusBar;

/**
 * Created by rooh on 4/18/17.
 */
public class GameInputHandler extends InputHandler {
    private Ship mShip;
    private Map map;

    public GameInputHandler(Ship bird, StatusBar statusBar, Map map) {
        super();
        mShip = bird;
        this.statusBar = statusBar;
        this.map = map;

    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        mShip.onClick();
        statusBar.onClick(screenX, screenY);
        map.onClick(screenX, screenY);

        return false;
    }
}
