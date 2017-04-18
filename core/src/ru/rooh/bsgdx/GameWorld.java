package ru.rooh.bsgdx;

import com.badlogic.gdx.Gdx;

/**
 * Created by rooh on 4/18/17.
 */
public class GameWorld {


    private Ship ship;

    public GameWorld(int midPointY) {
        ship = new Ship(33, midPointY - 5, 17, 12);
    }
    public void update(float delta) {
        Gdx.app.log("GameWorld", "update");
        ship.update(delta);
    }



    public Ship getShip() {
        return ship;
    }
}
