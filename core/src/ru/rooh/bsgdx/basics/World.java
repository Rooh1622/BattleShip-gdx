package ru.rooh.bsgdx.basics;

import ru.rooh.bsgdx.objects.ScrollHandler;

/**
 * Created by rooh on 4/20/17.
 */
public abstract class World {
    protected ScrollHandler scroller;

    public World() {

    }

    public void update(float delta) {
        //Gdx.app.log("GameWorld", "update");
    }


    public ScrollHandler getScroller() {
        return scroller;
    }
}

