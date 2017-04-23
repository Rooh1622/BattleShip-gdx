package ru.rooh.bsgdx.objects;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import ru.rooh.bsgdx.Main;
import ru.rooh.bsgdx.utils.AssetLoader;

import java.util.ArrayList;

public class Map {


    private float rotation; // For handling bird rotation
    private float x;
    private float y;
    private int width;
    private int height;


    private int _id;
    private ArrayList<Integer> show = new ArrayList<Integer>();

    private ArrayList<mRect> table;
    private Boolean moving = false;

    private float lastVelocity;

    public Map(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        table = new ArrayList<mRect>();
        Gdx.app.log("MAP", x + " " + y);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i == 0 && j == 0) table.add(new mRect(x + 3 + i * 10 + i + i, y + 3 + j * 10 + j + j, 10, 10));
                else if (i == 0 && j == 9)
                    table.add(new mRect(x + 3 + i * 10 + i + i, y + 3 + j * 10 + j + j - 1, 10, 10));
                else if (i == 9 && j == 0)
                    table.add(new mRect(x + 3 + i * 10 + i + i - 1, y + 3 + j * 10 + j + j, 10, 10));
                else if (i == 9 && j == 9)
                    table.add(new mRect(x + 3 + i * 10 + i + i - 1, y + 3 + j * 10 + j + j - 1, 10, 10));


                else if (i == 0) table.add(new mRect(x + 3 + i * 10 + i, y + 3 + j * 10 + j + j - 1, 11, 10));
                else if (i == 9) table.add(new mRect(x + 3 + i * 10 + i + 7, y + 3 + j * 10 + j + j - 1, 11, 10));
                else if (j == 0) table.add(new mRect(x + 3 + i * 10 + i + i - 1, y + 3 + j * 10 + j, 10, 11));
                else if (j == 9) table.add(new mRect(x + 3 + i * 10 + i + i - 1, y + 3 + j * 10 + j + 7, 10, 11));
                else table.add(new mRect(x + 3 + i * 11 + i - 1, y + 3 + j * 11 + j - 1, 11, 11));
            }
        }/*
        for (mRect r: table) {
            //Gdx.app.log(r.id + "", r.toString());

        }*/
    }

    public void draw(SpriteBatch batcher) {


        batcher.draw((TextureRegion) AssetLoader.map, x, y, width, height);
        for (int i : show) {
            batcher.draw((TextureRegion) AssetLoader.cross, table.get(i).x + 1, table.get(i).y + 1, 10, 10);
        }
        /*for (mRect r: table) {
            if(r.checked)
                batcher.draw((TextureRegion) AssetLoader.cross, r.x + 1, r.y + 1, 10, 10);
        }*/

    }

    public void showAll(SpriteBatch batcher) {

        for (Ship s : Main.Ships) {
            if (s.c4 != -1) {
                batcher.draw((TextureRegion) AssetLoader.cross, table.get(s.c4).x + 1, table.get(s.c).y + 1, 10, 10);
            }
            if (s.c3 != -1) {

                batcher.draw((TextureRegion) AssetLoader.cross, table.get(s.c3).x + 1, table.get(s.c).y + 1, 10, 10);
            }
            if (s.c2 != -1) {

                batcher.draw((TextureRegion) AssetLoader.cross, table.get(s.c2).x + 1, table.get(s.c).y + 1, 10, 10);
            }
            if (s.c != -1)
                batcher.draw((TextureRegion) AssetLoader.cross, table.get(s.c).x + 1, table.get(s.c).y + 1, 10, 10);
        }
    }
    public void onClick(int screenX, int screenY) {
        int id = -1;
        for (mRect r : table) {
            int cid = r.getId(screenX / Main.scaleX, screenY / Main.scaleY);
            if (cid != -1) {
                id = cid;
                break;
            }
        }
        if (id == -1) return;
        for (Ship s : Main.Ships) {
            if (s.checkCord(id)) show.add(id);
        }
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getRotation() {
        return rotation;
    }

    private class mRect extends Rectangle {
        public int id;
        public Boolean checked = false;

        public mRect(float x, float y, float w, float h) {
            super(x, y, w, h);
            this.id = _id++;
        }

        public int getId(float x, float y) {
            if (this.contains(x, y)) {
                Gdx.app.log("ID", this.id + "");
                return id;
                //checked = !checked;
            }
            return -1;
        }
    }
}
