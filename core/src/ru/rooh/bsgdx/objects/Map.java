package ru.rooh.bsgdx.objects;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import ru.rooh.bsgdx.Main;

import java.util.ArrayList;

public class Map {


    private float rotation; // For handling bird rotation
    private float x;
    private float y;
    private int width;
    private int height;


    private int _id;

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


                else if (i == 0 || i == 9) table.add(new mRect(x + 3 + i * 10 + i, y + 3 + j * 10 + j + j - 1, 11, 10));
                else if (j == 0 || j == 9) table.add(new mRect(x + 3 + i * 10 + i + i - 1, y + 3 + j * 10 + j, 10, 11));
                else table.add(new mRect(x + 3 + i * 11 + i - 1, y + 3 + j * 11 + j - 1, 11, 11));
            }
        }
        /*for (mRect r: table) {
            Gdx.app.log(r.id + "", r.toString());

        }*/
    }

    public void update(float delta) {

        //velocity.add(acceleration.cpy().scl(delta));

        /*if (velocity.y > 200) {
            velocity.y = 200;
        }*/


    }

    public void onClick(int screenX, int screenY) {
        for (mRect r : table) {
            r.getId(screenX / Main.scaleX, screenY / Main.scaleY);
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

        public mRect(float x, float y, float w, float h) {
            super(x, y, w, h);
            this.id = _id++;
        }

        public void getId(float x, float y) {
            if (this.contains(x, y))
                Gdx.app.log("ID", this.id + "");

        }
    }
}
