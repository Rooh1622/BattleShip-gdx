package ru.rooh.bsgdx.ui;

/**
 * Created by rooh on 4/18/17.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import ru.rooh.bsgdx.Main;
import ru.rooh.bsgdx.utils.AssetLoader;

public class InputBox {

    private float x, y, width, height;
    private Boolean hide;

    private TextureRegion buttonUp;
    private TextureRegion buttonDown;

    private Rectangle bounds;

    private boolean isPressed = false;

    private float scale;


    private String text = "";
    private String mask = "";

    public InputBox(float x, float y, float width, float height,
                    TextureRegion buttonUp, TextureRegion buttonDown, Boolean hide) {

        this.scale = Gdx.graphics.getWidth() / 136f;
        this.x = x - width / 2f;
        this.y = y - height / 2f;
        this.width = width;
        this.height = height;
        this.buttonUp = buttonUp;
        this.buttonDown = buttonDown;
        this.hide = hide;


        bounds = new Rectangle(this.x * Main.scaleX, this.y * Main.scaleY, width * Main.scaleX, height * Main.scaleY);
        // Gdx.app.log("InputBox", bounds.toString() + " " + x + "-" + y + "_" + width + "_" + height + " " + this.toString());

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
        if (hide) AssetLoader.font.draw(batcher, mask, x + 10, y + 3);
        else AssetLoader.font.draw(batcher, text, x + 10, y + 3);
        //Gdx.app.log("Button", isPressed + "");
    }

    public boolean isTouchDown(int screenX, int screenY) {
        //Gdx.app.log("Button", bounds.contains(screenX, screenY) + "");
        if (bounds.contains(screenX, screenY)) {
            isPressed = true;
            MyTextInputListener listener = new MyTextInputListener() {

                @Override
                public void input(String t) {
                    mask = "";
                    for (int i = 0; i < t.length(); i++) mask += '*';
                    if (hide) Main.prePasswd = t;
                    else Main.preLogin = t;
                    text = t;
                }

                @Override
                public void canceled() {
                    // handle input cancel
                }
            };
            if (hide) Gdx.input.getTextInput(listener, "Password", "", "");
            else Gdx.input.getTextInput(listener, "Login", "", "");
            return true;
        }

        return false;
    }

    public boolean isTouchUp(int screenX, int screenY) {

        if (bounds.contains(screenX, screenY) && isPressed) {
            isPressed = false;
            return true;
        }

        isPressed = false;
        return false;
    }


}