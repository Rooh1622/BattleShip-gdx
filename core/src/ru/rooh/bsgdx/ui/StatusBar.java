package ru.rooh.bsgdx.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import ru.rooh.bsgdx.Main;
import ru.rooh.bsgdx.utils.AssetLoader;

/**
 * Created by rooh on 4/23/17.
 */
public class StatusBar {
    private float x, y, width, height;

    private TextureRegion bg;

    private Rectangle settings, internet;

    private boolean isPressed = false;

    private float scale;

    public StatusBar() {

        this.x = 0;
        this.y = 0;
        this.width = Main.gameWidth;
        this.height = 15;
        this.bg = AssetLoader.statusBar;


        settings = new Rectangle(0, 0, 15 * Main.scaleX, 15 * Main.scaleY);
        internet = new Rectangle(125 * Main.scaleX, 0, 19 * Main.scaleX, 15 * Main.scaleY);
        //Gdx.app.log("Button", bounds.toString() + " " + x + "-" + y + "_" + width + "_" + height + " " + this.toString());

    }

    @Override
    public String toString() {
        return x + " " + y + " " + width + " " + height + " " + Main.scaleX + " " + Main.scaleY;
    }

    public boolean isClicked(int screenX, int screenY) {
        return settings.contains(screenX, screenY);
    }

    public void draw(SpriteBatch batcher, float runTime) {
        batcher.draw(bg, x, y, width, height);
        switch (Main.server_status) {
            case 0:
                batcher.draw((TextureRegion) AssetLoader.iNone, 127, 1, 12, 13);
                break;
            case 1:
                batcher.draw((TextureRegion) AssetLoader.internetAnimation.getKeyFrame(runTime), 127, 1, 12, 13);
                break;
            case 2:
                batcher.draw((TextureRegion) AssetLoader.iFull, 127, 1, 12, 13);
                break;
        }
        //Gdx.app.log("Button", isPressed + "");
    }

    public boolean onClick(int screenX, int screenY) {
        //Gdx.app.log("Button", bounds.contains(screenX, screenY) + "");
        if (settings.contains(screenX, screenY)) {
            isPressed = true;
            Main.changeScreen("menu");
            return true;
        }

        if (internet.contains(screenX, screenY)) {
            Gdx.app.log("Button", internet.contains(screenX, screenY) + " lol");
            Main.server.reconnect();
            return true;
        }

        return false;
    }


}

