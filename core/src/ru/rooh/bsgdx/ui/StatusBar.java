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

    private Rectangle settings, internet, ip;

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
        ip = new Rectangle(30 * Main.scaleX, 0, 10 * Main.scaleX, 15 * Main.scaleY);
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
        if (!Main.authorized) batcher.draw((TextureRegion) AssetLoader.iError, 127, 1, 12, 13);
        else
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
        batcher.draw((TextureRegion) AssetLoader.trophy, 17, 3, 9, 9);
        batcher.draw((TextureRegion) AssetLoader.ip, 30, 3, 9, 9);
        if (Main.turn == Main.myId) batcher.draw((TextureRegion) AssetLoader.redShip, 113, 3, 10, 10);
        else if (Main.turn == Main.enId) batcher.draw((TextureRegion) AssetLoader.blueShip, 113, 3, 10, 10);
        //batcher.draw((TextureRegion) AssetLoader.blueShip, 113, 3, 10, 10);
        //Gdx.app.log("Button", isPressed + "");
    }

    public boolean onClick(int screenX, int screenY) {
        //Gdx.app.log("Button", bounds.contains(screenX, screenY) + "");
        if (settings.contains(screenX, screenY) && Main.game_status != 1) {
            Main.dropGame();
            isPressed = true;
            Main.changeScreen("menu");


            return true;
        } else if (internet.contains(screenX, screenY)) {
            Gdx.app.log("Button", internet.contains(screenX, screenY) + " lol");
            if (Main.authorized)
                Main.server.reconnect();
            return true;
        } else if (ip.contains(screenX, screenY)) {
            //Gdx.app.log("Button", internet.contains(screenX, screenY) + " lol");
            MyTextInputListener listener = new MyTextInputListener() {

                @Override
                public void input(String t) {
                    Main.ip = t;
                }

                @Override
                public void canceled() {
                    Main.ip = "192.168.1.149";
                }
            };
            Gdx.input.getTextInput(listener, "IP - Debug", "192.168.1.149", "");
        }

        return false;
    }


}

