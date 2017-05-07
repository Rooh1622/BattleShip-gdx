package ru.rooh.bsgdx.ui;

/**
 * Created by rooh on 4/18/17.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import org.json.simple.JSONObject;
import ru.rooh.bsgdx.Main;
import ru.rooh.bsgdx.utils.AssetLoader;

public class SimpleButton {

    private float x, y, width, height;

    private TextureRegion buttonUp;
    private TextureRegion buttonDown;

    private Rectangle bounds;

    private boolean isPressed = false;

    private float scale;

    public SimpleButton(float x, float y, float width, float height,
                        TextureRegion buttonUp, TextureRegion buttonDown) {

        this.scale = Gdx.graphics.getWidth() / 136f;
        this.x = x - width / 2f;
        this.y = y - height / 2f;
        this.width = width;
        this.height = height;
        this.buttonUp = buttonUp;
        this.buttonDown = buttonDown;


        bounds = new Rectangle(this.x * Main.scaleX, this.y * Main.scaleY, width * Main.scaleX, height * Main.scaleY);
        Gdx.app.log("Button", bounds.toString() + " " + x + "-" + y + "_" + width + "_" + height + " " + this.toString());

    }

    @Override
    public String toString() {
        return x + " " + y + " " + width + " " + height + " " + Main.scaleX + " " + Main.scaleY;
    }

    public boolean isClicked(int screenX, int screenY) {
        return bounds.contains(screenX, screenY);
    }

    public void draw(SpriteBatch batcher) {
        if (isPressed) {
            batcher.draw(buttonDown, x, y, width, height);
        } else {
            batcher.draw(buttonUp, x, y, width, height);
        }
        batcher.draw(AssetLoader.pvpBtn, x, y + height * 1 + 5, width, height);
        //Gdx.app.log("Button", isPressed + "");
    }

    public boolean isTouchDown(int screenX, int screenY) {
        //Gdx.app.log("Button", bounds.contains(screenX, screenY) + "");
        if (bounds.contains(screenX, screenY) && Main.authorized) {
            isPressed = true;
            JSONObject json = new JSONObject();
            json.put("type", "queue");
            json.put("myId", Main.myId);
            Gdx.app.log("JSON", json.toJSONString() + "");
            Main.server.send(json.toJSONString());
            Main.changeScreen("game");
            return true;
        }

        return false;
    }

    public boolean isTouchUp(int screenX, int screenY) {

        // Мы будем учитывать только touchUp в нажатом состоянии.
        if (bounds.contains(screenX, screenY) && isPressed) {
            isPressed = false;
            return true;
        }

        // Когда палец с кнопки уберут, мы очистим флаг, что кнопка нажатая.
        isPressed = false;
        return false;
    }

}