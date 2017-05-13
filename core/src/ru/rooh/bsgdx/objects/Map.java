package ru.rooh.bsgdx.objects;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import org.json.simple.JSONObject;
import ru.rooh.bsgdx.Main;
import ru.rooh.bsgdx.utils.AssetLoader;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Map {


    public static CopyOnWriteArrayList<Dot> show = new CopyOnWriteArrayList<Dot>();
    public static ArrayList<Dot> show_e = new ArrayList<Dot>();
    private float rotation; // For handling bird rotation
    private float x;
    private float y;
    private int width;
    private int height;
    private int _id;
    private ArrayList<mRect> table;
    private Boolean moving = false;
    private Boolean pressed = false;
    private Boolean drawingField = true;
    private Boolean clickable = true;
    private Rectangle bounds;

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
        }
        bounds = new Rectangle(x + width - 32, y + height + 5, 32, 20);
        /*
        for (mRect r: table) {
            //Gdx.app.log(r.id + "", r.toString());

        }*/
    }

    private static int xyToId(int x, int y) {
        return x * 10 + y;
    }

    public void showConturOndestroy(int x, int y, int c, int id) { // c stands for color

        int edge = 0;
        if (x == 0) edge = 1;//up
        else if (x == 9) edge = 2;//down
        else if (y == 0) edge = 3;//left
        else if (y == 9) edge = 4;//right
        switch (edge) {
            case 3:
                //console.log(edge);

                createDot(x + 1, y + 1, true, c);
                createDot(x, y + 1, true, c);
                createDot(x - 1, y + 1, true, c);
                createDot(x + 1, y, true, c);
                createDot(x - 1, y, true, c);
                break;
            case 4:
                createDot(x + 1, y - 1, true, c);
                createDot(x, y - 1, true, c);
                createDot(x - 1, y - 1, true, c);
                createDot(x + 1, y, true, c);
                createDot(x - 1, y, true, c);
                break;
            case 1:

                createDot(x + 1, y, true, c);
                if (y != 0) {

                    createDot(x, y - 1, true, c);
                    createDot(x + 1, y - 1, true, c);
                }
                if (y != 9) {
                    createDot(x, y + 1, true, c);
                    createDot(x + 1, y + 1, true, c);
                }
                break;
            case 2:
                createDot(x - 1, y, true, c);
                if (y != 0) {
                    createDot(x, y - 1, true, c);
                    createDot(x - 1, y - 1, true, c);
                }
                if (y != 9) {
                    createDot(x, y + 1, true, c);
                    createDot(x - 1, y + 1, true, c);

                }
                break;
            default:

                createDot(x + 1, y + 1, true, c);
                createDot(x, y + 1, true, c);
                createDot(x - 1, y + 1, true, c);
                createDot(x + 1, y, true, c);
                createDot(x - 1, y, true, c);
                createDot(x + 1, y - 1, true, c);
                createDot(x, y - 1, true, c);
                createDot(x - 1, y - 1, true, c);
                break;
        }
    }

    private void createDot(int x, int y, Boolean lol, int c) {
        Dot d = new Dot(xyToId(x, y), true, c);
        switch (c) {
            case 1:
            case 3:

                for (Dot d2 : Map.show) {
                    if (d2.equals(d)) {

                        System.out.println("equals");
                        return;
                    }
                    if (d2.id == d.id) {

                        System.out.println("Contains");
                        return;
                    }
                }
                // d.show(true);

                System.out.println("ADDED");
                table.get(d.id).checked = true;
                Map.show.add(d);
                break;
            case 2:
            case 4:

                for (Dot d2 : Map.show_e) {
                    if (d2.equals(d)) {

                        System.out.println("equals");
                        return;
                    }
                    if (d2.id == d.id) {

                        System.out.println("Contains");
                        return;
                    }
                }
                // d.show(true);

                System.out.println("ADDED");
                Map.show_e.add(d);

        }

    }

    public void draw(SpriteBatch batcher) {


        batcher.draw((TextureRegion) AssetLoader.map, x, y, width, height);
        /*if(pressed)  batcher.draw(AssetLoader.switchBtnDark, x + width - 32, y + height + 5, 32, 20);
        else  batcher.draw(AssetLoader.switchBtn, x + width - 32, y + height + 5, 32, 20);
        pressed = false;*/

        batcher.draw(AssetLoader.switchBtn, x + width - 32, y + height + 5, 32, 20);
        if (drawingField) {
            for (Dot i : show) {
                if (i.id >= 0 && i.id <= 99)
                    batcher.draw(i.texture, table.get(i.id).x + 1, table.get(i.id).y + 1, 10, 10);
            }
        } else {
            for (Dot i : show_e) {
                batcher.draw(i.texture, table.get(i.id).x + 1, table.get(i.id).y + 1, 10, 10);
            }
        }
        drawCorners(batcher, drawingField);
        /*for (mRect r: table) {
            if(r.checked)
                batcher.draw((TextureRegion) AssetLoader.cross, r.x + 1, r.y + 1, 10, 10);
        }*/

    }

    public void drawCorners(SpriteBatch batcher, Boolean b) {
        if (b) {

            batcher.draw(AssetLoader.red, x, y, 3, 3);
            batcher.draw(AssetLoader.red, x + width - 3, y, 3, 3);
            batcher.draw(AssetLoader.red, x + width - 3, y + height - 3, 3, 3);
            batcher.draw(AssetLoader.red, x, y + height - 3, 3, 3);
        } else {
            batcher.draw(AssetLoader.blue, x, y, 3, 3);
            batcher.draw(AssetLoader.blue, x + width - 3, y, 3, 3);
            batcher.draw(AssetLoader.blue, x + width - 3, y + height - 3, 3, 3);
            batcher.draw(AssetLoader.blue, x, y + height - 3, 3, 3);
        }
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
        if (bounds.contains(screenX / Main.scaleX, screenY / Main.scaleY)) {
            drawingField = !drawingField;
            clickable = !clickable;
            pressed = true;
            return;
        }
        if (!clickable) return;
        int id = -1;
        for (mRect r : table) {
            int cid = r.getId(screenX / Main.scaleX, screenY / Main.scaleY);
            //Gdx.app.log("GAME_STATUS" , Main.game_status + " " + !r.checked);
            if (cid != -1 && !r.checked && Main.game_status == 2) {

                id = cid;
                //Map.show.add(new Dot(id, true, 1));
                //Map.show_e.add(new Dot(id, true, 2));
                JSONObject json = new JSONObject();
                json.put("type", "turn");
                json.put("client", "java");
                json.put("tile", id);
                json.put("myId", Main.myId);
                json.put("enId", Main.enId);
                json.put("ses_id", Main.session);
                Main.server.sendJson(json); //TODO debug : server off
                r.checked = true;
                break;
            }
        }/*
        if (id == -1) return;
        for (Ship s : Main.Ships) {
            if (s.checkCord(id)) show.add(id);
        }*/
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

