package ru.rooh.bsgdx.objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.rooh.bsgdx.utils.AssetLoader;

public class Dot {
    Boolean show = false;
    int id = -1;
    int color = -1;
    TextureRegion texture;
    // TextureRegion LastTexture;

    public Dot(int id, Boolean show, int color) {

        this.id = id;
        this.show = show;
        this.color = color;
        if (color == 1) texture = AssetLoader.cross;
        else if (color == 2) texture = AssetLoader.blueCross;
        else if (color == 3) texture = AssetLoader.miss;
        else if (color == 4) texture = AssetLoader.blueMiss;
        /*if (!show) {
            LastTexture = texture;
            texture = AssetLoader.transparent;
        }*/

    }/*
    public void show(Boolean b){
        show = b;
        texture = LastTexture;
    }*/

}
