package ru.rooh.bsgdx.assets;

/**
 * Created by rooh on 4/18/17.
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {

    public static Texture texture;
    public static TextureRegion bg, grass;

    public static Animation shipAnimation;
    public static TextureRegion ship, shipDown, shipUp;

    public static TextureRegion skullUp, skullDown, bar,playButtonUp,playButtonDown;

    public static BitmapFont font, shadow;

    public static void load() {

        font = new BitmapFont(Gdx.files.internal("core/assets/data/text.fnt"));
        font.getData().setScale(.25f, -.25f);
        shadow = new BitmapFont(Gdx.files.internal("core/assets/data/shadow.fnt"));
        shadow.getData().setScale(.25f, -.25f);

        texture = new Texture(Gdx.files.internal("core/assets/data/texture.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        bg = new TextureRegion(texture, 0, 0, 136, 43);
        bg.flip(false, true);

        grass = new TextureRegion(texture, 0, 43, 143, 11);
        grass.flip(false, true);

        shipDown = new TextureRegion(texture, 136, 0, 17, 12);
        shipDown.flip(false, true);

        ship = new TextureRegion(texture, 153, 0, 17, 12);
        ship.flip(false, true);

        shipUp = new TextureRegion(texture, 170, 0, 17, 12);
        shipUp.flip(false, true);

        playButtonUp = new TextureRegion(texture, 0, 83, 29, 16);
        playButtonUp.flip(false, true);

        playButtonDown = new TextureRegion(texture, 29, 83, 29, 16);
        playButtonDown.flip(false, true);


        TextureRegion[] ships = { shipDown, ship, shipUp };
        shipAnimation = new Animation(0.06f, ships);
        shipAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        //TextureRegion[] playButton = { shipDown, ship, shipUp };
        //shipAnimation = new Animation(0.06f, ships);
        //shipAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        skullUp = new TextureRegion(texture, 192, 0, 24, 14);
        // Create by flipping existing skullUp
        skullDown = new TextureRegion(skullUp);
        skullDown.flip(false, true);

        bar = new TextureRegion(texture, 136, 16, 22, 3);
        bar.flip(false, true);

    }

    public static void dispose() {
        // Мы должны избавляться от текстур, когда заканчивает работать с объектом в котором есть текстуры
        texture.dispose();
        font.dispose();
        shadow.dispose();
    }

}