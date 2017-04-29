package ru.rooh.bsgdx.utils;

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

    public static Texture texture, bg_sea, tShip, tMap, tstatusBar;
    public static TextureRegion bg, grass, sea, map, statusBar, cross, blueCross, miss, blueMiss, switchBtn, transparent;

    public static Animation shipAnimation, internetAnimation;
    public static TextureRegion ship, shipDown, shipUp;
    public static TextureRegion i1, i2, i3, i4, i5, i6, iFull, iNone;

    public static TextureRegion skullUp, skullDown, bar,playButtonUp,playButtonDown;

    public static BitmapFont font, shadow;

    public static void load() {

        font = new BitmapFont(Gdx.files.internal("data/text.fnt"));
        font.getData().setScale(.25f, -.25f);
        shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
        shadow.getData().setScale(.25f, -.25f);

        texture = new Texture(Gdx.files.internal("data/texture.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        bg_sea = new Texture(Gdx.files.internal("data/sea.png"));
        bg_sea.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        tMap = new Texture(Gdx.files.internal("data/map.png"));
        tMap.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        tShip = new Texture(Gdx.files.internal("data/ship.png"));
        tShip.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        tstatusBar = new Texture(Gdx.files.internal("data/bar.png"));
        tstatusBar.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        statusBar = new TextureRegion(tstatusBar, 0, 0, 144, 15);
        statusBar.flip(false, true);

        sea = new TextureRegion(bg_sea, 0, 0, 450, 486);
        sea.flip(false, true);

        map = new TextureRegion(tMap, 0, 0, 123, 123);
        map.flip(false, true);

        bg = new TextureRegion(texture, 0, 0, 136, 43);
        bg.flip(false, true);

        grass = new TextureRegion(texture, 0, 43, 143, 11);
        grass.flip(false, true);

        cross = new TextureRegion(texture, 216, 0, 10, 10);
        cross.flip(false, true);

        blueCross = new TextureRegion(texture, 226, 0, 10, 10);
        blueCross.flip(false, true);

        switchBtn = new TextureRegion(texture, 60, 83, 32, 20);
        switchBtn.flip(false, true);

        miss = new TextureRegion(texture, 216, 10, 10, 10);
        miss.flip(false, true);

        blueMiss = new TextureRegion(texture, 226, 10, 10, 10);
        blueMiss.flip(false, true);

        transparent = new TextureRegion(texture, 10, 128, 10, 10);
        transparent.flip(false, true);

        shipDown = new TextureRegion(tShip, 0, 0, 1920, 900);
        shipDown.flip(false, true);

        ship = new TextureRegion(tShip, 0, 0, 1920, 1080);
        ship.flip(false, true);

        shipUp = new TextureRegion(tShip, 0, 180, 1920, 900);
        shipUp.flip(false, true);

        playButtonUp = new TextureRegion(texture, 0, 83, 29, 16);
        playButtonUp.flip(false, true);

        playButtonDown = new TextureRegion(texture, 29, 83, 29, 16);
        playButtonDown.flip(false, true);


        TextureRegion[] ships = { shipDown, ship, shipUp };
        shipAnimation = new Animation(0.06f, ships);
        shipAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        //INTERNET
        i1 = new TextureRegion(texture, 244, 91, 12, 13);
        i1.flip(false, true);
        i2 = new TextureRegion(texture, 244, 78, 12, 13);
        i2.flip(false, true);
        i3 = new TextureRegion(texture, 244, 65, 12, 13);
        i3.flip(false, true);
        i4 = new TextureRegion(texture, 244, 52, 12, 13);
        i4.flip(false, true);
        i5 = new TextureRegion(texture, 244, 39, 12, 13);
        i5.flip(false, true);
        i6 = new TextureRegion(texture, 244, 26, 12, 13);
        i6.flip(false, true);

        iFull = new TextureRegion(texture, 244, 0, 12, 13);
        iFull.flip(false, true);
        iNone = new TextureRegion(texture, 244, 13, 12, 13);
        iNone.flip(false, true);


        //TextureRegion[] playButton = { shipDown, ship, shipUp };
        //shipAnimation = new Animation(0.06f, ships);
        //shipAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        TextureRegion[] internets = {i1, i2, i3, i4, i5, i6};
        internetAnimation = new Animation(0.06f, internets);
        internetAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

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