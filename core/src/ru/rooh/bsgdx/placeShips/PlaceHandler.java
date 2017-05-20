package ru.rooh.bsgdx.placeShips;

import ru.rooh.bsgdx.basics.InputHandler;
import ru.rooh.bsgdx.objects.PlacerMap;
import ru.rooh.bsgdx.ui.StatusBar;

/**
 * Created by rooh on 4/18/17.
 */
public class PlaceHandler extends InputHandler {
    private PlacerMap map;

    public PlaceHandler(StatusBar statusBar, PlacerMap map) {
        super();
        this.statusBar = statusBar;
        this.map = map;
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        statusBar.onClick(screenX, screenY);
        map.onClick(screenX, screenY);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        return false;
    }

}
