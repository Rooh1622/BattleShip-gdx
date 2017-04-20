package ru.rooh.bsgdx.menu;

import ru.rooh.bsgdx.basics.World;
import ru.rooh.bsgdx.objects.ScrollHandler;

/**
 * Created by rooh on 4/20/17.
 */
public class MenuWorld extends World {


    public MenuWorld(int midPointY) {
        scroller = new ScrollHandler(midPointY / 4 * 3, -20);
    }

    public void update(float delta) {
        //Gdx.app.log("GameWorld", "update");
        scroller.update(delta);
    }


}

