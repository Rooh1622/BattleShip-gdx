package ru.rooh.bsgdx.objects;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ru.rooh.bsgdx.Main;
import ru.rooh.bsgdx.utils.AssetLoader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

public class PlacerMap {


    public static CopyOnWriteArrayList<Dot> show = new CopyOnWriteArrayList<Dot>();
    public static ArrayList<Dot> stage_segment = new ArrayList<Dot>();
    public static ArrayList<ArrayList<Dot>> Ships = new ArrayList<ArrayList<Dot>>();
    private float x;
    private float y;
    private int width;
    private int height;
    private int _id;
    private int stage;
    private int stage_repeat;
    private ArrayList<mRect> table;
    private Boolean done = false;
    private Boolean pressed = false;
    private Boolean drawingField = true;
    private Boolean clickable = true;
    private Rectangle doneBounds, helpBounds;

    private float lastVelocity;

    public PlacerMap(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        stage = 4;
        stage_repeat = 1;
        table = new ArrayList<mRect>();
        Gdx.app.log("PacerMap", x + " " + y);
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
        doneBounds = new Rectangle(x + width - 29, y + height + 5, 29, 16);
        helpBounds = new Rectangle(x + width - 29 - 29 - 3, y + height + 5, 29, 16);
        /*
        for (mRect r: table) {
            //Gdx.app.log(r.id + "", r.toString());

        }*/
    }

    private static int xyToId(int x, int y) {
        return x * 10 + y;
    }

    private Dot createDot(int x, int y) {
        return new Dot(xyToId(x, y), true, 1);
    }

    public Boolean check(int x, int y) {
        Boolean check = true;
        //System.out.println("\tx >  " + x);
        //System.out.println("\ty >  " + y);
        ArrayList<Dot> dots = new ArrayList<Dot>();
        dots.add(createDot(x - 1, y - 1));
        dots.add(createDot(x - 1, y));
        dots.add(createDot(x - 1, y + 1));
        dots.add(createDot(x, y - 1));
        dots.add(createDot(x, y + 1));
        dots.add(createDot(x + 1, y - 1));
        dots.add(createDot(x + 1, y));
        dots.add(createDot(x + 1, y + 1));
        int neighbours = 0;
        for (Dot d : dots) {
            for (Dot d2 : show) {
                if (d2.id == d.id) {
                    //System.out.println("Contains");
                    if (!d2.allowChange) {
                        check = false;
                        System.out.println("Check >>  " + false);
                        return false;
                    }
                    neighbours++;
                }

            }
        }
        System.out.println("COUNT >>  " + neighbours);
        return neighbours <= 2;
    }

    public Boolean check(ArrayList<Dot> dots) {
        Boolean xchech = true, ycheck = true, xgapcheck = true, ygapcheck = true;
        int lastx = dots.get(0).id / 10;
        int lasty = dots.get(0).id % 10;
        for (Dot d : dots) {
            if ((d.id / 10 == lastx)) {
                lastx = d.id / 10;
            } else xchech = false;

            if (d.id % 10 == lasty) {
                lasty = d.id % 10;
            } else ycheck = false;
        }
        ArrayList<Integer> x = new ArrayList<Integer>();
        ArrayList<Integer> y = new ArrayList<Integer>();
        for (Dot d : dots) {
            x.add(d.id / 10);
            y.add(d.id % 10);
        }
        int xmax = Collections.max(x);
        int xmin = Collections.min(x);
        int ymax = Collections.max(y);
        int ymin = Collections.min(y);
        if ((xmax - xmin == stage - 1) ^ (xmax - xmin == -stage + 1)) {
        } else {
            xgapcheck = false;
        }
        if ((ymax - ymin == stage - 1) ^ (ymax - ymin == -stage + 1)) {
        } else {
            ygapcheck = false;
        }
        //System.out.println("GAP  " + xgapcheck);

        return (xchech || ycheck) && (xgapcheck || ygapcheck) && (dots.size() == stage);
    }


