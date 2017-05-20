package ru.rooh.bsgdx.placeShips;

import ru.rooh.bsgdx.Main;
import ru.rooh.bsgdx.basics.World;
import ru.rooh.bsgdx.objects.PlacerMap;
import ru.rooh.bsgdx.objects.ScrollHandler;

/**
 * Created by rooh on 4/20/17.
 */
public class PlaceWorld extends World {
    private PlacerMap map;

    public PlaceWorld(int midPointY) {
        map = new PlacerMap((Main.gameWidth - 123) / 2, Main.gameHeight / 20 + 5, 123, 123);

        scroller = new ScrollHandler(Main.gameHeight / 10 * 5, -10);
    }

    public void update(float delta) {
        //Gdx.app.log("GameWorld", "update");
        scroller.update(delta);
    }

    public PlacerMap getMap() {
        return map;
    }

}

