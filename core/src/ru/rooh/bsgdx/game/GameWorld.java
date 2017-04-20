package ru.rooh.bsgdx.game;

import ru.rooh.bsgdx.Main;
import ru.rooh.bsgdx.basics.World;
import ru.rooh.bsgdx.objects.ScrollHandler;
import ru.rooh.bsgdx.objects.Ship;

/**
 * Created by rooh on 4/18/17.
 */
public class GameWorld extends World {


    private Ship ship;


    public GameWorld(int midPointY) {
        ship = new Ship(33, midPointY - 5, 17, 12);
        scroller = new ScrollHandler(Main.gameHeight / 10 * 5, -10);
    }
    public void update(float delta) {
        //Gdx.app.log("GameWorld", "update");
        ship.update(delta);
        scroller.update(delta);
    }



    public Ship getShip() {
        return ship;
    }
}