    public void draw(SpriteBatch batcher) {


        batcher.draw((TextureRegion) AssetLoader.map, x, y, width, height);
        /*if(pressed)  batcher.draw(AssetLoader.switchBtnDark, x + width - 32, y + height + 5, 32, 20);
        else  batcher.draw(AssetLoader.switchBtn, x + width - 32, y + height + 5, 32, 20);
        pressed = false;*/

        batcher.draw(AssetLoader.nextBtn, x + width - 29, y + height + 5, 29, 16);
        batcher.draw(AssetLoader.helpBtn, x + width - 29 - 29 - 3, y + height + 5, 29, 16);
        if (drawingField) {
            for (Dot i : show) {
                if (i.id >= 0 && i.id <= 99)
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
        batcher.draw(AssetLoader.red, x, y, 3, 3);
        batcher.draw(AssetLoader.red, x + width - 3, y, 3, 3);
        batcher.draw(AssetLoader.red, x + width - 3, y + height - 3, 3, 3);
        batcher.draw(AssetLoader.red, x, y + height - 3, 3, 3);

    }

    public void nextStage() {
        if (stage == 1) {
            if (stage_segment.size() == 1 && check((int) stage_segment.get(0).id / 10, (int) stage_segment.get(0).id % 10)) {
                Ships.add((ArrayList<Dot>) stage_segment.clone());

                stage_repeat--;
                stage_segment.clear();
                if (stage_repeat <= 0) {
                    Gdx.app.log("Stage", "All Done!");
                    jsoniseField();
                    //Main.server.sendJson(jsoniseField());
                    done = true;
                    return;
                }
                for (Dot d : show) d.commit();
                Gdx.app.log("Stage", "NEXT Stage is " + stage + " of " + stage_repeat + " parts");

            } else {

                Gdx.app.log("Stage", "WRONG");
            }
        } else if (check(stage_segment)) {
            Ships.add((ArrayList<Dot>) stage_segment.clone());
            stage_repeat--;
            for (Dot d : show) d.commit();
            if (stage_repeat == 0) {
                if (stage == 4) stage_repeat = 2;
                else if (stage == 3) stage_repeat = 3;
                else if (stage == 2) stage_repeat = 4;
                else if (stage == 1) stage_repeat = 0;

                stage--;


            }
            //System.out.println("Stage>>  " + Ships);
            stage_segment.clear();
            Gdx.app.log("Stage", "NEXT Stage is " + stage + " of " + stage_repeat + " parts");
        } else {

            Gdx.app.log("Stage", "WRONG");
        }
    }
    public void onClick(int screenX, int screenY) {
        if (doneBounds.contains(screenX / Main.scaleX, screenY / Main.scaleY)) {
            nextStage();
            return;
        } else if (helpBounds.contains(screenX / Main.scaleX, screenY / Main.scaleY)) {

            return;
        }
        if (done) return;
        for (mRect r : table) {
            int cid = -1;
            int id = -1;
            cid = r.getId(screenX / Main.scaleX, screenY / Main.scaleY);
            //Gdx.app.log("GAME_STATUS" , Main.game_status + " " + !r.checked);
            if (cid != -1) {
                id = cid;
                Dot d = new Dot(id, true, 1);
                //System.out.println(">> " + show.contains(d));
                for (Dot d2 : show) {
                    if (d2.id == d.id && d2.allowChange) {
                        //System.out.println("Contains");
                        show.remove(d2);
                        stage_segment.remove(d2);
                        return;
                    }
                }


                if (check((int) d.id / 10, (int) d.id % 10)) {
                    show.add(d);
                    stage_segment.add(d);
                }

                //System.out.println("Add  " );
                //System.out.println("Check ><><>  " + check(stage_segment));
                //Map.show_e.add(new Dot(id, true, 2));

                //r.checked = true;
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

    public JSONArray jsoniseField() {
        JSONArray json_root = new JSONArray();
        System.out.println("Ships>>  " + Ships);
        for (ArrayList<Dot> list : Ships) {
            System.out.println("\tList>>  " + list);
            JSONObject jsonShip = new JSONObject();
            jsonShip.put("size", list.size());
            for (int i = 0; i < list.size(); i++) {
                jsonShip.put("c" + i, list.get(i).id);
            }
            json_root.add(jsonShip);
        }

        //System.out.println("JSON>>  " + json_root);
        return json_root;
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

