package ru.rooh.bsgdx.objects;

public class Map {


    private float rotation; // For handling bird rotation
    private float x;
    private float y;
    private int width;
    private int height;
    private Boolean moving = false;

    private float lastVelocity;

    public Map(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }

    public void update(float delta) {

        //velocity.add(acceleration.cpy().scl(delta));

        /*if (velocity.y > 200) {
            velocity.y = 200;
        }*/


    }

    public void onClick() {
        //velocity.y = -140;
        /*if(moving) moving = false;
                else moving = true;*/

        //Gdx.app.log("Ship", moving + "");
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

}
