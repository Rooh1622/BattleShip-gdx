package ru.rooh.bsgdx.login;

import ru.rooh.bsgdx.basics.World;
import ru.rooh.bsgdx.objects.ScrollHandler;

/**
 * Created by rooh on 4/20/17.
 */
public class LoginWorld extends World {


    public LoginWorld(int midPointY) {
        scroller = new ScrollHandler(midPointY / 4 * 3, -20);
    }

    public void update(float delta) {
        //Gdx.app.log("GameWorld", "update");
        scroller.update(delta);
    }


}

